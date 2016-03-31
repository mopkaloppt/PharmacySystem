package com.pharmacysystem.db.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import com.pharmacysystem.db.ConnectionManager;
import com.pharmacysystem.db.util.InputHelper;

public class Prescribed {
	int drug_id;
	int doctor_id;
	int patient_id;
	String isRenewable;
	Timestamp date_prescribed;
	
	private static Connection conn = ConnectionManager.getInstance().getConnection();

	/*
     * displays Prescription
     */ 
	public void displayPrescribed() throws SQLException {
		ResultSet rs = null;

		try {
			
			Statement stmt 	= conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY);
				 
			rs = stmt.executeQuery("SELECT * FROM prescribed");

			rs.last();
			int nRows = rs.getRow();
			if (nRows == 0) {
				System.out.println("No prescriptions were found");	
			} else {
				System.out.println("Number of prescriptions: " + nRows);
				rs.beforeFirst();
			}

			while (rs.next()) {
				StringBuffer buffer = new StringBuffer();
				buffer.append("DrugID: " + rs.getInt("drug_id"));
				buffer.append("\nDoctorID: " + rs.getInt("doctor_id"));
				buffer.append("\nPatientID: " + rs.getInt("patient_id"));
				buffer.append("\nRenewable: " + rs.getString("isRenewable"));
				buffer.append("\nDate prescribed: " + rs.getString("date_prescribed"));
				

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
     * inserts a Prescription
     */ 
	public void insertPrescribed() {
		
		try {
			
			PreparedStatement stmt = conn.prepareStatement(
					"INSERT INTO prescribed VALUES(?,?,?,?,?)",
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			drug_id = InputHelper.getId("Enter DrugID (5 digits): ");
			stmt.setInt(1, drug_id);
			
			doctor_id = InputHelper.getId("Enter DoctorID (4 digits): ");
			stmt.setInt(2, doctor_id);
		
			patient_id = InputHelper.getId("Enter PatientID (4 digits): ");
			stmt.setInt(3, patient_id);
			
			isRenewable = InputHelper.getInput("Is this drug renewable? (y/n): ");
			stmt.setString(4, isRenewable);

			//java.util.Date date = new java.util.Date();
			//date_prescribed = new Timestamp(date.getTime());
			// Still have to fix the time format
			date_prescribed = Timestamp.valueOf(java.time.LocalDateTime.now());
			stmt.setTimestamp(5, date_prescribed);
			
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
     * deletes a Prescription
     */ 
	public void deletePrescribed() {
    	
    }
    
    
    
    /*
     * updates a Prescription
     */ 
	public void updatePrescribed() {
    	
    }
    

}
