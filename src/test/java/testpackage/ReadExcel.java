package testpackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	public static String [][] readXL(String fpath,String fsheet) throws IOException
	{
		 String[][] xData;  
	     int xRows, xCols;
	     File myxl = new File(fpath);                                
	     FileInputStream myStream = new FileInputStream(myxl);
	     @SuppressWarnings("resource")
		XSSFWorkbook myWB = new XSSFWorkbook(myStream);                                
	     XSSFSheet mySheet = myWB.getSheet(fsheet);  
	     xRows = mySheet.getLastRowNum()+1;                                
	     xCols = mySheet.getRow(0).getLastCellNum();   
	     xData = new String[xRows][xCols];        
	   System.out.println("Total Rows in Excel are " + xRows);
	   System.out.println("Total Cols in Excel are " + xCols);
	     for (int i = 0; i < xRows; i++) {                           
	             XSSFRow row = mySheet.getRow(i);
	             for (int j = 0; j < xCols; j++) {                               
	                 XSSFCell cell = row.getCell(j);
	                 String value = "-";
	                 if (cell!=null){
	                     value = cellToString(cell);
	                 }
	                 xData[i][j] = value;      
	                 //System.out.print(value);
	                 //System.out.print("----");
	                 }        
	             //System.out.println("");
	             }    
	     myxl = null; // Memory gets released
	     return xData;
	}
	public static String cellToString(XSSFCell cell) { 
	    // This function will convert an object of type excel cell to a string value
	    //int type = cell.getCellType();
	    Object result;
	    DataFormatter datafor = new DataFormatter();
	    result = datafor.formatCellValue(cell);
	    return result.toString();
	    }	

}
