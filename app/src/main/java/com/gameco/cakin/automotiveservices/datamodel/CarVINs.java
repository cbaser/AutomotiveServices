package com.gameco.cakin.automotiveservices.datamodel;

/**
 * Created by cakin on 11/22/2017.
 */

public class CarVINs {
    public static String getBMWi3() {
        return BMWi3;
    }

    public static String getBMW120d() {
        return BMW120d;
    }

    public static String getBMW140i() {
        return BMW140i;
    }

    public static String getBMWM235i() {
        return BMWM235i;
    }

    private static  String BMWi3 ="WBY1Z21000V308999" ;
    private static String BMW120d = "WBAUD91090P381103";
    private static String BMW140i ="WBA1R91060VA0394";
    private static String BMWM235i = "WBA1J71080V593471";


    public static String getVINFromType(String carType){
        String VIN="";
        switch (carType){
            case "BMW i3":
                VIN = getBMWi3();
                break;
            case "BMW 120d":
                VIN = getBMWi3();
                break;
            case "BMW 140i":
                VIN = getBMWi3();
                break;
            case "BMW M235i":
                VIN = getBMWi3();
                break;

        }
        return VIN;
    }
}
