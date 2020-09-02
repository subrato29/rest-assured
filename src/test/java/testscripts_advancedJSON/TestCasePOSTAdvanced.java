package testscripts_advancedJSON;

import org.testng.annotations.Test;

import postsapiobjectrepository.Info;
import postsapiobjectrepository.InfoAdvanced;
import postsapiobjectrepository.Posts;
import postsapiobjectrepository.PostsAdvanced;
import postsapiobjectrepository._Posts;

import com.api.base.DriverScript;
import com.api.support.ReportEvent;
import com.api.utilities.Utility;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.*;

public class TestCasePOSTAdvanced extends DriverScript{

			@Test
			public void test_01(){
						isTestCaseRunnable("TC022");
						
						InfoAdvanced infoAdvanced1 = new InfoAdvanced();
						
						infoAdvanced1.setEmail(xls.getCellData("Test Cases", "Email", rowNum));
						infoAdvanced1.setPhone(xls.getCellData("Test Cases", "Phone", rowNum));
						infoAdvanced1.setAddress(xls.getCellData("Test Cases", "Address", rowNum));
						
						InfoAdvanced infoAdvanced2 = new InfoAdvanced();
						
						infoAdvanced2.setEmail(xls.getCellData("Test Cases", "Email_1", rowNum));
						infoAdvanced2.setPhone(xls.getCellData("Test Cases", "Phone_1", rowNum));
						infoAdvanced2.setAddress(xls.getCellData("Test Cases", "Address_1", rowNum));

						PostsAdvanced postAdvanced = new PostsAdvanced();
						
						postAdvanced.setId(xls.getCellData("Test Cases", "Id", rowNum));
						postAdvanced.setTitle(xls.getCellData("Test Cases", "Title", rowNum));
						postAdvanced.setAuthor(xls.getCellData("Test Cases", "Author", rowNum));
						postAdvanced.setInfoAdvanced(new InfoAdvanced[]{infoAdvanced1, infoAdvanced2});
					    
						
						Response response = given().
																	body(postAdvanced).
																	when().
																	contentType(ContentType.JSON).
																	post(xls.getCellData("Test Cases", "Url", rowNum));
						
						System.out.println("Advanced POST response sent is : "+response.asString());
						ReportEvent.testStepReport(testCaseName, "pass", "Advanced POST response sent is : "+response.asString());
				
			}
	
  }
