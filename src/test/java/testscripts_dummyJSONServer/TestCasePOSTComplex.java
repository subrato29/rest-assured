package testscripts_dummyJSONServer;

import org.testng.annotations.Test;

import postsapiobjectrepository.Info;
import postsapiobjectrepository.Posts;
import postsapiobjectrepository._Posts;

import com.api.base.DriverScript;
import com.api.support.ReportEvent;
import com.api.utilities.Utility;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.*;

public class TestCasePOSTComplex extends DriverScript{

			@Test
			public void test_01(){
						isTestCaseRunnable("TC012");
						
						Info info = new Info();
						
						info.setEmail(xls.getCellData("Test Cases", "Email", rowNum));
						info.setPhone(xls.getCellData("Test Cases", "Phone", rowNum));
						info.setAddress(xls.getCellData("Test Cases", "Address", rowNum));

						_Posts post = new _Posts();
						
						post.setId(xls.getCellData("Test Cases", "Id", rowNum));
						post.setTitle(xls.getCellData("Test Cases", "Title", rowNum));
						post.setAuthor(xls.getCellData("Test Cases", "Author", rowNum));
						post.setInfo(info);
					    
						
						Response response = given().
																	body(post).
																	when().
																	contentType(ContentType.JSON).
																	post(xls.getCellData("Test Cases", "Url", rowNum));
						
						System.out.println("Complex POST response sent is : "+response.asString());
						ReportEvent.testStepReport(testCaseName, "pass", "Complex POST response sent is : "+response.asString());
				
			}
	
	
}
