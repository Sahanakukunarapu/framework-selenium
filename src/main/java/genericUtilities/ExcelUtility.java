package genericUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
/**
 * this class contains all reusable methods to read and write the data from/to excel
 * @author vanik
 */
public class ExcelUtility {

	private Workbook wb;
	private Sheet sh;
	DataFormatter df;
	/**
	 * 
	 * @author vanik
	 * @param excelPath
	 * @param sheetName
	 */
	
	public void excelInit(String excelPath, String sheetName) {
		FileInputStream fis=null;
		try {
		       fis=new FileInputStream(excelPath);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
			 try {
				wb = WorkbookFactory.create(fis);
			} catch (EncryptedDocumentException | IOException e) {
				
				e.printStackTrace();
			}
			
		
		sh=wb.getSheet(sheetName);
		df =new DataFormatter();

	}
	public Map<String, String> readFromExcel(String expectedTestName) {
		Map<String,String> map=new HashMap<String,String>();
		
		for(int i=0;i<=sh.getLastRowNum();i++) {
			if(df.formatCellValue(sh.getRow(i).getCell(1)).equals(expectedTestName)) {
				for(int j=i;j<=sh.getLastRowNum();j++) {
					map.put(df.formatCellValue(sh.getRow(j).getCell(2)),df.formatCellValue(sh.getRow(j).getCell(3)));
					if(df.formatCellValue(sh.getRow(j).getCell(2)).equals("####"))
						break;
				}
				break;
			}
		}
	return map;	
	}
	public void  updateTestStatus(String excelPath, String status, String expectedTestName) {
		for(int i=0;i<=sh.getLastRowNum();i++) {
			if(df.formatCellValue(sh.getRow(i).getCell(1)).equals(expectedTestName)) {
			Cell c=sh.getRow(i).createCell(4);
				c.setCellValue(status);
				break;
				
			}
		}
		FileOutputStream fos=null;
		try {
			fos=new FileOutputStream(excelPath);
		} catch (FileNotFoundException e) {
						e.printStackTrace();
		}
		try {
			wb.write(fos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	/**
	 * This method is used to close excel
	 * @author vanik
	 */
	public void closeExcel() {
		try {
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
