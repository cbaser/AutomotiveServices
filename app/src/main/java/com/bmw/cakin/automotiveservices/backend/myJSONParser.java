package com.bmw.cakin.automotiveservices.backend;


import android.util.Log;

import com.bmw.cakin.automotiveservices.datamodel.BMWCarData;
import com.bmw.cakin.automotiveservices.datamodel.CarVINs;
import com.bmw.cakin.automotiveservices.datamodel.CurrentCar;
import com.bmw.cakin.automotiveservices.datamodel.Telematics;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
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



    public CurrentCar convertToCarData(){
        BMWCarData[] bmwCarData;
        CurrentCar currentCar = new CurrentCar();

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


            currentCar.setVIN(bmwCarData[lastIndex].getVIN());

            for (int i=0;i<telematicValues.size();i++){
                if(telematicValues.get(i).getVIN().equals(i3VINCode)){
                    if(telematicValues.get(i).getTelematics().contains("bmwcardata_mileage"))
                        currentCar.setMileage( getTelematicValue("bmwcardata_mileage",telematicValues.get(i).getTelematics()));
                    if(telematicValues.get(i).getTelematics().contains("bmwcardata_averageDistance"))
                        currentCar.setAverage_distance_per_week(getTelematicValue("bmwcardata_averageDistance",telematicValues.get(i).getTelematics()));
                    if(telematicValues.get(i).getTelematics().contains("bmwcardata_remainingFuel"))
                        currentCar.setRemaining_fuel(getTelematicValue("bmwcardata_remainingFuel",telematicValues.get(i).getTelematics()));
                    if(telematicValues.get(i).getTelematics().contains("bmwcardata_batteryVoltage"))
                        currentCar.setBatteryVoltage(getTelematicValue("bmwcardata_batteryVoltage",telematicValues.get(i).getTelematics()));
                    if(telematicValues.get(i).getTelematics().contains("bmwcardata_nextServiceDistance"))
                        currentCar.setNextServiceDistance(getTelematicValue("bmwcardata_nextServiceDistance",telematicValues.get(i).getTelematics()));
                    if(telematicValues.get(i).getTelematics().contains("bmwcardata_SegmentLastTripECOTimeOfActivation"))
                        currentCar.setActive_time_of_eco_mode(getTelematicValue("bmwcardata_SegmentLastTripECOTimeOfActivation",telematicValues.get(i).getTelematics()));
                    if(telematicValues.get(i).getTelematics().contains("bmwcardata_SegmentLastTripFuelConsumption"))
                        currentCar.setFuel_consumption(getTelematicValue("bmwcardata_SegmentLastTripFuelConsumption",telematicValues.get(i).getTelematics()));

                }

            }



        }catch (Exception e){
            e.printStackTrace();
        }
        return currentCar;
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
//      if(telematicValues.get(i).contains("bmwcardata_mileage")){
//       currentCar.setMileage( getTelematicValue("bmwcardata_mileage",telematicValues.get(i)));
//                   int position = telematicValues.get(i).indexOf("bmwcardata_mileage");
//                   int valuePos = telematicValues.get(i).indexOf("value",position);
//                   int endPos = telematicValues.get(i).indexOf("}",valuePos);
//                   String mileage = telematicValues.get(i).substring(valuePos,endPos);
//                   currentCar.setMileage(mileage);
//     }
//                if(telematicValues.get(i).contains("bmwcardata_averageDistance")){
//                    int position = telematicValues.get(i).indexOf("bmwcardata_averageDistance");
//                    int valuePos = telematicValues.get(i).indexOf("value",position);
//                    int endPos = telematicValues.get(i).indexOf("}",valuePos);
//                    String averageDistance = telematicValues.get(i).substring(valuePos,endPos);
//                    currentCar.setAverage_distance_per_week(averageDistance);
//                }
//                if(telematicValues.get(i).contains("bmwcardata_remainingFuel")){
//                    int position = telematicValues.get(i).indexOf("bmwcardata_remainingFuel");
//                    int valuePos = telematicValues.get(i).indexOf("value",position);
//                    int endPos = telematicValues.get(i).indexOf("}",valuePos);
//                    String remainingFuel = telematicValues.get(i).substring(valuePos,endPos);
//                    currentCar.setRemaining_fuel(remainingFuel);
//                }

//                if(telematicValues.get(i).contains("bmwcardata_batteryVoltage")){
//                    int position = telematicValues.get(i).indexOf("bmwcardata_batteryVoltage");
//                    int valuePos = telematicValues.get(i).indexOf("value",position);
//                    int endPos = telematicValues.get(i).indexOf("}",valuePos);
//                    String batteryVoltage = telematicValues.get(i).substring(valuePos,endPos);
//                    currentCar.setBatteryVoltage(batteryVoltage);
//                }
//                if(telematicValues.get(i).contains("bmwcardata_nextServiceDistance")){
//                    int position = telematicValues.get(i).indexOf("bmwcardata_nextServiceDistance");
//                    int valuePos = telematicValues.get(i).indexOf("value",position);
//                    int endPos = telematicValues.get(i).indexOf("}",valuePos);
//                    String nextService = telematicValues.get(i).substring(valuePos,endPos);
//                    currentCar.setNextServiceDistance(nextService);
//                }
//                if(telematicValues.get(i).contains("bmwcardata_SegmentLastTripECOTimeOfActivation")){
//                    int position = telematicValues.get(i).indexOf("bmwcardata_SegmentLastTripECOTimeOfActivation");
//                    int valuePos = telematicValues.get(i).indexOf("value",position);
//                    int endPos = telematicValues.get(i).indexOf("}",valuePos);
//                    String ecoTrip = telematicValues.get(i).substring(valuePos,endPos);
//                    currentCar.setActive_time_of_eco_mode(ecoTrip);
//                }
//                if(telematicValues.get(i).contains("bmwcardata_SegmentLastTripFuelConsumption")){
//                    int position = telematicValues.get(i).indexOf("bmwcardata_SegmentLastTripFuelConsumption");
//                    int valuePos = telematicValues.get(i).indexOf("value",position);
//                    int endPos = telematicValues.get(i).indexOf("}",valuePos);
//                    String fuelConsumption = telematicValues.get(i).substring(valuePos,endPos);
//                    currentCar.setFuel_consumption(fuelConsumption);
//                }


//            String rawString = bmwCarData[lastIndex].getTelematics();
//
//          if(!rawString.endsWith("}")){
//              rawString = rawString.substring(0,rawString.lastIndexOf("}"));
//              rawString = rawString+"}]}";
//          }
//            JsonObject telematicObject = parser.parse(rawString).getAsJsonObject();
//            JsonElement telematicElement = telematicObject.get("telematicKeyValues");
//             telematics = gson.fromJson(telematicElement,Telematics[].class);
//
//             if(telematics!=null)
//             currentCar.setTelematicsList(Arrays.asList(telematics));

