package genaricUtil;
/**
 * @author
 * @Description:This Class consist of Rest assured generic Methods
 */



import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestAPIUtil

{

	/**
	 * @Description: This method is used to send the POST request
	 * @param obj
	 * @param token
	 * @param Endpoint
	 * @param _isReport
	 * @return
	 */
	public  Response postRequest(Object obj, String token, String Endpoint,boolean _isReport, Map<String, String>... pathParamsAndHeaders)
	{
		ExtentTest testlevel = ThreadLocalclass.gettestlevel();
		RequestSpecification requestSpec = given().auth().oauth2(token);
		if(pathParamsAndHeaders.length>=1){
			if (pathParamsAndHeaders[0].size()>=1)requestSpec.pathParams(pathParamsAndHeaders[0]);
		}
		if(pathParamsAndHeaders.length>=2)	{
			if (pathParamsAndHeaders[1].size()>=1) requestSpec.headers(pathParamsAndHeaders[1]);
		}
		requestSpec.contentType(ContentType.JSON).body(obj).log().all();
		try {

		if (_isReport) {
			//testlevel.log(Status.INFO,"Request Body : "+ new ObjectMapper().writeValueAsString(obj));
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonNode = mapper.readTree(mapper.writeValueAsString(obj));
			String prettyJsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
			testlevel.log(Status.INFO, "<b>Request Body:-</b>" + System.lineSeparator() + "<pre>" + prettyJsonString + "</pre>");
		}

		}catch (Exception e){		}
		Response res=requestSpec.when().post(Endpoint);
		//res.then().log().all();
		return res;
	}
	//To Send the authorisation in Authorization header
	/*public  Response postRequest(Object obj, String token, String Endpoint,boolean _isReport, Map<String, String>... pathParamsAndHeaders)
	{
		ExtentTest testlevel = ThreadLocalclass.gettestlevel();
		RequestSpecification requestSpec = given().header("Authorization","Bearer "+token);
		if(pathParamsAndHeaders.length>=1){
			if (pathParamsAndHeaders[0].size()>=1)requestSpec.pathParams(pathParamsAndHeaders[0]);
		}
		if(pathParamsAndHeaders.length>=2)	{
			if (pathParamsAndHeaders[1].size()>=1) requestSpec.headers(pathParamsAndHeaders[1]);
		}
		requestSpec.contentType(ContentType.JSON).body(obj).log().all();
		try {

		if (_isReport) {
			//testlevel.log(Status.INFO,"Request Body : "+ new ObjectMapper().writeValueAsString(obj));
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonNode = mapper.readTree(mapper.writeValueAsString(obj));
			String prettyJsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
			testlevel.log(Status.INFO, "<b>Request Body:-</b>" + System.lineSeparator() + "<pre>" + prettyJsonString + "</pre>");
		}

		}catch (Exception e){		}
		Response res=requestSpec.when().post(Endpoint);
		//res.then().log().all();
		return res;
	}*/
	/**
	 * @Description: This method is used to send the put request
	 * @param obj
	 * @param token
	 * @param Endpoint
	 * @param _isReport
	 * @return
	 */
	public  Response putRequest(Object obj, String token, String Endpoint,boolean _isReport, Map<String, String>... pathParamsAndHeaders)
	{
		ExtentTest testlevel = ThreadLocalclass.gettestlevel();
		RequestSpecification requestSpec = given().auth().oauth2(token);
		if(pathParamsAndHeaders.length>=1){
			if (pathParamsAndHeaders[0].size()>=1)requestSpec.pathParams(pathParamsAndHeaders[0]);
		}
		if(pathParamsAndHeaders.length>=2)	{
			if (pathParamsAndHeaders[1].size()>=1) requestSpec.headers(pathParamsAndHeaders[1]);
		}
		requestSpec.contentType(ContentType.JSON).body(obj).log().all();
		try {
				if (_isReport) {
					//testlevel.log(Status.INFO, "<b>Request Body:-</b>" + System.lineSeparator() + "<pre>"+ new ObjectMapper().writeValueAsString(obj) + "</pre>");
					ObjectMapper mapper = new ObjectMapper();
					JsonNode jsonNode = mapper.readTree(mapper.writeValueAsString(obj));
					String prettyJsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
					testlevel.log(Status.INFO, "<b>Request Body:-</b>" + System.lineSeparator() + "<pre>" + prettyJsonString + "</pre>");
				}
		}catch (Exception e){		}
		Response res=requestSpec.when().put(Endpoint);
		return res;
	}
	/**
	 * @Description: This method is used to send the get request
	 * @param token
	 * @param Endpoint
	 * @param _isReport
	 * @return
	 */
	public  Response getRequest(String token, String Endpoint,boolean _isReport, Map<String, String>... pathParamsAndHeaders)
	{
		//ExtentTest testlevel = ThreadLocalclass.gettestlevel();
		RequestSpecification requestSpec = given().auth().oauth2(token);
		if(pathParamsAndHeaders.length>=1){
			if (pathParamsAndHeaders[0].size()>=1)requestSpec.pathParams(pathParamsAndHeaders[0]);
		}
		if(pathParamsAndHeaders.length>=2)	{
			if (pathParamsAndHeaders[1].size()>=1) requestSpec.headers(pathParamsAndHeaders[1]);
		}
		requestSpec.contentType(ContentType.JSON).log().all();

		/*try {
			//testlevel.log(Status.INFO, "");
			if (_isReport)	testlevel.log(Status.INFO,"Request Body : "+ new ObjectMapper().writeValueAsString(obj));
		}catch (Exception e){		}*/
		Response res=requestSpec.when().get(Endpoint);
		return res;
	}



	/**
	 * @description: This method is used to send the POST request with  path parameter
	 * @param obj
	 * @param Key
	 * @param Value
	 * @param token
	 * @param Endpoint
	 * @return
	 */
	public  Response postRequestWithPathparam(Object obj,String Key,String Value,String token, String Endpoint,ExtentTest testlevel)
	{
		RequestSpecification requestSpec = given().auth().oauth2(token)
				.pathParams(Key, Value)
				.contentType(ContentType.JSON)
				.body(obj).log().all();

		try {

			testlevel.log(Status.INFO,"Request Body : "+ new ObjectMapper().writeValueAsString(obj));
		}catch (Exception e){

		}
		Response res=requestSpec.when().post(Endpoint);
		return res;
	}

	/**
	 * @Description: This method is used to send the GET request without path parameter
	 * @param token
	 * @param Endpoint
	 * @return
	 */
	public  Response getRequest(String token, String Endpoint)
	{
		Response res =given().auth().oauth2(token)
				.when().get(Endpoint);
		return res;
	}
	/**
	 * @Description: This method is used to send the GET request with path parameter
	 * @param Key
	 * @param Value
	 * @param token
	 * @param Endpoint
	 * @return
	 */
	public  Response getRequestWithPathparam(String Key,String Value,String token, String Endpoint)
	{
		Response res =given().auth().oauth2(token)
				.pathParams(Key,Value)
				.log().all()
				.when().get(Endpoint);
		return res;
	}

	/**
	 * @Description: This method is used to send the PUT request without path parameter
	 * @param obj
	 * @param token
	 * @param Endpoint
	 * @return
	 */
	public  Response putRequest(Object obj,String token, String Endpoint)
	{
		Response res = given().auth().oauth2(token)
				.contentType(ContentType.JSON)
				.body(obj)
				.when().put(Endpoint);
		return res;
	}
	/**
	 * @Description: This method is used to send the PUT request without path parameter & capture the request body in report
	 * @param obj
	 * @param token
	 * @param Endpoint
	 * @param testlevel
	 * @return
	 */
	public  Response putRequest(Object obj,String token, String Endpoint,ExtentTest testlevel)
	{
		RequestSpecification reqestspec = given().auth().oauth2(token)
				.contentType(ContentType.JSON)
				.body(obj).log().all();
		try {
			testlevel.log(Status.INFO, new ObjectMapper().writeValueAsString(obj));
		}catch (Exception e){

		}
		Response res = reqestspec.when().put(Endpoint);
		return res;
	}

	/**
	 * @Description: This method is used to send the PUT request with path parameter
	 * @Param Key
	 * @Param Value
	 * @param obj
	 * @param token
	 * @param Endpoint
	 * @return
	 */
	public  Response putRequestWithPathparam(String Key,String Value,Object obj,String token, String Endpoint)
	{
		Response res = given().auth().oauth2(token)
				.pathParams(Key,Value)
				.contentType(ContentType.JSON)
				.body(obj).log().all()
				.when().put(Endpoint);
		return res;
	}
	/**
	 * @Description: This method is used to send the PUT request with path parameter & capture the rquest body in report
	 * @Param Key
	 * @Param Value
	 * @param obj
	 * @param token
	 * @param Endpoint
	 * @return
	 */
	public  Response putRequestWithPathparam(String Key,String Value,Object obj,String token, String Endpoint,ExtentTest testlevel)
	{
		RequestSpecification requestSpec = given().auth().oauth2(token)
				.pathParams(Key, Value)
				.contentType(ContentType.JSON)
				.body(obj).log().all();
		try {
			testlevel.log(Status.INFO, new ObjectMapper().writeValueAsString(obj));
		}catch (Exception e){

		}
		Response res=requestSpec.when().put(Endpoint);
		return res;
	}



	/**
	 * @Description: This method is used to extract the response data based on JSON path
	 * @param res
	 * @param path
	 * @return
	 */
	public String  getjasondata(Response res, String path)

	{
		String data=null;
		try {
			data = res.jsonPath().get(path).toString();
		}catch (NullPointerException E){
			//return null;
		}
		return data;
	}
	/**
	 * @Description: This method is used to extract the list of response data based on JSON path
	 * @param res
	 * @param path
	 * @return
	 */
	public List<String> getListjasondata(Response res, String path)
	{
		List<String> data= res.jsonPath().getList(path);

		return data;
	}


}
