package com.qa.api.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {
	
	public static Object[][] readExcelData(String filename) {
		
		Object data[][]=null;
		String filenameWithPath = "src/test/resources/testData/"+filename;
		try {
			FileInputStream file = new FileInputStream(filenameWithPath);
			Workbook book = WorkbookFactory.create(file);
			Sheet sheet = book.getSheet("GoRest");
			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			for(int row=0; row< sheet.getLastRowNum();row++)
			{
				for(int col=0 ;col<sheet.getRow(0).getLastCellNum();col++)
				{
					data[row][col]= sheet.getRow(row + 1).getCell(col).toString();
				}
			}
								
		} catch (FileNotFoundException e) {
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
