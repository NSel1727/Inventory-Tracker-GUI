/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Noah Seligson
 */

package ucf.assignments;

import javafx.collections.ObservableList;
import javafx.stage.FileChooser;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class fileSaver {

    public static File getFile(String typePart1, String typePart2){
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Save List");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(typePart1, typePart2));
        return chooser.showSaveDialog(null);
    }

    public static void saveHTML(ObservableList<item> trackerList, File file){
        try{
            if(file == null){
                return;
            }
            FileWriter writer = new FileWriter(file);
            String htmlString = "";
            for(item item : trackerList) {
                htmlString += "<tr>" +
                        "    <td style=\"text-align:left\">" + item.value + " </td>" +
                        "    <td style=\"text-align:left\">" + item.serialNumber + " </td>" +
                        "    <td style=\"text-align:left\">" + item.name + " </td>" +
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

        }catch(Exception ex){
            JOptionPane.showMessageDialog(new JFrame(), "The list could not be saved", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void saveJSON(ObservableList<item> trackerList, File file){
        try{
            if(file == null){
                return;
            }

        }catch(Exception ex){
            JOptionPane.showMessageDialog(new JFrame(), "The list could not be saved", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
