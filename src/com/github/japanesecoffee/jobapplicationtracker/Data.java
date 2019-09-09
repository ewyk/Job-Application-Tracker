package com.github.japanesecoffee.jobapplicationtracker;

import java.sql.*;

public class Data {
    //this method connects to db and creates statements to add values to table
    public void addJobEntry(String query) {
        //try-with-resources statement automatically closes declared resources when try block exits
        try(Connection connect = DriverManager.getConnection("jdbc:sqlite:jobs.sqlite");
            PreparedStatement prepStmt = connect.prepareStatement(query);
        ) {

            System.out.println("Connected to database");

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
