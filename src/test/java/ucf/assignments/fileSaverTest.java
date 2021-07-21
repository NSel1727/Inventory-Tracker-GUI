/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Noah Seligson
 */

package ucf.assignments;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class fileSaverTest {

    @Test
    void saveHTMLCreatesHTMLFileBasedOnInventory() {
        try {
            addItemController add = new addItemController();
            add.saveNewItem("Toy Story DVD", "1231231234", "$19.99");

            File file = new File("Inventory.html");
            fileSaver.saveHTML(add.trackerList, file);

            Scanner scan = new Scanner(file);
            assertTrue(scan.nextLine().contains("Toy Story DVD"));
        }catch(FileNotFoundException ex){
            ex.printStackTrace();
        }
    }

    @Test
    void saveTSVCreatesTSVFileBasedOnInventory() {
        try {
            addItemController add = new addItemController();
            add.saveNewItem("Bicycle", "5555555555", "$30.54");

            File file = new File("Inventory.txt");
            fileSaver.saveTSV(add.trackerList, file);

            Scanner scan = new Scanner(file);
            scan.nextLine();
            assertTrue(scan.next().equals("$30.54"));
        }catch(FileNotFoundException ex){
            ex.printStackTrace();
        }
    }

    @Test
    void saveJSONCreatesJSONFileBasedOnInventory() {
        try {
            addItemController add = new addItemController();
            add.saveNewItem("Meatballs", "mB1234SP12", "$7.99");

            File file = new File("Inventory.json");
            fileSaver.saveJSON(add.trackerList, file);

            Scanner scan = new Scanner(file);
            scan.nextLine();
            scan.nextLine();
            scan.nextLine();
            assertTrue(scan.nextLine().contains("Meatballs"));
        }catch(FileNotFoundException ex){
            ex.printStackTrace();
        }
    }
}