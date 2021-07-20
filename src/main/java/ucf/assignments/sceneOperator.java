/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Noah Seligson
 */

package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class sceneOperator {
    //Credit to method ideas to Professor Hollander
    Map<String, Scene> scenes = new HashMap<>();

    public void initiateScenes(){
        ObservableList<item> trackerList = FXCollections.observableArrayList();
        ArrayList<String> serialList = new ArrayList<>();

        inventoryTrackerController inventory = new inventoryTrackerController(trackerList, serialList, this);
        addItemController add = new addItemController(trackerList, serialList, this);
        saveInquiryController save = new saveInquiryController(trackerList, this);

        Parent root;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("inventoryTracker.fxml"));
            loader.setController(inventory);

            root = loader.load();
            scenes.put("inventoryTracker", new Scene(root));

            loader = new FXMLLoader(getClass().getResource("addItem.fxml"));
            loader.setController(add);

            root = loader.load();
            scenes.put("addItem", new Scene(root));

            loader = new FXMLLoader(getClass().getResource("saveInquiry.fxml"));
            loader.setController(save);

            root = loader.load();
            scenes.put("saveInquiry", new Scene(root));
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public Scene getScene(String sceneName){
        return scenes.get(sceneName);
    }
}
