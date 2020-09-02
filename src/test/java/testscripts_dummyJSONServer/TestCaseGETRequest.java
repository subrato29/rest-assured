package testscripts_dummyJSONServer;

import org.testng.annotations.Test;

import com.api.base.DriverScript;
import com.api.support.ReportEvent;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.*;

public class TestCaseGETRequest extends DriverScript{

			@Test
			public void test_01(){
						isTestCaseRunnable("TC006");
						Response response = given().
																	when().
																	get(xls.getCellData("Test Cases", "Url", rowNum));
						
						System.out.println("GET response is : "+response.asString());
						ReportEvent.testStepReport(testCaseName, "pass", "GET response is : "+response.asString());
				
			}
	
	
}
