package com.pharmacysystem.db.tables;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.pharmacysystem.db.ConnectionManager;
import com.pharmacysystem.db.util.InputHelper;

public class Inventory {
	int pharmacy_id;
	int drug_id;
	int stock;
	public static Statement s;
	private static Connection conn = ConnectionManager.getInstance().getConnection();
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

		String drugID = InputHelper.getInput("What is the drugID");
		String inventoryID = InputHelper.getInput("What is the inventoryID?");
		String input = "";
		Boolean contains = false;
		try {
			s = conn.createStatement();
			ResultSet r = s.executeQuery("SELECT * FROM Inventory");
			ArrayList<Integer> key1 = new ArrayList<Integer>();
			ArrayList<Integer> key2 = new ArrayList<Integer>();
			while (r.next()) {
				key1.add(r.getInt("drugID"));
				key2.add(r.getInt("inventoryID"));
			}

			for(int i = 0; i < key1.size(); i++){
				if(key1.get(i).equals(Integer.parseInt(drugID)) && key2.get(i).equals(Integer.parseInt(inventoryID)))
					contains = true;
			}

			if(!contains){
				System.out.println("Touple does not exist, exiting");
				return;
			}

			input = InputHelper.getInput("What would you like to update?\n pharmacyID, or stock?");
			s = conn.createStatement();
			s.executeQuery("Select " + input + " from Inventory WHERE DrugID = " + drugID + " and inventoryID = " + inventoryID);
			s.close();
		} catch (SQLException e) {
			System.out.println("Try again, attribute does not exist in table.");
			return;
		}

		String result = InputHelper.getInput("What would you like to update this to?");

		try {
			s = conn.createStatement();
			s.executeUpdate("UPDATE Inventory SET stock = '" + result + "' WHERE DrugID = " + drugID + " and inventoryID = " + inventoryID);
			System.out.println("Updated");

		} catch (SQLException e) {
			System.out.println("Attribute could not be changed, please enter valid parameters.");;
		}
    }
    

}
