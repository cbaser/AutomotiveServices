package com.gameco.cakin.automotiveservices.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.gameco.cakin.automotiveservices.R;


public class OpeningActivity extends AppCompatActivity {
    private long backPressedTime = 0;
    //private TextView singup;
    private Button singin;
    private ImageView mainIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.anim_from_bottom_bounce);
        mainIcon = (ImageView) findViewById(R.id.mainIcon);
        mainIcon.setAnimation(animation);
       // singup = (TextView) findViewById(R.id.singupLayout);
        singin = (Button) findViewById(R.id.signin_button);

//        singup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//            }
//        });

        singin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainIcon.setAnimation(null);
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
    public void onClick(View v){
        mainIcon.setAnimation(null);
        Intent it = new Intent(OpeningActivity.this,SingupActivity.class);
        startActivity(it);
    }

}
