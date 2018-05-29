package com.gameco.cakin.automotiveservices.backend;

import android.net.Uri;
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
    private void setResponse(String response){
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
            case "VIN":
                this.setResponse(doRegister(strings));
                break;
            case "Telematics":
                this.setResponse(getTelematics());
                break;
        }

        return response;
    }

    protected void onPreExecute(){
    }


    private String doRegister(String[] args){
        String VIN = args[1];
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("http://vmkrcmar20.informatik.tu-muenchen.de/saveVinToDatabase.php")
                .appendQueryParameter("VIN",VIN);

        try{
            String register_website = "http://vmkrcmar20.informatik.tu-muenchen.de/saveVinToDatabase.php";
            HttpURLConnection connection = this.establishConnection(register_website +"?VIN="+VIN);
            assert connection != null;
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();
            InputStream is = connection.getInputStream();
            if(is != null){
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
            e.printStackTrace();
        }
        return response;

    }
    private String getTelematics(){

        String jsonTelematics = null;

            try{
                StringBuilder stringBuilder = new StringBuilder();
                String telematic_website = "http://vmkrcmar20.informatik.tu-muenchen.de/getTelematicKeys.php";
                HttpURLConnection connection = this.establishConnection(telematic_website);
                assert connection != null;
                connection.connect();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((jsonTelematics =bufferedReader.readLine()) != null){
                    stringBuilder.append(jsonTelematics).append("\n");
                }
                return stringBuilder.toString().trim();

            }catch (Exception e){
                e.printStackTrace();
            }

        return jsonTelematics;
    }



}
//    private String doLogin(String[] args) {
//
//        try{
//            String username = args[1];
//            String password = args[2];
//
//            HttpURLConnection connection =  this.establishConnection(this.login_website+"?username="+username+"&password="+password);
//            connection.setRequestMethod("GET");
//            connection.setDoInput(true);
//            connection.connect();
//            InputStream is=connection.getInputStream();
//            if (is != null) {
//                StringBuilder sb = new StringBuilder();
//                String line;
//                try {
//                    BufferedReader reader = new BufferedReader(
//                            new InputStreamReader(is));
//                    while ((line = reader.readLine()) != null) {
//                        sb.append(line);
//                    }
//                    reader.close();
//                } finally {
//                    is.close();
//                }
//                response = sb.toString();
//            }
//        }catch (Exception e){
//            response=null;
//        }
//        return response;
//    }