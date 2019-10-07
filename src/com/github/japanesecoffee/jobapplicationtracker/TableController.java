package com.github.japanesecoffee.jobapplicationtracker;

import com.jfoenix.controls.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
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
    private JFXButton btn_add;

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
    private TableColumn<JobEntry, Number> column_num;

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
    private TableColumn<JobEntry, String> column_date_responded;

//    @FXML
//    private TableColumn<JobEntry, Boolean> column_rejected;

    private FXMLLoader loader;
    private String query, company, position, location, industry, notes, dateResponded;
    private Integer number, rejected;
    Data dataObject;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataObject = new Data();

        btn_add.setOnAction(e->{
            save();
        });

        btn_update.setOnAction(e->{
            update();
        });

        btn_delete.setOnAction(e->{
            delete();
        });

//        toggle_rejected.setOnAction(e->{
//            toggle();
//        });

        refresh();

        //fills form with info from the selected tableview entry
        tblview.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                JobEntry job = tblview.getSelectionModel().getSelectedItem();
                number = job.getNumber().get();
                txt_company.setText(job.getCompany().get());
                txt_position.setText(job.getPosition().get());
                txt_location.setText(job.getLocation().get());
                txt_industry.setText(job.getIndustry().get());
                txt_notes.setText(job.getNotes().get());
                //dateResponded is returned as a String and needs to be parsed
                try {
                    date_responded.setValue(LocalDate.parse(job.getDateResponded().get()));
                }
                catch (Exception e) {
                    date_responded.setValue(null);
                }
                if(job.getRejected().get()) {
                    toggle_rejected.setSelected(true);
                }
                else {
                    toggle_rejected.setSelected(false);
                }
            }
        });
    }

    private void initTable() {
        column_num.setCellValueFactory(cell->cell.getValue().getNumber());
        column_company.setCellValueFactory(cell->cell.getValue().getCompany());
        column_position.setCellValueFactory(cell->cell.getValue().getPosition());
        column_location.setCellValueFactory(cell->cell.getValue().getLocation());
        column_industry.setCellValueFactory(cell->cell.getValue().getIndustry());
        column_notes.setCellValueFactory(cell->cell.getValue().getNotes());
        column_date_responded.setCellValueFactory(cell->cell.getValue().getDateResponded());
//        column_rejected.setCellValueFactory(cell->cell.getValue().getRejected());
    }

    private void refresh() {
        initTable();
        query = "SELECT a.job_entry_id, a.company, a.position, a.location, a.industry, a.notes, a.date_responded, a.rejected FROM job_entry as a;";
        tblview.setItems(dataObject.printTable(query));
    }

    private void getTextfields() {
        company = txt_company.getText();
        position = txt_position.getText();
        location = txt_location.getText();
        industry = txt_industry.getText();
        notes = txt_notes.getText();
        //datepicker value parsed as String because SQLite stores date as String value
        dateResponded = String.valueOf(date_responded.getValue());
        //SQLite stores boolean as 0 or 1
        rejected = toggle_rejected.isSelected() ? 1 : 0;
    }

    private void setEmptyTextfields() {
        txt_company.setText("");
        txt_position.setText("");
        txt_location.setText("");
        txt_industry.setText("");
        txt_notes.setText("");
        date_responded.setValue(null);
        toggle_rejected.setSelected(false);
    }

    private void save() {
        getTextfields();

        query = "INSERT INTO job_entry VALUES (null, '"+company+"', '"+position+"', '"+location+"', '"+
                industry+"', '"+notes+"', '"+dateResponded+"', '"+rejected+"');";
        dataObject.addJobEntry(query);

        setEmptyTextfields();
        refresh();
    }

    private void update() {
        getTextfields();

        query = "UPDATE job_entry SET company='"+company+"', position='"+position+"', location='"+location+"', " +
                "industry='"+ industry+"', notes='"+notes+"', date_responded='"+dateResponded+"', rejected='"+rejected+"' WHERE job_entry_id="+number+"";
        dataObject.updateJobEntry(query);

        setEmptyTextfields();
        refresh();
    }

    private void delete() {
        query = "DELETE FROM job_entry WHERE job_entry_id="+number+"";
        dataObject.deleteJobEntry(query);

        setEmptyTextfields();
        refresh();
    }

//    private void toggle() {
//        if(toggle_rejected.isSelected()) {
//
//        }
//    }
}
