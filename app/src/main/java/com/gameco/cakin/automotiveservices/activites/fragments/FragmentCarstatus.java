package com.gameco.cakin.automotiveservices.activites.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.backend.BackendHelper;
import com.gameco.cakin.automotiveservices.datamodel.Car;

/**
 * Created by cakin on 11/4/2017.
 */

public class FragmentCarstatus extends Fragment {

    private Car car;



    private BackendHelper backendHelper;

    public FragmentCarstatus(){
        backendHelper = new BackendHelper();


    }
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_carstatus,container,false);
        try{
            car =  backendHelper.tryTelematics("Telematics");
            TextView mileage = (TextView) view.findViewById(R.id.tv_mileage);

            mileage.setText(car.getMileage().replace("\"",""));

            TextView average_distance = (TextView) view.findViewById(R.id.tv_av_distance);

            average_distance.setText(car.getAverage_distance_per_week().replace("\"",""));

            TextView remainingFuel = (TextView) view.findViewById(R.id.tv_fuel_state);

            remainingFuel.setText(car.getRemaining_fuel().replace("\"",""));


            TextView batteryLevel = (TextView) view.findViewById(R.id.tv_battery_state);

            batteryLevel.setText(car.getBatteryVoltage().replace("\"",""));

            TextView nextService = (TextView) view.findViewById(R.id.tv_next_service);

            nextService.setText(car.getNextServiceDistance().replace("\"",""));

            TextView ecoActive = (TextView) view.findViewById(R.id.tv_eco_time);

            ecoActive.setText(car.getActive_time_of_eco_mode().replace("\"",""));

            TextView fuelConsumption= (TextView) view.findViewById(R.id.tv_fuel_consumption);

            fuelConsumption.setText(car.getFuel_consumption().replace("\"",""));

        }catch (Exception e){
            e.printStackTrace();
        }



        return view;
    }


}
