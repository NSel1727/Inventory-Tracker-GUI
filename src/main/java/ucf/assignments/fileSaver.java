/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Noah Seligson
 */

package ucf.assignments;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;

public class fileSaver {

    public static void saveHTML(ObservableList<item> trackerList, File file){
        try{
            if(file == null){
                return;
            }
            FileWriter writer = new FileWriter(file);
            String htmlString = "";
            for(item i : trackerList) {
                htmlString += "<tr>" +
                        "    <td style=\"text-align:left\">" + i.value + " </td>" +
                        "    <td style=\"text-align:left\">" + i.serialNumber + " </td>" +
                        "    <td style=\"text-align:left\">" + i.name + " </td>" +
                        "  </tr>";
            }
            writer.write("<table style=\"width:25%\"> <tr> <th style=\"text-align:left\">Value</th> <th style=\"text-align:left\">Serial Number</th> <th style=\"text-align:left\">Name</th> </tr>" + htmlString + "</table>");
            writer.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(new JFrame(), "The list could not be saved", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void saveTSV(ObservableList<item> trackerList, File file){
        try{
            if(file == null){
                return;
            }
            FileWriter writer = new FileWriter(file);
            writer.write("Value\tSerial Number\tName\t");
            writer.write("\n");
            for(item i : trackerList){
                writer.write(i.value + "\t" + i.serialNumber + "\t" + i.name);
                writer.write("\n");
            }
            writer.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(new JFrame(), "The list could not be saved", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void saveJSON(ObservableList<item> trackerList, File file){
        try{
            if(file == null){
                return;
            }
            FileWriter writer = new FileWriter(file);
            writer.write(getJSONString(trackerList));
            writer.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(new JFrame(), "The list could not be saved", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static String getJSONString(ObservableList<item> trackerList){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        return gson.toJson(trackerList);
    }

    public static File getFile(){
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Save Inventory");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("HTML Files (*.html)", "*.html"),
                new FileChooser.ExtensionFilter("TSV Files (*.txt)", "*.txt"),
                new FileChooser.ExtensionFilter("JSON Files (*.json)", "*.json")
        );
        return chooser.showSaveDialog(null);
    }
}