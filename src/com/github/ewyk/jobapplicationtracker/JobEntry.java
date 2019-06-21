package com.github.ewyk.jobapplicationtracker;

import java.sql.Connection;
import java.sql.DriverManager;
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
	private Connection connect;
	private Statement sqlStatement;
	
	public JobEntry(int job_entry_id, String company, String position, String location, String industry, String notes) {
		super();
		this.job_entry_id = job_entry_id;
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
	
	public void addJobEntry() {
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/job_tracker_db", "root", "PontiacFire2");
			sqlStatement = connect.createStatement();
			
			System.out.println("Connected to database");
			System.out.println(JobEntry.job_entry_id);
			String insertInto = "INSERT INTO job_entry (company, position, location, industry, notes)";
			String values = "VALUES ('" + company + "', '" + position + "', '" + location + "', '" + industry + "', '" + notes + "');";
			
			sqlStatement.executeUpdate(insertInto + values);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	

}
