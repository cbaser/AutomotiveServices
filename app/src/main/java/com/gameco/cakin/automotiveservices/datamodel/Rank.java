package com.gameco.cakin.automotiveservices.datamodel;

/**
 * Created by cakin on 12/27/2017.
 */

public class Rank {
    public Rank(int position, String region) {
        this.position = position;
        this.region = region;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    private int position;
    private String region;
}
