package com.github.japanesecoffee.jobapplicationtracker;

import db.SQLiteConnection;

public class Main {

	public static void main(String[] args) {
		//press a button, which will create instance of job entry
		JobEntry job1 = new JobEntry("Square", "mobile developer", "New York, New York", "tech industry", "");
		
		//press another button, which will add new entry to db
//		job1.addJobEntry();
		
		//press refresh button to see newly added job within application?
//		System.out.println("PRINTING TABLE...");
//		job1.printTable();
		
		//press button to delete selected entry
//		job1.deleteJobEntry(4);
		
		job1.printTable();

	}

}
