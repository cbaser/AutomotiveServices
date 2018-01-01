package com.gameco.cakin.automotiveservices.datamodel;

/**
 * Created by cakin on 12/26/2017.
 */

public class Friend {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    private String name;
    private int point;

    public Friend(String name,int point){
        this.name=name;
        this.point=point;
    }
}
