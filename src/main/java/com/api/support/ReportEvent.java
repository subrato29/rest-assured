package com.api.support;

import java.io.IOException;

import javax.swing.plaf.synth.SynthSpinnerUI;

import com.api.base.DriverScript;
import com.api.reports.ExtentManager;
import com.api.utilities.SendMail;
import com.api.utilities.Utility;
import com.api.utilities.Zip;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ReportEvent extends SendMail{
			
	 static ExtentReports report = ExtentManager.getInstance();
	 static ExtentTest test;
	 static int countOfCallingEndTest = 0;
	 static int countOfIntializingTest = 0;
	
				public static String reportStepFailed(String comment){
							try{
										String path =  Utility.screeshotCapture();
										String failedComment = "<font color='red' face='Cambria'><b>" + comment +"</b></font>";
										String failedStep = "<a href="+path+">"+failedComment+"</a>";										
										return failedStep;
								
							}catch(Throwable t){
										System.out.println("Exception for reportStepFailed is : "+t.getMessage());
										return null;
							}
							
				}
				
				
				public static String reportStepPassed(String comment){
					try{
								String passedStep = "<font color='black' face='Cambria'><b>" + comment +"</b></font>";								
								return passedStep;
						
					}catch(Throwable t){
								System.out.println("Exception for reportStepPassed is : "+t.getMessage());
								return null;
					}

		}
				
				public static String reportStepInfo(String comment){
					try{
								String passedStep = "<font color='blue' face='Cambria'><i>" + comment +"</i></font>";								
								return passedStep;
						
					}catch(Throwable t){
								System.out.println("Exception for reportStepInfo is : "+t.getMessage());
								return null;
					}
								
		}
				
				public static String reportStepWarning(String comment){
					try{
								String warningStep = "<font color='orange' face='Cambria'><b>" + comment +"</b></font>";								
								return warningStep;
						
					}catch(Throwable t){
								System.out.println("Exception for reportStepWarning is : "+t.getMessage());
								return null;
					}
						
					
			}
				
				public static String reportStepSkip(String comment){
					try{
								String skippedStep = "<font color='sky blue' face='Cambria'><b>" + comment +"</b></font>";								
								return skippedStep;
						
					}catch(Throwable t){
								System.out.println("Exception for reportStepSkip is : "+t.getMessage());
								return null;
					}
						
					
				}
				
			public static void testStepReport(String testName, String testStepExecutionStatus,String comment)	{
							
							if(test==null){
								test = report.startTest(testName);
							}
		
							try{
								
										switch(testStepExecutionStatus.toUpperCase()){
										
										case "PASS" :
											test.log(LogStatus.PASS, reportStepPassed(comment));
											break;
												
										case "FAIL" :
											test.log(LogStatus.FAIL, reportStepFailed(comment));
											break;
											
										case "WARNING" :
											test.log(LogStatus.WARNING, reportStepWarning(comment));
											break;
											
										case "SKIP"	:
											test.log(LogStatus.SKIP, reportStepSkip(comment));
											break;
										
										case "INFO" :
											test.log(LogStatus.INFO, reportStepInfo(comment));
											if(comment.equalsIgnoreCase("Ending the test")){
														test = null;
														countOfCallingEndTest++;
											}
											break;
											
										case "START" :
											test=null;
										}
										
											
							}finally{
										if(report!=null){
											report.endTest(test);
							        		report.flush();
										}
										
										if(countOfCallingEndTest==DriverScript.countOfExecutableTestCases){
													try {
																boolean fnStatus = Utility.openHTMLReport();
																if(fnStatus==true){
																		System.out.println("HTML Report is opened");
																		Utility.fnLogging("HTML Report is opened");
																}else{
																		System.out.println("HTML Report is NOT opened");
																		Utility.fnLogging("HTML Report is NOT opened");
																}
																Zip.zipFile();
									// *************************************** Send Mail *******************************************				
																if(Utility.fnReadPropFile("SendMail").equalsIgnoreCase("True")){	
																			try {
																					SendMail.attachReportsToEmail();
																			} catch (Throwable t) {
																					Utility.fnLogging("Exception of Sending Mail : "+t.getMessage());
																			}
																} 
									// *************************************** Send Mail *******************************************							
													} catch (IOException e) {
																e.printStackTrace();
													}
										}
										
							}
					
				
			}
				
				
				
}
