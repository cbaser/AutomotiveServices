package com.gameco.cakin.automotiveservices.backend;


import android.util.Log;

import com.gameco.cakin.automotiveservices.datamodel.BMWCarData;
import com.gameco.cakin.automotiveservices.datamodel.CarVINs;
import com.gameco.cakin.automotiveservices.datamodel.Car;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Arrays;
import java.util.List;

/**
 * Created by cakin on 11/14/2017.
 */

public class myJSONParser {
    private String jsonString;

    public void setJsonString(String jsonString){
        this.jsonString = jsonString;
        Log.i("JsonString",jsonString);
    }



    public Car convertToCarData(){
        BMWCarData[] bmwCarData;
        Car car = new Car();

        try{

           JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(jsonString).getAsJsonObject();
            JsonElement jsonElement = jsonObject.get("telematicKeys");


            Gson gson = new Gson();
            CarVINs carVINs = new CarVINs();
            bmwCarData = gson.fromJson(jsonElement,BMWCarData[].class);
            int lastIndex = 0;
            String i3VINCode = carVINs.getBMWi3();
           List<BMWCarData> telematicValues = Arrays.asList(bmwCarData);

            for (int i=0;i<bmwCarData.length;i++){
               if( bmwCarData[i].getVIN().equals(i3VINCode))
               {
                lastIndex = i;

               }
            }


            car.setVIN(bmwCarData[lastIndex].getVIN());

            for (int i=0;i<telematicValues.size();i++){
                if(telematicValues.get(i).getVIN().equals(i3VINCode)){
                    if(telematicValues.get(i).getTelematics().contains("bmwcardata_mileage"))
                        car.setMileage( getTelematicValue("bmwcardata_mileage",telematicValues.get(i).getTelematics()));
                    if(telematicValues.get(i).getTelematics().contains("bmwcardata_averageDistance"))
                        car.setAverage_distance_per_week(getTelematicValue("bmwcardata_averageDistance",telematicValues.get(i).getTelematics()));
                    if(telematicValues.get(i).getTelematics().contains("bmwcardata_remainingFuel"))
                        car.setRemaining_fuel(getTelematicValue("bmwcardata_remainingFuel",telematicValues.get(i).getTelematics()));
                    if(telematicValues.get(i).getTelematics().contains("bmwcardata_batteryVoltage"))
                        car.setBatteryVoltage(getTelematicValue("bmwcardata_batteryVoltage",telematicValues.get(i).getTelematics()));
                    if(telematicValues.get(i).getTelematics().contains("bmwcardata_nextServiceDistance"))
                        car.setNextServiceDistance(getTelematicValue("bmwcardata_nextServiceDistance",telematicValues.get(i).getTelematics()));
                    if(telematicValues.get(i).getTelematics().contains("bmwcardata_SegmentLastTripECOTimeOfActivation"))
                        car.setActive_time_of_eco_mode(getTelematicValue("bmwcardata_SegmentLastTripECOTimeOfActivation",telematicValues.get(i).getTelematics()));
                    if(telematicValues.get(i).getTelematics().contains("bmwcardata_SegmentLastTripFuelConsumption"))
                        car.setFuel_consumption(getTelematicValue("bmwcardata_SegmentLastTripFuelConsumption",telematicValues.get(i).getTelematics()));

                }

            }



        }catch (Exception e){
            e.printStackTrace();
        }
        return car;
    }

    private String getTelematicValue(String searchedString, String where){
        String telematicString = "";
        if(where.contains(searchedString)) {
            int position = where.indexOf(searchedString);
            int valuePos = where.indexOf("value", position);
            int endPos = where.indexOf("}", valuePos);
            if(valuePos!= -1 && endPos!= -1)
            telematicString   = where.substring(valuePos, endPos);
        }


        return telematicString;
    }
}

