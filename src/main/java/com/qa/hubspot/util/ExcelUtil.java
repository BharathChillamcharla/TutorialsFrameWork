package com.qa.hubspot.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	
	
	
	public static String TEST_DATA_PATH="./src/main/java/com/qa/hubspot/testdata/TutorialsNinjaTestData.xlsx";
	public static Workbook book;
	public static  Sheet sheet;
	
	
	public static Object[][] getData(String sheetName)
	{
		
		Object data[][]=null;
		try {
			
			FileInputStream fis = new FileInputStream(TEST_DATA_PATH);
			book=WorkbookFactory.create(fis);
			sheet=book.getSheet(sheetName);
			int rows=sheet.getLastRowNum();
			int columns=sheet.getRow(0).getLastCellNum();
			data=new Object[rows][columns];
			
			for(int i=0;i<rows;i++)
			{
				for(int j=0;j<columns;j++)
				{
					data[i][j]=sheet.getRow(i+1).getCell(j).toString();
				}
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return data;
		
	}
	
	
	

}
