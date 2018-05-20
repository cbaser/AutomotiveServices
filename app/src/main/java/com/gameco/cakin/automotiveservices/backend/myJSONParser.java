package com.gameco.cakin.automotiveservices.backend;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.gameco.cakin.automotiveservices.activites.LoginActivity;
import com.gameco.cakin.automotiveservices.activites.MainActivity;
import com.gameco.cakin.automotiveservices.activites.ProgressActivity;
import com.gameco.cakin.automotiveservices.datamodel.BMWCarData;
import com.gameco.cakin.automotiveservices.datamodel.CarVINs;
import com.gameco.cakin.automotiveservices.datamodel.Car;
import com.gameco.cakin.automotiveservices.datamodel.CurrentUser;
import com.gameco.cakin.automotiveservices.firebase.MyFirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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
    private Car car;
    public void setJsonString(String jsonString){
        this.jsonString = jsonString;
    }




    public Car convertToCarData(){

        car = new Car();
        startParsing();

        return car;
    }
    private void startParsing(){
        try{
            MyFirebaseDatabase  firebaseDatabase  = new MyFirebaseDatabase(ProgressActivity.getContextOfApplication());
            //    String i3VINCode;
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(jsonString).getAsJsonObject();

            Gson gson = new Gson();
            String  VIN = firebaseDatabase.getUserFromPreferences().getCar().getVIN();
            JsonElement jsonElement = jsonObject.get("telematicKeys");

            BMWCarData[] bmwCarData = gson.fromJson(jsonElement,BMWCarData[].class);
            int lastIndex = 0;

            List<BMWCarData> telematicValues = Arrays.asList(bmwCarData);

            for (int i=0;i<bmwCarData.length;i++){
                if( bmwCarData[i].getVIN().equals(VIN))
                {
                    lastIndex = i;

                }
            }


            car.setVIN(bmwCarData[lastIndex].getVIN());

            for (int i=0;i<telematicValues.size();i++){
                if(telematicValues.get(i).getVIN().equals(VIN)){
                    if(telematicValues.get(i).getTelematics().contains("bmwcardata_mileage"))
                        car.setMileage( getTelematicValue("bmwcardata_mileage",telematicValues.get(i).getTelematics()));
                    if(telematicValues.get(i).getTelematics().contains("bmwcardata_averageDistance"))
                        car.setAverage_Distance(getTelematicValue("bmwcardata_averageDistance",telematicValues.get(i).getTelematics()));
                    if(telematicValues.get(i).getTelematics().contains("bmwcardata_remainingFuel"))
                        car.setRemaining_Fuel(getTelematicValue("bmwcardata_remainingFuel",telematicValues.get(i).getTelematics()));
                    if(telematicValues.get(i).getTelematics().contains("bmwcardata_batteryVoltage"))
                        car.setBattery_Level(getTelematicValue("bmwcardata_batteryVoltage",telematicValues.get(i).getTelematics()));
                    if(telematicValues.get(i).getTelematics().contains("bmwcardata_nextServiceDistance"))
                        car.setNext_Service_Distance(getTelematicValue("bmwcardata_nextServiceDistance",telematicValues.get(i).getTelematics()));
                    if(telematicValues.get(i).getTelematics().contains("bmwcardata_SegmentLastTripECOTimeOfActivation"))
                        car.setECO_Time(getTelematicValue("bmwcardata_SegmentLastTripECOTimeOfActivation",telematicValues.get(i).getTelematics()));
                    if(telematicValues.get(i).getTelematics().contains("bmwcardata_SegmentLastTripFuelConsumption"))
                        car.setFuel_Consumption(getTelematicValue("bmwcardata_SegmentLastTripFuelConsumption",telematicValues.get(i).getTelematics()));

                }

            }



        }catch (Exception e){
            e.printStackTrace();
        }

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
//  private DatabaseReference mRef;
//   BMWCarData[] bmwCarData;
//   mDatabase = FirebaseDatabase.getInstance();
//  FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
// mRef =mDatabase.getReference("Users").child(LoginActivity.user_full_name);

//        mRef = mDatabase.getReference().child("Users").child(user.getEmail().replace(".",","));
//        mRef.child("Car").child("vin").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//              VIN = (String) snapshot.getValue();
//              //prints "Do you have data? You'll love Firebase."
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });
//   VIN =mDatabase.getReference("car").child("vin").getKey();

//  CarVINs carVINs = new CarVINs();
//    if(LoginActivity.LoggedIn_User_Email.contains("can"))
//  i3VINCode = carVINs.getBMWi3();
//    else
//         i3VINCode = carVINs.getBMWM235i();