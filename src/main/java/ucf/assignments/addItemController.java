/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Noah Seligson
 */

package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Locale;

public class addItemController{

    @FXML public Text badSerial;
    @FXML public Text badValue;
    @FXML public Text badName;
    @FXML public TextField nameBox;
    @FXML public TextField serialBox;
    @FXML public TextField valueBox;

    public ObservableList<item> trackerList;
    public ArrayList<String> serialList;
    public sceneOperator operator;

    public addItemController(ObservableList<item> trackerList, ArrayList<String> serialList, sceneOperator operator){
        this.trackerList = trackerList;
        this.serialList = serialList;
        this.operator = operator;
    }

    public addItemController(ObservableList<item> trackerList, ArrayList<String> serialList){
        this.trackerList = trackerList;
        this.serialList = serialList;
    }

    public addItemController(){
        this.trackerList = FXCollections.observableArrayList();
        this.serialList = new ArrayList<>();
    }

    @FXML
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
        item item = new item(value, serialNumber, name);

        trackerList.add(item);
        serialList.add(serialNumber.toLowerCase(Locale.ROOT));
    }

    public boolean properName(String name){
        badName.setVisible(true);
        String result = lengthTest(name);
        if(result.equals("Too Short")){
            badName.setText("Too Short");
            return false;
        }
        if(result.equals("Too Long")){
            badName.setText("Too Long");
            return false;
        }
        badName.setVisible(false);
        return true;
    }

    public String lengthTest(String name){
        if(name.length() < 2){
            return "Too Short";
        }
        if(name.length() > 256){
            return "Too Long";
        }
        return "";
    }

    public boolean properSerial(String serial){
        String result = serialTest(serial);
        if(result.equals("Already Exists")){
            badSerial.setText("Already Exists");
            badSerial.setVisible(true);
            return false;
        }
        if(result.equals("Incorrect Format")){
            badSerial.setText("Incorrect Format");
            badSerial.setVisible(true);
            return false;
        }
        badSerial.setVisible(false);
        return true;
    }

    public String serialTest(String serial){
        if(serialList.contains(serial.toLowerCase(Locale.ROOT))){
            return "Already Exists";
        }
        if(!(serial.matches("^[a-zA-Z0-9]*$")) || serial.length() != 10){
            return "Incorrect Format";
        }
        return "";
    }

    public boolean properValue(String value) {
        String result = valueTest(value);
        if(result.equals("Improper")){
            badValue.setVisible(true);
            return false;
        }
        badValue.setVisible(false);
        return true;
    }

    public String valueTest(String value){
        if (value.length() > 0 && value.charAt(0) == '$') {
            String temp = value.substring(1);
            try {
                Double.parseDouble(temp);
                return "";
            } catch (Exception ex) {
                return "Improper";
            }
        }
        try {
            Double.parseDouble(value);
            return "";
        } catch (Exception ex) {
            return "Improper";
        }
    }
}