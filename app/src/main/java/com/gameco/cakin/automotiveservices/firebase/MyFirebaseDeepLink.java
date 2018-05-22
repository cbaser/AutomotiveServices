package com.gameco.cakin.automotiveservices.firebase;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.ShortDynamicLink;

public class MyFirebaseDeepLink {
    private final String TAG= getClass().getName();
    private FirebaseDynamicLinks firebaseDynamicLinks;
    private FirebaseAnalytics firebaseAnalytics;
    private Activity activity;
    public MyFirebaseDeepLink(Activity activity){
        this.activity = activity;

    }


        public void shareLongDynamicLink() {
            Intent intent = new Intent();
            String msg = "Install GamECO " + buildDynamicLink();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, msg);
            intent.setType("text/plain");
            activity.startActivity(intent);
        }

    private String buildDynamicLink(){
        DynamicLink dynamicLink = FirebaseDynamicLinks.getInstance().createDynamicLink()
                .setLink(Uri.parse("http://vmkrcmar20.informatik.tu-muenchen.de/uploads/gameco.apk*/"))
                .setDynamicLinkDomain("q76z9.app.goo.gl")
                .setAndroidParameters(new DynamicLink.AndroidParameters.Builder().build())
                .buildDynamicLink();



        return dynamicLink.getUri().toString();
    }
    public void shareShortDynamicLink(View view){
        Task<ShortDynamicLink> shortDynamicLinkTask = FirebaseDynamicLinks.getInstance().createDynamicLink()
                .setLongLink(Uri.parse(buildDynamicLink()))
                .buildShortDynamicLink()
                .addOnCompleteListener(activity, new OnCompleteListener<ShortDynamicLink>() {
                    @Override
                    public void onComplete(@NonNull Task<ShortDynamicLink> task) {
                        if(task.isSuccessful()){
                            Uri shortLink = task.getResult().getShortLink();
                            Uri flowchartLink = task.getResult().getPreviewLink(); //flowchart link is a debugging URL

                            Log.d(TAG, shortLink.toString());
                            Log.d(TAG, flowchartLink.toString());
                            Intent intent = new Intent();
                            String msg = "Install GamECO " + shortLink.toString();
                            intent.setAction(Intent.ACTION_SEND);
                            intent.putExtra(Intent.EXTRA_TEXT, msg);
                            intent.setType("text/plain");
                            activity.startActivity(intent);
                        }
                    }
                }).addOnCompleteListener(new OnCompleteListener<ShortDynamicLink>() {
                    @Override
                    public void onComplete(@NonNull Task<ShortDynamicLink> task) {
                        Toast.makeText(activity,"Share GamECO",Toast.LENGTH_LONG).show();
                    }
                });
    }

}
