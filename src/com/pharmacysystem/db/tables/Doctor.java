package com.pharmacysystem.db.tables;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.pharmacysystem.db.ConnectionManager;
import com.pharmacysystem.db.util.InputHelper;

public class Doctor  {

	int			doctor_id;
	String		d_fname;
	String 		d_lname;
	String		credentials;
	String		address;
	String		doctor_phone;


	private static Connection conn = ConnectionManager.getInstance().getConnection();
	
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
				displayDoctor();
				break;
			case "2":
				insertDoctor();
				break;
			case "3":
				//doctor_id = InputHelper.getId("Enter the ID you wish to delete: ");
				
				//deleteDoctor(doctor_id);
				break;
			case "4":
				updateDoctor();
				break;									
			case "quit":
				System.out.println("Bye!");
				System.exit(0);
				break;
			}		
			break;


		case "2": // Patient
			Patient pat = new Patient();
			System.out.println("What would you like to do?");
			System.out.println("1 - Display patients");
			System.out.println("2 - Insert patient");
			System.out.println("3 - Delete patient");
			System.out.println("4 - Update patient");
			doc_subOptions  = InputHelper.getInput("Enter the number or type 'quit' if you wish to exit: ");
			switch (doc_subOptions) {
			case "1":
				pat.displayPatient();
				break;
			case "2":
				pat.insertPatient();
				break;
			case "3":
				//pat.deletePatient();
				break;
			case "4":
				//pat.updatePatient();
				break;

			case "quit":
				System.out.println("Bye!");
				System.exit(0);
				break;
			}
			break;

		case "3": // Drug
			// Doctor can only see drug info, cannot modify
			Drug drug = new Drug();
			drug.displayDrug();
			break;

		case "4": // Prescription
			Prescribed prescr = new Prescribed();
			System.out.println("What would you like to do?");
			System.out.println("1 - Display prescriptions");
			System.out.println("2 - Insert prescription");
			System.out.println("3 - Delete prescription");
			System.out.println("4 - Update prescription");
			doc_subOptions  = InputHelper.getInput("Enter the number or type 'quit' if you wish to exit: ");
			switch (doc_subOptions) {
			case "1":
				prescr.displayPrescribed();
				break;
			case "2":
				prescr.insertPrescribed();
				break;
			case "3":
				//prescr.deletePrescribed();
				break;
			case "4":
				//prescr.updatePrescribed();	
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
	 * displays doctors
	 */ 
	public void displayDoctor() throws SQLException {

		ResultSet rs = null;

		try {
			
			Statement stmt 	= conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY);
				 
			rs = stmt.executeQuery("SELECT * FROM doctor");

			rs.last();
			int nRows = rs.getRow();
			if (nRows == 0) {
				System.out.println("No doctors were found");	
			} else {
				System.out.println("Number of doctors: " + nRows);
				rs.beforeFirst();
			}

			while (rs.next()) {
				StringBuffer buffer = new StringBuffer();

				buffer.append("DoctorID: " + rs.getInt("doctor_id"));
				buffer.append("\nFirst name: " + rs.getString("d_fname"));
				buffer.append("\nLast name: " + rs.getString("d_lname"));
				buffer.append("\nCredentials: " + rs.getString("credentials"));
				buffer.append("\nAddress: " + rs.getString("address"));
				buffer.append("\nPhone: " + rs.getString("phone") + "\n");

				System.out.println(buffer.toString());					
			}	
			stmt.close();

		}								
		catch (SQLException ex) {
			System.out.println("Message: " + ex.getMessage());
		}	
		finally {
			rs.close();
		}


	}


	/*
	 * inserts a doctor
	 */ 
	public void insertDoctor() throws IOException, SQLException {

		try {
			
			PreparedStatement stmt = conn.prepareStatement(
					"INSERT INTO doctor VALUES(?,?,?,?,?,?)",
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			doctor_id = InputHelper.getId("Enter ID (4 digits): ");
			stmt.setInt(1, doctor_id);

			d_fname = InputHelper.getInput("Enter first name: ");
			stmt.setString(2, d_fname);

			d_lname =  InputHelper.getInput("Enter last name: ");
			stmt.setString(3, d_lname);

			credentials =  InputHelper.getInput("Enter credentials (e.g. MD, University): ");
			stmt.setString(4, credentials);

			address = InputHelper.getInput("Enter address: ");

				if (address.length() == 0) {
					stmt.setString(5, null);
				}
				else {
					stmt.setString(5, address);
				}

			doctor_phone = InputHelper.getInput("Enter phone number (e.g. XXX-XXX-XXXX): ");
			stmt.setString(6, doctor_phone);
		
			
			int affected = stmt.executeUpdate();
			
			if (affected == 1) {
				System.out.println("A row has been inserted");
				
			}
			else {
				System.out.println("No rows have been inserted!");
			}

			// commit work 
			conn.commit();
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
	 * deletes a doctor
	 */ 
	public boolean deleteDoctor(int doctor_id) throws IOException, SQLException {
		return true;
	
	}


	/*
	 * updates a doctor
	 */ 
	public void updateDoctor() throws IOException, SQLException {
		
	
		
	}

}

