/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Noah Seligson
 */

package ucf.assignments;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class inventoryTrackerController implements Initializable {
    public TableView itemTable;
    public TextField itemCount;
    public Button deleteItemButton;
    public Button editItemButton;
    public Button addItemButton;
    public ObservableList<item> trackerList;
    public ArrayList<String> serialList;
    public TableColumn valueColumn;
    public TableColumn serialColumn;
    public TableColumn nameColumn;
    public sceneOperator operator;

    @Override @FXML
    public void initialize(URL location, ResourceBundle resources) {
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        serialColumn.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        itemTable.setItems(trackerList);
        trackerList.addListener(new ListChangeListener<item>() {
            @Override
            public void onChanged(Change<? extends item> c) {
                itemCount.setText(trackerList.size() + "/100");
                if(trackerList.size() == 100){
                    addItemButton.setVisible(false);
                }else{
                    addItemButton.setVisible(true);
                }
            }
        });
        itemTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
              deleteItemButton.setVisible(true);
              editItemButton.setVisible(true);
            }
        });
    }

    public inventoryTrackerController(ObservableList<item> trackerList, ArrayList<String> serialList, sceneOperator operator){
        this.trackerList = trackerList;
        this.serialList = serialList;
        this.operator = operator;
    }

    @FXML
    public void deleteItemButtonClicked(ActionEvent actionEvent) {
        serialList.remove(trackerList.indexOf(itemTable.getSelectionModel().getSelectedItem()));
        trackerList.remove(trackerList.indexOf(itemTable.getSelectionModel().getSelectedItem()));
        if(trackerList.size() == 0){
            deleteItemButton.setVisible(false);
            editItemButton.setVisible(false);
        }
    }

    @FXML
    public void addItemButtonClicked(ActionEvent actionEvent) {
        Stage stage = new Stage();
        stage.setTitle("Add Item");
        stage.setScene(operator.getScene("addItem"));
        stage.show();
    }

    @FXML
    public void editItemButtonClicked(ActionEvent actionEvent) {
        editItemController.index = trackerList.indexOf(itemTable.getSelectionModel().getSelectedItem());
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addItem.fxml"));
            editItemController edit = new editItemController(trackerList, serialList);
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

    @FXML
    public void byNameOptionClicked(ActionEvent actionEvent) {
    }

    @FXML
    public void bySerialOptionClicked(ActionEvent actionEvent) {

    }

    @FXML
    public void htmlOptionSelected(ActionEvent actionEvent) {

    }

    @FXML
    public void tsvOptionSelected(ActionEvent actionEvent) {

    }

    @FXML
    public void jsonOptionSelected(ActionEvent actionEvent) {

    }

    @FXML
    public void loadButtonClicked(ActionEvent actionEvent) {

    }

    @FXML
    public void exitButtonClicked(ActionEvent actionEvent) {
        Platform.exit();
    }
}
