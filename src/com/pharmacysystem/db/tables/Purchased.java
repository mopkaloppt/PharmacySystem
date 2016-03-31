package com.pharmacysystem.db.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import com.pharmacysystem.db.ConnectionManager;

import com.pharmacysystem.db.util.InputHelper;;

public class Purchased {
	
	String receipt_no;
	int patient_id; // inherit from Patient
	int pharmacy_id; // inherit from Pharmacy
	int drug_id; // inherit from Drug 
	double price;
	String date_purchased; // timestamp SQL type
	
	private static Connection conn = ConnectionManager.getInstance().getConnection();

	/*
     * displays purchases
     */ 
	
	public void displayPurchased() throws SQLException {
		
		ResultSet rs = null;

		try (
				Statement stmt 	= conn.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE, 
						ResultSet.CONCUR_READ_ONLY);
				) {
			rs = stmt.executeQuery("SELECT * FROM purchased");

			rs.last();
			int nRows = rs.getRow();
			if (nRows == 0) {
				System.out.println("No purchases were found");	
			} else {
				System.out.println("Number of purchases: " + nRows);
				rs.beforeFirst();
			}

			while (rs.next()) {
				StringBuffer buffer = new StringBuffer();

				buffer.append("Receipt No.: " + rs.getString("receipt_no"));
				buffer.append("\nPatientID: " + rs.getInt("patient_id"));
				buffer.append("\nPharmacyID: " + rs.getString("pharmacy_id"));
				buffer.append("\nDrugID: " + rs.getString("drug_id"));
				buffer.append("\nPrice: " + rs.getString("price"));
				buffer.append("\nDate purchased: " + rs.getString("date_purchased"));

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
     * inserts a purchase
     */ 
	public void insertPurchased() {
		
		try {
			
			PreparedStatement stmt = conn.prepareStatement(
					"INSERT INTO purchased VALUES(?,?,?,?,?,?)",
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			// Should receipt_no be auto-incremented? 
			receipt_no = InputHelper.getInput("Receipt no. (e.g. xxx-xxx-xxx): ");
			stmt.setString(1, receipt_no);

			patient_id = InputHelper.getId("Enter PatientID: ");
			stmt.setInt(2, patient_id );

			pharmacy_id =  InputHelper.getId("Enter PharmarcyID: ");
			stmt.setInt(3, pharmacy_id);

			drug_id =  InputHelper.getId("Enter DrugID: ");
			stmt.setInt(4, drug_id);

			price = InputHelper.getPrice("Enter price: ");
			stmt.setDouble(5, price);

			date_purchased = 
					new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(java.sql.Date.valueOf(java.time.LocalDate.now()));
				
			stmt.setString(6, date_purchased);
		
			
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
     * deletes a purchase
     */ 
	public void deletePurchased() {
    	
    }
    
    
    
    /*
     * updates a purchase
     */ 
	public void updatePurchased() {
    	
    }
    
    
}
