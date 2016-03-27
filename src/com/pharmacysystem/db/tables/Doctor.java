package com.pharmacysystem.db.tables;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.pharmacysystem.db.Main;

public class Doctor {

	int		doctor_id;
	String		d_fname;
	String 		d_lname;
	String		credentials;
	String		address;
	String		doctor_phone;

	Connection conn = Main.getConn();

	/*
	 * displays doctors
	 */ 


	public void displayDoctor() throws SQLException {

		ResultSet rs = null;

		try (
				Statement stmt 	= conn.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE, 
						ResultSet.CONCUR_READ_ONLY);
				) {
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
				buffer.append(rs.getString("d_fname"));
				buffer.append(rs.getString("d_lname"));
				buffer.append(rs.getString("credentials"));
				buffer.append(rs.getString("address"));
				buffer.append(rs.getString("doctor_phone"));

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
	 * inserts a doctor
	 */ 
	public void insertDoctor() {
		// for testing
		System.out.println("inesert Doctor");
	}



	/*
	 * deletes a doctor
	 */ 
	public void deleteDoctor() {
		// for testing
		System.out.println("delete Doctor");
	}


	/*
	 * updates a doctor
	 */ 
	public void updateDoctor() {
		// for testing
		System.out.println("update Doctor");
	}

}
