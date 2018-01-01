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

    public String tryLogin(String... strings) {
       backendController.execute(strings);
       return backendController.getResponse();
    }

    public String tryRegister(String ... strings){
        backendController.execute(strings);
        return backendController.getResponse();
    }
    public Car tryTelematics(String ... strings){
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
