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

public class CountOfAgents_TS024 extends DriverScript{
		
	
			@Test
			public void test_01(){
					isTestCaseRunnable("TC024");
					
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
					
					if(String.valueOf(response.getStatusCode()).equals("200")){
								System.out.println("This is the valid response and the response code is : "+response.getStatusCode());
								ReportEvent.testStepReport(testCaseName, "pass", "This is the valid response and the response code is : "+response.getStatusCode());
					}else{
								System.out.println("This is not the valid response and the response code is : "+response.getStatusCode());
								ReportEvent.testStepReport(testCaseName, "fail", "This is NOT the valid response and the response code is : "+response.getStatusCode());
					}
										
			}

}
