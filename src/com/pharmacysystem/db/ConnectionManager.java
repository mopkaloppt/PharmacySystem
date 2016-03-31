package com.pharmacysystem.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.pharmacysystem.db.util.InputHelper;

public class ConnectionManager {
	
	private static ConnectionManager instance = null;
	
	private final String CONN_STRING = 
			"jdbc:oracle:thin:@localhost:1522:ug";
	private final String USERNAME =
			InputHelper.getInput("Enter your oracle username: ");
	private final String PASSWORD = 
			InputHelper.getInput("Enter your oracle password: ");
	


	// user is allowed 3 login attempts
	//private static int attemptsAllowed = 4;
	
	
	private Connection conn = null; 
	
	// A private constructor that can only be constructed within this class
	private ConnectionManager() {
		
	}
	

	public static ConnectionManager getInstance() {
		if (instance == null) {
			instance = new ConnectionManager();
		}
		return instance;
	}
	
	private boolean openConnection() {
			
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
			e.printStackTrace();
			return false;
		}
	}
	
	public Connection getConnection()  {
		
		if (conn == null) {
			if (openConnection()) {
				return conn;
			}
			else {
				//checkLogInAttempts();
				return null;
			}
		}
		return conn;
	}
	
	public void closeConnection() {
		System.out.println("Closing Connection");
		try {
			conn.close();
			conn = null;
		}
		catch (Exception e) {
			
		}		
	}
	
	/*
	 * Security check for Log-in attempts 
	 */ 
	
//	public void checkLogInAttempts()  {
//		
//		attemptsAllowed--;
//
//		System.out.println(" You have " + attemptsAllowed + " more attempts");
//
//		if (attemptsAllowed == 0) {
//			System.exit(-1);
//		}	
//		
//	}
	

}
