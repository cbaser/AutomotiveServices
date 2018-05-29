package com.gameco.cakin.automotiveservices.backend;

import com.gameco.cakin.automotiveservices.datamodel.Car;

/**
 * Created by cakin on 11/14/2017.
 */

public class BackendHelper {
    private String jsonString;
    private BackendController backendController;
    private myJSONParser jsonParser;
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
//    public void setJsonString(String jsonString){
//        this.jsonString = jsonString;
//    }







}
