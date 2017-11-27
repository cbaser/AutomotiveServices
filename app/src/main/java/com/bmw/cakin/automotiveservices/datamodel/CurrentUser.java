package com.bmw.cakin.automotiveservices.datamodel;

import java.util.ArrayList;

/**
 * Created by cakin on 11/27/2017.
 */

public class CurrentUser {
    public CurrentUser(){
        friendsList = new ArrayList<String>();
        challenges = new ArrayList<String>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public ArrayList<String> getFriendsList() {
        return friendsList;
    }


    public ArrayList<String> getChallenges() {
        return challenges;
    }


    private String username;
    private String vehicleName;
    private ArrayList<String> friendsList;
    private ArrayList<String> challenges;
}
