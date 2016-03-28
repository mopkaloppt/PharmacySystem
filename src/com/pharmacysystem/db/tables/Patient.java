package com.pharmacysystem.db.tables;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.pharmacysystem.db.Main;
import com.pharmacysystem.db.util.InputHelper;

public class Patient {
	int 		patient_id;
	int 		doctor_id; // inherit from Doctor class 
	String 		p_fname;
	String 		p_lname;
	String		illness;
	String		address;

	Connection conn = Main.getConn();
	
	/*
	 * Displays profile of a patient
	 */ 
	public void displayProfile(String first_name, String last_name) throws SQLException {
	
		// ERROR: Invalid column index !!!!
		ResultSet rs = null;

		try (
				PreparedStatement stmt = conn.prepareStatement(
						"SELECT patient_id, doctor_id, p_fname, p_lname, illness, address FROM patient WHERE p_fname = ? AND p_lname = ?",				
						ResultSet.TYPE_SCROLL_INSENSITIVE, 
						ResultSet.CONCUR_READ_ONLY);
				) {
			stmt.setString(3, first_name);
			stmt.setString(4, last_name);
			rs = stmt.executeQuery();
			// commit work 
			conn.commit();
						
			rs.last();
			int nRows = rs.getRow();
			if (nRows == 0) {
				System.out.println(first_name + last_name + " not found!");	
			} 
			else {
				StringBuffer buffer = new StringBuffer();

				buffer.append("PatientID: " + rs.getInt("patient_id"));
				buffer.append("\nDoctorID: " + rs.getInt("doctor_id"));
				buffer.append("\nPatient name: " + rs.getString("p_fname"));
				buffer.append("\nLast name: " + rs.getString("p_lname"));
				buffer.append("\nIllness: " + rs.getString("illness"));
				buffer.append("\nAddress: " + rs.getString("address"));

				System.out.println(buffer.toString());					
				rs.beforeFirst();	
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
				System.out.println("Number of patients: " + nRows);
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
	 * Updates profile of a patient
	 */ 
	public void updateProfile() {
		// for testing
		System.out.println("Your profile has been updated");
		
	}



	/*
	 * Updates a Patient
	 */ 
	public void updatePatient() {
		// for testing
		System.out.println("update Patient");
	}


}
