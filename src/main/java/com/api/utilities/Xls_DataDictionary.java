package com.api.utilities;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import com.api.support.Xls_Reader;

public class Xls_DataDictionary {

			public static Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir")+"\\src\\main\\java\\com\\test\\data\\TestDataSheet.xlsx");
			
			static LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
		//	static HashMap<Integer, String> columnIndex = new HashMap<Integer, String>();
			static LinkedHashMap<String,Integer> columnIndex = new LinkedHashMap<String, Integer>();
			static int colIndex =0;
			static String[] arrayColumnHeader = {"TestCaseName","APIResponseCode"};
			
			public static void main(String[] args) {
								
				//			System.out.println(xls.getCellRowNum("Test Cases", "TestCaseName", "Correy_POC"));
				//			System.out.println(""+xls.getColumnCount("Test Cases"));
							for(int colIndex = 0;colIndex<xls.getColumnCount("Test Cases");colIndex++){
										for(int i=0;i<arrayColumnHeader.length;i++){
												if(xls.getCellData("Test Cases", colIndex, 1).equalsIgnoreCase(arrayColumnHeader[i])){
														columnIndex.put(xls.getCellData("Test Cases", colIndex, 1),colIndex+1);
												}
										}
										
							}
							/*System.out.println(columnIndex.values().toArray()[0]);
							System.out.println(columnIndex.values().toArray()[1]);
							
							System.out.println(Integer.parseInt(columnIndex.values().toArray()[1].toString())-Integer.parseInt(columnIndex.values().toArray()[0].toString()));
				*/
							int startColIndex = Integer.parseInt(columnIndex.values().toArray()[0].toString());
							int endColIndex = Integer.parseInt(columnIndex.values().toArray()[1].toString());
							
							 /*for(Map.Entry index:columnIndex.entrySet()){  
								   				System.out.println("Column Index : "+index.getKey()+" ------- Column Value : "+index.getValue());
							 } */
							
							 System.out.println("**************************************************************************************");
							 
							/*while(colIndex<xls.getColumnCount("Test Cases")){
				//		for(colIndex = 0;colIndex<xls.getColumnCount("Test Cases");colIndex++){
									dataSet.put(xls.getCellData("Test Cases", colIndex, 1), xls.getCellData("Test Cases", colIndex, xls.getCellRowNum("Test Cases", "TCID", "TC001")));
									colIndex++;
							}*/
								
							 for(int colNum=startColIndex-1;colNum<endColIndex;colNum++){
								 		dataSet.put(xls.getCellData("Test Cases", colNum, 1), xls.getCellData("Test Cases", colNum, xls.getCellRowNum("Test Cases", "TCID", "TC002")));
							 }
								 
							for(Map. Entry index:dataSet.entrySet()){  
				   						System.out.println("Column Header : "+index.getKey()+"-------Corresponding Value : "+index.getValue());  
							}
						
			}		
}
