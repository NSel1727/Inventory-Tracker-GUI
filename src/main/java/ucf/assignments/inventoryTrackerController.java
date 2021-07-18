/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Noah Seligson
 */

package ucf.assignments;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class inventoryTrackerController implements Initializable {
    public TableView itemTable;
    public TextField itemCount;
    public Button deleteItemButton;
    public Button editItemButton;
    public ObservableList<item> trackerList;
    public TableView tableView;
    public TableColumn valueColumn;
    public TableColumn serialColumn;
    public TableColumn nameColumn;
    public sceneOperator operator;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        serialColumn.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        itemTable.setItems((ObservableList) trackerList);
    }

    public inventoryTrackerController(ObservableList<item> trackerList, TableView tableView, sceneOperator operator){
        this.trackerList = trackerList;
        this.tableView = tableView;
        this.operator = operator;
    }

    public void deleteItemButtonClicked(ActionEvent actionEvent) {

    }

    public void editItemButtonClicked(ActionEvent actionEvent) {

    }

    public void addItemButtonClicked(ActionEvent actionEvent) {
        Stage stage = new Stage();
        stage.setTitle("Add Item");
        stage.setScene(operator.getScene("addItem"));
        stage.show();
    }

    public void byNameOptionClicked(ActionEvent actionEvent) {
    }

    public void bySerialOptionClicked(ActionEvent actionEvent) {

    }

    public void htmlOptionSelected(ActionEvent actionEvent) {

    }

    public void tsvOptionSelected(ActionEvent actionEvent) {

    }

    public void jsonOptionSelected(ActionEvent actionEvent) {

    }

    public void loadButtonClicked(ActionEvent actionEvent) {

    }

    public void exitButtonClicked(ActionEvent actionEvent) {
        Platform.exit();
    }
}
