package api.utilites;



import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {
    
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String repName;
	
	
	
	
	
	public void onStart(ITestContext context) {
		String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	    repName="Test-Report"+timeStamp+".html";
		
	    sparkReporter  = new ExtentSparkReporter( ".\\reports\\"+repName);
	    sparkReporter.config().setDocumentTitle("restAssuredAutomationproject");
	    sparkReporter.config().setDocumentTitle("MyReport");
	    sparkReporter.config().setReportName("pet store using api");
	    sparkReporter.config().setTheme(Theme.DARK);
	    extent = new ExtentReports();
	    extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "Pest Store Api");
	}





	
	public void onTestSuccess(ITestResult result) {
		test=extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS,"Test Passed");
	}






	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
	    test.log(Status.FAIL,"Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
	}





	public void onTestSkipped(ITestResult result) {
		test=extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP,"Test Skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
	}



	public void onFinish(ITestContext context) {
	extent.flush();
	}
	
	

}
