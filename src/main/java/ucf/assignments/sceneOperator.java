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
import javafx.scene.control.TableView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class sceneOperator {
    //Credit to method ideas to Professor Hollander
    Map<String, Scene> scenes = new HashMap<>();

    public void initiateScenes(){
        ObservableList<item> trackerList = FXCollections.observableArrayList();
        TableView view = new TableView();

        inventoryTrackerController inventory = new inventoryTrackerController(trackerList, view, this);
        addItemController add = new addItemController(trackerList, view, this);

        Parent root;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("inventoryTracker.fxml"));
        loader.setController(inventory);

        try{
            root = loader.load();
            scenes.put("inventoryTracker", new Scene(root));
        }catch(IOException ex){
            ex.printStackTrace();
        }

        loader = new FXMLLoader(getClass().getResource("addItem.fxml"));
        loader.setController(add);

        try{
            root = loader.load();
            scenes.put("addItem", new Scene(root));
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public Scene getScene(String sceneName){
        return scenes.get(sceneName);
    }
}
