package ExtentReportConfig;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {

    public static ExtentReports getReportObject(){
        //ExtentReports, ExtentSparkReporter
        String reportPath = System.getProperty("user.dir")+"//reports//index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test Results");
        ExtentReports extReports = new ExtentReports();
        extReports.attachReporter(reporter);
        //Tester name
        extReports.setSystemInfo("Tester","Ars");
        return extReports;
    }
}
