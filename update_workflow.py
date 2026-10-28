import os

workflow = """name: Build APK
on: [push, workflow_dispatch]
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - name: Set up JDK 17
        uses: actions/setup-java@v4
        with:
          java-version: '17'
          distribution: 'temurin'
      - name: Set up Android SDK
        uses: android-actions/setup-android@v3
      - name: Setup Gradle
        uses: gradle/actions/setup-gradle@v3
      - name: Build APK
        run: gradle assembleDebug --stacktrace
      - name: Upload APK
        uses: actions/upload-artifact@v4
        with:
          name: NanheSitare-APK
          path: app/build/outputs/apk/debug/*.apk
"""

props = """android.useAndroidX=true
android.nonTransitiveRClass=true
org.gradle.jvmargs=-Xmx2048m -Dfile.encoding=UTF-8
"""

os.makedirs(".github/workflows", exist_ok=True)
with open(".github/workflows/build-apk.yml", "w", encoding="utf-8") as f:
    f.write(workflow)

with open("gradle.properties", "w", encoding="utf-8") as f:
    f.write(props)

print("FINAL WORKFLOW UPDATED!")
