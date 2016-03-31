package com.pharmacysystem.db.tables;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.pharmacysystem.db.ConnectionManager;
import com.pharmacysystem.db.util.InputHelper;

public class Patient {
	int 		patient_id;
	int 		doctor_id; // inherit from Doctor class 
	String 		p_fname;
	String 		p_lname;
	String		illness;
	String		address;
	public static Statement s;
	private static Connection conn = ConnectionManager.getInstance().getConnection();
	
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
				displayMyProfile(first_name, last_name);
				break;
			case "2":
				// they can only see themselves .. 
//				String first_name = InputHelper.getInput("Enter your first name: ");
//				String last_name = InputHelper.getInput("Enter your last name: ");
				//patient.updateMyProfile(first_name, last_name);
				break;
			case "quit":
				System.out.println("Bye!");
				System.exit(0);
				break;
			}		
			break;
			
		case "2": // Prescription
			// patient can only see the prescriptions that have been assigned to themselves
			// they can only see themselves .. 
//			String first_name = InputHelper.getInput("Enter your first name: ");
//			String last_name = InputHelper.getInput("Enter your last name: ");
			//patient.seeMyPrescriptions(first_name, last_name);
			break;
			
		case "3": // Purchase History
			System.out.println("What would you like to do?");
			System.out.println("1 - See your purchase history");
			System.out.println("2 - Delete your purchase history");
			pat_subOptions = InputHelper.getInput("Enter the number or type 'quit' if you wish to exit: ");
			
			switch (pat_subOptions) {
			case "1":
				// can only see the purchases with the same name
				// they can only see themselves .. 
//				String first_name = InputHelper.getInput("Enter your first name: ");
//				String last_name = InputHelper.getInput("Enter your last name: ");
				//patient.displayPurchased(first_name, last_name);
				break;
			case "2":
				// they can only see themselves .. 
//				String first_name = InputHelper.getInput("Enter your first name: ");
//				String last_name = InputHelper.getInput("Enter your last name: ");
				//patient.deletePurchased(first_name, last_name);
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
	 * Displays Patients
	 */ 
	public void displayPatient() throws SQLException {

		ResultSet rs = null;

		try (
				Statement stmt 	= conn.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE, 
						ResultSet.CONCUR_READ_ONLY);
				) {
			rs = stmt.executeQuery("SELECT * FROM patient");

			rs.last();
			int nRows = rs.getRow();
			if (nRows == 0) {
				System.out.println("No patients were found");	
			} else {
				System.out.println("Number of patients: " + nRows + "\n");
				rs.beforeFirst();
			}

			while (rs.next()) {
				StringBuffer buffer = new StringBuffer();

				buffer.append("PatientID: " + rs.getInt("patient_id"));
				buffer.append("\nDoctorID: " + rs.getInt("doctor_id"));
				buffer.append("\nPatient name: " + rs.getString("p_fname"));
				buffer.append("\nLast name: " + rs.getString("p_lname"));
				buffer.append("\nIllness: " + rs.getString("illness"));
				buffer.append("\nAddress: " + rs.getString("address"));
				buffer.append("\n");
				

				System.out.println(buffer.toString());					
			}	

		}								
		catch (SQLException ex) {
			System.out.println("Message: " + ex.getMessage());
		}	
		finally {
			rs.close();
		}	
	}


	/*
	 * Inserts a Patient
	 */ 
	public void insertPatient() throws IOException, SQLException{

		try {

			PreparedStatement stmt = conn.prepareStatement(
					"INSERT INTO patient VALUES(?,?,?,?,?,?)",
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			patient_id = InputHelper.getId("Enter PatientID (4 digits): ");
			stmt.setInt(1, patient_id);

			doctor_id = InputHelper.getId("Enter DoctorID (4 digits): ");
			stmt.setInt(2, doctor_id);

			p_fname = InputHelper.getInput("Enter Patient's first name: ");
			stmt.setString(3, p_fname);

			p_lname =  InputHelper.getInput("Enter Patient's last name: ");
			stmt.setString(4, p_lname);

			illness =  InputHelper.getInput("Describe Patient's illness: ");
			stmt.setString(5, illness);

			address = InputHelper.getInput("Enter Patient's address: ");
			stmt.setString(6, address);

	
			stmt.executeUpdate();

			// commit work 
			conn.commit();
			System.out.println("Successfuly committed to the database");
			stmt.close();

		}
		catch (SQLException ex) {
			System.out.println("Message: " + ex.getMessage());
			try {
				// undo the insert
				conn.rollback();	
			}
			catch (SQLException ex2) {
				System.out.println("Message: " + ex2.getMessage());
				System.exit(-1);
			}
		}
	}


	/*
	 * Deletes a Patient
	 */ 
	public void deletePatient() {
		// for testing
		System.out.println("delete Patient");
	}
	

	/*
	 * Updates a Patient
	 */ 
	public void updatePatient() {
		// for testing
		System.out.println("update Patient");
	}
	
	
	
	
	
	
	// THESE METHODS ARE FOR CURRENTLY LOGGED-IN PATIENT //
	
	
	/*
	 * Displays profile of a patient
	 */ 
	public void displayMyProfile(String p_fname, String p_lname) throws SQLException {
	
		// ERROR: It can never find a patient!!! Don't know why
		ResultSet rs = null;

		try (
				PreparedStatement stmt = conn.prepareStatement(
						"SELECT * FROM patient WHERE p_fname = ? AND p_lname = ?",				
						ResultSet.TYPE_SCROLL_INSENSITIVE, 
						ResultSet.CONCUR_READ_ONLY);
				) {
		
			stmt.setString(1, p_fname);
			stmt.setString(2, p_lname);
	
			rs = stmt.executeQuery();
			
			// it never gets the row back.. don't know why!!!
			if  (rs.next()) {
				StringBuffer buffer = new StringBuffer();

				buffer.append("PatientID: " + rs.getInt("patient_id"));
				buffer.append("\nDoctorID: " + rs.getInt("doctor_id"));
				buffer.append("\nPatient name: " + rs.getString("p_fname"));
				buffer.append("\nLast name: " + rs.getString("p_lname"));
				buffer.append("\nIllness: " + rs.getString("illness"));
				buffer.append("\nAddress: " + rs.getString("address"));
				buffer.append("\n");
				System.out.println(buffer.toString());						
								
			}
			else {
				System.out.println(p_fname + " " + p_lname + " not found!");	
		
			}
								
		}
		catch (SQLException ex) {
			System.out.println("Message: " + ex.getMessage());
		}	
		finally {
			rs.close();
		}			
	}
	
	
	
	/*
	 * Updates profile of a patient
	 */ 
	public void updateMyProfile(String p_fname, String p_lname) {
		String key = InputHelper.getInput("What's the id of the patient?");
		String input = "";
		try {
			s = conn.createStatement();
			ResultSet r = s.executeQuery("SELECT * FROM Patient");
			// Creates array with all ids of Patients
			ArrayList<Integer> ids = new ArrayList<Integer>();
			while (r.next()) {
				ids.add(r.getInt("patientID"));
			}

			if (!ids.contains(Integer.parseInt(key))){
				System.out.println("Patient not found, exiting.");
				return;
			}

			input = InputHelper.getInput("What would you like to update about the patient\n Please choose one: doctorID, name, illness, or address?");
			s = conn.createStatement();
			s.executeQuery("Select " + input + " from Patient WHERE PatientID = "+key);
			s.close();
		} catch (SQLException e) {
			System.out.println("Try again, attribute does not exist in table.");
			return;
		}

		String change = InputHelper.getInput("What would you like to change this to?");
		try {
			s = conn.createStatement();
			s.executeUpdate("UPDATE Patient SET "+input+ " = '" + change + "' WHERE patientID = " + key);
			System.out.println("Updated");

		} catch (SQLException e) {
			System.out.println("Attribute could not be changed, please enter valid parameters.");;
		}
		
	}

	
	/*
	 * Patients see their prescriptions
	 */ 
	public void seeMyPrescriptions(String p_fname, String p_lname) {
		// more or less same logic as in displayMyProfile if that one works
		
	}


}
