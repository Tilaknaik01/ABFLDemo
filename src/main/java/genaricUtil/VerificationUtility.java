package genaricUtil;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;

import java.text.DecimalFormat;
import java.util.Map;

public class VerificationUtility {

    /**
     * This method is used to verify multiple parameter in responses
     * @param response
     * @param JSONPATH###Expdata
     */

    public void verifyResponseBody( Response response, String... act_expDatas){
        //int count=0;
        ExtentTest test = ThreadLocalclass.gettestlevel();
        for (String act_exp:act_expDatas ) {
            String[] dataSet = act_exp.split(CommonParameter.KEY_VALUE_SEPERATOR);
            String jsonPath = dataSet[0];
            String expData = dataSet[1];
            String[] ar = jsonPath.split("\\.");
            String actualData=ThreadLocalclass.getrestAPIUtil().getjasondata(response, jsonPath);
            try{
                Double doubleActData = Double.parseDouble(actualData);
                Double doubleExpData =Double.parseDouble(expData);
                DecimalFormat format = new DecimalFormat("0.##");
                actualData=format.format(doubleActData);
                expData=format.format(doubleExpData);
            }
            catch (Exception e){

            }
            try {
                Assert.assertEquals(actualData, expData, ar[ar.length - 1] + " is not matching in response");
                test.log(Status.PASS, "Verified that '" + ar[ar.length - 1] + "' is correct in response.");
            }catch (Error e){
                test.log(Status.FAIL, "Verified that '" + ar[ar.length - 1] + "' is  incorrect in response."+
                        " <br> Actual data is : <b>"+actualData+"</b> <br> " +
                        "Expected data is : <b>"+expData);
                //count++;
            }
        }
		/*if (count>=1){
			Assert.fail("Response validation Failed");
		}*/

    }

    public void verifyResponseBody(String sheetName, Response response, String TCID,String... act_expDatas) {
        //int count=0;
        ExtentTest test = ThreadLocalclass.gettestlevel();
        ExcelUtil excelUtil = ThreadLocalclass.getexcelUtil();
        excelUtil.initSheet(sheetName);
        Map<String, String> map = excelUtil.getExcelDataForSingleRow(TCID);
        String prefix=map.get("JsonPathPrefix");
        if (prefix==null || prefix.equals("")) throw new RuntimeException("Please enter JsonPathPrefix in Excel");

        for (Map.Entry<String, String> act_exp : map.entrySet()) {
            String key = act_exp.getKey();

            if (key.startsWith("?")){
                key=key.substring(1);
            }
            String expData = act_exp.getValue();
            String jsonPath=prefix+key;
            if (jsonPath.endsWith("#") || key.equals("JsonPathPrefix"))continue;

            String actualData=ThreadLocalclass.getrestAPIUtil().getjasondata(response, jsonPath);
            try{
                Double doubleActData = Double.parseDouble(actualData);
                Double doubleExpData =Double.parseDouble(expData);
                DecimalFormat format = new DecimalFormat("0.##");
                actualData=format.format(doubleActData);
                expData=format.format(doubleExpData);
            }
            catch (Exception e){

            }
            try {
                Assert.assertEquals(actualData, expData, key + " is not matching in response");
                test.log(Status.PASS, "Verified that '" + key + "' is correct in response.");
            } catch (Error e) {
                test.log(Status.FAIL, "Verified that '" + key + "' is  not matching in response."+
                        " <br> Actual data is : <b>"+actualData+"</b> <br> " +
                        "Expected data is : <b>"+expData);
                //count++;
            }
        }
        if (act_expDatas.length>=1) verifyResponseBody(response,act_expDatas);
				/*if (count>=1){
			Assert.fail("Response validation Failed");
		}*/
    }

