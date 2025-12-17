package com.qa.api.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class twoDimentionalArray {

	public static void main(String[] args) {
		String[][] twoDarray = {
				{"manisha","java","active"},
				{"rohit","AI","active"},
				{"Swara","LLM","active"},
				{"Dinesh","java","inactive"}
		};
		
		for(String[] e :twoDarray ) {
			for(String s : e ) {
				System.out.println(s);
			}
			// System.out.println(Arrays.toString(e));			
		}
		
		
		
//		String[] mylist = {"apple","banana","orange"};
//		List<String> twoDarray = new ArrayList<String>(Arrays.asList(mylist));
//		twoDarray.add("listA");
//		twoDarray.add("listB");
//		twoDarray.add("listC");
//		twoDarray.add("listD");
//		twoDarray.add("listE");
//	//	twoDarray = Arrays.asList(mylist);
//
//		
//		for(String e :twoDarray ) {
//			System.out.println(e);			
//		}

	}

}
