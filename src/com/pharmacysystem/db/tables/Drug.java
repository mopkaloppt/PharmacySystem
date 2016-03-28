package com.pharmacysystem.db.tables;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.pharmacysystem.db.Main;
import com.pharmacysystem.db.util.InputHelper;

public class Drug {

	int drug_id;
	String drug_name;
	String drug_info;
	String dosage; 

	Connection conn = Main.getConn();


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

	}

}


