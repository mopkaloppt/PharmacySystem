package com.pharmacysystem.db.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.pharmacysystem.db.Main;
import com.pharmacysystem.db.util.InputHelper;

public class Pharmacist extends Pharmacy {

	int 		employee_id;
	String		emp_fname; 
	String 		emp_lname; 
	int 		pharmacy_id;
	//int 		pharmacy_id = getPharmacyID(); // Do we have to inherit from Pharmacy class??

	Connection conn = Main.getConn();

	/*
	 * displays Pharmacists
	 */ 
	public void displayPharmacist() throws SQLException {
		ResultSet rs = null;

		try (
				Statement stmt 	= conn.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE, 
						ResultSet.CONCUR_READ_ONLY);
				) {
			rs = stmt.executeQuery("SELECT * FROM pharmacist");

			rs.last();
			int nRows = rs.getRow();
			if (nRows == 0) {
				System.out.println("No employees were found");	
			} else {
				System.out.println("Number of employees: " + nRows);
				rs.beforeFirst();
			}

			while (rs.next()) {
				StringBuffer buffer = new StringBuffer();

				buffer.append("EmployeeID: " + rs.getInt("employee_id"));
				buffer.append("\nPharmacyID: " + rs.getInt("pharmacy_id"));
				buffer.append("\nPharmacist name: " + rs.getString("emp_fname"));
				buffer.append("\nLast name: " + rs.getString("emp_lname"));

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
	 * inserts a Pharmacist
	 */ 
	public void insertPharmacist() {
	
		try {

			PreparedStatement stmt = conn.prepareStatement(
					"INSERT INTO pharmacist VALUES(?,?,?,?)",
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			employee_id = InputHelper.getId("Enter ID (4 digits): ");
			stmt.setInt(1, employee_id);

			emp_fname = InputHelper.getInput("Enter first name: ");
			stmt.setString(2, emp_fname);

			emp_lname =  InputHelper.getInput("Enter last name: ");
			stmt.setString(3, emp_lname);

			pharmacy_id = InputHelper.getId("Enter PharmacyID (4 digits): ");
			stmt.setInt(4, pharmacy_id);

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
	 * deletes a Pharmacist
	 */ 
	public void deletePharmacist() {
		// for testing
		System.out.println("delete Pharmacist");

	}



	/*
	 * updates a Pharmacist
	 */ 
	public void updatePharmacist() {
		// for testing
		System.out.println("update Pharmacist");

	}

}
