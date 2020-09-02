package testscriptsofbankaccounts;

import org.testng.annotations.Test;

import postsapiobjectrepository.Posts;

import com.api.base.DriverScript;
import com.api.support.ReportEvent;
import com.api.utilities.Utility;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.*;

public class TestCasePATCHRequest_TS019 extends DriverScript{

			@Test
			public void test_01(){
						isTestCaseRunnable("TC019");
						
					//	post.setId(xls.getCellData("Test Cases", "Id", rowNum));
				//		post.setTitle(xls.getCellData("Test Cases", "Title", rowNum));
					//	post.setAuthor(xls.getCellData("Test Cases", "Author", rowNum));
					    						
						Response response = given().parameter("id", "59761c23b30d971669fb42ff").
																	//body("{"+Utility.printStringInDoubleQuotes("name")+":"+Utility.printStringInDoubleQuotes(xls.getCellData("Test Cases", "ClientName", rowNum))+"}").
																	body("{"+Utility.printStringInDoubleQuotes("name")+":"+Utility.printStringInDoubleQuotes(xls.getCellData("Test Cases", "ClientName", rowNum))+"}").
																	when().
																	contentType("application/json").
																	patch(xls.getCellData("Test Cases", "Url", rowNum));
						
						System.out.println("PATCH response sent is : "+response.asString());
						ReportEvent.testStepReport(testCaseName, "pass", "PATCH response sent is : "+response.asString());
				
			}
	
	
}
