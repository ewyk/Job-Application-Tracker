package com.github.japanesecoffee.jobapplicationtracker;

import db.SQLiteConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	private FXMLLoader loader;

	@Override
	public void start(Stage primaryStage) {

		try {
			loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("table.fxml"));
			TableController controller = new TableController();
			loader.setController(controller);
			loader.load();
			Scene scene = new Scene(loader.getRoot());
			scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Job Application Tracker");
			primaryStage.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

//public class Main {

	public static void main(String[] args) {
		launch(args);
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
