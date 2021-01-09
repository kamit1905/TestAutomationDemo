package com.app.listner;


import com.app.base.TestBase;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.ScreenCapture;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

//import static CommonLib.DataManger.getPropertyFile;
//import static CommonLib.Executor.ProjectName;


public class ListnerDemo1 implements IReporter {
    private static final String OUTPUT_FOLDER ="/Reports1/";
    private static final String FILE_NAME = "Test_Summary_Report_"+".html";
    static String dir = System.getProperty("user.dir");
    //static String ReportPath = getPropertyFile("ReportPath");
    //String ExtentReportXMLPath = getPropertyFile("ExtentReportXMLPath");
    private ExtentReports extent;
    ScreenCapture screenCapture;
    public static ExtentTest test;
   
   
    
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        init();

        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();
            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();

                buildTestNodes(context.getFailedTests(), Status.FAIL);
                buildTestNodes(context.getSkippedTests(), Status.SKIP);
                buildTestNodes(context.getPassedTests(), Status.PASS);

            }
        }

        for (String s : Reporter.getOutput()) {
            extent.setTestRunnerOutput(s);
        }

        extent.flush();

    }

    private void init() {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(dir+OUTPUT_FOLDER + FILE_NAME);
        htmlReporter.config().setDocumentTitle("WiseDaily_Automation_Report :" );
        htmlReporter.config().setReportName("WiseDaily_Automation_Report :");
        //htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setTheme(Theme.STANDARD);
       // htmlReporter.onScreenCaptureAdded();


        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setReportUsesManualConfiguration(true);

    }

    private void buildTestNodes(IResultMap tests, Status status) {



        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
                int count = result.getMethod().getTotalInvocationCount();

                if (count > 1) {

                    test = extent.createTest(result.getMethod().getMethodName() + "_Iteration_");

                } else {

                    test = extent.createTest(result.getMethod().getMethodName());
                }
                for (String group : result.getMethod().getGroups())
                    test.assignCategory(group);

                if (result.getThrowable() != null) {
                    test.log(status, result.getThrowable());
                } else {

                    test.log(status, "Test " + status.toString().toLowerCase() + "ed");

                }

                test.getModel().setStartTime(getTime(result.getStartMillis()));
                test.getModel().setEndTime(getTime(result.getEndMillis()));
            }
        }
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
}