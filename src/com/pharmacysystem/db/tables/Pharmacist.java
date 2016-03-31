package com.pharmacysystem.db.tables;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.pharmacysystem.db.ConnectionManager;
import com.pharmacysystem.db.util.InputHelper;

public class Pharmacist extends Pharmacy {

	int 		employee_id;
	String		emp_fname; 
	String 		emp_lname; 
	int 		pharmacy_id;
	//int 		pharmacy_id = getPharmacyID(); // Do we have to inherit from Pharmacy class??

	private static Connection conn = ConnectionManager.getInstance().getConnection();
	
	/*
	 * Options for Pharmacist 
	 */ 
	public void pharmacistOptions(String pharm_options) throws IOException, SQLException {
		/*	pharm_options to access the following:
		1 - Pharmacy Info 
		2 - Pharmacist Database
		3 - Inventory
		4 - Drug Info
		5 - Prescription
		6 - Order */
			
		switch (pharm_options) {

		case "1": // Pharmacy Info 	
			Pharmacy pharm = new Pharmacy();
			System.out.println("What would you like to do?");
			System.out.println("1 - Display pharmacies");
			System.out.println("2 - Insert pharmacy");
			System.out.println("3 - Delete pharmacy");
			System.out.println("4 - Update pharmacy");
						
			String pharm_subOptions = InputHelper.getInput("Enter the number or type 'quit' if you wish to exit: ");
			
			switch (pharm_subOptions) {
			case "1":
				pharm.displayPharmacy();		
				break;
			case "2":
				pharm.insertPharmacy();
				break;
			case "3":
				//pharmacist.deletePharmacy();
				break;
			case "4":
				//pharmacist.updatePharmacy();
				break;
			case "quit":
				System.out.println("Bye!");
				System.exit(0);
				break;
			}
			break;
			
		case "2": // Pharmacist Database
			System.out.println("What would you like to do?");
			System.out.println("1 - Display pharmacists");
			System.out.println("2 - Insert pharmacists");
			System.out.println("3 - Delete pharmacists");
			System.out.println("4 - Update pharmacists");
			pharm_subOptions = InputHelper.getInput("Enter the number or type 'quit' if you wish to exit: ");
			
			switch (pharm_subOptions) {
			case "1":
				displayPharmacist();
				break;
			case "2":
				insertPharmacist();
				break;
			case "3":
				//pharmacist.deletePharmacist();
				break;
			case "4":
				//pharmacist.updatePharmacist();
				break;	
			case "quit":
				System.out.println("Bye!");
				System.exit(0);
				break;
			}
			break;
			
		case "3": // Inventory
			// figure out which class is the highest in the hierarchy 
			Inventory inventory = new Inventory();
			System.out.println("What would you like to do?");
			System.out.println("1 - Display inventories");
			System.out.println("2 - Insert inventory");
			System.out.println("3 - Delete inventory");
			System.out.println("4 - Update inventory");

			pharm_subOptions = InputHelper.getInput("Enter the number or type 'quit' if you wish to exit: ");
			
			switch (pharm_subOptions) {
			case "1":			
				inventory.displayInventory();
				break;
			case "2":
				inventory.insertInventory();
				break;
			case "3":
				//inventory.deleteInventory();
				break;
			case "4":
				//inventory.updateInventory();
				break;	
			}
			break;
			
		case "4": // Drug Info
			// figure out which class is the highest in the hierarchy 
			Drug drug = new Drug();
			
			System.out.println("What would you like to do?");
			System.out.println("1 - Display drugs");
			System.out.println("2 - Insert drug");
			System.out.println("3 - Delete drug");
			System.out.println("4 - Update drug");
			
			pharm_subOptions = InputHelper.getInput("Enter the number or type 'quit' if you wish to exit: ");
			
			switch (pharm_subOptions) {
			case "1":
				drug.displayDrug();		
				break;
			case "2":
				drug.insertDrug();
				break;
			case "3":
				//drug.deleteDrug();
				break;
			case "4":
				//drug.updateDrug();
				break;	
			}
			break;
			
		case "5": // Prescription
			Prescribed prescr = new Prescribed();
			
			System.out.println("What would you like to do?");
			System.out.println("1 - Display prescriptions");
			System.out.println("2 - Delete prescription");
			
			pharm_subOptions = InputHelper.getInput("Enter the number or type 'quit' if you wish to exit: ");
	
			switch (pharm_subOptions) {
			case "1":
				prescr.displayPrescribed();
				break;
			case "2":
				//prescr.deletePrescribed();
				break;
			case "quit":
				System.out.println("Bye!");
				System.exit(0);
				break;
			}
			break;
			
		case "6": // Order
			// figure out which class is the highest in the hierarchy
			Purchased purch = new Purchased();
			System.out.println("What would you like to do?");
			System.out.println("1 - Display all purchasing orders");
			System.out.println("2 - Delete purchasing order");
			System.out.println("3 - Update purchasing order");
	
			pharm_subOptions = InputHelper.getInput("Enter the number or type 'quit' if you wish to exit: ");
			
			switch (pharm_subOptions) {
			case "1":
				purch.displayPurchased();
				break;
			case "2":
				//purch.deletePurchased();
				break;
			case "3":
				//purch.updatePurchased();
				break;
			case "quit":
				System.out.println("Bye!");
				System.exit(0);
				break;	
			}
			break;
			
		case "quit":
			System.out.println("Bye!");
			System.exit(0);
			break;
		}			
	}

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
				buffer.append("\nPharmacyID: " + rs.getInt("pharmacy_id"));
				buffer.append("\nPharmacist name: " + rs.getString("emp_fname"));
				buffer.append("\nLast name: " + rs.getString("emp_lname"));

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
	
		try {

			PreparedStatement stmt = conn.prepareStatement(
					"INSERT INTO pharmacist VALUES(?,?,?,?)",
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			employee_id = InputHelper.getId("Enter ID (4 digits): ");
			stmt.setInt(1, employee_id);

			emp_fname = InputHelper.getInput("Enter first name: ");
			stmt.setString(2, emp_fname);

			emp_lname =  InputHelper.getInput("Enter last name: ");
			stmt.setString(3, emp_lname);

			pharmacy_id = InputHelper.getId("Enter PharmacyID (4 digits): ");
			stmt.setInt(4, pharmacy_id);

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
