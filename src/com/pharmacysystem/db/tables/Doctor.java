package com.pharmacysystem.db.tables;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.pharmacysystem.db.Main;

public class Doctor {
	
	int                doctor_id;
	String             d_fname;
	String             d_lname;
	String             credentials;
	String             address;
	String 			   doctor_phone;
	

	/*
     * displays doctors
     */ 
	
	public static void displayDoctor(ResultSet rs) throws SQLException {
		
		// Haven't tested this method yet, may or may not work
	
//		rs.last();
//		int nRows = rs.getRow();
//		if (nRows == 0) {
//			System.out.println("No doctors were found");	
//		} else {
//			System.out.println("Number of doctors: " + nRows);
//			rs.beforeFirst();
//		}
//		
//		while (rs.next()) {
//			StringBuffer buffer = new StringBuffer();
//			
//			buffer.append("DoctorID: " + rs.getInt("doctor_id"));
//			buffer.append(rs.getString("d_fname"));
//			buffer.append(rs.getString("d_lname"));
//			buffer.append(rs.getString("credentials"));
//			buffer.append(rs.getString("address"));
//			buffer.append(rs.getString("doctor_phone"));
//			
//			System.out.println(buffer.toString());					
//		}	
	}


	
    /*
     * inserts a doctor
     */ 
    private void insertDoctor() {
		
    }
    
    
    
    /*
     * deletes a doctor
     */ 
    private void deleteDoctor() {
    	
    }
    
     
    /*
     * updates a doctor
     */ 
    private void updateDoctor() {
    	
    }
    
}
