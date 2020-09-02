package testscripts_advancedJSON;

import jsonpathrespository.JSONPathExpression;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.api.base.DriverScript;
import com.api.support.ReportEvent;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.*;

public class AuthorWiseAddressCount_TS025 extends DriverScript{
		
	
			@Test
			public void test_01(){
					isTestCaseRunnable("TC025");
					
					Response response = given().	
																when().
																get(xls.getCellData("Test Cases", "Url", rowNum));
					
					int countOfClients = response.
															then().
															contentType(ContentType.JSON).
															extract().
															path("JSON.size()");
																
					System.out.println("No of agents : "+countOfClients);
					ReportEvent.testStepReport(testCaseName, "pass", "No of agents : "+countOfClients);
					
				//	for(int i =0;i<countOfClients;i++){
						 String author	= response.
															then().
															contentType(ContentType.JSON).
															extract().
															path(".info[0]..address");		//[0].id
													//		path("[0].author");
						System.out.println("xxxx : "+author);
					
										
		}

}
