package testscripts;

import org.testng.annotations.Test;

import com.api.base.DriverScript;
import com.api.support.ReportEvent;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.*;

public class WeatherGetRequestParam extends DriverScript{
		
	
		//	String apiCall = "http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b1b15e88fa797225412429c1c50c122a1";
		//simple Get Request for getting weather request by City name
			@Test
			public void test_01(){
					isTestCaseRunnable("TC002");
					Response response = given().
																param("q", xls.getCellData("Test Cases", "City", rowNum)).
																param("appid", xls.getCellData("Test Cases", "AppId", rowNum)).		
																when().
																get(xls.getCellData("Test Cases", "Url", rowNum));
					
					if(String.valueOf(response.getStatusCode()).toString().equals(xls.getCellData("Test Cases", "APIResponseCode", rowNum))){
							ReportEvent.testStepReport(testCaseName, "pass", "The API is working fine and the response code is : "+xls.getCellData("Test Cases", "APIResponseCode", rowNum));
							System.out.println("PASS - The Response code is : "+response.getStatusCode());
					}else{
							ReportEvent.testStepReport(testCaseName, "fail", "The API is NOT working fine and the response code is : "+xls.getCellData("Test Cases", "APIResponseCode", rowNum));
							System.out.println("FAIL - The Response code is : "+response.getStatusCode());
					}
			}

			
				
	
	
}
