package com.pharmacysystem.db;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;

import com.pharmacysystem.db.util.InputHelper;


public class Main {
	private static final String CONN_STRING = "jdbc:oracle:thin:@localhost:1521:ug";

	// user is allowed 3 login attempts
	private static int loginAttempts = 0;


	public static boolean connect(String username, String password) {
	
		try ( 	
				Connection conn = DriverManager.getConnection(CONN_STRING, username, password);
				//				Statement stmt 	= conn.createStatement(
				//						ResultSet.TYPE_SCROLL_INSENSITIVE, 
				//						ResultSet.CONCUR_READ_ONLY);
				) {

			System.out.println("\nConnected to Oracle!");	
			return true;
		} 
		catch (SQLException e) {

			System.err.println(e);
			e.printStackTrace();
		}
		return false; 

	}

	public static void login (String user_type) {

		String username = null;
		String password =  null;

		switch (user_type) {

		case "doctor":
			System.out.println("Welcome Doctor!");

			username = InputHelper.getInput("Please enter you Oracle username: ");
			password = InputHelper.getInput("Oracle password: ");

			if (!connect(username, password)) {
				loginAttempts++;	

				if (loginAttempts >= 3) {
					System.exit(-1);
				}
				else {		
					connect(username, password);			
				}
			}
			else connect(username, password);


		case "patient":
			System.out.println("Welcome to your Medical Profile!");

			username = InputHelper.getInput("Please enter you Oracle username: ");
			password = InputHelper.getInput("Oracle password: ");  

			if (!connect(username, password)) {
				loginAttempts++;	

				if (loginAttempts >= 3) {
					System.exit(-1);
				}
				else {		
					connect(username, password);			
				}
			}
			else connect(username, password);


		case "pharmacist":
			System.out.println("Welcome to Pharmacy System Management!");

			username = InputHelper.getInput("Please enter you Oracle username: ");
			password = InputHelper.getInput("Oracle password: ");

			if (!connect(username, password)) {
				loginAttempts++;	

				if (loginAttempts >= 3) {
					System.exit(-1);
				}
				else {		
					connect(username, password);			
				}
			}
			else connect(username, password);


		case "quit": 
			System.out.println("Bye!");
			System.exit(0);


		default: 
			System.out.println("Invalid user type: " + user_type);
			System.out.println(" Try again");
			start();

		}	

	}

	public static void start() {
		System.out.println("Please specify yourself as one of the following: doctor, pharmacist or patient");
		System.out.println("Or type 'quit', if you wish to exit");
		login(InputHelper.getInput("I am a: "));

	}

	public static void main(String[] args) throws SQLException{	
		start();
	}

}
