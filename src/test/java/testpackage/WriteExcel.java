package testpackage;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcel {

	public static void writeXL(String fPath, String fSheet, String[][] xData) throws Exception{
	    File outFile = new File(fPath);
	    @SuppressWarnings("resource")
		XSSFWorkbook wb = new XSSFWorkbook();
	    XSSFSheet osheet = wb.createSheet(fSheet);
	    int xR_TS = xData.length;
	    int xC_TS = xData[0].length;
	    for (int myrow = 0; myrow < xR_TS; myrow++) {
	        XSSFRow row = osheet.createRow(myrow);
	        for (int mycol = 0; mycol < xC_TS; mycol++) {
	            XSSFCell cell = row.createCell(mycol);
	           // cell.setCellType(XSSFCell.CELL_TYPE_STRING);
	            cell.setCellValue(xData[myrow][mycol]);
	        }
	        FileOutputStream fOut = new FileOutputStream(outFile);
	        wb.write(fOut);
	        fOut.flush();
	        fOut.close();
	    }
	   }
	
}
