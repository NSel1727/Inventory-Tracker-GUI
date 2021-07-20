/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Noah Seligson
 */

package ucf.assignments;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class inventoryTrackerController implements Initializable {
    public TableView itemTable;
    public TextField itemCount;
    public TextField searchBar;
    public Button deleteItemButton;
    public Button editItemButton;
    public Button addItemButton;
    public Button exitSearchButton;
    public ObservableList<item> trackerList;
    public ArrayList<String> serialList;
    public TableColumn valueColumn;
    public TableColumn serialColumn;
    public TableColumn nameColumn;
    public sceneOperator operator;
    public Menu loadButton;


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
    public void searchButtonClicked(ActionEvent actionEvent){
        ObservableList<item> filtered = FXCollections.observableArrayList();
        for (item item : trackerList){
            if(searchIsContained(item, searchBar.getText())){
                filtered.add(item);
            }
        }
        itemTable.setItems(filtered);
        exitSearchButton.setVisible(true);
        searchBar.setText("");
    }

    @FXML
    public void exitSearchButtonClicked(ActionEvent actionEvent){
        itemTable.setItems(trackerList);
        exitSearchButton.setVisible(false);
    }

    @FXML
    public void htmlOptionSelected(ActionEvent actionEvent) {
        fileSaver.saveHTML(trackerList, fileSaver.getFile("HTML Files (*.html)", "*.html"));
    }

    @FXML
    public void tsvOptionSelected(ActionEvent actionEvent) {
        fileSaver.saveTSV(trackerList, fileSaver.getFile("TSV Files (*.txt)", "*.txt"));
    }

    @FXML
    public void jsonOptionSelected(ActionEvent actionEvent) {
        fileSaver.saveJSON(trackerList, fileSaver.getFile("JSON Files (*.json)", "*.json"));
    }

    @FXML
    public void loadButtonClicked(ActionEvent actionEvent) {
        File file = fileLoader.chooseFile();
        if(file != null){
            trackerList.clear();
            serialList.clear();
            if(file.toString().contains("html")){
                ObservableList<item> htmlInventory = fileLoader.loadHTML(file);
                trackerList.addAll(htmlInventory);
                serialList.addAll(loadedSerialList(htmlInventory));
            }else if(file.toString().contains("txt")){
                ObservableList<item> tsvInventory = fileLoader.loadTSV(file);
                trackerList.addAll(tsvInventory);
                serialList.addAll(loadedSerialList(tsvInventory));
            }else if(file.toString().contains("json")){
                ObservableList<item> jsonInventory = fileLoader.loadJSON(file);
                trackerList.addAll(jsonInventory);
                serialList.addAll(loadedSerialList(jsonInventory));
            }
        }
    }

    @FXML
    public void exitButtonClicked(ActionEvent actionEvent) {
        Platform.exit();
    }

    public boolean searchIsContained(item item, String search){
        if(item.name.toLowerCase().contains(search.toLowerCase()) || item.serialNumber.toLowerCase().contains(search.toLowerCase())){
            return true;
        }
        return false;
    }

    public ArrayList<String> loadedSerialList(ObservableList<item> inventory){
        ArrayList<String> serialList = new ArrayList<>();

        for(item i : inventory){
            serialList.add(i.serialNumber.toLowerCase(Locale.ROOT));
        }
        return serialList;
    }
}
