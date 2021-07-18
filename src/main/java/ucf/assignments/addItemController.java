/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Noah Seligson
 */

package ucf.assignments;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class addItemController{

    public ObservableList<item> trackerList;
    public sceneOperator operator;
    public Text badSerial;
    public Text badValue;
    public Text badName;
    public TextField nameBox;
    public TextField serialBox;
    public TextField valueBox;
    public ArrayList<String> serialList;

    public addItemController(ObservableList<item> trackerList, ArrayList<String> serialList, sceneOperator operator){
        this.trackerList = trackerList;
        this.serialList = serialList;
        this.operator = operator;
    }

    public addItemController(ObservableList<item> trackerList, ArrayList<String> serialList){
        this.trackerList = trackerList;
        this.serialList = serialList;
    }

    public void completeButtonClicked(ActionEvent actionEvent) {
        if(properName(nameBox.getText()) & properSerial(serialBox.getText()) & properValue(valueBox.getText())){
            saveNewItem(nameBox.getText(), serialBox.getText(), valueBox.getText());

            nameBox.clear();
            serialBox.clear();
            valueBox.clear();

            valueBox.getScene().getWindow().hide();
        }
    }

    public void saveNewItem(String name, String serialNumber, String value){
        if(!(value.contains("$"))){
            value = "$" + value;
        }
        item item = new item();
        item.name = name;
        item.serialNumber = serialNumber;
        item.value = value;

        serialList.add(serialNumber);
        trackerList.add(item);
    }

    public boolean properName(String name){
        badName.setVisible(true);
        if(name.length() < 2){
            badName.setText("Too Short");
            return false;
        }
        if(name.length() > 256){
            badName.setText("Too Long");
            return false;
        }
        badName.setVisible(false);
        return true;
    }

    public boolean properSerial(String serial){
        if(serialList.contains(serial)){
            badSerial.setText("Already Exists");
            badSerial.setVisible(true);
            return false;
        }
        if(!(serial.matches("^[a-zA-Z0-9]*$")) || serial.length() != 10){
            badSerial.setText("Incorrect Format");
            badSerial.setVisible(true);
            return false;
        }
        badSerial.setVisible(false);
        return true;
    }

    public boolean properValue(String value) {
        if (value.length() > 0 && value.charAt(0) == '$') {
            String temp = value.substring(1);
            try {
                Double.parseDouble(temp);
                badValue.setVisible(false);
                return true;
            } catch (Exception ex) {
                badValue.setVisible(true);
                return false;
            }
        }
        try {
            Double.parseDouble(value);
            badValue.setVisible(false);
            return true;
        } catch (Exception ex) {
            badValue.setVisible(true);
            return false;
        }
    }
}
