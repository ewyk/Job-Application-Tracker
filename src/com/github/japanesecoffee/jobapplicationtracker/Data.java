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
    //updates entries in job entry table
    public void updateJobEntry(String query) {
        try(Connection connect = DriverManager.getConnection("jdbc:sqlite:jobs.sqlite");
            PreparedStatement prepStmt = connect.prepareStatement(query);
        ) {

//            prepStmt.executeUpdate();
            int numRows = prepStmt.executeUpdate();
            System.out.println(numRows + " entry updated");
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
                list.add(new JobEntry(results.getInt(1), results.getString(2), results.getString(3),
                        results.getString(4), results.getString(5), results.getString(6), results.getString(7)));
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
}
