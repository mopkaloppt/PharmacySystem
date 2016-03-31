package com.pharmacysystem.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.pharmacysystem.db.tables.Doctor;
import com.pharmacysystem.db.tables.Patient;
import com.pharmacysystem.db.tables.Pharmacist;
import com.pharmacysystem.db.util.InputHelper;


public class Main {
	
	public static Connection conn;

	Doctor doctor;
	Patient patient;
	Pharmacist pharmacist;

	/*
	 * Login to the database
	 */ 	
	public void start (String user_type) throws SQLException, IOException {

		switch (user_type) {

		case "doctor":
				// doctor is only allowed access for certain tables 
				// Action: create, update, delete, display on
				// Tables: doctor, patient, prescribed, display on drug
				doctor = new Doctor();

				System.out.println("Welcome Doctor!\n");
				System.out.println("You can access the following: ");
				System.out.println("1 - Doctor");
				System.out.println("2 - Patient");
				System.out.println("3 - Drug");
				System.out.println("4 - Prescription");
				String doc_options = InputHelper.getInput("Enter the number or type 'quit' if you wish to exit: ");

				doctor.doctorOptions(doc_options);
				
				System.out.println("Would you like to do something else?");
				String doMoreStuff = InputHelper.getInput("type 'y' if you do, or 'quit' if you wish to exit: ");
				if (doMoreStuff.equals("y")) {
					start("doctor");
				}
			break;

		case "patient":
				// patient is only allowed access for certain tables 
				// Action: display on
				// Tables: prescribed, purchased, pharmacy, doctor
				patient = new Patient();
				
				System.out.println("Welcome to your Medical Profile!\n");
				System.out.println("You can access the following: ");
				System.out.println("1 - Your Profile");
				System.out.println("2 - Prescription");
				System.out.println("3 - Purchase History");
				String pat_options =  InputHelper.getInput("Enter the number or type 'quit' if you wish to exit: ");

				patient.patientOptions(pat_options);
				
				System.out.println("Would you like to do something else?");
				doMoreStuff = InputHelper.getInput("type 'y' if you do, or 'quit' if you wish to exit: ");
				if (doMoreStuff.equals("y")) {
					start("patient");
				}				
			break;

		case "pharmacist":
			// pharmacist is only allowed access for certain tables 
			// Actions: create, update, delete, display on
			// Tables: pharmacy, pharmacist, inventory, drug, prescribed, purchased
			pharmacist = new Pharmacist();
			
			System.out.println("Welcome to Pharmacy System Management!\n");
			System.out.println("You can access the following: ");
			System.out.println("1 - Pharmacy Info");
			System.out.println("2 - Pharmacist Database");
			System.out.println("3 - Inventory");
			System.out.println("4 - Drug Info");
			System.out.println("5 - Prescription");
			System.out.println("6 - Order");	
			String pharm_options = InputHelper.getInput("Enter the number or type 'quit' if you wish to exit: ");

			pharmacist.pharmacistOptions(pharm_options);	
		
			System.out.println("Would you like to do something else?");
			doMoreStuff = InputHelper.getInput("type 'y' if you do, or 'quit' if you wish to exit: ");
			if (doMoreStuff.equals("y")) {
				start("pharmacist");
			}			
			break;

		case "quit": 
			System.out.println("Bye!");	
			System.exit(0);
			break;

		default: 
			System.out.println("Invalid user type: " + user_type);
			System.out.println(" Try again");
			start(InputHelper.getInput("I am a: "));
			break;
		}	
	}

	
	
	public static void main(String[] args) throws SQLException, IOException{	
		// Logging-In
		ConnectionManager.getInstance();
		
		System.out.println("Please specify yourself as one of the following: doctor, pharmacist or patient");
		System.out.println("Or type 'quit', if you wish to exit");
		
		Main database = new Main();
		database.start(InputHelper.getInput("I am a: "));
		ConnectionManager.getInstance().closeConnection();		
	}

}
