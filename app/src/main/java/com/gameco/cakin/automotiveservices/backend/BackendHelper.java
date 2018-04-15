package com.gameco.cakin.automotiveservices.backend;

import com.gameco.cakin.automotiveservices.datamodel.Car;
import com.gameco.cakin.automotiveservices.datamodel.Telematics;

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
    public String tryRegister(String ... strings){
        try{
            jsonString= backendController.execute(strings).get();
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonString;
    }
    public Car tryTelematics(String ... strings){
        try {
            jsonParser.setJsonString(backendController.execute(strings).get());
        }catch (Exception e){
            e.printStackTrace();
        }
        return  jsonParser.convertToCarData();
    }
    public void setJsonString(String jsonString){
        this.jsonString = jsonString;
    }







}
