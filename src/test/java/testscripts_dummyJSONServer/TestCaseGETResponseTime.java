package testscripts_dummyJSONServer;

import org.testng.annotations.Test;

import com.api.base.DriverScript;
import com.api.support.ReportEvent;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.*;

public class TestCaseGETResponseTime extends DriverScript{

			@Test
			public void test_01(){
						isTestCaseRunnable("TC014");
						Response response = given().
																	when().
																	get(xls.getCellData("Test Cases", "Url", rowNum));
						
						Long responseTime = response.
						then().
						extract().
						time();
						
						System.out.println("Response Time for the GET request is : "+responseTime);
						ReportEvent.testStepReport(testCaseName, "pass", "Response Time for the GET request is : "+responseTime);
				
			}
	
	
}
