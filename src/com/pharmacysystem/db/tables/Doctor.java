package com.pharmacysystem.db.tables;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import com.pharmacysystem.db.Main;
import com.pharmacysystem.db.util.InputHelper;

public class Doctor extends Patient {

	int			doctor_id;
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
				buffer.append("\nPhone: " + rs.getString("phone"));

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
