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

public class saveInquiryController {

    @FXML public Text warningText;

    public ObservableList<item> trackerList;
    public sceneOperator operator;

    public saveInquiryController(ObservableList<item> trackerList, sceneOperator operator){
        this.trackerList = trackerList;
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
    }

    @FXML
    public void doNotSaveButtonClicked(ActionEvent actionEvent) {
        warningText.getScene().getWindow().hide();
    }
}
