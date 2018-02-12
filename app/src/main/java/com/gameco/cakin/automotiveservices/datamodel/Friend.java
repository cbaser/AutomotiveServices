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
    private Drawable drawable;
    private String name;
    private long point;

    public Friend(Drawable drawable,String name,long point){
        this.drawable = drawable;
        this.name=name;
        this.point=point;
    }
    public Friend(){

    }

    public Drawable getImage() {
        return drawable;
    }

    public void setImage(Drawable drawable) {
        this.drawable = drawable;
    }
}
