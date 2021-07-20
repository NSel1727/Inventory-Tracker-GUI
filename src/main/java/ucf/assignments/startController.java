/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Noah Seligson
 */

package ucf.assignments;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class startController {

    public Button startButton;

    public void startButtonClicked(ActionEvent actionEvent) {
        try{
            sceneOperator operator = new sceneOperator();
            operator.initiateScenes();
            Scene scene = operator.getScene("inventoryTracker");

            Stage stage = new Stage();
            stage.setTitle("Inventory Tracker");
            stage.setScene(scene);
            stage.show();
            startButton.getScene().getWindow().hide();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void exitButtonClicked(ActionEvent actionEvent) {
        Platform.exit();
    }
}
