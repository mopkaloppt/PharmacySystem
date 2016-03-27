package com.pharmacysystem.db.tables;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Drug {
	
	int drug_id;
	String drud_name;
	String drug_info;
	String dosage; 
	

	/*
     * displays drugs
     */ 
	
	public static void displayDrug(ResultSet rs) throws SQLException {
		
//		rs.last();
//		int nRows = rs.getRow();
//		if (nRows == 0) {
//			System.out.println("No drugs were found");	
//		} else {
//			System.out.println("Number of drugs: " + nRows);
//			rs.beforeFirst();
//		}
//		
//		while (rs.next()) {
//			StringBuffer buffer = new StringBuffer();
//			
//			buffer.append("DrugID: " + rs.getInt("drug_id"));
//			buffer.append(rs.getString("drug_name"));
//			buffer.append(rs.getString("drug_info"));
//			buffer.append(rs.getString("dosage"));
//			
//			System.out.println(buffer.toString());					
//		}	
	}


    /*
     * inserts a drug
     */ 
	public void insertDrug() {
		
    	
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


