package testscripts;

import jsonpathrespository.JSONPathExpression;

import org.testng.annotations.Test;

import com.api.base.DriverScript;
import com.api.support.ReportEvent;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.*;

public class ExtractWeatherDescByJsonPath extends DriverScript{
		
			@Test
			public void test_01(){
					isTestCaseRunnable("TC005");
					/*
					 String weatherReport = given().
																param("id", xls.getCellData("Test Cases", "CityId", rowNum)).
																param("appid", xls.getCellData("Test Cases", "AppId", rowNum)).		
																when().
																get(xls.getCellData("Test Cases", "Url", rowNum)).
																then().
																contentType(ContentType.JSON).
																extract().
																path(JSONPathExpression.weatherDesc);
					*/
					
					Response response = given().
																param("id", xls.getCellData("Test Cases", "CityId", rowNum)).
																param("appid", xls.getCellData("Test Cases", "AppId", rowNum)).		
																when().
																get(xls.getCellData("Test Cases", "Url", rowNum));	
					
					
					String actualWeatherReport = response.
																				then().
																				contentType(ContentType.JSON).
																				extract().
																				path(JSONPathExpression.weatherDesc);
																			//	path("$.weather[0].description");
					//Test Validation
					if(actualWeatherReport.equalsIgnoreCase(xls.getCellData("Test Cases", "ExpectedWeatherReport", rowNum))){
								System.out.println("PASS - The weather report is : "+actualWeatherReport);
								ReportEvent.testStepReport(testCaseName, "pass", "The weather report is correct and it is : "+actualWeatherReport);
					}else{
								System.out.println("FAIL - The weather report is : "+actualWeatherReport);
								ReportEvent.testStepReport(testCaseName, "fail", "The weather report is incorrect and it is : "+actualWeatherReport);
					}
					
				
					
			}

}
