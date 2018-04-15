package com.gameco.cakin.automotiveservices.datamodel;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

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
    public long getPoint() {
        return point;
    }
    public void setPoint(long point) {
        this.point = point;
    }
    private String name;
    private long point;

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    private String VIN;

    public Friend(String name,long point,String VIN){
        this.name=name;
        this.point=point;
        this.VIN =VIN;
    }
    public Friend(){

    }


}
