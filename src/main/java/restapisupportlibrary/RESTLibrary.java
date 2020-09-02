package restapisupportlibrary;

import java.util.LinkedHashMap;
import java.util.Map;

import com.api.support.Xls_Reader;

public class RESTLibrary {

			public static Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir")+"\\src\\main\\java\\com\\test\\data\\TestDataSheet.xlsx");

					public static void dataDictionary(String startColHeader, String endColHeader, String testCaseId){
					
					String valueOfCorrespondingKey = null;
					LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
					LinkedHashMap<String,Integer> columnIndex = new LinkedHashMap<String, Integer>();
					String[] arrayColumnHeader = {startColHeader,endColHeader};
					for(int colIndex = 0;colIndex<xls.getColumnCount("Test Cases");colIndex++){
						for(int i=0;i<arrayColumnHeader.length;i++){
								if(xls.getCellData("Test Cases", colIndex, 1).equalsIgnoreCase(arrayColumnHeader[i])){
										columnIndex.put(xls.getCellData("Test Cases", colIndex, 1),colIndex+1);
								}
						}
						
			}
			
			int startColIndex = Integer.parseInt(columnIndex.values().toArray()[0].toString());
			int endColIndex = Integer.parseInt(columnIndex.values().toArray()[1].toString());
			
			//	 System.out.println("**************************************************************************************");
			 	
			 for(int colNum=startColIndex-1;colNum<endColIndex;colNum++){
				 		dataSet.put(xls.getCellData("Test Cases", colNum, 1), xls.getCellData("Test Cases", colNum, xls.getCellRowNum("Test Cases", "TCID", testCaseId)));
			 }
				 
			for(Map. Entry index:dataSet.entrySet()){  
						//	System.out.println("Column Header : "+index.getKey()+"-------Corresponding Value : "+index.getValue());
							//driver.findElement(By.xpath("//input[@name='"+index.getKey().toString()+"']")).sendKeys(index.getValue().toString());
							valueOfCorrespondingKey = index.getValue().toString();
							//break;
						
			}
			//return valueOfCorrespondingKey;
		}	
				
	}