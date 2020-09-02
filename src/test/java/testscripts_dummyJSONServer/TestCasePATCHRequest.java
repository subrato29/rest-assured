package testscripts_dummyJSONServer;

import org.testng.annotations.Test;

import postsapiobjectrepository.Posts;

import com.api.base.DriverScript;
import com.api.support.ReportEvent;
import com.api.utilities.Utility;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.*;

public class TestCasePATCHRequest extends DriverScript{

			@Test
			public void test_01(){
						isTestCaseRunnable("TC010");
						
						Posts post = new Posts();
						
					//	post.setId(xls.getCellData("Test Cases", "Id", rowNum));
				//		post.setTitle(xls.getCellData("Test Cases", "Title", rowNum));
					//	post.setAuthor(xls.getCellData("Test Cases", "Author", rowNum));
					    						
						Response response = given().
																	body("{"+Utility.printStringInDoubleQuotes("title")+":"+Utility.printStringInDoubleQuotes(xls.getCellData("Test Cases", "Title", rowNum))+"}").
																	when().
																	contentType(ContentType.JSON).
																	patch(xls.getCellData("Test Cases", "Url", rowNum)+"/"+xls.getCellData("Test Cases", "Id", rowNum));
						
						System.out.println("PATCH response sent is : "+response.asString());
						ReportEvent.testStepReport(testCaseName, "pass", "PATCH response sent is : "+response.asString());
				
			}
	
	
}
