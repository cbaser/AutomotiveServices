package com.gameco.cakin.automotiveservices.backend;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by cakin on 11/14/2017.
 */

public class BackendController extends AsyncTask<String,Void,String>{
    private String response="";
    private String login_website = "http://vmkrcmar20.informatik.tu-muenchen.de/login.php";
    private String register_website ="http://vmkrcmar20.informatik.tu-muenchen.de/signup.php";
    private String telematic_website = "http://vmkrcmar20.informatik.tu-muenchen.de/getTelematicKeys.php";


    public String getResponse(){
        return response;
    }
    public void setResponse(String response){


        this.response= response;

    }

    private HttpURLConnection establishConnection(String website){
        try{
            URL url = new URL(website);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000);

            return connection;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    @Override
    protected String doInBackground(String... strings) {
        String operationType = strings[0];

        switch (operationType){
            case "Login":
                this.setResponse(doLogin(strings));
                break;
            case "Register":
                this.setResponse(doRegister(strings));
            case "Telematics":
                this.setResponse(getTelematics());
        }

        return response;
    }

    protected void onPreExecute(){
    }

    private String doLogin(String[] args) {

        try{
            String username = args[1];
            String password = args[2];

            HttpURLConnection connection =  this.establishConnection(this.login_website+"?username="+username+"&password="+password);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();
            InputStream is=connection.getInputStream();
            if (is != null) {
                StringBuilder sb = new StringBuilder();
                String line;
                try {
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(is));
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                    reader.close();
                } finally {
                    is.close();
                }
                response = sb.toString();
            }
        }catch (Exception e){
            response=null;
        }
        return response;
    }
    private String doRegister(String[] args){
        String username = args[1];
        String password = args[2];
        String fullname = args[3];
        String email = args[4];
        HttpURLConnection connection = this.establishConnection(this.register_website);
        return null;

    }
    private String getTelematics(){

        String jsonTelematics = null;

            try{
                StringBuilder stringBuilder = new StringBuilder();
                HttpURLConnection connection = this.establishConnection(this.telematic_website);
                connection.connect();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((jsonTelematics =bufferedReader.readLine()) != null){
                    stringBuilder.append(jsonTelematics+"\n");
                }
      //          Log.i(  "STRINGBUILDER",stringBuilder.toString().trim());
                return stringBuilder.toString().trim();

            }catch (Exception e){
                e.printStackTrace();
            }

        return jsonTelematics;
    }



}
