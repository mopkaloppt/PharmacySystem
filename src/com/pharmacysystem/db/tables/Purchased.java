package com.pharmacysystem.db.tables;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.pharmacysystem.db.util.InputHelper;;

public class Purchased {
	
	String receipt_no;
	int patient_id; // inherit from Patient
	int pharmacy_id; // inherit from Pharmacy
	int drug_id; // inherit from Drug 
	String price;
	String date_purchased; // timestamp SQL type 
	

	/*
     * displays purchases
     */ 
	
	public static void displayPurchased(ResultSet rs) throws SQLException {
		
	}


    /*
     * inserts a purchase
     */ 
    private void insertPurchased() {
		
    	
    }
    
    
    
    /*
     * deletes a purchase
     */ 
    private void deletePurchased() {
    	
    }
    
    
    
    /*
     * updates a purchase
     */ 
    private void updatePurchased() {
    	
    }
    
    
}
