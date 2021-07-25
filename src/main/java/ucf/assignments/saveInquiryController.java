/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Noah Seligson
 */

package ucf.assignments;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

public class saveInquiryController {

    @FXML public Text warningText;

    public ObservableList<item> trackerList;
    public ArrayList<String> serialList;
    public sceneOperator operator;

    public saveInquiryController(ObservableList<item> trackerList, ArrayList<String> serialList, sceneOperator operator){
        this.trackerList = trackerList;
        this.serialList = serialList;
        this.operator = operator;
    }

    @FXML
    public void saveButtonClicked(ActionEvent actionEvent) {
        File file = fileSaver.getFile();
        if(file != null) {
            if (file.toString().contains("html")) {
                fileSaver.saveHTML(trackerList, file);
            } else if (file.toString().contains("txt")) {
                fileSaver.saveTSV(trackerList, file);
            } else if (file.toString().contains("json")) {
                fileSaver.saveJSON(trackerList, file);
            }
        }
        warningText.getScene().getWindow().hide();
        loadOperations();
    }

    @FXML
    public void doNotSaveButtonClicked(ActionEvent actionEvent) {
        loadOperations();
        warningText.getScene().getWindow().hide();
    }

    public void loadOperations(){
        trackerList.clear();
        serialList.clear();
        File file = inventoryTrackerController.inquiryFile;
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

    public ArrayList<String> loadedSerialList(ObservableList<item> inventory){
        ArrayList<String> serialList = new ArrayList<>();

        for(item i : inventory){
            serialList.add(i.serialNumber.toLowerCase(Locale.ROOT));
        }
        return serialList;
    }
}
