/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Noah Seligson
 */

package ucf.assignments;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class fileLoader {

    public static ObservableList<item> loadHTML(File file){
        try {
            ObservableList<item> htmlInventory= FXCollections.observableArrayList();
            Scanner scan = new Scanner(file);
            String htmlString = scan.nextLine();

            Document doc = Jsoup.parse(htmlString);
            Element table = doc.select("table").get(0);
            Elements rows = table.select("tr");

            for(int i = 1; i < rows.size(); i++){
                Element row = rows.get(i);
                Elements columns = row.select("td");
                htmlInventory.add(new item(columns.get(0).text(), columns.get(1).text(), columns.get(2).text()));
            }
            return htmlInventory;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(new JFrame(), "There was an error loading the inventory", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public static ObservableList<item> loadTSV(File file){
        try {
            Scanner scan = new Scanner(file);
            ObservableList<item> tsvInventory= FXCollections.observableArrayList();
            scan.nextLine();
            while(scan.hasNextLine()){
                tsvInventory.add(new item(scan.next(), scan.next(), scan.nextLine()));
            }
            return tsvInventory;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(new JFrame(), "There was an error loading the inventory", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public static ObservableList<item> loadJSON(File file){
        try {
            Scanner scan = new Scanner(file);
            String jsonString = "";
            while (scan.hasNextLine()){
                jsonString += scan.nextLine();
            }
            Gson gson = new Gson();
            JsonArray jArray = gson.fromJson(jsonString, JsonArray.class);
            List<item> tempInventory = gson.fromJson(jArray.toString(), new TypeToken<ArrayList<item>>() {
            }.getType());
            ObservableList<item> jsonInventory = FXCollections.observableArrayList(tempInventory);
            return jsonInventory;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(new JFrame(), "There was an error loading the inventory", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public static File chooseFile(){
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Load Inventory");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("HTML Files (*.html)", "*.html"),
                new FileChooser.ExtensionFilter("TSV Files (*.txt)", "*.txt"),
                new FileChooser.ExtensionFilter("JSON Files (*.json)", "*.json")
        );
        File file = chooser.showOpenDialog(null);
        if(file == null){
            return null;
        }
        return file;
    }

}
