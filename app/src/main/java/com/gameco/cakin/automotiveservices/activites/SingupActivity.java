package com.gameco.cakin.automotiveservices.activites;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Path;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.backend.BackendHelper;
import com.gameco.cakin.automotiveservices.datamodel.Car;
import com.gameco.cakin.automotiveservices.datamodel.CarVINs;
import com.gameco.cakin.automotiveservices.datamodel.Friend;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class SingupActivity extends AppCompatActivity {
    private Button signupBtn;
    private EditText passwordField,fullNameField,eMailField;
    private FirebaseAuth firebaseAuth;
    private String [] carTypes = {"BMW i3","BMW 120d","BMW 140i","BMW M235i"};
    private Spinner spinner;
    private String selectedType;
    private ArrayAdapter<String> dataAdapter;
    private static final String TAG = "EmailPassword";
    private ImageView sback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);
        firebaseAuth = FirebaseAuth.getInstance();
        spinner = (Spinner) findViewById(R.id.spinner);
        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, carTypes);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                ((TextView)parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.colorLeaGreen));
                ((TextView)parent.getChildAt(0)).setTextSize(16);
                selectedType = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        passwordField = (EditText) findViewById(R.id.password_edit);
        fullNameField = (EditText) findViewById(R.id.fullname_edit);
        eMailField = (EditText) findViewById(R.id.email_edit);

        signupBtn = (Button) findViewById(R.id.signup_button);
        sback=(ImageView) findViewById(R.id.backButton);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount(eMailField.getText().toString(),passwordField.getText().toString(),fullNameField.getText().toString());
            }
        });
        sback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(SingupActivity.this,OpeningActivity.class);
                startActivity(it);
            }
        });
    }
    private void createAccount(final String email,final String password, final String fullName){
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }
        if(password.length()<6){
            Toast.makeText(SingupActivity.this, "Password must be at least 6 characters",
                    Toast.LENGTH_SHORT).show();
        }
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.e(TAG, "createUserWithEmail:success");
                    Toast.makeText(SingupActivity.this, "Account created.",
                            Toast.LENGTH_SHORT).show();
                    FirebaseUser user = firebaseAuth.getCurrentUser();
        //            String userFullName = fullNameField.getText().toString();
                    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

                    DatabaseReference reference = rootRef.child("Users").child(fullName);
                    Car car = new Car();
                    car.setCarName(selectedType);
                    car.setVIN(CarVINs.getVINFromType(selectedType));
                    reference.child("Full Name").setValue(fullName);
                    reference.child("Email").setValue(email);
                    reference.child("Password").setValue(password);
                    reference.child("Car").setValue(car);
                    reference.child("Level").setValue("Newbie");
                    reference.child("Points").setValue(12345);
                    reference.child("ChallengeCount").setValue(0);


                    Toast.makeText(SingupActivity.this, "Please log in",
                            Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SingupActivity.this, OpeningActivity.class));

                } else {
                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                    Toast.makeText(SingupActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
    private boolean validateForm(){
        boolean valid =true;

        String email = eMailField.getText().toString();
        if(TextUtils.isEmpty(email)){
            eMailField.setError("Required");
            valid =false;
        }
        else
            eMailField.setError(null);

        String password =passwordField.getText().toString();
        if(TextUtils.isEmpty(email)){
            passwordField.setError("Required");
            valid =false;
        }
        else
            passwordField.setError(null);

        return valid;
    }

}

//        if(firebaseAuth.getCurrentUser()==null){
//            finish();
//            startActivity(new Intent(getApplicationContext(),OpeningActivity.class));
//        }
//     BackendHelper backendHelper = new BackendHelper();
//      backendHelper.tryRegister("Register",fullName.getText().toString(),eMail.getText().toString(),userName.getText().toString(),password.getText().toString());