package testscriptsofbankaccounts;

//import java.util.ArrayList;
import java.util.List;
//import java.util.Map;



import jsonpathrespository.JSONPathExpression;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.api.base.DriverScript;
import com.api.support.ReportEvent;
import com.api.utilities.Utility;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.matcher.ResponseAwareMatcher;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ValidatableResponse;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
//import static io.restassured.module.jsv.JsonSchemaValidator;

public class ValidationClientFullDetails_TS021 extends DriverScript{
		
	
			@Test
			public void test_01(){
					isTestCaseRunnable("TC021");
						
					Response response = (Response) given().queryParam("id", "59761c23fcb6254b1a06dad5").															
																		when().
																		get(xls.getCellData("Test Cases", "Url", rowNum))
																		.then()
																		.body("clients[2].id", equalTo("59761c23fcb6254b1a06dad5"));
					
					System.out.println(response.asString());
					int countOfAClientAttribute = response.
																				then().
																				contentType(ContentType.JSON).
																				extract().
																				path("clients[0].size()");	
					
					System.out.println("The no of arttributes of each client is : "+countOfAClientAttribute);
					
										
								int countOfClients = response.
										then().
										contentType(ContentType.JSON).
										extract().
										path("clients.size()");	

					int countOfClientsHavingInputId=0;
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
																						then().body("clients[2].id", equalTo("59761c23fcb6254b1a06dad5")).
																						contentType(ContentType.JSON).
																					//	body("clients[2].company", equalTo("ORGANICA")).toString();
																						/*.assertThat()
																			            .body(".", containsAllKeys("total", "streams")).*/
																						extract().response().toString();
																					//	path("clients["+i+"]");
											
									System.out.println(companyName);	
										
									}

								
					}
					
			}

}
