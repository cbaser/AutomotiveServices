package com.bmw.cakin.automotiveservices.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.bmw.cakin.automotiveservices.R;


public class OpeningActivity extends AppCompatActivity {
    private long backPressedTime = 0;
    private LinearLayout singup;
    private TextView singin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);
        singup = (LinearLayout)findViewById(R.id.singupLayout);
        singin = (TextView)findViewById(R.id.singin);

        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(OpeningActivity.this,SingupActivity.class);
                startActivity(it);

            }
        });

        singin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(OpeningActivity.this,LoginActivity.class);
                startActivity(it);
            }
        });

    }
    @Override
    public void onBackPressed() {
        backPressedTime = (backPressedTime + 1);
        Toast.makeText(getApplicationContext(), " Press Back again to Exit ", Toast.LENGTH_SHORT).show();
        if (backPressedTime > 1) {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
            OpeningActivity.this.finish();
        }
    }

}
