package propertyfileConfig;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import genaricUtil.FilePaths;

/**
 * @author:
 * @Description: This method is used to read the data from property file.
 */

public class PropertyFileReader implements ConfigReader
{
	String filePath;
	Properties prop;
	
	@Override
	public void OpenPropertyfile(String env) throws IOException
	{
	
		if(env.equals("qa"))
		{
	filePath=FilePaths.QAPropfilePath;
	
		}
		else
		{
			System.out.println("Please enter the env");
		}
	FileInputStream file = new FileInputStream(filePath);
	prop = new Properties();
	prop.load(file);
	
	}

	@Override
	public String getQA_DBURL() {

      
		return prop.getProperty("QA_DBURL");
	}
	@Override
	public String getDBUSERNAME() {
		return prop.getProperty("QA_DBUSERNAME");
				
	}
	@Override
	public String getDBPASSWORD() {
		return prop.getProperty("QA_DBPASSWORD");
	}




	@Override
	public String getEnv() {
		return prop.getProperty("env");
	}




	@Override
	public String getHost() {
		// TODO Auto-generated method stub
		return prop.getProperty("host");
	}

	@Override
	public String getAuthorization() {
		return prop.getProperty("Authorization");
	}

	@Override
	public String getClientID() {
		return prop.getProperty("ClientID");
	}

	@Override
	public String getClientSecret() {
		return prop.getProperty("ClientSecret");
	}

	@Override
	public String getscopes() {
		return prop.getProperty("scopes");
	}

	@Override
	public String getAuthURl() {
		return prop.getProperty("authURL");
	}

	@Override
	public String getColendingDBUsername() {
		return prop.getProperty("COLENDING_USERNAME");
	}

	@Override
	public String getColendingDBPassword() {
		return prop.getProperty("COLENDING_PASSWORD");
	}
	@Override
	public String getColendingDBUrl() {
		return prop.getProperty("COLENDING_HOST");
	}
	@Override
	public String getAuthToken(String auth) {
		return prop.getProperty(auth);
	}

	public String getEnvPropertiesData(String env,String key) {
		try {
			FileInputStream file = new FileInputStream(env);
			Properties properties = new Properties();
			properties.load(file);
			return properties.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "No Such Key in property file: " + key;
	}


	/**
	 * @Description: This method return the value associated with key in property file and all the key value are defined under folder Test data
	 * with file name DbQuery.properties
	 * @param key
	 * @return value
	 */
	public static String getPropertiesData(String key) {

		try {
			FileInputStream file = new FileInputStream(FilePaths.DbQueryfilePath);
			Properties properties = new Properties();
			properties.load(file);
			return properties.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "No Such Key in property file: " + key;
	}

}
	




