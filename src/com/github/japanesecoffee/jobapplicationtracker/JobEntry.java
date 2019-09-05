package com.github.japanesecoffee.jobapplicationtracker;

import java.sql.*;

public class JobEntry {
	private static int job_entry_id;
	private String company;
	private String position;
	private String location;
	private String industry;
	private String notes;
	private Progress progressBar;
	
	//MySQL auto increments job_entry_id, so id might not be needed in constructor
//	public JobEntry(String company, String position, String location, String industry, String notes) {
//		super();
////		this.job_entry_id = job_entry_id;
//		this.company = company;
//		this.position = position;
//		this.location = location;
//		this.industry = industry;
//		this.notes = notes;
//	}

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
	public void addJobEntry(String company, String position, String location, String industry, String notes) {
		//try-with-resources statement automatically closes declared resources when try block exits
		try(Connection connect = DriverManager.getConnection("jdbc:sqlite:jobs.sqlite");
			PreparedStatement prepStmt = connect.prepareStatement("INSERT INTO job_entry " +
					"(company, position, location, industry, notes) VALUES (?, ?, ?, ?, ?)");
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
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	//TODO: might have to make separate update methods for each column
	public void updateJobEntry(int id, String company, String position, String location, String industry, String notes) {
		try(Connection connect = DriverManager.getConnection("jdbc:sqlite:jobs.sqlite");
			PreparedStatement prepStmt = connect.prepareStatement("UPDATE job_entry SET company = ? , " +
					"position = ? , " + "location = ? , " + "industry = ? , " + "notes = ? " + "WHERE job_entry_id = ?");
			) {

			prepStmt.setString(1, company);
			prepStmt.setString(2, position);
			prepStmt.setString(3, location);
			prepStmt.setString(4, industry);
			prepStmt.setString(5, notes);
			prepStmt.setInt(6, id);
			prepStmt.executeUpdate();
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	//deletes entries in job entry table
	public void deleteJobEntry(int job_entry_id) {
		try(Connection connect = DriverManager.getConnection("jdbc:sqlite:jobs.sqlite");
			PreparedStatement prepStmt = connect.prepareStatement("DELETE FROM job_entry WHERE job_entry_id = ?");
		) {
			prepStmt.setInt(1, job_entry_id);
			
			int numRows = prepStmt.executeUpdate();
			System.out.println(numRows + " entry deleted");
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//prints all entries within the job entry table
	public void printTable() {
		try(Connection connect = DriverManager.getConnection("jdbc:sqlite:jobs.sqlite");
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
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	

}
