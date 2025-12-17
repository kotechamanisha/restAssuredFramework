package com.qa.api.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class CSVReaderUtil {
	
	 private static final String CSV_PATH =   "src/test/resources/testData/";
	 
	 private static  List<String[]> rows;
	
	public  static Object[][] CSVTestingData(String filename) {
		String file = CSV_PATH+filename;
			
				
		CSVReader reader;
		try {
			reader = new CSVReader(new FileReader(file));
			rows = reader.readAll();
			System.out.println("rows size is  "+ rows.size());
			reader.close();
			
		} 
		catch(FileNotFoundException e){	
			e.printStackTrace();
		}catch (IOException | CsvException e) {
			e.printStackTrace();
		}
		
		System.out.println("rows size is  "+ rows.size());
		Object[][] data = new Object[rows.size()][];
		for(int i =0;i< rows.size();i++) {
				data[i]=rows.get(i);
			System.out.println("data of "+ Arrays.toString(rows.get(i)));
		}
		return data;
	}

	

}
