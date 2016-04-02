package com.pharmacysystem.db.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.pharmacysystem.db.ConnectionManager;
import com.pharmacysystem.db.util.InputHelper;

public class Prescribed {
	int drug_id;
	int doctor_id;
	int patient_id;
	String isRenewable;
	Timestamp date_prescribed;
	public static Statement s;
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
		String patient_id = InputHelper.getInput("What is the patientID");
		String doctor_id = InputHelper.getInput("What is the doctorID?");
		String drug_id = InputHelper.getInput("What is the drugID?");
		String input = "";
		Boolean contains = false;
		try {
			s = conn.createStatement();
			ResultSet r = s.executeQuery("SELECT * FROM prescribed");
			ArrayList<String> key1 = new ArrayList<String>();
			ArrayList<String> key2 = new ArrayList<String>();
			ArrayList<String> key3 = new ArrayList<String>();
			while (r.next()) {
				key1.add(r.getString("patient_id"));
				key2.add(r.getString("doctor_id"));
				key3.add(r.getString("drug_id"));
			}

			for(int i = 0; i < key1.size(); i++){
				if(key1.get(i).equals(patient_id) && key2.get(i).equals(doctor_id) && key1.get(i).equals(drug_id))
					contains = true;
			}

			if(!contains){
				System.out.println("Touple does not exist, exiting");
				return;
			}

			input = InputHelper.getInput("What would you like to update?\n isRenewable or date_prescribed?");
			s = conn.createStatement();
			s.executeQuery("Select " + input + " from prescribed WHERE patient_id = " + patient_id + " and doctor_id = "+doctor_id+ " and drug_id = "+drug_id);
			s.close();
		} catch (SQLException e) {
			System.out.println("Try again, attribute does not exist in table.");
			return;
		}

		String result = InputHelper.getInput("What would you like to update this to?");

		try {
			s = conn.createStatement();
			s.executeUpdate("UPDATE prescribed SET " + input + " = '" + result + "' WHERE patient_id = " + patient_id + " and doctor_id = "+doctor_id+ " and drug_id = "+drug_id);
			System.out.println("Updated");

		} catch (SQLException e) {
			System.out.println("Attribute could not be changed, please enter valid parameters.");
		}
    }
    

}
