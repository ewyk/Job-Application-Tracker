package com.github.japanesecoffee.jobapplicationtracker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
    public ObservableList<JobEntry> printTable(String query) {
        ObservableList list = FXCollections.observableArrayList();
        try(Connection connect = DriverManager.getConnection("jdbc:sqlite:jobs.sqlite");
            PreparedStatement sqlStmt = connect.prepareStatement(query);
            ResultSet results = sqlStmt.executeQuery();
        ) {
            while(results.next()) {
                list.add(new JobEntry(results.getString(1), results.getString(2),
                        results.getString(3), results.getString(4), results.getString(5)));
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
}
