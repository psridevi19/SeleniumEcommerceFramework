package ecom.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReporterNG {

    public static ExtentReports getReportObject()
    {
         ExtentReports extent;

        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String reportpath = System.getProperty("user.dir")+"reports/ExtentReport_"+timestamp+".html";
      //  String path = System.getProperty("user.dir")+"/reports/index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportpath);
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test Results");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester","Sridevi Ponnusamy");
        return extent;
    }
}
