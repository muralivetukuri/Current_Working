package com.prokarma.qa.tests;

import java.util.ArrayList;

public class Normalisation_Comparision {

	public static void main(String[] args) {
		
		ArrayList<ArrayList<String>> before_List = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> after_List = new ArrayList<ArrayList<String>>();
		
		ArrayList<String> temp_List = new ArrayList<String>();
		
		temp_List.add("9765031000Cornelius012019");
		temp_List.add("01/08/2019-02/06/2019");
		temp_List.add("30");
		temp_List.add("$371.71(USD)");
		temp_List.add("$371.71(USD)");
		temp_List.add("$0.00(USD)");
		temp_List.add("$0.00(USD)");
		temp_List.add("ELECTRIC");
		temp_List.add("9765031000");
		temp_List.add("Asset-23779196IN");
		temp_List.add("Cornelius");
		
		before_List.add(temp_List);
		temp_List = new ArrayList<String>();
		
		temp_List.add("1774500000Cornelius012019");
		temp_List.add("01/08/2019-02/06/2019");
		temp_List.add("30");
		temp_List.add("$2,369.15(USD)");
		temp_List.add("$2,369.15(USD)");
		temp_List.add("$0.00(USD)");
		temp_List.add("$0.00(USD)");
		temp_List.add("ELECTRIC");
		temp_List.add("1774500000");
		temp_List.add("Asset-09912193AB");
		temp_List.add("Cornelius");	
		
		before_List.add(temp_List);
		temp_List = new ArrayList<String>();
		
		temp_List.add("9765031000Cornelius122018");
		temp_List.add("12/06/2018-01/08/2019");
		temp_List.add("34");
		temp_List.add("$760.44(USD)");
		temp_List.add("$360.35(USD)");
		temp_List.add("$392.46(USD)");
		temp_List.add("$7.63(USD)");
		temp_List.add("ELECTRIC");
		temp_List.add("9765031000");
		temp_List.add("Asset-23779196IN");
		temp_List.add("Cornelius");
		
		after_List.add(temp_List);
		temp_List = new ArrayList<String>();
		
		temp_List.add("1774500000Cornelius122018");
		temp_List.add("12/06/2018-01/08/2019");
		temp_List.add("34");
		temp_List.add("$2,459.69(USD)");
		temp_List.add("$2,459.69(USD)");
		temp_List.add("$0.00(USD)");
		temp_List.add("$0.00(USD)");
		temp_List.add("ELECTRIC");
		temp_List.add("1774500000");
		temp_List.add("Asset-09912193AB");
		temp_List.add("Cornelius");	
		
		after_List.add(temp_List);
		temp_List = new ArrayList<String>();
		
		System.out.println(before_List);
		System.out.println(after_List);
	}

}
