package com.github.japanesecoffee.jobapplicationtracker;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class JobEntry {
	private final IntegerProperty number;
	private final StringProperty company;
	private final StringProperty position;
	private final StringProperty location;
	private final StringProperty industry;
	private final StringProperty notes;
	private final StringProperty dateResponded;
	private final BooleanProperty rejected;

	public JobEntry(Integer number, String company, String position, String location, String industry, String notes, String dateResponded, Boolean rejected) {
        this.number = new SimpleIntegerProperty(number);
		this.company = new SimpleStringProperty(company);
		this.position = new SimpleStringProperty(position);
		this.location = new SimpleStringProperty(location);
		this.industry = new SimpleStringProperty(industry);
		this.notes = new SimpleStringProperty(notes);
        this.dateResponded = new SimpleStringProperty(dateResponded);
        this.rejected = new SimpleBooleanProperty(rejected);
    }

	public IntegerProperty getNumber() {
		return number;
	}

	public StringProperty getCompany() {
		return company;
	}

	public StringProperty getPosition() {
		return position;
	}

	public StringProperty getLocation() {
		return location;
	}

	public StringProperty getIndustry() {
		return industry;
	}

	public StringProperty getNotes() {
		return notes;
	}

	public StringProperty getDateResponded() {
		return dateResponded;
	}

	public BooleanProperty getRejected() {
		return rejected;
	}
}
