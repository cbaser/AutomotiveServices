package com.gameco.cakin.automotiveservices.activites.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.activites.OpeningActivity;
import com.gameco.cakin.automotiveservices.datamodel.CurrentUser;
import com.gameco.cakin.automotiveservices.firebase.MyFirebaseDatabase;
import com.gameco.cakin.automotiveservices.firebase.MyFirebaseUserAuth;

import java.io.InputStream;

import static android.app.Activity.RESULT_OK;

public class FragmentProfile extends Fragment {
    private static final int PICK_IMAGE_REQUEST = 123;
    private String TAG = "FRAGMENTPROFILE";
    private ImageView imageView;
    private TextView txtFullName, txtCarVIN, txtScore;
    private CurrentUser currentUser;
    private MyFirebaseDatabase firebaseDatabase;
    private MyFirebaseUserAuth myFirebaseUserAuth;
    private EditText emailText, oldPassText, newPassText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_userdetails, container, false);
        firebaseDatabase = new MyFirebaseDatabase(this.getActivity());
        myFirebaseUserAuth = new MyFirebaseUserAuth(this.getActivity());
        currentUser = firebaseDatabase.getUserFromPreferences();
        /** Initialize GUI Parts */
        CardView cardView = view.findViewById(R.id.cardView_profile_upper);
        imageView = (ImageView) cardView.findViewById(R.id.profPic_Profile);
        txtCarVIN = (TextView) cardView.findViewById(R.id.txtCarVIN_Profile);
        txtScore = (TextView) cardView.findViewById(R.id.txtScore_Profile);
        txtFullName = (TextView) cardView.findViewById(R.id.txtFullName_Profile);

        View detailsView = view.findViewById(R.id.cardView_profile_down);
        emailText = detailsView.findViewById(R.id.edit_profile_email);
        oldPassText = detailsView.findViewById(R.id.edit_profile_old_password);
        newPassText = detailsView.findViewById(R.id.edit_profile_new_password);
        setupGUI();


        FloatingActionButton buttonSelectImage = view.findViewById(R.id.floatingSelectImage);

        buttonSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadPicture();

            }
        });
        Button updateEmailBtn = detailsView.findViewById(R.id.profileUpdateEmailBtn);
        updateEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = createAlertDialog("Update Email", "Are you sure you want to update your email?");
                builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        if (checkValidEmailOrPass(emailText.getText().toString(), true)) {
                            myFirebaseUserAuth.updateEmailAuth(emailText.getText().toString());
                        } else {
                            Toast.makeText(getActivity().getApplicationContext(), "Please Check your Email Address", Toast.LENGTH_LONG).show();
                            Log.e(TAG, emailText.getText().toString());
                        }

                    }
                })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        }).show();
            }
        });
        Button updatePasswordBtn = detailsView.findViewById(R.id.profileUpdatePasswordBtn);
        updatePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = createAlertDialog("Update Password", "Are you sure you want to update your password?");
                builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        if (checkOldPassword(oldPassText.getText().toString())) {
                            if (checkValidEmailOrPass(newPassText.getText().toString(), false)) {
                                myFirebaseUserAuth.updatePassAuth(newPassText.getText().toString());
                                Toast.makeText(getActivity().getApplicationContext(), "Restarting Application", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getActivity().getApplicationContext(), "Your Password Should be more than 7 Characters", Toast.LENGTH_LONG).show();
                            }
                        } else
                            Toast.makeText(getActivity().getApplicationContext(), "Please Check your old password", Toast.LENGTH_LONG).show();


                    }
                })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        }).show();

            }
        });
        Button profileDeleteBtn = detailsView.findViewById(R.id.profileDeleteBtn);
        profileDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = createAlertDialog("Delete Account", "Are you sure you want to delete your account?");
                builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        myFirebaseUserAuth.deleteUserAuth();
                        Intent it = new Intent(getActivity(), OpeningActivity.class);
                        getActivity().startActivity(it);

                    }
                })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        }).show();
            }
        });


        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri filePath = data.getData();
            Intent editIntent = new Intent(Intent.ACTION_EDIT);
            editIntent.setDataAndType(filePath, "image/*");
            editIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(Intent.createChooser(editIntent, null));
            Log.e(TAG, filePath.getEncodedPath());
            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(data.getData());
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                firebaseDatabase.uploadProfileImage(bitmap);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void setupGUI() {
        txtFullName.setText(currentUser.getNickName());
        txtCarVIN.setText(currentUser.getCar().getVIN());
        txtScore.setText(String.format(Integer.toString(currentUser.getPoints()), "%d"));
        firebaseDatabase.getProfileImage(imageView);
    }

    private void uploadPicture() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Please Select a Picture"), PICK_IMAGE_REQUEST);
    }

    private boolean checkOldPassword(String oldPass) {
        return oldPass.equals(currentUser.getPassword());
    }

    private boolean checkValidEmailOrPass(String input, boolean emailOrPass) {
        if (emailOrPass)
            return (!TextUtils.isEmpty(input) && Patterns.EMAIL_ADDRESS.matcher(input).matches());
        else
            return (!TextUtils.isEmpty(input) && input.length() > 7);

    }

    private AlertDialog.Builder createAlertDialog(String title, String message) {

        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this.getActivity());
        builder.setTitle(title)
                .setMessage(message)
                .setIcon(android.R.drawable.ic_dialog_alert);

        return builder;


    }


}

