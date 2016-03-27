package com.pharmacysystem.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.pharmacysystem.db.tables.Doctor;
import com.pharmacysystem.db.tables.Patient;
import com.pharmacysystem.db.tables.Pharmacist;
import com.pharmacysystem.db.util.InputHelper;


public class Main {
	private static final String CONN_STRING = "jdbc:oracle:thin:@localhost:1522:ug";
	public static Connection conn;
	// number of attempts is BUGGY!!!
	// user is allowed 3 login attempts
	private static int loginAttempts = 0;
	private static int attemptsAllowed = 3;



	public boolean connect(String username, String password) throws SQLException {

		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

		try {
			conn = DriverManager.getConnection(CONN_STRING, username, password);

			System.out.println("Connected to Oracle!");	
			return true;
		} 
		catch (SQLException e) {

			System.err.println(e);
			e.printStackTrace();
		}
		return false; 

	}

	public void login (String user_type) throws SQLException {

		String username = null;
		String password =  null;

		switch (user_type) {

		case "doctor":
			System.out.println("Welcome Doctor!");

			username = InputHelper.getInput("Please enter your Oracle username: ");
			password = InputHelper.getInput("Oracle password: ");

			if (connect(username, password)) {
				// docotr is only allowed access for certain tables 
				// Action: create, update, delete, display on
				// Tables: doctor, patient, prescribed, display on drug
				Doctor doctor = new Doctor();
				doctor.displayDoctor();
				doctor.insertDoctor();
				doctor.deleteDoctor();
				doctor.updateDoctor();

			}
			else {
				loginAttempts++;
				attemptsAllowed = attemptsAllowed - loginAttempts;

				System.out.println(" You have " + attemptsAllowed + " more attempts");

				if (loginAttempts >= 3) {
					System.exit(-1);
				}
				else {		
					start();						
				}							
			}

			break;


		case "patient":
			System.out.println("Welcome to your Medical Profile!");

			username = InputHelper.getInput("Please enter your Oracle username: ");
			password = InputHelper.getInput("Oracle password: ");  

			if (connect(username, password)) {
				// patient is only allowed access for certain tables 
				// Action: display on
				// Tables: prescribed, purchased, pharmacy, doctor
				Patient patient = new Patient();
				patient.displayPatient();	
				patient.insertPatient();
				patient.deletePatient();
				patient.updatePatient();

			}

			break;


		case "pharmacist":
			System.out.println("Welcome to Pharmacy System Management!");

			username = InputHelper.getInput("Please enter your Oracle username: ");
			password = InputHelper.getInput("Oracle password: ");

			if (connect(username, password)) {

				// pharmacist is only allowed access for certain tables 
				// Actions: create, update, delete, display on
				// Tables: pharmacy, pharmacist, inventory, drug, prescribed, purchased
				Pharmacist pharmacist = new Pharmacist();
				pharmacist.displayPharmacist();
				pharmacist.insertPharmacist();
				pharmacist.deletePharmacist();
				pharmacist.updatePharmacist();

			}
			else {
				loginAttempts++;
				attemptsAllowed = attemptsAllowed - loginAttempts;

				System.out.println(" You have " + attemptsAllowed + " more attempts");

				if (loginAttempts >= 3) {
					System.exit(-1);
				}
				else {		
					start();						
				}
			}

			break;


		case "quit": 
			System.out.println("Bye!");
			System.exit(0);
			break;


		default: 
			System.out.println("Invalid user type: " + user_type);
			System.out.println(" Try again");
			start();
			break;
		}	

	}

	public void start() throws SQLException {
		System.out.println("Please specify yourself as one of the following: doctor, pharmacist or patient");
		System.out.println("Or type 'quit', if you wish to exit");
		login(InputHelper.getInput("I am a: "));

	}

	public static Connection getConn() {
		return conn;
	}

	public static void main(String[] args) throws SQLException{	
		Main database = new Main();
		database.start();
	}

}
