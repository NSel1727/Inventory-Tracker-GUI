/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Noah Seligson
 */

package ucf.assignments;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class fileLoaderTest {

    @Test
    void loadHTMLLoadsCreatedList() {
        addItemController add = new addItemController();
        add.saveNewItem("Fan", "1234512345", "$19.99");

        File file = new File("Inventory.txt");
        fileSaver.saveHTML(add.trackerList, file);

        ObservableList<item> loadedList = fileLoader.loadHTML(file);
        assertTrue(loadedList.get(0).name.equals("Fan"));
    }

    @Test
    void loadTSVLoadsCreatedList() {
        addItemController add = new addItemController();
        add.saveNewItem("Fan", "1234512345", "$19.99");

        File file = new File("Inventory.txt");
        fileSaver.saveTSV(add.trackerList, file);

        ObservableList<item> loadedList = fileLoader.loadTSV(file);
        assertTrue(loadedList.get(0).serialNumber.equals("1234512345"));
    }

    @Test
    void loadJSONLoadsCreatedList() {
        addItemController add = new addItemController();
        add.saveNewItem("Fan", "1234512345", "$19.99");

        File file = new File("Inventory.txt");
        fileSaver.saveJSON(add.trackerList, file);

        ObservableList<item> loadedList = fileLoader.loadJSON(file);
        assertTrue(loadedList.get(0).name.equals("Fan"));
    }
}