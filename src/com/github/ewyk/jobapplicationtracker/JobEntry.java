package com.github.ewyk.jobapplicationtracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class JobEntry {
	private static int job_entry_id;
	private String company;
	private String position;
	private String location;
	private String industry;
	private String notes;
	private Progress progressBar;
	
	//MySQL auto increments job_entry_id, so id might not be needed in constructor
	public JobEntry(String company, String position, String location, String industry, String notes) {
		super();
//		this.job_entry_id = job_entry_id;
		this.company = company;
		this.position = position;
		this.location = location;
		this.industry = industry;
		this.notes = notes;
	}

	public int getJob_entry_id() {
		return job_entry_id;
	}

	public String getCompany() {
		return company;
	}

	public String getPosition() {
		return position;
	}

	public String getLocation() {
		return location;
	}

	public String getIndustry() {
		return industry;
	}

	public String getNotes() {
		return notes;
	}
	
	//this method connects to db and creates statements to add values to table
	public void addJobEntry() {
		//try-with-resources statement automatically closes declared resources when try block exits
		try(Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/job_tracker_db", "root", "PontiacFire2");
			PreparedStatement prepStmt = connect.prepareStatement("INSERT INTO job_entry (company, position, location, industry, notes) VALUES (?, ?, ?, ?, ?)");
		) {
						
			System.out.println("Connected to database");
			
			prepStmt.setString(1, company);
			prepStmt.setString(2, position);
			prepStmt.setString(3, location);
			prepStmt.setString(4, industry);
			prepStmt.setString(5, notes);
			
//			prepStmt.executeUpdate();
			int numRows = prepStmt.executeUpdate();
			System.out.println(numRows + " entry added");
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	//TODO: might have to make separate update methods for each column
//	public void updateJobEntry() {
//		try {
//			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/job_tracker_db", "root", "PontiacFire2");
//			sqlStatement = connect.createStatement();
//			
//			String sql = "UPDATE job_tracker_db " 
//					+ "SET"
//		}
//		catch(Exception e) {
//			System.out.println(e);
//		}
//		finally {
//			try {
//				sqlStatement.close();
//			}
//			catch (Exception e){
//				System.out.println(e);
//			}
//			try {
//				connect.close();
//			}
//			catch (Exception e){
//				System.out.println(e);
//			}
//		}
//	}
	//deletes entries in job entry table
	public void deleteJobEntry(int job_entry_id) {
		try(Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/job_tracker_db", "root", "PontiacFire2");
			PreparedStatement prepStmt = connect.prepareStatement("DELETE FROM job_entry WHERE job_entry_id = ?");
		) {
			prepStmt.setInt(1, job_entry_id);
			
			int numRows = prepStmt.executeUpdate();
			System.out.println(numRows + " entry deleted");
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	//prints all entries within the job entry table
	public void printTable() {
		try(Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/job_tracker_db", "root", "PontiacFire2");
			Statement sqlStmt = connect.createStatement();
			ResultSet results = sqlStmt.executeQuery("SELECT * FROM job_entry");
		) {
			while(results.next()) {
				System.out.println(results.getInt("job_entry_id") + " " 
						+ results.getString("company") + " "
						+ results.getString("position") + " "
						+ results.getString("location") + " "
						+ results.getString("industry") + " "
						+ results.getString("notes") + " ");
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	

}
