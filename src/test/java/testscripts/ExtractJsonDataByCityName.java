package testscripts;

import org.testng.annotations.Test;

import com.api.base.DriverScript;
import com.api.support.ReportEvent;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.*;

public class ExtractJsonDataByCityName extends DriverScript{
		
			@Test
			public void test_01(){
					isTestCaseRunnable("TC003");
					Response response = given().
																param("q", xls.getCellData("Test Cases", "City", rowNum)).
																param("appid", xls.getCellData("Test Cases", "AppId", rowNum)).		
																when().
																get(xls.getCellData("Test Cases", "Url", rowNum));
							
					System.out.println("Extracted Json data after API Response is: "+response.asString());
					ReportEvent.testStepReport(testCaseName, "pass", "Extracted Json data after API Response is : "+response.asString());
			}

}
