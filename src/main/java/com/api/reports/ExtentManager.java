package com.api.reports;


//http://relevantcodes.com/Tools/ExtentReports2/javadoc/index.html?com/relevantcodes/extentreports/ExtentReports.html


import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.api.utilities.Utility;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	private static ExtentReports extent;
	public static String dynamicHtmlReportPath;
	public static ExtentReports getInstance() {
		if (extent == null) {
			
			//************************************************************************************************************
			/*
			Calendar cal = Calendar.getInstance();
	        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	        String strDate = sdf.format(cal.getTime());
	        
			SimpleDateFormat sdf1 = new SimpleDateFormat();
			sdf1.applyPattern("MM/dd/yyyy HH:mm:ss");
	        Date date = null;
			try {
				date = sdf1.parse(strDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
	        String strDateNow=sdf1.format(date);
			String fileName = strDateNow.toString().replace("/", "_").replace(":", "_").replace(" ", "_")+".html";

			File dir = new File(System.getProperty("user.dir")+"\\Results\\TestResult_"+strDateNow.toString().replace("/", "_").replace(":", "_").replace(" ", "_"));
	        dir.mkdir();
	        extent = new ExtentReports(System.getProperty("user.dir")+"\\Results\\TestResult_"+strDateNow.toString().replace("/", "_").replace(":", "_").replace(" ", "_")+"\\"+fileName);
	        */
			 dynamicHtmlReportPath= htmlReportPath();
	      //************************************************************************************************************
			extent.loadConfig(new File(System.getProperty("user.dir")+"\\ReportsConfig.xml"));
			// optional
			try {
				extent.addSystemInfo(
						"Environment", Utility.fnReadPropFile("test_environment")).addSystemInfo("Automation tools used",Utility.fnReadPropFile("automation_tool_used")).addSystemInfo("Nature of AUT",Utility.fnReadPropFile("nature_of_aut")).addSystemInfo("Name of the AUT", Utility.fnReadPropFile("name_of_aut")).addSystemInfo("Tested Browser", Utility.fnReadPropFile("browserType"));
			} catch (Throwable t) {
				// TODO Auto-generated catch block
					Utility.fnLogging("Exception for Extent Report : "+t.getMessage());
			}
			
		}
		return extent;
		
	}
	
	
	public static String htmlReportPath(){
				Calendar cal = Calendar.getInstance();
		        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		        String strDate = sdf.format(cal.getTime());
		        
				SimpleDateFormat sdf1 = new SimpleDateFormat();
				sdf1.applyPattern("MM/dd/yyyy HH:mm:ss");
		        Date date = null;
				try {
					date = sdf1.parse(strDate);
				} catch (ParseException e) {
					e.printStackTrace();
				}
		        String strDateNow=sdf1.format(date);
				String fileName = "HTMLReport_"+strDateNow.toString().replace("/", "_").replace(":", "_").replace(" ", "_")+".html";
		
				File dir = new File(System.getProperty("user.dir")+"\\Results\\TestResult_"+strDateNow.toString().replace("/", "_").replace(":", "_").replace(" ", "_"));
		        dir.mkdir();
		        extent = new ExtentReports(System.getProperty("user.dir")+"\\Results\\TestResult_"+strDateNow.toString().replace("/", "_").replace(":", "_").replace(" ", "_")+"\\"+fileName);
				return (System.getProperty("user.dir")+"\\Results\\TestResult_"+strDateNow.toString().replace("/", "_").replace(":", "_").replace(" ", "_")+"\\"+fileName);
	}
	
	
}
