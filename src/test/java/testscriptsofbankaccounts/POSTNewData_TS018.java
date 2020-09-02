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
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.*;

public class POSTNewData_TS018 extends DriverScript{
		
	
			@Test
			public void test_01(){
					isTestCaseRunnable("TC018");
					
					RestAssured.baseURI = "http://localhost:3000/db";
					
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
					
					
					Response response = given().
																contentType("application/json").
																body(postClientInfo).
																when().
															//	post(xls.getCellData("Test Cases", "Url", rowNum));
																post(RestAssured.baseURI);
					
					System.out.println("POST response sent is : "+response.asString());
					ReportEvent.testStepReport(testCaseName, "pass", "POST response sent is : "+response.asString());

					
			}

}
