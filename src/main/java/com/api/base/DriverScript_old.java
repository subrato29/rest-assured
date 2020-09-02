package com.api.base;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import org.testng.Assert;
/*import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;*/
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


/*import com.HCL_SIC.accelerators.ActionEngine;*/


/*import com.HCL_SIC.support.WebDriverFactory;*/

import com.api.reports.ExtentManager;
import com.api.support.ReportEvent;
import com.api.support.Xls_Reader;
import com.api.utilities.Constants;
import com.api.utilities.Utility;


//import junit.framework.Assert;

public class DriverScript_old{
		
	public static Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir")+"\\src\\main\\java\\com\\test\\data\\TestDataSheet.xlsx");
	
	/*public static WebDriver webDdriver=null;
	public static EventFiringWebDriver driver= null;*/
	public static int rowNum=2;
	public static int rowNumTemp;
	public static int rowNumExecutableTC = 2;
	public static int count= 0;
	public static String testCaseName;
	public static String testCaseId;
	public static SoftAssert softAssert;
	public static int errorCount=0;
	
	static TreeMap<Integer,String> executableTCIndex=new TreeMap<Integer,String>();

			public static int getRowNumForExecutableTestCases()
			{
				//System.out.println("total excel row count :" + xls.getRowCount(Constants.TEST_CASE_SHEET_NAME));
							while(rowNumExecutableTC<=xls.getRowCount(Constants.TEST_CASE_SHEET_NAME))
							{
								
								/*System.out.println("value in the cell " + rowNumExecutableTC  + " : " + xls.getCellData(Constants.TEST_CASE_SHEET_NAME, Constants.TEST_CASE_RUNMODE, rowNumExecutableTC));
								System.out.println("value in the constant Constants.TEST_CASE_RUNMODE_YES : " + Constants.TEST_CASE_RUNMODE_YES);*/
									if(xls.getCellData(Constants.TEST_CASE_SHEET_NAME, Constants.TEST_CASE_RUNMODE, rowNumExecutableTC).toUpperCase().equals(Constants.TEST_CASE_RUNMODE_YES))
									{
										count++;
									}
									rowNumExecutableTC++;
							}
						rowNumExecutableTC=2;			
						return count;
			}
			
	
			
			
			public static boolean isTestCaseRunnable(String tcId){
				boolean isExecutable=false;
				rowNum = xls.getCellRowNum(Constants.TEST_CASE_SHEET_NAME, Constants.TEST_CASE_ID, tcId);
				testCaseName = xls.getCellData(Constants.TEST_CASE_SHEET_NAME, Constants.TEST_CASE_NAME, rowNum);
						if(xls.getCellData(Constants.TEST_CASE_SHEET_NAME, Constants.TEST_CASE_RUNMODE, rowNum).equalsIgnoreCase(Constants.TEST_CASE_RUNMODE_YES))
						{
									ReportEvent.testStepReport(testCaseName, "Info", "Starting the test");
								
											if(xls.getCellData(Constants.TEST_CASE_SHEET_NAME, Constants.AUT_TO_START, rowNum).equalsIgnoreCase(Constants.AUT_TO_START_YES))
											{
												/*driver=null;
								    				if(driver==null)*/{
										    				try {
										    						/*	webDdriver = WebDriverFactory.openBrowser(webDdriver);
										    							driver = new EventFiringWebDriver(webDdriver);
										    							driver.manage().deleteAllCookies();
										    							driver.manage().timeouts().implicitlyWait(Integer.parseInt(Utility.fnReadPropFile("ImplicitWait").toString()), TimeUnit.SECONDS);
										    						//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
										    							if(Utility.fnReadPropFile("ScreenMaximize").toUpperCase().equals("Y")){
										    										driver.manage().window().maximize();
										    							}
										    							driver.navigate().to(Utility.fnReadPropFile("URL"));*/
										    							System.out.println("Application is opening");
										    							Utility.fnLogging("Application is opening");
										    							
										    				} catch (Throwable t) {
										    							System.out.println("Exception for AUT to start : "+t.getMessage());
										    							Utility.fnLogging("Exception for AUT to start : "+t.getMessage());
										    				}
								    				}
							    			}else{
							    							System.out.println("Please make sure 'AUT_To_Start' flag to 'Y' in your datasheet, if you want to start the application");
							    							Utility.fnLogging("Please make sure 'AUT_To_Start' flag to 'Y' in your datasheet, if you want to start the application");
							    			}
									isExecutable= true;	
						}else{
									isExecutable= false;
									System.out.println("---------The Test Case id is either invalid or Runmode of it is not 'Y' ---------"+tcId);
									Assert.fail("---------The Test Case id is either invalid or Runmode of it is not 'Y' ---------"+tcId);
						}
				
				return isExecutable;
				
			}

	
		public static final int countOfExecutableTestCases = getRowNumForExecutableTestCases();

    @AfterClass
    		public void afterClass() 
    	{
    	
		    	if(xls.getCellData(Constants.TEST_CASE_SHEET_NAME, Constants.AUT_TO_CLOSE, rowNum).equalsIgnoreCase(Constants.AUT_TO_CLOSE_YES))
		    	{
					//driver.quit();
		    	}
    			
		    	if(xls.getCellData(Constants.TEST_CASE_SHEET_NAME, Constants.TEST_CASE_RUNMODE, rowNum).equalsIgnoreCase(Constants.TEST_CASE_RUNMODE_YES)){
		    				ReportEvent.testStepReport(testCaseName, "Info", "Ending the test");
		    	}
		    	
		    	if(errorCount!=0)
		    	{
		    					System.out.println("Count of total error count after all test script run : "+errorCount);
		    					Utility.fnLogging("Count of total error count after all test script run : "+errorCount);
	    						//softAssert.assertAll();
	    						Utility.fnLogging("Value of errorCount is "+errorCount);
	    		}
		    	
		    	/*if(xls.getCellData(Constants.TEST_CASE_SHEET_NAME, Constants.AUT_TO_CLOSE, rowNum).equalsIgnoreCase(Constants.AUT_TO_CLOSE_YES)){
		    			driver.quit();
		    	}*/
		    	

    	}


	}
