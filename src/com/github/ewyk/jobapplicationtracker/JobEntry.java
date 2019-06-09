package com.github.ewyk.jobapplicationtracker;

public class JobEntry {
	private int job_entry_id;
	private String company;
	private String position;
	private String location;
	private String industry;
	private String notes;
	private Progress progressBar;
	
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
	
	

}