    /**
     * This method is used to verify multiple parameter from data base
     * @param query
     * @param act_expDatas ColName###expData
     */
    //Verify database with respect to specified key value
    public void verifyDataBase(String query, String... act_expDatas){
        ExtentTest test = ThreadLocalclass.gettestlevel();
        String tableName=Databaseutil.getTableName(query);

        //int count=0;
        for (String act_exp:act_expDatas ) {
            String[] dataSet = act_exp.split(CommonParameter.KEY_VALUE_SEPERATOR);
            String colName="";
            try{
                colName= dataSet[0];
                }catch (Exception e){
            }
            String expData="";
            try{
                expData = dataSet[1]+"";
            }catch (Exception e) {
            }

            String actualData=ThreadLocalclass.getdatabaseutil().returnValueByColumnname(query,colName)+"";
            try{
                Double doubleActData = Double.parseDouble(actualData);
                Double doubleExpData =Double.parseDouble(expData);
                DecimalFormat format = new DecimalFormat("0.##");
                actualData=format.format(doubleActData);
                expData=format.format(doubleExpData);
            }
            catch (Exception e){

            }
                try {
                    Assert.assertEquals(actualData, expData, colName + " was not stored in DB .");

                    test.log(Status.PASS, "Verified '" + colName + "' in '" + tableName + "' table.");

                } catch (Error e) {
                    test.log(Status.FAIL, "'" + colName + "' is not found/Not correct in '" + tableName + "' table." +
                            " <br> Actual data is : <b>" + actualData + "</b> <br> " +
                            "Expected data is : <b>" + expData);
                }
        }
		/*if (count>=1){
			Assert.fail("Response validation Failed");
		}*/

    }
    //Verify database with excel data
    public void verifyDataBase( String query,int a,String sheetName, String TCID, String... act_expDatas) {
        ExtentTest test = ThreadLocalclass.gettestlevel();
        //int count=0;
        ExcelUtil excelUtil = ThreadLocalclass.getexcelUtil();
        excelUtil.initSheet(sheetName);
        Map<String, String> map = excelUtil.getExcelDataForSingleRow(TCID);
        String tableName=Databaseutil.getTableName(query);

        for (Map.Entry<String, String> act_exp : map.entrySet()) {
            String key = act_exp.getKey();
            String expData = act_exp.getValue();
            if(key.equals("JsonPathPrefix")||key.startsWith("?"))continue;
            if (key.endsWith("#")){
                key=key.substring(0,key.length()-1);
            }
            String actualData=ThreadLocalclass.getdatabaseutil().returnValueByColumnname(query, key);
            try{
                Double doubleActData = Double.parseDouble(actualData);
                Double doubleExpData =Double.parseDouble(expData);
                DecimalFormat format = new DecimalFormat("0.##");
                actualData=format.format(doubleActData);
                expData=format.format(doubleExpData);
            }
            catch (Exception e){

            }
            try {
                Assert.assertEquals( actualData, expData, key + " is not matching in Database Table '"+tableName+"'.");
                test.log(Status.PASS, "Verified that '" + key + "' is correct in '"+tableName+"' table.");
            } catch (Error e) {
                test.log(Status.FAIL, "'" +key +"' is  not matching/Entry is not happening in'"+tableName+"' table ." +
                        " <br> Actual data is : <b>"+actualData+"</b> <br> " +
                        "Expected data is : <b>"+expData);
                //count++;
            }
        }
        if (act_expDatas.length>=1) verifyDataBase(query, act_expDatas);
				/*if (count>=1){
			Assert.fail("Response validation Failed");
		}*/
    }


    /**
     * @Description: This method is used to verify response status Line as "PreconditionFailed" and add the response in extent report
     * @param res
     */
    public  void verifyResponseHeaders(Response res, String expCode) {
        ExtentTest testlevel = ThreadLocalclass.gettestlevel();
        String[] statusCodeWithMsg = expCode.split(CommonParameter.KEY_VALUE_SEPERATOR);
        int expStatusCode = Integer.parseInt(statusCodeWithMsg[0]);
        String expResponseMessage = statusCodeWithMsg[1];
        ValidatableResponse ValidateResponse = res.then();
        testlevel.log(Status.INFO,"Status code is :"+res.getStatusCode());
        testlevel.log(Status.INFO,"Response Message is :"+ res.getStatusLine());
        testlevel.log(Status.INFO,"Content Type is " +res.contentType());
try {
    testlevel.log(Status.INFO, "<b>Response Body:-</b>"+System.lineSeparator()+"<pre>"+ res.getBody().prettyPrint()+ "</pre>");
}catch (Exception e){

}
        Assert.assertEquals(ValidateResponse.extract().statusCode(),expStatusCode,"Response showing different Status Code");
        Assert.assertTrue(ValidateResponse.extract().statusLine().contains(expResponseMessage),"Response showing different Status message");
        testlevel.log(Status.PASS,"Verified the Response Code as "+expStatusCode+" & Response Message as "+expResponseMessage);
    }
}
