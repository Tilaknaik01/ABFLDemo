package genaricUtil;

import com.aventstack.extentreports.ExtentTest;
import propertyfileConfig.PropertyFileReader;

/**
 * @author:
 * @Description: This class is used to handle multiple thread
 */

public class ThreadLocalclass
{
	public static ThreadLocal<ExtentTest> test=new ThreadLocal<>();
	public  static ThreadLocal<ExtentTest> testlevel=new ThreadLocal<>();
	public static  ThreadLocal<ExtentTest> classlevel=new ThreadLocal<>();
	public static  ThreadLocal<ExcelUtil> excelUtil=new ThreadLocal<>();
	public static  ThreadLocal<RestAPIUtil> restAPIUtil=new ThreadLocal<>();
	public static  ThreadLocal<Databaseutil>  databaseutil=new ThreadLocal<>();

	public static  ThreadLocal<PropertyFileReader>  propfile=new ThreadLocal<>();

	public static  ThreadLocal<String> token=new ThreadLocal<>();

	public static ExtentTest gettest() { return test.get(); }



	public static void settest(ExtentTest stest) { test.set(stest);

	}
	public static PropertyFileReader getpropfile() { return propfile.get(); }

	public static void setpropfile(PropertyFileReader setpropfile) { propfile.set(setpropfile);

	}

	public static ExcelUtil getexcelUtil() {
		return excelUtil.get(); }

	public static void setexcelUtil(ExcelUtil sexcelUtil) { excelUtil.set(sexcelUtil);

	}
	public static RestAPIUtil getrestAPIUtil() { return restAPIUtil.get(); }



	public static void setrestAPIUtil(RestAPIUtil srestAPIUtil) { restAPIUtil.set(srestAPIUtil);

	}

	public static Databaseutil getdatabaseutil() { return databaseutil.get(); }



	public static void setDatabaseutil(Databaseutil sdatabaseutil) { databaseutil.set(sdatabaseutil);

	}


	public static String gettoken() { return token.get(); }



	public static void settoken(String stoken) { token.set(stoken);

	}



	public static ExtentTest   gettestlevel()
	{
		return testlevel.get();
	}


	public static void settestlevel(ExtentTest stestlevel)
	{
		testlevel.set(stestlevel);

	}

	public static ExtentTest   getclasslevel()
	{
		return classlevel.get();
	}


	public  static void setclasslevel(ExtentTest sclasslevel)
	{
		classlevel.set(sclasslevel);
	}
}