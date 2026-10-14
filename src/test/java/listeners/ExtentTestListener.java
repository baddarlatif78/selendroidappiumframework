package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.*;
import reporting.ExtentManager;
import reporting.ExtentTestManager;

public class ExtentTestListener implements ITestListener, ISuiteListener {
    private ExtentReports report;
    @Override public void onStart(ISuite suite) { report = ExtentManager.getInstance(); }
    @Override public void onTestStart(ITestResult result) {
        ExtentTest test = ExtentManager.getInstance().createTest(result.getMethod().getDescription() != null
                ? result.getMethod().getDescription() : result.getMethod().getMethodName());
        test.assignCategory(result.getTestClass().getName());
        ExtentTestManager.setTest(test);
        test.info("Started: " + result.getMethod().getMethodName());
    }
    @Override public void onTestSuccess(ITestResult result) {
        if (ExtentTestManager.getTest() != null) ExtentTestManager.getTest().pass("Test passed");
        ExtentTestManager.removeTest();
    }
    @Override public void onTestFailure(ITestResult result) {
        if (ExtentTestManager.getTest() != null) {
            ExtentTestManager.getTest().fail(result.getThrowable());
            ExtentTestManager.getTest().info("Failed test: " + result.getMethod().getMethodName());
        }
        ExtentTestManager.removeTest();
    }
    @Override public void onTestSkipped(ITestResult result) {
        if (ExtentTestManager.getTest() != null) ExtentTestManager.getTest().skip("Test skipped: " + result.getThrowable());
        ExtentTestManager.removeTest();
    }
    @Override public void onFinish(ISuite suite) { if (report != null) report.flush(); }
}