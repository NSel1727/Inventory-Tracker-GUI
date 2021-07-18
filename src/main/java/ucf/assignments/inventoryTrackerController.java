/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Noah Seligson
 */

package ucf.assignments;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class inventoryTrackerController implements Initializable {
    public TableView itemTable;
    public TextField itemCount;
    public Button deleteItemButton;
    public Button editItemButton;
    public ObservableList<item> trackerList;
    public TableColumn valueColumn;
    public TableColumn serialColumn;
    public TableColumn nameColumn;
    public sceneOperator operator;
    public static int index;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        serialColumn.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        itemTable.setItems(trackerList);
        itemTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
              deleteItemButton.setVisible(true);
              editItemButton.setVisible(true);
            }
        });
    }

    public inventoryTrackerController(ObservableList<item> trackerList, TableView itemTable, sceneOperator operator){
        this.trackerList = trackerList;
        this.itemTable = itemTable;
        this.operator = operator;
    }

    public void deleteItemButtonClicked(ActionEvent actionEvent) {
        trackerList.remove(trackerList.indexOf(itemTable.getSelectionModel().getSelectedItem()));
        if(trackerList.size() == 0){
            deleteItemButton.setVisible(false);
            editItemButton.setVisible(false);
        }
    }

    public void addItemButtonClicked(ActionEvent actionEvent) {
        Stage stage = new Stage();
        stage.setTitle("Add Item");
        stage.setScene(operator.getScene("addItem"));
        stage.show();
    }

    public void editItemButtonClicked(ActionEvent actionEvent) {
        editItemController.index = trackerList.indexOf(itemTable.getSelectionModel().getSelectedItem());
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addItem.fxml"));
            editItemController edit = new editItemController(trackerList, itemTable);
            loader.setController(edit);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Edit Item");
            stage.setScene(scene);
            stage.show();
        }catch(IOException ex){
            ex.printStackTrace();
        }
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
