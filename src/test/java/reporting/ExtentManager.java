package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import config.ConfigReader;

import java.nio.file.Files;
import java.nio.file.Path;

public final class ExtentManager {
    private static ExtentReports extent;
    private ExtentManager() {}

    public static synchronized ExtentReports getInstance() {
        if (extent == null) {
            try {
                Path report = Path.of(ConfigReader.get("report.path", "test-output/ExtentReport.html"));
                if (!report.isAbsolute()) report = Path.of(System.getProperty("user.dir")).resolve(report);
                Files.createDirectories(report.getParent());
                ExtentSparkReporter spark = new ExtentSparkReporter(report.toString());
                spark.config().setDocumentTitle("Selendroid Appium Automation");
                spark.config().setReportName("Mobile Automation Execution Report");
                extent = new ExtentReports();
                extent.attachReporter(spark);
                extent.setSystemInfo("Framework", "Appium + Java + TestNG + POM");
                extent.setSystemInfo("Platform", ConfigReader.get("platform.name", "Android"));
                extent.setSystemInfo("Device", ConfigReader.get("device.name", "Android Emulator"));
            } catch (Exception e) {
                throw new RuntimeException("Unable to initialize Extent Report", e);
            }
        }
        return extent;
    }
}