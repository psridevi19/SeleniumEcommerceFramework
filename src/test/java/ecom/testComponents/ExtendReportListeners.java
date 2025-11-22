package ecom.testComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import ecom.resources.ExtentReporterNG;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExtendReportListeners extends BaseTest implements ITestListener {

    WebDriver driver;
    protected static ExtentReports extent = ExtentReporterNG.getReportObject();
    //protected ExtentTest test;
    ThreadLocal<ExtentTest> threadLocal = new ThreadLocal();
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("In On test start");
        test = extent.createTest(result.getMethod().getMethodName());
        threadLocal.set(test);
    }

    public void onTestSuccess(ITestResult result) {
        threadLocal.get().log(Status.PASS,"Test Passed");
    }

    //Added Screenshot capture when test fails
    public void onTestFailure(ITestResult result) {

        System.out.println("In On test failure");
        threadLocal.get().fail(result.getThrowable());
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String filePath;
        try {
            filePath = getScreenShot(result.getMethod().getMethodName(),driver);
        } catch
        (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("In On test failure - SCreen shot path : "+filePath);
     //   test.addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
        test.fail("Test failed... Check Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(filePath).build());

        System.out.println("In On test failure, Adding screenshot for allure");
//Adding screenshot to allure report.
        Allure.addAttachment("Adding Invalid Credentials", getScreenshotAsFileInputStream(driver,result.getMethod().getMethodName()));

    }

    public void onTestSkipped(ITestResult result) {
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }

    public void onStart(ITestContext context) {
    }

    public void onFinish(ITestContext context) {
        System.out.println("On Test Finish");
        extent.flush();
    }
}
