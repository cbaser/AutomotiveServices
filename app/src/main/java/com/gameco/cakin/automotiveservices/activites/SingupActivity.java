package com.gameco.cakin.automotiveservices.activites;

import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.backend.BackendHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SingupActivity extends AppCompatActivity {
    private Button signupBtn;
    private EditText userNameField,passwordField,fullNameField,eMailField;
    private FirebaseAuth firebaseAuth;

    private static final String TAG = "EmailPassword";
    private ImageView sback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);
        firebaseAuth = FirebaseAuth.getInstance();

//        if(firebaseAuth.getCurrentUser()==null){
//            finish();
//            startActivity(new Intent(getApplicationContext(),OpeningActivity.class));
//        }




        userNameField = (EditText) findViewById(R.id.username_edit);
        passwordField = (EditText) findViewById(R.id.password_edit);
        fullNameField = (EditText) findViewById(R.id.fullname_edit);
        eMailField = (EditText) findViewById(R.id.email_edit);
        signupBtn = (Button) findViewById(R.id.signup_button);
        sback=(ImageView) findViewById(R.id.backButton);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount(eMailField.getText().toString(),passwordField.getText().toString());
           //     BackendHelper backendHelper = new BackendHelper();
          //      backendHelper.tryRegister("Register",fullName.getText().toString(),eMail.getText().toString(),userName.getText().toString(),password.getText().toString());

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
    private void createAccount(String email, final String password){
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
                    Log.d(TAG, "createUserWithEmail:success");
                    Toast.makeText(SingupActivity.this, "Account created.",
                            Toast.LENGTH_SHORT).show();
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    String userFullName = fullNameField.getText().toString();
                    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

                    DatabaseReference reference = rootRef.child("Users").child(userFullName);
                    reference.child("Name").setValue(userFullName);
                    reference.child("Email").setValue(user.getEmail());
                    reference.child("Password").setValue(password);
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
