/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Noah Seligson
 */

package ucf.assignments;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class editItemController extends addItemController implements Initializable{
    public ObservableList<item> trackerList;
    public ArrayList<String> serialList;
    public TableView itemTable;
    public Text badSerial;
    public Text badValue;
    public Text badName;
    public TextField nameBox;
    public TextField serialBox;
    public TextField valueBox;
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
        serialList.add(serialBox.getText());
    }

    public void saveEditedItem(String name, String serialNumber, String value){
        if(!(value.contains("$"))){
            value = "$" + value;
        }
        serialList.add(serialNumber);
        trackerList.get(index).name = name;
        trackerList.get(index).serialNumber = serialNumber;
        trackerList.get(index).value = value;

        trackerList.set(index, trackerList.get(index));
    }
}
