package com.pharmacysystem.db.tables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.pharmacysystem.db.Main;

public class Patient {
	int 		patient_id;
	int 		doctor_id; // inherit from Doctor class 
	String 		p_fname;
	String 		p_lname;
	String		illness;
	String		address;

	Connection conn = Main.getConn();

	//	public Patient() {
	//		doctor_id = Doctor.
	//	}
	//	

	/*
	 * displays Patients
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
				buffer.append("DoctorID: " + rs.getInt("doctor_id"));
				buffer.append(rs.getString("p_fname"));
				buffer.append(rs.getString("p_lname"));
				buffer.append(rs.getString("illness"));
				buffer.append(rs.getString("address"));

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
	 * inserts a Patient
	 */ 
	public void insertPatient() {
		// for testing
		System.out.println("insert Patient");
	}



	/*
	 * deletes a Patient
	 */ 
	public void deletePatient() {
		// for testing
		System.out.println("delete Patient");
	}



	/*
	 * updates a Patient
	 */ 
	public void updatePatient() {
		// for testing
		System.out.println("update Patient");
	}


}
