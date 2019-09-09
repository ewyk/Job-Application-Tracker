package com.github.japanesecoffee.jobapplicationtracker;

import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class TableController implements Initializable {

    @FXML
    private AnchorPane anchorpane_parent;

    @FXML
    private Pane pane_top;

    @FXML
    private Label label_title;

    @FXML
    private AnchorPane anchorpane_center;

    @FXML
    private AnchorPane anchorpane_left;

    @FXML
    private JFXTabPane tabpane_left;

    @FXML
    private Tab tab_jobentry;

    @FXML
    private AnchorPane anchorpane_jobentry;

    @FXML
    private JFXTextField txt_company;

    @FXML
    private JFXTextField txt_position;

    @FXML
    private JFXTextField txt_location;

    @FXML
    private JFXTextField txt_industry;

    @FXML
    private JFXTextField txt_notes;

    @FXML
    private JFXButton btn_new;

    @FXML
    private JFXButton btn_save;

    @FXML
    private JFXButton btn_update;

    @FXML
    private JFXButton btn_delete;

    @FXML
    private Tab tab_progress;

    @FXML
    private AnchorPane anchorpane_progress;

    @FXML
    private JFXToggleButton toggle_application;

    @FXML
    private JFXDatePicker date_applied;

    @FXML
    private JFXDatePicker date_interview1;

    @FXML
    private JFXDatePicker date_interview2;

    @FXML
    private JFXDatePicker date_interview3;

    @FXML
    private JFXDatePicker date_offerext;

    @FXML
    private JFXDatePicker date_accept;

    @FXML
    private AnchorPane anchorpane_right;

    @FXML
    private TableView<JobEntry> tblview;

    @FXML
    private TableColumn<JobEntry, Integer> column_id;

    @FXML
    private TableColumn<JobEntry, String> column_company;

    @FXML
    private TableColumn<JobEntry, String> column_position;

    @FXML
    private TableColumn<JobEntry, String> column_location;

    @FXML
    private TableColumn<JobEntry, String> column_industry;

    @FXML
    private TableColumn<JobEntry, String> column_notes;

    @FXML
    private TableColumn<Progress, Date> column_applied;

    @FXML
    private TableColumn<Progress, Date> column_interview1;

    @FXML
    private TableColumn<Progress, Date> column_interview2;

    @FXML
    private TableColumn<Progress, Date> column_interview3;

    @FXML
    private TableColumn<Progress, Date> column_offerext;

    @FXML
    private TableColumn<Progress, Date> column_accept;

    private FXMLLoader loader;
    private String query, company, position, location, industry, notes;
    private Date applied, interview1, interview2, interview3, offer_extended, acceptedOrRejected;
    Data dataObject;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataObject = new Data();

        btn_save.setOnAction(e->{
            save();
        });
    }

    private void save() {
        company = txt_company.getText();
        position = txt_position.getText();
        location = txt_location.getText();
        industry = txt_industry.getText();
        notes = txt_notes.getText();

        query = "INSERT INTO job_entry VALUES (null, '"+company+"', '"+position+"', '"+location+"', '"+
                industry+"', '"+notes+"');";
        dataObject.addJobEntry(query);
    }
}
