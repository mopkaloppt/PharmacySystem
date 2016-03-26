package com.pharmacysystem.db.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class InputHelper {
	
	// Get string input 
	public static String getInput(String prompt) {
		
		BufferedReader stdin = new BufferedReader(
				new InputStreamReader(System.in));
		
		System.out.println(prompt);
		System.out.flush();
		
		try {
			return stdin.readLine();
		} catch (Exception e) {
			return "Error: " + e.getMessage();	
		}
	}
	
	// Get drug price 
	public static double getPrice(String prompt) throws NumberFormatException {
		
		String input = getInput(prompt);
		return Double.parseDouble(input);
	}

}
