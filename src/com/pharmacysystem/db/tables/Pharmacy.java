package com.pharmacysystem.db.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.pharmacysystem.db.ConnectionManager;
import com.pharmacysystem.db.util.InputHelper;

public class Pharmacy {
	int pharmacy_id;
	String pharmacy_name;
	String pharmacy_address; 
	String pharmacy_phone;
	
	private static Connection conn = ConnectionManager.getInstance().getConnection();
	
	/*
     * displays Pharmacies
     */ 
	public void displayPharmacy() throws SQLException {
		ResultSet rs = null;

		try {
			
			Statement stmt 	= conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY);
				 
			rs = stmt.executeQuery("SELECT * FROM pharmacy");

			rs.last();
			int nRows = rs.getRow();
			if (nRows == 0) {
				System.out.println("No pharmacies were found");	
			} else {
				System.out.println("Number of pharmacies: " + nRows);
				rs.beforeFirst();
			}
		
			while (rs.next()) {
				StringBuffer buffer = new StringBuffer();

				buffer.append("PharmacyID: " + rs.getInt("pharmacy_id"));
				buffer.append("\nPharmacy name: " + rs.getString("pharmacy_name"));
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
     * inserts a Pharmacy
     */ 
	public void insertPharmacy() {
		
		try {
			
			PreparedStatement stmt = conn.prepareStatement(
					"INSERT INTO pharmacy VALUES(?,?,?,?)",
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			pharmacy_id = InputHelper.getId("Enter PharmacyID (4 digits): ");
			stmt.setInt(1, pharmacy_id);

			pharmacy_name = InputHelper.getInput("Enter pharmacy name: ");
			stmt.setString(2, pharmacy_name);

			pharmacy_address = InputHelper.getInput("Enter pharmacy address: ");
			stmt.setString(3, pharmacy_address);

			pharmacy_phone = InputHelper.getInput("Enter phone number (e.g. XXX-XXX-XXXX): ");
			stmt.setString(4, pharmacy_phone);
			
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
     * deletes a Pharmacy
     */ 
	public void deletePharmacy() {
    	
    }
    
    
    
    /*
     * updates a Pharmacy
     */ 
	public void updatePharmacy() {
    	
    }
	
	// Do I need this method??
	public Integer getPharmacyID() {
		return pharmacy_id;
	}
    

}
