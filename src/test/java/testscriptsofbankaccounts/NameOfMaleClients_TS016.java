package testscriptsofbankaccounts;

import java.util.ArrayList;

import jsonpathrespository.JSONPathExpression;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.api.base.DriverScript;
import com.api.support.ReportEvent;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.*;

public class NameOfMaleClients_TS016 extends DriverScript{
		
	
			@Test
			public void test_01(){
					isTestCaseRunnable("TC016");
					
					Response response = given().															
																when().
																get(xls.getCellData("Test Cases", "Url", rowNum));
					
					int countOfClients = response.
																	then().
																	contentType(ContentType.JSON).
																	extract().
																	path("clients.size()");
					int countOfMaleClients=0;
					String tempNameOfMaleClient = null;
					
					for(int i =0;i<countOfClients;i++){
									String gender	= response.
																			then().
																			contentType(ContentType.JSON).
																			extract().
																			path("clients["+i+"].gender");
									if(gender.equalsIgnoreCase("male")){
											countOfMaleClients++;
											String nameOfMaleClient	= response.
													then().
													contentType(ContentType.JSON).
													extract().
													path("clients["+i+"].name");
												//	System.out.println("name of male clients : "+nameOfMaleClient);
											tempNameOfMaleClient = tempNameOfMaleClient+" , "+nameOfMaleClient;
											
									}

								
					}
								
					System.out.println("Count of male clients : "+countOfMaleClients);
					ReportEvent.testStepReport(testCaseName, "pass", "Count of male clients : "+countOfMaleClients);
					
					System.out.println("The name of the MALE clients are : "+tempNameOfMaleClient.substring(7));
					ReportEvent.testStepReport(testCaseName, "pass", "The name of the MALE clients are : "+tempNameOfMaleClient.substring(7));
			}

}
