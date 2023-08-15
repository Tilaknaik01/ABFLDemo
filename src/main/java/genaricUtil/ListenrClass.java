package genaricUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.testng.IClassListener;
import org.testng.ITestClass;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * @author:
 * @description: This method is used to generate extent report.
 */
public class ListenrClass implements ITestListener, IClassListener
{
	ExtentReports report;
	@Override
	public void onBeforeClass(ITestClass testClass) {
		//ExtentTest child = classLevelLogger.get().createNode(method.getName());
		//ExtentTest parent = extent.createTest(getClass().getSimpleName());
		ExtentTest classlevel = report.createTest(testClass.getName());
		ThreadLocalclass.setclasslevel(classlevel);
		   
		
	}

	@Override
	public void onAfterClass(ITestClass testClass)
	{
		
       report.flush();
  
	}
	
	@Override
	public void onTestStart(ITestResult result) 
	{
		ExtentTest testlevel = ThreadLocalclass.getclasslevel().createNode(result.getName());

		ThreadLocalclass.settestlevel(testlevel);
		
	   
		
	}

	@Override
	public void onTestSuccess(ITestResult result) 
	{

		System.out.println("Test Case Success");

		//ThreadLocalclass.gettestlevel().log(Status.PASS, result.getName());
		
		 report.flush();
		
	}

	@Override
	public void onTestFailure(ITestResult result)
	{
		
		
		//testlevelLog().log(Status.FAIL, "Test case is failed"+""+result.getName());
		//test.log(Status.FAIL, result.getThrowable());
		String excepionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		testlevelLog().fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occurred:Click to see"
				+ "</font>" + "</b >" + "</summary>" + result.getThrowable()+"\n"+excepionMessage.replaceAll(",", "<br>") + "</details>"
				+ " \n");
		//test.log(Status.FAIL, result.getThrowable());
	
		 report.flush();
		
	}

	@Override
	public void onTestSkipped(ITestResult result)
	{
		testlevelLog().skip(result.getMethod().getMethodName());
		testlevelLog().log(Status.SKIP ,result.getThrowable());
		
	}

	

	@Override
	public void onStart(ITestContext context) 
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));

		ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir")+"/ExtentReports/ExtentReports"+dtf.format(now)+".html");
	  /*          Date d = new Date();

	           SimpleDateFormat d1=new SimpleDateFormat("dd-M-yyyy hh-mm-ss");
	          String data = d1.format(d);*/

		// ExtentSparkReporter spark = new ExtentSparkReporter("./ExtendRepots/"+data+"report.html");
		spark.config().setDocumentTitle("FexliLoan Api Execution Report");
		spark.config().setReportName("Test Case Exection Report");
		spark.config().setTheme(Theme.DARK);



		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("BuildNo", "5.1");
		report.setSystemInfo("Env", "Pre-Prod");


	}

	@Override
	public void onFinish(ITestContext context) 
	{
		report.flush();
		
	}

	public  static ExtentTest testlevelLog()
	{
		return ThreadLocalclass.gettestlevel();
	}

}
