package com.gameco.cakin.automotiveservices.activites.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.activites.LoginActivity;
import com.gameco.cakin.automotiveservices.backend.BackendHelper;
import com.gameco.cakin.automotiveservices.controller.FrontController;
import com.gameco.cakin.automotiveservices.datamodel.Car;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by cakin on 11/4/2017.
 */

public class FragmentCarstatus extends Fragment {

    private Car car;
    public static FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private FrontController frontController;
    private TextView mileage,average_distance,remainingFuel,batteryLevel,nextService,ecoActive,fuelConsumption;




    private BackendHelper backendHelper;

    public FragmentCarstatus(){
        backendHelper = new BackendHelper();
        frontController = new FrontController(this);


    }
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_carstatus,container,false);
        try{
            car =  backendHelper.tryTelematics("Telematics");
             mileage = (TextView) view.findViewById(R.id.tv_mileage);

            mileage.setText(car.getMileage().replace("\"",""));

             average_distance = (TextView) view.findViewById(R.id.tv_av_distance);

            average_distance.setText(car.getAverage_distance_per_week().replace("\"",""));

             remainingFuel = (TextView) view.findViewById(R.id.tv_fuel_state);

            remainingFuel.setText(car.getRemaining_fuel().replace("\"",""));


             batteryLevel = (TextView) view.findViewById(R.id.tv_battery_state);

            batteryLevel.setText(car.getBatteryVoltage().replace("\"",""));

             nextService = (TextView) view.findViewById(R.id.tv_next_service);

            nextService.setText(car.getNextServiceDistance().replace("\"",""));

             ecoActive = (TextView) view.findViewById(R.id.tv_eco_time);

            ecoActive.setText(car.getActive_time_of_eco_mode().replace("\"",""));

             fuelConsumption= (TextView) view.findViewById(R.id.tv_fuel_consumption);

            fuelConsumption.setText(car.getFuel_consumption().replace("\"",""));


            updateCarData();

        }catch (Exception e){
            e.printStackTrace();
        }



        return view;
    }
    private void updateCarData(){
        mDatabase =FirebaseDatabase.getInstance();
         mRef = mDatabase.getReference();
         mRef.child("Users").child(LoginActivity.user_full_name).addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(DataSnapshot dataSnapshot) {
                 dataSnapshot.getRef().child("Car").child("Mileage").setValue(mileage.getText().toString());
                 dataSnapshot.getRef().child("Car").child("Average distance").setValue(average_distance.getText().toString());
                 dataSnapshot.getRef().child("Car").child("Remaining Fuel").setValue(remainingFuel.getText().toString());
                 dataSnapshot.getRef().child("Car").child("Next Service").setValue(nextService.getText().toString());
                 dataSnapshot.getRef().child("Car").child("Fuel Consumption").setValue(fuelConsumption.getText().toString());
             }

             @Override
             public void onCancelled(DatabaseError databaseError) {

             }
         });

    }

}
