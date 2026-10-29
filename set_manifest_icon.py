import re

manifest_path = "app/src/main/AndroidManifest.xml"
with open(manifest_path, "r", encoding="utf-8") as f:
    content = f.read()

# Update icon and roundIcon
content = re.sub(r'android:icon="[^"]*"', 'android:icon="@drawable/ic_nanhesitare_logo"', content)
content = re.sub(r'android:roundIcon="[^"]*"', 'android:roundIcon="@drawable/ic_nanhesitare_logo"', content)

with open(manifest_path, "w", encoding="utf-8") as f:
    f.write(content)

print("SUCCESS: App Icon linked in AndroidManifest.xml!")
