package com.pharmacysystem.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.pharmacysystem.db.tables.Doctor;
import com.pharmacysystem.db.tables.Drug;
import com.pharmacysystem.db.tables.Inventory;
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
	Doctor doctor;
	Patient patient;
	Pharmacist pharmacist;


	/*
	 * Connect to OracleDriver
	 */ 
	public boolean connect(String username, String password) throws SQLException {

		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

		try {
			conn = DriverManager.getConnection(CONN_STRING, username, password);
			return true;
		} 
		catch (SQLException e) {
			System.err.println(e);
			e.printStackTrace();
		}
		return false; 
	}

	/*
	 * Login to the database
	 */ 	
	public void login (String user_type) throws SQLException, IOException {

		String username = null;
		String password = null;

		switch (user_type) {

		case "doctor":

			username = InputHelper.getInput("Please enter your Oracle username: ");
			password = InputHelper.getInput("Oracle password: ");

			if (connect(username, password)) {
				// doctor is only allowed access for certain tables 
				// Action: create, update, delete, display on
				// Tables: doctor, patient, prescribed, display on drug
				doctor = new Doctor();

				System.out.println("Welcome Doctor!");
				System.out.println("You can access the following: ");
				System.out.println("1 - Doctor");
				System.out.println("2 - Patient");
				System.out.println("3 - Drug");
				System.out.println("4 - Prescription");
				String doc_options = InputHelper.getInput("Enter the number or type 'quit' if you wish to exit: ");

				doctorOptions(doc_options);

			}
			else {
				checkLogInAttempts();				
			}
			break;

		case "patient":

			username = InputHelper.getInput("Please enter your Oracle username: ");
			password = InputHelper.getInput("Oracle password: ");  

			if (connect(username, password)) {
				// patient is only allowed access for certain tables 
				// Action: display on
				// Tables: prescribed, purchased, pharmacy, doctor
				patient = new Patient();
				
				System.out.println("Welcome to your Medical Profile!");
				System.out.println("You can access the following: ");
				System.out.println("1 - Your Profile");
				System.out.println("2 - Prescription");
				System.out.println("3 - Purchase History");
				String pat_options =  InputHelper.getInput("Enter the number or type 'quit' if you wish to exit: ");

				patientOptions(pat_options);

			}
			else {
				checkLogInAttempts();				
			}
			break;

		case "pharmacist":

			username = InputHelper.getInput("Please enter your Oracle username: ");
			password = InputHelper.getInput("Oracle password: ");

			if (connect(username, password)) {

				// pharmacist is only allowed access for certain tables 
				// Actions: create, update, delete, display on
				// Tables: pharmacy, pharmacist, inventory, drug, prescribed, purchased
				pharmacist = new Pharmacist();
				
				System.out.println("Welcome to Pharmacy System Management!");
				System.out.println("You can access the following: ");
				System.out.println("1 - Pharmacy Info");
				System.out.println("2 - Pharmacist Database");
				System.out.println("3 - Inventory");
				System.out.println("4 - Drug Info");
				System.out.println("5 - Prescription");
				System.out.println("6 - Order");	
				String pharm_options = InputHelper.getInput("Enter the number or type 'quit' if you wish to exit: ");

				pharmacistOptions(pharm_options);						
			}	
			else {
				checkLogInAttempts();				
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

	/*
	 * Options for Doctor 
	 */ 
	public void doctorOptions(String doc_options) throws IOException, SQLException {
		/*	doc_options to access the following:
			1 - Doctor 
			2 - Patient
			3 - Drug
			4 - Prescription */

		switch (doc_options) {
		case "1": // Doctor
			System.out.println("What would you like to do?");
			System.out.println("1 - Display doctors");
			System.out.println("2 - Insert doctor");
			System.out.println("3 - Delete doctor");
			System.out.println("4 - Update doctor");
			String doc_subOptions = InputHelper.getInput("Enter the number or type 'quit' if you wish to exit: ");
			switch (doc_subOptions) {
			case "1":
				doctor.displayDoctor();
				break;
			case "2":
				doctor.insertDoctor();
				break;
			case "3":
				//doctor.deleteDoctor();
				break;
			case "4":
				//doctor.updateDoctor();
				break;									
			case "quit":
				System.out.println("Bye!");
				System.exit(0);
				break;
			}		
			break;


		case "2": // Patient
			System.out.println("What would you like to do?");
			System.out.println("1 - Display patients");
			System.out.println("2 - Insert patient");
			System.out.println("3 - Delete patient");
			System.out.println("4 - Update patient");
			doc_subOptions  = InputHelper.getInput("Enter the number or type 'quit' if you wish to exit: ");
			switch (doc_subOptions) {
			case "1":
				doctor.displayPatient();
				break;
			case "2":
				doctor.insertPatient();
				break;
			case "3":
				//doctor.deletePatient();
				break;
			case "4":
				//doctor.updatePatient();
				break;

			case "quit":
				System.out.println("Bye!");
				System.exit(0);
				break;
			}
			break;

		case "3": // Drug
			// About to work on this 
			//doctor.displayDrug();
			break;

		case "4": // Prescription
//			doctor.displayPrescribed();
//			doctor.insertPrescribed();
//			doctor.deletePrescribed();
//			doctor.updatePrescribed();												
			break;
			
		case "quit":
			System.out.println("Bye!");
			System.exit(0);
			break;
		}					
	}
	
	/*
	 * Options for Patient 
	 */ 
	public void patientOptions(String pat_options) throws IOException, SQLException {
		/*	pat_options to access the following:
		1 - Your Profile 
		2 - Prescription
		3 - Purchase History */

		switch (pat_options) {

		case "1": // Your Profile
			// can only see themselves !!!
			System.out.println("What would you like to do?");
			System.out.println("1 - Display your profile");
			System.out.println("2 - Update your profile");
			String pat_subOptions = InputHelper.getInput("Enter the number or type 'quit' if you wish to exit: ");
	
			switch (pat_subOptions) {
			case "1":
				// they can only see themselves .. 
				String first_name = InputHelper.getInput("Enter your first name: ");
				String last_name = InputHelper.getInput("Enter your last name: ");
				
				// ERROR: Invalid column index !!!!
				patient.displayProfile(first_name, last_name);
				break;
			case "2":
				//patient.updateProfile();
				break;
			case "quit":
				System.out.println("Bye!");
				System.exit(0);
				break;
			}		
			break;
			
		case "2": // Prescription
			//patient.displayPrescribed();
			break;
			
		case "3": // Purchase History
			System.out.println("What would you like to do?");
			System.out.println("1 - See your purchase history");
			System.out.println("2 - Delete your purchase history");
			pat_subOptions = InputHelper.getInput("Enter the number or type 'quit' if you wish to exit: ");
			
			switch (pat_subOptions) {
			case "1":
				//patient.displayPurchased();
				break;
			case "2":
				//patient.deletePurchased();
				break;
			case "quit":
				System.out.println("Bye!");
				System.exit(0);
				break;
			}				
			break;
			
		case "quit":
			System.out.println("Bye!");
			System.exit(0);
			break;
		}				
	}

	/*
	 * Options for Pharmacist 
	 */ 
	public void pharmacistOptions(String pharm_options) throws IOException, SQLException {
		/*	pharm_options to access the following:
		1 - Pharmacy Info 
		2 - Pharmacist Database
		3 - Inventory
		4 - Drug Info
		5 - Prescription
		6 - Order */
			
		switch (pharm_options) {

		case "1": // Pharmacy Info 	
			System.out.println("What would you like to do?");
			System.out.println("1 - Display pharmacies");
			System.out.println("2 - Insert pharmacy");
			System.out.println("3 - Delete pharmacy");
			System.out.println("4 - Update pharmacy");
						
			String pharm_subOptions = InputHelper.getInput("Enter the number or type 'quit' if you wish to exit: ");
			
			switch (pharm_subOptions) {
			case "1":
				pharmacist.displayPharmacy();		
				break;
			case "2":
				pharmacist.insertPharmacy();
				break;
			case "3":
				//pharmacist.deletePharmacy();
				break;
			case "4":
				//pharmacist.updatePharmacy();
				break;
			case "quit":
				System.out.println("Bye!");
				System.exit(0);
				break;
			}
			break;
			
		case "2": // Pharmacist Database
			System.out.println("What would you like to do?");
			System.out.println("1 - Display pharmacists");
			System.out.println("2 - Insert pharmacists");
			System.out.println("3 - Delete pharmacists");
			System.out.println("4 - Update pharmacists");
			pharm_subOptions = InputHelper.getInput("Enter the number or type 'quit' if you wish to exit: ");
			
			switch (pharm_subOptions) {
			case "1":
				pharmacist.displayPharmacist();
				break;
			case "2":
				pharmacist.insertPharmacist();
				break;
			case "3":
				//pharmacist.deletePharmacist();
				break;
			case "4":
				//pharmacist.updatePharmacist();
				break;	
			case "quit":
				System.out.println("Bye!");
				System.exit(0);
				break;
			}
			break;
			
		case "3": // Inventory
			// figure out which class is the highest in the hierarchy 
			Inventory inventory = new Inventory();
			System.out.println("What would you like to do?");
			System.out.println("1 - Display inventories");
			System.out.println("2 - Insert inventory");
			System.out.println("3 - Delete inventory");
			System.out.println("4 - Update inventory");

			pharm_subOptions = InputHelper.getInput("Enter the number or type 'quit' if you wish to exit: ");
			
			switch (pharm_subOptions) {
			case "1":			
				inventory.displayInventory();
				break;
			case "2":
				inventory.insertInventory();
				break;
			case "3":
				//inventory.deleteInventory();
				break;
			case "4":
				//inventory.updateInventory();
				break;	
			}
			break;
			
		case "4": // Drug Info
			// figure out which class is the highest in the hierarchy 
			Drug drug = new Drug();
			
			System.out.println("What would you like to do?");
			System.out.println("1 - Display drugs");
			System.out.println("2 - Insert drug");
			System.out.println("3 - Delete drug");
			System.out.println("4 - Update drug");
			
			pharm_subOptions = InputHelper.getInput("Enter the number or type 'quit' if you wish to exit: ");
			
			switch (pharm_subOptions) {
			case "1":
				drug.displayDrug();		
				break;
			case "2":
				drug.insertDrug();
				break;
			case "3":
				//drug.deleteDrug();
				break;
			case "4":
				//drug.updateDrug();
				break;	
			}
			break;
			
		case "5": // Prescription
			// figure out which class is the highest in the hierarchy 
			System.out.println("What would you like to do?");
			
//			pharm_subOptions = InputHelper.getInput("Enter the number or type 'quit' if you wish to exit: ");
//	
//			switch (pharm_subOptions) {
//			case "1":
//				
//				break;
//			case "2":
//	
//				break;
//			case "3":
//		
//				break;
//			case "4":
//				
//				break;	
//			}
			break;
			
		case "6": // Order
			// figure out which class is the highest in the hierarchy
			System.out.println("What would you like to do?");
			
//			pharm_subOptions = InputHelper.getInput("Enter the number or type 'quit' if you wish to exit: ");
//			
//			switch (pharm_subOptions) {
//			case "1":
//				
//				break;
//			case "2":
//	
//				break;
//			case "3":
//		
//				break;
//			case "4":
//				
//				break;	
//			}
			break;
			
		case "quit":
			System.out.println("Bye!");
			System.exit(0);
			break;
		}			
	}

	/*
	 * Start the PharmacySystem
	 */ 
	public void start() throws SQLException, IOException {
		System.out.println("Please specify yourself as one of the following: doctor, pharmacist or patient");
		System.out.println("Or type 'quit', if you wish to exit");
		login(InputHelper.getInput("I am a: "));

	}
	
	/*
	 * Security check for Log-in attempts 
	 */ 
	public void checkLogInAttempts() throws IOException, SQLException {
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

	/*
	 * Distribute connection to other classes
	 */ 
	public static Connection getConn() {
		return conn;
	}

	
	public static void main(String[] args) throws SQLException, IOException{	
		Main database = new Main();
		database.start();
	}

}
