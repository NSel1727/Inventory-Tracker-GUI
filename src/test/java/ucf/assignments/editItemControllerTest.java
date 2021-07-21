/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Noah Seligson
 */

package ucf.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class editItemControllerTest {

    @Test
    void saveEditedItemEditsName() {
        editItemController edit = new editItemController();
        edit.trackerList.add(new item("Super Smash Bros Melee", "3333344444", "$59.99"));
        edit.saveEditedItem("Super Smash Bros Ultimate", "1234512345", "$49.99");
        assertTrue(edit.trackerList.get(0).name.equals("Super Smash Bros Ultimate"));
    }

    @Test
    void saveEditedItemEditsSerialNumber() {
        editItemController edit = new editItemController();
        edit.trackerList.add(new item("Super Smash Bros Melee", "3333344444", "$59.99"));
        edit.saveEditedItem("Super Smash Bros Ultimate", "1234512345", "$49.99");
        assertTrue(edit.trackerList.get(0).serialNumber.equals("1234512345"));
    }

    @Test
    void saveEditedItemEditsValue() {
        editItemController edit = new editItemController();
        edit.trackerList.add(new item("Super Smash Bros Melee", "3333344444", "$59.99"));
        edit.saveEditedItem("Super Smash Bros Ultimate", "1234512345", "$49.99");
        assertTrue(edit.trackerList.get(0).value.equals("$49.99"));
    }
}