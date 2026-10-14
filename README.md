# Selendroid Appium framework (POM + Extent + CI)

## Included
- Page Object Model classes
- BaseTest driver setup/teardown
- `config.properties` configuration
- TestNG test suite with nine scenarios
- Extent Spark HTML report via TestNG listener
- GitHub Actions workflow in `.github/workflows/android-appium.yml`

## Local setup
1. Install Java 21, Node.js, Android SDK/platform-tools and start an Android emulator.
2. Install Appium and driver if needed:
   `npm install -g appium`
   `appium driver install uiautomator2`
3. Put `selendroid-test-app.apk` in the project root next to `pom.xml`.
4. Check `adb devices` shows the emulator as `device`.
5. Start Appium in another terminal: `appium`
6. Import project in Eclipse: File > Import > Maven > Existing Maven Projects.
7. Right-click project > Maven > Update Project.
8. Right-click `testng.xml` > Run As > TestNG Suite.
9. Open `test-output/ExtentReport.html` after execution.

## Configuration
Change values in `config.properties`. You can override Java system properties, e.g.
`mvn test -Ddevice.name="Pixel_7_API_35" -Dapp.path=/absolute/path/selendroid-test-app.apk`.

## GitHub Actions / CI
Commit and push the project to GitHub. Add the APK as `selendroid-test-app.apk` at project root if its redistribution is permitted. Workflow triggers on push to main, pull request to main, or manual Run workflow. The emulator job runs `mvn test` and uploads Extent/TestNG/Appium logs as artifacts.

## Important limitations to verify
- The sample package/activity and all UI locators must be verified against your APK with Appium Inspector.
- CI emulator action configuration can change; verify the workflow against your GitHub repository's current runner/action support.
- TC03 may need correct WebView context and HTML selectors. TC04 field order/default values, TC06 exact toast text, and TC08/TC09 expected crash/recovery behavior must be checked on the real APK.
- A robust CI pipeline should store APKs securely if the APK cannot be committed publicly.
