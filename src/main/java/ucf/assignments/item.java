/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Noah Seligson
 */

package ucf.assignments;

public class item {
    public String value;
    public String name;
    public String serialNumber;

    public item(String value, String serialNumber, String name){
        this.value = value;
        this.serialNumber = serialNumber;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }
}
