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
		String patientID = InputHelper.getInput("What is the patientID");
		String doctorID = InputHelper.getInput("What is the doctorID?");
		String drugID = InputHelper.getInput("What is the drugID?");
		String input = "";
		Boolean contains = false;
		try {
			s = conn.createStatement();
			ResultSet r = s.executeQuery("SELECT * FROM Prescribed");
			ArrayList<Integer> key1 = new ArrayList<Integer>();
			ArrayList<Integer> key2 = new ArrayList<Integer>();
			ArrayList<Integer> key3 = new ArrayList<Integer>();
			while (r.next()) {
				key1.add(r.getInt("patientID"));
				key2.add(r.getInt("doctorID"));
				key3.add(r.getInt("drugID"));
			}

			for(int i = 0; i < key1.size(); i++){
				if(key1.get(i).equals(Integer.parseInt(patientID)) && key2.get(i).equals(Integer.parseInt(doctorID)) && key1.get(i).equals(Integer.parseInt(drugID)))
					contains = true;
			}

			if(!contains){
				System.out.println("Touple does not exist, exiting");
				return;
			}

			input = InputHelper.getInput("What would you like to update?\n isRenewable or datePrescribed?");
			s = conn.createStatement();
			s.executeQuery("Select " + input + " from Prescribed WHERE patientID = " + patientID + " and doctorID = "+doctorID+ " and drugID = "+drugID);
			s.close();
		} catch (SQLException e) {
			System.out.println("Try again, attribute does not exist in table.");
			return;
		}

		String result = InputHelper.getInput("What would you like to update this to?");

		try {
			s = conn.createStatement();
			s.executeUpdate("UPDATE Prescribed SET " + input + " = '" + result + "' WHERE patientID = " + patientID + " and doctorID = "+doctorID+ " and drugID = "+drugID);
			System.out.println("Updated");

		} catch (SQLException e) {
			System.out.println("Attribute could not be changed, please enter valid parameters.");
		}
    }
    

}
