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

public class Drug {

	int drug_id;
	String drug_name;
	String drug_info;
	String dosage;
	public static Statement s;
	private static Connection conn = ConnectionManager.getInstance().getConnection();


	/*
	 * displays drugs
	 */ 

	public void displayDrug() throws SQLException {

		ResultSet rs = null;

		try {

			Statement stmt 	= conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY);

			rs = stmt.executeQuery("SELECT * FROM drug");

			rs.last();
			int nRows = rs.getRow();
			if (nRows == 0) {
				System.out.println("No drugs were found");	
			} else {
				System.out.println("Number of drugs: " + nRows);
				rs.beforeFirst();
			}

			while (rs.next()) {
				StringBuffer buffer = new StringBuffer();

				buffer.append("DrugID: " + rs.getInt("drug_id"));
				buffer.append("\nDrug name: " + rs.getString("drug_name"));
				buffer.append("\nDrug info: " + rs.getString("drug_info"));
				buffer.append("\nDosage: " + rs.getString("dosage"));

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
	 * inserts a drug
	 */ 
	public void insertDrug() throws IOException, SQLException {

		try {

			PreparedStatement stmt = conn.prepareStatement(
					"INSERT INTO drug VALUES(?,?,?,?)",
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			drug_id = InputHelper.getId("Enter DrugID (5 digits): ");
			stmt.setInt(1, drug_id);

			drug_name = InputHelper.getInput("Enter drug name: ");
			stmt.setString(2, drug_name);

			drug_info =  InputHelper.getInput("Enter drug info: ");
			stmt.setString(3, drug_info);

			dosage =  InputHelper.getInput("Enter dosage: ");
			stmt.setString(4, dosage);

			
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
	 * deletes a drug
	 */ 
	public void deleteDrug() {

	}



	/*
	 * updates a drug
	 */ 
	public void updateDrug() {
		String input = "";
		String key = InputHelper.getInput("What is the drugID");
		try {
			s = conn.createStatement();
			ResultSet r = s.executeQuery("SELECT * FROM drug");
			ArrayList<Integer> ids = new ArrayList<Integer>();
			while (r.next()) {
				ids.add(r.getInt("drugID"));
			}

			if (!ids.contains(Integer.parseInt(key))){
				System.out.println("Drug not found, exiting.");
				return;
			}

			input = InputHelper.getInput("What would you like to update?\n name, info, or dosage?");
			s = conn.createStatement();
			s.executeQuery("Select " + input + " from drug WHERE DrugID = "+key);
			s.close();
		} catch (SQLException e) {
			System.out.println("Try again, attribute does not exist in table.");
			return;
		}
		String result = InputHelper.getInput("What is the new value?");

		try {
			s = conn.createStatement();
			s.executeUpdate("UPDATE Drug SET " +input+ " = '" + result + "' WHERE drugID = " + key);
			System.out.println("Updated");

		} catch (SQLException e) {
			System.out.println("Try again, attribute cannot be changed to that value.");;
		}
	}

}


