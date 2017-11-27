package com.bmw.cakin.automotiveservices.backend;

import android.widget.Toast;

import com.bmw.cakin.automotiveservices.datamodel.BMWCarData;
import com.bmw.cakin.automotiveservices.datamodel.CurrentCar;
import com.bmw.cakin.automotiveservices.datamodel.Telematics;

import java.util.List;

/**
 * Created by cakin on 11/14/2017.
 */

public class BackendHelper {
    private Telematics[] values = null;
    private String jsonString;
    BackendController backendController;
    myJSONParser jsonParser;
    public BackendHelper(){
         backendController = new BackendController();
         jsonParser = new myJSONParser();

    }

    public String tryLogin(String... strings) {
       backendController.execute(strings);
       return backendController.getResponse();
    }

    public String tryRegister(String ... strings){
        backendController.execute(strings);
        return backendController.getResponse();
    }
    public CurrentCar tryTelematics(String ... strings){
        try {
            jsonParser.setJsonString(backendController.execute(strings).get());
        }catch (Exception e){
            e.printStackTrace();
        }

       // jsonParser.setJsonString(jsonString);

        return  jsonParser.convertToCarData();
    }
    public void setJsonString(String jsonString){
        this.jsonString = jsonString;
    }







}
