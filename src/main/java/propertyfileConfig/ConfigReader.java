package propertyfileConfig;

import java.io.IOException;

/**
 * @author:
 * @Description: This method is used to configure property file key name.
 */
public interface ConfigReader 
{
	String getQA_DBURL();
	String getDBUSERNAME();
	String getDBPASSWORD();
	String getEnv();
	String getHost();

	String getAuthorization();
	String getClientID();
	String getClientSecret();
	String getscopes();
	String getAuthURl();

	String getAuthToken(String auth);
     void OpenPropertyfile(String env) throws IOException;

	 String getColendingDBUsername();
	 String getColendingDBPassword();
	 String getColendingDBUrl();
}
