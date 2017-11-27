package com.bmw.cakin.automotiveservices.activites.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bmw.cakin.automotiveservices.R;
import com.bmw.cakin.automotiveservices.adapters.TelematicAdapter;
import com.bmw.cakin.automotiveservices.backend.BackendHelper;
import com.bmw.cakin.automotiveservices.datamodel.BMWCarData;
import com.bmw.cakin.automotiveservices.backend.myJSONParser;
import com.bmw.cakin.automotiveservices.datamodel.CurrentCar;
import com.bmw.cakin.automotiveservices.datamodel.CurrentUser;
import com.bmw.cakin.automotiveservices.datamodel.Telematics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cakin on 11/4/2017.
 */

public class FragmentCarstatus extends Fragment {
    private ListView telematicListView;
    private CurrentCar currentCar;



    private BackendHelper backendHelper;

    public FragmentCarstatus(){
        backendHelper = new BackendHelper();


    }
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_carstatus,container,false);
        try{
            currentCar =  backendHelper.tryTelematics("Telematics");
            TextView mileage = (TextView) view.findViewById(R.id.tv_mileage);

            mileage.setText(currentCar.getMileage());

            TextView average_distance = (TextView) view.findViewById(R.id.tv_av_distance);

            average_distance.setText(currentCar.getAverage_distance_per_week());

            TextView remainingFuel = (TextView) view.findViewById(R.id.tv_fuel_state);

            remainingFuel.setText(currentCar.getRemaining_fuel());


            TextView batteryLevel = (TextView) view.findViewById(R.id.tv_battery_state);

            batteryLevel.setText(currentCar.getBatteryVoltage());

            TextView nextService = (TextView) view.findViewById(R.id.tv_next_service);

            nextService.setText(currentCar.getNextServiceDistance());

            TextView ecoActive = (TextView) view.findViewById(R.id.tv_eco_time);

            ecoActive.setText(currentCar.getActive_time_of_eco_mode());

            TextView fuelConsumption= (TextView) view.findViewById(R.id.tv_fuel_consumption);

            fuelConsumption.setText(currentCar.getFuel_consumption());

        }catch (Exception e){
            e.printStackTrace();
        }

  //     telematicListView = (ListView) view.findViewById(R.id.telematicList);

  //     populateList(telematicListView,telematics);

        return view;
    }
//    private void populateList(ListView listView,List<Telematics> TelematicsList){
//        ArrayAdapter<Telematics> telematicAdapter = new TelematicAdapter(getActivity(), TelematicsList);
//        listView.setAdapter(telematicAdapter);
//    }

}
//     //   String[] menuItems = {"Do something!","Do something else!","Do yet another thing!"};
//        jsonAssetsAdapter.setAssetManager(getActivity().getAssets());
//        ListView listView = (ListView)view.findViewById(R.id.telematicKeyList);
//
//        ArrayAdapter<String> listViewAdapter = jsonAssetsAdapter.buildAdapter(getActivity());
//
//        listView.setAdapter(listViewAdapter);