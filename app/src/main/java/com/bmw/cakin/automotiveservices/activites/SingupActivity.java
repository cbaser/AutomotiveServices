package com.bmw.cakin.automotiveservices.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bmw.cakin.automotiveservices.R;
import com.bmw.cakin.automotiveservices.backend.BackendController;
import com.bmw.cakin.automotiveservices.backend.BackendHelper;

public class SingupActivity extends AppCompatActivity {
    private Button signupBtn;
    private EditText userName,password,fullName,eMail;

    private ImageView sback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        userName = (EditText) findViewById(R.id.username_edit);
        password = (EditText) findViewById(R.id.password_edit);
        fullName = (EditText) findViewById(R.id.fullname_edit);
        eMail = (EditText) findViewById(R.id.email_edit);
        signupBtn = (Button) findViewById(R.id.signup_button);
        sback=(ImageView) findViewById(R.id.backButton);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackendHelper backendHelper = new BackendHelper();
                backendHelper.tryRegister("Register",fullName.getText().toString(),eMail.getText().toString(),userName.getText().toString(),password.getText().toString());

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

}
