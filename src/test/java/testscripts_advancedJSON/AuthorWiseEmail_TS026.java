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

public class AuthorWiseEmail_TS026 extends DriverScript{
		
	
			@Test
			public void test_01(){
					isTestCaseRunnable("TC026");
					
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
					
					for(int i =0;i<countOfClients;i++){
						 String author	= response.
															then().
															contentType(ContentType.JSON).
															extract().
															path("["+i+"].author");		//[0].id
													//		path("[0].author");
						
						for(int j=0;j<2;j++){
							if(i==0){
										System.out.println("The author is : "+author);
										String email	= response.
																		then().
																		contentType(ContentType.JSON).
																		extract().
																		path("["+i+"].info["+j+"].email");   //$.[1].infoAdvanced[0].email
										
										System.out.println(email);
										ReportEvent.testStepReport(testCaseName, "pass", "The email is : "+email+" for corresponding author : "+author);
												
							}else{
										System.out.println("The author is : "+author);
										String email	= response.
																		then().
																		contentType(ContentType.JSON).
																		extract().
																		path("["+i+"].infoAdvanced["+j+"].email"); 
										
										System.out.println(email);
										ReportEvent.testStepReport(testCaseName, "pass", "The email is : "+email+" for corresponding author : "+author);
							}
								
						}
						
				}
										
		}

}
