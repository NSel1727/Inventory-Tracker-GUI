/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Noah Seligson
 */

package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class editItemController extends addItemController implements Initializable{

    @FXML public TextField nameBox;
    @FXML public TextField serialBox;
    @FXML public TextField valueBox;

    public ObservableList<item> trackerList;
    public ArrayList<String> serialList;
    public static int index;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameBox.setText(trackerList.get(index).name);
        serialBox.setText(trackerList.get(index).serialNumber);
        valueBox.setText(trackerList.get(index).value);
    }

    public editItemController(ObservableList<item> trackerList, ArrayList<String> serialList){
        super(trackerList, serialList);
        this.trackerList = trackerList;
        this.serialList = serialList;
    }

    public editItemController(){
        this.trackerList = FXCollections.observableArrayList();
        this.serialList = new ArrayList<>();
        index = 0;
    }

    @FXML @Override
    public void completeButtonClicked(ActionEvent actionEvent) {
        serialList.remove(index);
        if(properName(nameBox.getText()) & properSerial(serialBox.getText()) & properValue(valueBox.getText())){
            saveEditedItem(nameBox.getText(), serialBox.getText(), valueBox.getText());

            nameBox.clear();
            serialBox.clear();
            valueBox.clear();

            valueBox.getScene().getWindow().hide();
            return;
        }
        serialList.add(serialBox.getText().toLowerCase(Locale.ROOT));
    }

    public void saveEditedItem(String name, String serialNumber, String value){
        if(value.contains("$")){
            value = value.substring(1);
        }
        value = "$" + Math.round(Double.parseDouble(value) * 100.00) / 100.00;

        serialList.add(serialNumber.toLowerCase(Locale.ROOT));
        trackerList.get(index).name = name;
        trackerList.get(index).serialNumber = serialNumber;
        trackerList.get(index).value = value;

        trackerList.set(index, trackerList.get(index));
    }
}