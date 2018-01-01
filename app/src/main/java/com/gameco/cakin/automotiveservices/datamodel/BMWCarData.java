package com.gameco.cakin.automotiveservices.datamodel;

import java.io.Serializable;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by cakin on 10/26/2017.
 */

public class BMWCarData implements Serializable{
    public int getVin_id() {
        return vin_id;
    }

    public void setVin_id(int vin_id) {
        this.vin_id = vin_id;
    }
    @SerializedName("vin_id")
    @Expose
    private int vin_id;

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }
    @SerializedName("VIN")
    @Expose
    private String VIN;

    public String getTelematics() {
        return telematics;
    }

    public void setTelematics(String telematics) {
        this.telematics = telematics;
    }

    @SerializedName("telematics")
    @Expose
    private String telematics;

}
