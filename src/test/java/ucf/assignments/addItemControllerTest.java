/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Noah Seligson
 */

package ucf.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class addItemControllerTest {

    @Test
    void saveNewItemSavesItem() {
        addItemController add = new addItemController();
        add.saveNewItem("Skyward Sword HD", "1111111111", "$59.99");
        assertTrue(add.trackerList.size() == 1);
    }

    @Test
    void lengthTestRejectsTooShortName() {
        addItemController add = new addItemController();
        String test = add.nameTest("i");
        assertEquals("Too Short", test);
    }

    @Test
    void serialTestRejectsNameWithSpecialCharacters() {
        addItemController add = new addItemController();
        String test = add.serialTest("11212@DDD!");
        assertEquals("Incorrect Format", test);
    }

    @Test
    void serialTestRejectsDuplicateSerialName() {
        addItemController add = new addItemController();
        add.serialList.add("0123456789");
        String test = add.serialTest("0123456789");
        assertEquals("Already Exists", test);
    }

    @Test
    void valueTestRejectsIncorrectFormat() {
        addItemController add = new addItemController();
        String test = add.valueTest("$30.#.5");
        assertEquals("Improper", test);
    }
}