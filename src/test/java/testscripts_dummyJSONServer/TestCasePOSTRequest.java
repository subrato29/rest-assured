package testscripts_dummyJSONServer;

import org.testng.annotations.Test;

import com.api.base.DriverScript;
import com.api.support.ReportEvent;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.*;

public class TestCasePOSTRequest extends DriverScript{

			@Test
			public void test_01(){
						isTestCaseRunnable("TC007");
						Response response = given().
																	body("		{\"id\":\" 2\", "
																			+ "	\"title\":\" tempTitle\","
																			+ "	\"author\":\" Robert\"}		").
																		when().
																		contentType(ContentType.JSON).
																		post(xls.getCellData("Test Cases", "Url", rowNum));
						
						System.out.println("POST response is : "+response.asString());
						ReportEvent.testStepReport(testCaseName, "pass", "POST response is : "+response.asString());
				
			}
	
	
}
