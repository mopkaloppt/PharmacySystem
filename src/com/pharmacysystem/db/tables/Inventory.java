package com.pharmacysystem.db.tables;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.pharmacysystem.db.Main;
import com.pharmacysystem.db.util.InputHelper;

public class Inventory {
	int pharmacy_id;
	int drug_id;
	int stock;
	
	Connection conn = Main.getConn();
	/*
     * displays inventories
     */ 
	public void displayInventory() throws SQLException {
		
		ResultSet rs = null;

		try {
			
			Statement stmt 	= conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY);
				 
			rs = stmt.executeQuery("SELECT * FROM inventory");

			rs.last();
			int nRows = rs.getRow();
			if (nRows == 0) {
				System.out.println("No inventories were found");	
			} else {
				System.out.println("Number of inventories: " + nRows);
				rs.beforeFirst();
			}

			while (rs.next()) {
				StringBuffer buffer = new StringBuffer();

				buffer.append("PharmacyID: " + rs.getInt("pharmacy_id"));
				buffer.append("\nDrugID: " + rs.getInt("drug_id"));
				buffer.append("\nStock: " + rs.getInt("stock"));
			
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
     * inserts an Inventory
     */ 
	public void insertInventory() throws IOException, SQLException {
		
		try {

			PreparedStatement stmt = conn.prepareStatement(
					"INSERT INTO inventory VALUES(?,?,?)",
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			pharmacy_id = InputHelper.getId("Enter PharmacyID (4 digits): ");
			stmt.setInt(1, pharmacy_id);
			
			drug_id = InputHelper.getId("Enter DrugID (5 digits): ");
			stmt.setInt(2, drug_id);
			
			stock = InputHelper.getStock("Enter stock number: ");
			stmt.setInt(3, stock);

		
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
     * deletes an Inventory
     */ 
	public void deleteInventory() {
    	
    }
    
    
    
    /*
     * updates an Inventory
     */ 
	public void updateInventory() {
    	
    }
    

}
