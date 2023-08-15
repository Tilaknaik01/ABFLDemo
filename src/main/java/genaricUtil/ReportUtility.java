package genaricUtil;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.response.Response;

public class ReportUtility {

    public ExtentTest intialLogForTest(String testCaseName){
        ExcelUtil excelUtil = ThreadLocalclass.getexcelUtil();
        excelUtil.initSheet(ExcelUtil.INTIAL_TESTSCRIPT_REPORT_LOG_SHEET);
        ExtentTest test = ThreadLocalclass.gettestlevel();
        test.log(Status.INFO, "Description :- "+excelUtil.getExcelDataForSingleRow(testCaseName, "TC Description"));
        test.log(Status.INFO, "TestcaseType :- "+excelUtil.getExcelDataForSingleRow(testCaseName,"TC Type"));
        return test;
    }


}
