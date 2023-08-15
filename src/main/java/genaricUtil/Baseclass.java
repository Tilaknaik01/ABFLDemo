package genaricUtil;
/**
 * This class consist of all the basic Pre-condition & Post-condition of all Script for the script
 * @author
 *
 */

import java.io.IOException;

import java.sql.SQLException;
import java.time.Instant;


import java.lang.reflect.Method;
import java.sql.SQLException;
import java.time.Instant;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import org.testng.annotations.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;
import propertyfileConfig.ObjectReader;
import propertyfileConfig.PropertyFileReader;

import static io.restassured.RestAssured.*;


public class Baseclass
{
	public Databaseutil databaseutil=new Databaseutil();
	public ReportUtility reportUtil=new ReportUtility();
	public VerificationUtility verificationUtil=new VerificationUtility();
	public RestAPIUtil restAPIUtil=new RestAPIUtil();
	public PropertyFileReader propfile=new PropertyFileReader();
	public ExcelUtil excelUtil=new ExcelUtil();
	public JavaUtil javaUtil=new JavaUtil();


	public int exp;
	public int temp;
	public static Instant expdate;

	/*ClassName_MethodName*/
	protected String TCID;

	public String token;
	public SoftAssert softAssert=new SoftAssert();

	/**
	 * This method is used to made a connection with DataBase & generate token .
	 * @throws SQLException
	 * @throws IOException
	 */
	@BeforeSuite
	public void ConnectToDatabase() throws Exception {
		ObjectReader.reader.OpenPropertyfile("qa");
		RestAssured.baseURI=ObjectReader.reader.getHost();
		ThreadLocalclass.setrestAPIUtil(restAPIUtil);
		if(token==null) {
			Response response = renewToken();
			token = response.jsonPath().get("data.userToken");
//System.out.println(token);
//exp=response.jsonPath().get("expires_in");
//expdate=Instant.now().plusSeconds(exp-300);*/
		}
		ThreadLocalclass.settoken(token);
		ThreadLocalclass.setexcelUtil(excelUtil);
		excelUtil.openExcelFile(FilePaths.QAEXCELFILE);
//databaseutil.connectdatabase();
		ThreadLocalclass.setDatabaseutil(databaseutil);
	}
	//@BeforeMethod(alwaysRun = true)
	public void ConnectionCheck() throws Exception {
		if (databaseutil.isDbConnected() == false) {
			databaseutil.connectdatabase();
			ThreadLocalclass.setDatabaseutil(databaseutil);
		}
	}

	@BeforeMethod(alwaysRun = true)

	public void checkingToken(Method mtd) throws Exception {
		TCID=mtd.getDeclaringClass().getSimpleName() +"_"+mtd.getName();
	}


	/**
	 * This method is call the assertAll method..
	 */
	@AfterMethod
	public void afterMethod(){
		softAssert.assertAll();
	}

	/**
	 * This method is used to close data base connection.
	 */
	@AfterSuite
	public void Disconnetdatabase() throws SQLException

	{
//databaseutil.closedatabase();
	}


	public Response renewToken()

	{
		Response response = given().header("auth-cred", ObjectReader.reader.getAuthorization()).log().all().when().get(EndPoint.LOGIN);
		//response.then().log().all();
		return response;
	}


}