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

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
    private Drawable drawable;
    private String name;
    private int point;

    public Friend(Drawable drawable,String name,int point){
        this.drawable = drawable;
        this.name=name;
        this.point=point;
    }

    public Drawable getImage() {
        return drawable;
    }

    public void setImage(Drawable drawable) {
        this.drawable = drawable;
    }
}
