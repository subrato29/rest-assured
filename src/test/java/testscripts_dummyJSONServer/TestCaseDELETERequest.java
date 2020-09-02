package testscripts_dummyJSONServer;

import org.testng.annotations.Test;

import postsapiobjectrepository.Posts;

import com.api.base.DriverScript;
import com.api.support.ReportEvent;
import com.api.utilities.Utility;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.*;

public class TestCaseDELETERequest extends DriverScript{

			@Test
			public void test_01(){
						isTestCaseRunnable("TC011");
																		
						Response response = given().
																	body("{"+Utility.printStringInDoubleQuotes("title")+":"+Utility.printStringInDoubleQuotes(xls.getCellData("Test Cases", "Title", rowNum))+"}").
																	when().
																	delete(xls.getCellData("Test Cases", "Url", rowNum)+"/"+xls.getCellData("Test Cases", "Id", rowNum));
						
						System.out.println("Deleted response is : "+response.asString());
						ReportEvent.testStepReport(testCaseName, "pass", "Deleted response is : "+response.asString());
				
			}
	
	
}
