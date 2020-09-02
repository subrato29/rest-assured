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

public class NameOfFemaleClients_TS017 extends DriverScript{
		
	
			@Test
			public void test_01(){
					isTestCaseRunnable("TC017");
					
					Response response = given().															
																when().
																get(xls.getCellData("Test Cases", "Url", rowNum));
					
					int countOfClients = response.
																	then().
																	contentType(ContentType.JSON).
																	extract().
																	path("clients.size()");
					int countOfFemaleClients=0;
					String tempNameOfFemaleClient = null;
					
					for(int i =0;i<countOfClients;i++){
									String gender	= response.
																			then().
																			contentType(ContentType.JSON).
																			extract().
																			path("clients["+i+"].gender");
									if(gender.equalsIgnoreCase("female")){
											countOfFemaleClients++;
											String nameOfFemaleClient	= response.
													then().
													contentType(ContentType.JSON).
													extract().
													path("clients["+i+"].name");
												//	System.out.println("name of male clients : "+nameOfMaleClient);
											tempNameOfFemaleClient = tempNameOfFemaleClient+" , "+nameOfFemaleClient;
											
									}

								
					}
								
					System.out.println("Count of female clients : "+countOfFemaleClients);
					ReportEvent.testStepReport(testCaseName, "pass", "Count of female clients : "+countOfFemaleClients);
					
					System.out.println("The name of the FEMALE clients is/are : "+tempNameOfFemaleClient.substring(7));
					ReportEvent.testStepReport(testCaseName, "pass", "The name of the FEMALE clients is/are : "+tempNameOfFemaleClient.substring(7));
			}

}
