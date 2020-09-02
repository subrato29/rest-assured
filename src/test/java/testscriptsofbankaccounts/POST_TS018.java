package testscriptsofbankaccounts;

import java.util.ArrayList;

import jsonpathrespository.JSONPathExpression;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import postsapiobjectrepository.Post_BankAccount;

import com.api.base.DriverScript;
import com.api.support.ReportEvent;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

import static com.jayway.restassured.RestAssured.*;

public class POST_TS018 extends DriverScript{
		
	
			@Test
			public void test_01(){
					isTestCaseRunnable("TC018");
					
					String APIUrl = "http://localhost:3000/db";
					
					Post_BankAccount postClientInfo = new Post_BankAccount();
					
					postClientInfo.setId(xls.getCellData("Test Cases", "ClientId", rowNum));
					postClientInfo.setIsActive(xls.getCellData("Test Cases", "ClientIsActive", rowNum));
					postClientInfo.setAge(xls.getCellData("Test Cases", "ClientAge", rowNum));
					postClientInfo.setName(xls.getCellData("Test Cases", "ClientName", rowNum));
					postClientInfo.setGender(xls.getCellData("Test Cases", "Gender", rowNum));
					postClientInfo.setCompany(xls.getCellData("Test Cases", "ClientCompany", rowNum));
					postClientInfo.setEmail(xls.getCellData("Test Cases", "ClientEmail", rowNum));
					postClientInfo.setPhone(xls.getCellData("Test Cases", "ClientPhone", rowNum));
					postClientInfo.setAddress(xls.getCellData("Test Cases", "ClientAddress", rowNum));
					
					
					//Initializing payload or API body
				//	String APIBody = "{API Body}"; //e.g.- "{\"key1\":\"value1\",\"key2\":\"value2\"}"
								
					// Building request using requestSpecBuilder
					RequestSpecBuilder builder = new RequestSpecBuilder();
						
					//Setting API's body
					builder.setBody(postClientInfo);
						
					//Setting content type as application/json or application/xml
					builder.setContentType("application/json; charset=UTF-8");
						
					RequestSpecification requestSpec = builder.build();

					//Making post request with authentication, leave blank in case there are no credentials- basic("","")
					Response response = given().
							body(requestSpec).
							when().
						//	post(xls.getCellData("Test Cases", "Url", rowNum));
							post(APIUrl);
					
					JSONObject JSONResponseBody = new JSONObject(response.body().asString());
					
			}

}
