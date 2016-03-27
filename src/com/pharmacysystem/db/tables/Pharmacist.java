package com.pharmacysystem.db.tables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.pharmacysystem.db.Main;

public class Pharmacist {

	int 		employee_id;
	String		emp_fname; 
	String 		emp_lname; 
	int 		pharmacy_id; // inherit from Pharmacy class 

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
				buffer.append("PharmacyID: " + rs.getInt("pharmacy_id"));
				buffer.append(rs.getString("e_fname"));
				buffer.append(rs.getString("e_lname"));

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
		// for testing
		System.out.println("insert Pharmacist");
		

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
