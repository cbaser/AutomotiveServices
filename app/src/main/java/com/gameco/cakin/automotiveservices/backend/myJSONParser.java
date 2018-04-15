package com.gameco.cakin.automotiveservices.backend;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.gameco.cakin.automotiveservices.activites.LoginActivity;
import com.gameco.cakin.automotiveservices.activites.MainActivity;
import com.gameco.cakin.automotiveservices.datamodel.BMWCarData;
import com.gameco.cakin.automotiveservices.datamodel.CarVINs;
import com.gameco.cakin.automotiveservices.datamodel.Car;
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
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
  //  private DatabaseReference mRef;
    //TODO Retrieve VIN From User
    private String VIN;
    private Car car;
    private BMWCarData[] bmwCarData;
    public static FirebaseDatabase mDatabase;
    public void setJsonString(String jsonString){
        this.jsonString = jsonString;
        Log.e("JsonString",jsonString);
    }



    public Car convertToCarData(){
     //   BMWCarData[] bmwCarData;
        car = new Car();
     //   mDatabase = FirebaseDatabase.getInstance();
      //  FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
       // mRef =mDatabase.getReference("Users").child(LoginActivity.user_full_name);
        startParsing();
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
        return car;
    }
    private void startParsing(){
        try{
            //    String i3VINCode;
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(jsonString).getAsJsonObject();
            Context applicationContext = MainActivity.getContextOfApplication();
            sharedPreferences = applicationContext.getSharedPreferences("userdetails",Context.MODE_PRIVATE);
            VIN = sharedPreferences.getString("VIN","");
            JsonElement jsonElement = jsonObject.get("telematicKeys");

            Gson gson = new Gson();
            //  CarVINs carVINs = new CarVINs();
            bmwCarData = gson.fromJson(jsonElement,BMWCarData[].class);
            int lastIndex = 0;
            //    if(LoginActivity.LoggedIn_User_Email.contains("can"))
            //  i3VINCode = carVINs.getBMWi3();
            //    else
            //         i3VINCode = carVINs.getBMWM235i();
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

