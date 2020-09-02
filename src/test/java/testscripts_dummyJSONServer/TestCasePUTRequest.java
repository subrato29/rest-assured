package testscripts_dummyJSONServer;

import org.testng.annotations.Test;

import postsapiobjectrepository.Posts;

import com.api.base.DriverScript;
import com.api.support.ReportEvent;
import com.api.utilities.Utility;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.*;

public class TestCasePUTRequest extends DriverScript{

			@Test
			public void test_01(){
						isTestCaseRunnable("TC009");
						
						Posts post = new Posts();
						
						/*
						post.setId(id);
						post.setTitle(title);
						post.setAuthor(author);*/
					    
						post.setId(xls.getCellData("Test Cases", "Id", rowNum));
						post.setTitle(xls.getCellData("Test Cases", "Title", rowNum));
						post.setAuthor(xls.getCellData("Test Cases", "Author", rowNum));
					    
						/*post.setId("12");
						post.setTitle("Post Object");
						post.setAuthor("Debby");
						*/
						
						Response response = given().
																	body(post).
																	when().
																	contentType(ContentType.JSON).
																	put(xls.getCellData("Test Cases", "Url", rowNum)+"/"+xls.getCellData("Test Cases", "Id", rowNum));
						
						System.out.println("PUT response sent is : "+response.asString());
						ReportEvent.testStepReport(testCaseName, "pass", "PUT response sent is : "+response.asString());
				
			}
	
	
}
