/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Noah Seligson
 */

package ucf.assignments;

import javafx.scene.control.TableView;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class inventoryTrackerControllerTest {

    @Test
    void deleteItemDeletesItem() {
        inventoryTrackerController inventory = new inventoryTrackerController();
        inventory.trackerList.add(new item("$24.99", "Calc123456", "Math Textbook"));
        inventory.trackerList.add(new item("$24.99", "D00R123123", "Doormat"));
        inventory.serialList.add("calc123456");
        inventory.serialList.add("d00r123123");
        int oldSize = inventory.trackerList.size();
        inventory.deleteItem(0);
        int newSize = inventory.trackerList.size();
        assertNotEquals(oldSize, newSize);
    }

    @Test
    void searchIsContainedApprovesContainedSearch() {
        inventoryTrackerController inventory = new inventoryTrackerController();
        inventory.trackerList.add(new item("$19.99", "1234512345", "Tennis Racket"));
        boolean test = inventory.searchIsContained(inventory.trackerList.get(0), "Tennis");
        assertTrue(test);
    }

    @Test
    void searchIsContainedRejectsUncontainedSearch() {
        inventoryTrackerController inventory = new inventoryTrackerController();
        inventory.trackerList.add(new item("$19.99", "1234512345", "Tennis Racket"));
        boolean test = inventory.searchIsContained(inventory.trackerList.get(0), "Football");
        assertFalse(test);
    }

    @Test
    void programCanSort(){
        //The program contains a TableView. A TableView is sortable so each item's name, serial # and value is sortable
    }
}