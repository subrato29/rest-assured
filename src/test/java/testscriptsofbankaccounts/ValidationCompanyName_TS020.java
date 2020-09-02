package testscriptsofbankaccounts;

import java.util.List;

import jsonpathrespository.JSONPathExpression;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.api.base.DriverScript;
import com.api.support.ReportEvent;
import com.api.utilities.Utility;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.*;

public class ValidationCompanyName_TS020 extends DriverScript{
		
	
			@Test
			public void test_01(){
					isTestCaseRunnable("TC020");
					
					Response response = given().															
							when().
							get(xls.getCellData("Test Cases", "Url", rowNum));

					int countOfClients = response.
													then().
													contentType(ContentType.JSON).
													extract().
													path("clients.size()");	
					
				//	int countOfClientsHavingInputId=0;
					String companyName = null;
					
					for(int i =0;i<countOfClients;i++){
									String clientId= response.
																			then().
																			contentType(ContentType.JSON).
																			extract().
																			path("clients["+i+"].id");
									if(clientId.equalsIgnoreCase(xls.getCellData("Test Cases", "ClientId", rowNum))){
											//countOfClientsHavingInputId++;
											companyName	= response.
																						then().
																						contentType(ContentType.JSON).
																						extract().
																						path("clients["+i+"].company");
											
									}

								
					}
						
				//	System.out.println("Company Name : "+companyName);
					if(companyName.equalsIgnoreCase(xls.getCellData("Test Cases", "ClientCompany", rowNum))){
							System.out.println("PASS - Company Name : "+companyName);
							ReportEvent.testStepReport(testCaseName, "pass", "Company Name : "+Utility.printStringInDoubleQuotes(companyName)+" for the client id : "+Utility.printStringInDoubleQuotes(xls.getCellData("Test Cases", "ClientId", rowNum)));
					}else{	
							System.out.println("FAIL - Company Name : "+companyName);
							ReportEvent.testStepReport(testCaseName, "fail", "Company Name : "+Utility.printStringInDoubleQuotes(companyName)+" for the client id : "+Utility.printStringInDoubleQuotes(xls.getCellData("Test Cases", "ClientId", rowNum)));	
					}
			}

}
