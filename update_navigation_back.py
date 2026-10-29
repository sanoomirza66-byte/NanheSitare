import os

path = "app/src/main/java/com/nanhesitare/app/ui/MainActivity.kt"
if not os.path.exists(path):
    # Try finding MainActivity anywhere in java folder
    for root, dirs, files in os.walk("app/src/main/java"):
        if "MainActivity.kt" in files:
            path = os.path.join(root, "MainActivity.kt")
            break

print(f"Targeting: {path}")

with open(path, "r", encoding="utf-8") as f:
    code = f.read()

# Make sure BackHandler import exists
if "androidx.activity.compose.BackHandler" not in code:
    code = "import androidx.activity.compose.BackHandler\n" + code

# Inject BackHandler inside AppNavigation or Screen container
if "BackHandler" not in code or "currentScreen != Screen.Home" not in code:
    snippet = """
    // Prevent exiting app when navigating sub-screens
    if (currentScreen != Screen.Home) {
        BackHandler {
            currentScreen = Screen.Home
        }
    }
"""
    # Look for where screens are switched
    if "when (currentScreen)" in code:
        code = code.replace("when (currentScreen)", snippet + "\n        when (currentScreen)")
        with open(path, "w", encoding="utf-8") as f:
            f.write(code)
        print("SUCCESS: BackHandler successfully added to MainActivity.kt!")
    else:
        print("Note: Check MainActivity structure to place BackHandler.")
else:
    print("BackHandler already present.")
