package com.gameco.cakin.automotiveservices.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.backend.BackendHelper;
import com.gameco.cakin.automotiveservices.datamodel.Car;
import com.gameco.cakin.automotiveservices.firebase.MyFirebaseDatabase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class SingupActivity extends AppCompatActivity {
    private Button signupBtn;
    private EditText passwordField,nicknameField,eMailField,VINField;
    private FirebaseAuth firebaseAuth;
    private BackendHelper backendHelper;
    private ProgressBar progressBar;

    private StorageReference picsRef;
    private MyFirebaseDatabase myFirebaseDatabase;
    private RelativeLayout layout;
    private String[] tmp;
    private boolean response;
    private static final String TAG = "SignUpActivity";
    private ImageView sback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        myFirebaseDatabase = new MyFirebaseDatabase(this);
        setContentView(R.layout.activity_singup);
        backendHelper = new BackendHelper();
        firebaseAuth = FirebaseAuth.getInstance();
        passwordField = (EditText) findViewById(R.id.password_edit);
        nicknameField = (EditText) findViewById(R.id.nickname_edit);
        eMailField = (EditText) findViewById(R.id.email_edit);
        VINField = (EditText) findViewById(R.id.vin_edit);


        signupBtn = (Button) findViewById(R.id.signup_button);
        sback=(ImageView) findViewById(R.id.backButton);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar = new ProgressBar(SingupActivity.this,null,android.R.attr.progressBarStyleLarge);
                layout = findViewById(R.id.signup_activity);
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100,100);
                params.addRule(RelativeLayout.CENTER_IN_PARENT);
                layout.addView(progressBar,params);

                Log.d(TAG, "createAccount:" + eMailField.getText().toString());
                if (!validateForm()) {
                    return;
                }


                else
                    createAccount();
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

    private void createAccount(){
        tmp = new String[2];
        tmp[0] = "VIN";
        tmp[1] = VINField.getText().toString();
        progressBar.setVisibility(View.VISIBLE);
        String response =backendHelper.tryRegister(tmp);
        Log.e(TAG,response);
        if(response.equals("OK")){
            firebaseAuth.createUserWithEmailAndPassword(eMailField.getText().toString(),passwordField.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Log.e(TAG, "createUserWithEmail:success");

                        Toast.makeText(SingupActivity.this, "Account created.",Toast.LENGTH_SHORT).show();
                        myFirebaseDatabase.createAccountInFirebaseDatabase(eMailField.getText().toString(),
                                passwordField.getText().toString(),
                                nicknameField.getText().toString(),
                                VINField.getText().toString(),
                                "No Picture");

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
        else
            Log.e(TAG,response);
            progressBar.setVisibility(View.GONE);





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
        if(TextUtils.isEmpty(password)){
            passwordField.setError("Required");
            valid =false;
        }
        else
            passwordField.setError(null);
        String VIN =VINField.getText().toString();
        if(TextUtils.isEmpty(VIN)){
            VINField.setError("Required");
            valid =false;
        }
        if(passwordField.getText().toString().length()<6){
            Toast.makeText(SingupActivity.this, "Password must be at least 6 characters",
                    Toast.LENGTH_SHORT).show();
        }
        else
            VINField.setError(null);
        return valid;
    }


}
//     FirebaseUser user = firebaseAuth.getCurrentUser();
//            String userFullName = fullNameField.getText().toString();
//                        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
//                        DatabaseReference reference = rootRef.child("Users").child(email);
//                        Car car = new Car();
//                  //      car.setCarName(selectedType);
//                        car.setVIN(VIN);
//                        reference.child("Full Name").setValue(fullName);
//                        reference.child("Email").setValue(email);
//                        reference.child("Password").setValue(password);
//                        reference.child("Car").setValue(car);
//                        reference.child("Level").setValue("Newbie");
//                        reference.child("Points").setValue(0);
//                        reference.child("ChallengeCount").setValue(0);

//  private String [] carTypes = {"BMW i3","BMW 120d","BMW 140i","BMW M235i"};
//   private Spinner spinner;
// private ArrayAdapter<String> dataAdapter;
//       spinner = (Spinner) findViewById(R.id.spinner);
//        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, carTypes);
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//   spinner.setAdapter(dataAdapter);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
//                ((TextView)parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.colorLeaGreen));
//                ((TextView)parent.getChildAt(0)).setTextSize(16);
//                selectedType = parent.getSelectedItem().toString();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });


//        if(firebaseAuth.getCurrentUser()==null){
//            finish();
//            startActivity(new Intent(getApplicationContext(),OpeningActivity.class));
//        }
//     BackendHelper backendHelper = new BackendHelper();
//     ////    public void createAccountInFirebaseDatabase(String email,String password,String fullname,String VIN){
//////        // FirebaseUser user = firebaseAuth.getCurrentUser();
//////        //            String userFullName = fullNameField.getText().toString();
//////        //   DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
//////        DatabaseReference reference = rootRef.child(email.replace(".",","));
//////        Car car = new Car();
//////        car.setVIN(VIN);
//////        reference.child("Full Name").setValue(fullname);
//////        reference.child("Email").setValue(email);
//////        reference.child("Password").setValue(password);
//////        reference.child("Car").setValue(car);
//////        reference.child("Level").setValue("Newbie");
//////        reference.child("Points").setValue(0);
//////        reference.child("ChallengeCount").setValue(0);
//////
//////
//////    }
////    public interface OnCheckUserExist{
////        void exist();
////        void notExist();
////    } backendHelper.tryRegister("Register",fullName.getText().toString(),eMail.getText().toString(),userName.getText().toString(),password.getText().toString());