package com.gameco.cakin.automotiveservices.activites.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.datamodel.Car;
import com.gameco.cakin.automotiveservices.datamodel.CurrentUser;
import com.gameco.cakin.automotiveservices.firebase.MyFirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;


/**
 * Created by cakin on 11/4/2017.
 */

public class FragmentCarstatus extends Fragment {
    private int GALLERY = 1, CAMERA = 2;
    private String TAG = "FragmentCarStatus";
    private static final int PICK_IMAGE_REQUEST = 124;
    private TextView mileage,average_distance,remainingFuel,batteryLevel,nextService,ecoActive,fuelConsumption,vehicleName;
    private ImageView carPicture;
    private MyFirebaseDatabase myFirebaseDatabase;
    private FloatingActionButton uploadPictureBtn;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState)
        {
            myFirebaseDatabase = new MyFirebaseDatabase(this.getActivity());
        View view = inflater.inflate(R.layout.fragment_carstatus,container,false);
        carPicture =(ImageView) view.findViewById(R.id.car_picture);
        vehicleName =(TextView) view.findViewById(R.id.vehicleName);
        mileage = (TextView) view.findViewById(R.id.tv_mileage);
        average_distance = (TextView) view.findViewById(R.id.tv_av_distance);
        remainingFuel = (TextView) view.findViewById(R.id.tv_fuel_state);
        batteryLevel = (TextView) view.findViewById(R.id.tv_battery_state);
        nextService = (TextView) view.findViewById(R.id.tv_next_service);
        ecoActive = (TextView) view.findViewById(R.id.tv_eco_time);
        fuelConsumption= (TextView) view.findViewById(R.id.tv_fuel_consumption);
        uploadPictureBtn = (FloatingActionButton) view.findViewById(R.id.floatingSelectCarImage);
        uploadPictureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            showPictureDialog();
            }
        });
        setupGUIFromObject();

        return view;
    }

    private void setupGUIFromObject(){

        CurrentUser currentUser = myFirebaseDatabase.getUserFromPreferences();
        Car car = currentUser.getCar();
        mileage.setText(car.getMileage());
        average_distance.setText(car.getAverage_Distance());
        remainingFuel.setText(car.getRemaining_Fuel());
        batteryLevel.setText(car.getBattery_Level());
        nextService.setText(car.getNext_Service_Distance());
        fuelConsumption.setText(car.getFuel_Consumption());
        vehicleName.setText(car.getVIN());
        ecoActive.setText(car.getECO_Time());
        myFirebaseDatabase.getCarImage(carPicture);

    }
    private void showPictureDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this.getActivity());
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallery();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }
    public void choosePhotoFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        if(requestCode ==GALLERY && requestCode ==RESULT_OK&& data!=null && data.getData()!=null){
            Uri filePath = data.getData();
            Log.e(TAG, filePath.getEncodedPath());
            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(data.getData());
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                myFirebaseDatabase.uploadCarImage(bitmap);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }



        if (requestCode == CAMERA && resultCode == RESULT_OK ) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            myFirebaseDatabase.uploadCarImage(bitmap);

        }
    }




}
