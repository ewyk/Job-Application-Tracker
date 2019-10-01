package com.github.japanesecoffee.jobapplicationtracker;

import com.jfoenix.controls.*;
import javafx.beans.value.ObservableValue;
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
    private JFXToggleButton toggle_rejected;

    @FXML
    private JFXDatePicker date_responded;

    @FXML
    private AnchorPane anchorpane_right;

    @FXML
    private TableView<JobEntry> tblview;

    @FXML
    private TableColumn<JobEntry, Integer> column_num;

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
    private TableColumn<JobEntry, Date> column_date_responded;

    private FXMLLoader loader;
    private Integer number;
    private String query, company, position, location, industry, notes;
    private Date dateResponded;
    Data dataObject;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataObject = new Data();

        btn_save.setOnAction(e->{
            save();
        });
    }

    private void initTable() {
//        column_num.setCellValueFactory(cell->cell.getValue().getNumber());
        column_company.setCellValueFactory(cell->cell.getValue().getCompany());
        column_position.setCellValueFactory(cell->cell.getValue().getPosition());
        column_location.setCellValueFactory(cell->cell.getValue().getLocation());
        column_industry.setCellValueFactory(cell->cell.getValue().getIndustry());
        column_notes.setCellValueFactory(cell->cell.getValue().getNotes());
        column_date_responded.setCellValueFactory(cell-> (ObservableValue<Date>) cell.getValue().getDateResponded());
    }

    private void refresh() {
        initTable();
        query = "SELECT a.number, a.company, a.position, a.location, a.industry, a.notes, a.date_responded FROM job_entry as a;";
        tblview.setItems(dataObject.printTable(query));
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

        txt_company.setText("");
        txt_position.setText("");
        txt_location.setText("");
        txt_industry.setText("");
        txt_notes.setText("");

        refresh();
    }
}
