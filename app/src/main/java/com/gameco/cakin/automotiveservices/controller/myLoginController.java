package com.gameco.cakin.automotiveservices.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.activites.LoginActivity;
import com.gameco.cakin.automotiveservices.activites.ProgressActivity;
import com.gameco.cakin.automotiveservices.backend.BackendHelper;
import com.gameco.cakin.automotiveservices.firebase.MyFirebaseDatabase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.onesignal.OneSignal;

public abstract class myLoginController {
    private BackendHelper backendHelper;
    SharedPreferences sharedPreferences;
    FirebaseAuth firebaseAuth;
    protected MyFirebaseDatabase myFirebaseDatabase;
    SharedPreferences.Editor editor;
    AppCompatActivity appCompatActivity;
    abstract void initialize();
     abstract void handleAccount();
    public myLoginController(AppCompatActivity appCompatActivity){
         this.appCompatActivity = appCompatActivity;
         backendHelper = new BackendHelper();
         myFirebaseDatabase = new MyFirebaseDatabase(appCompatActivity);
         firebaseAuth = FirebaseAuth.getInstance();
     }

     void startMainActivity(){
         Intent i = new Intent(appCompatActivity, ProgressActivity.class);
         i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
         i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
         appCompatActivity.startActivity(i);
         appCompatActivity.finish();


     }
     void startRegistration(String email, final String name, final String photoUri){
         View popupView = appCompatActivity.getLayoutInflater().inflate(R.layout.popup_registration, null);
         final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
         Button registerButton;
         final EditText emailEdit,nameEdit,vinEdit;
         emailEdit = popupView.findViewById(R.id.popup_registration_email_edit);
         nameEdit  = popupView.findViewById(R.id.popup_registration_username_edit);
         vinEdit = popupView.findViewById(R.id.popup_registration_vin_edit);
         registerButton = popupView.findViewById(R.id.popup_registration_button);

         emailEdit.setText(email);
         nameEdit.setText(name);


         registerButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String[] tmp = new String[2];
                 tmp[0] = "VIN";
                 if(!vinEdit.getText().toString().isEmpty() &&  (vinEdit.getText().toString().length() == 17)){
                     tmp[1] = vinEdit.getText().toString();
                     if (backendHelper.tryRegister(tmp).contains("OK")){
                         myFirebaseDatabase.createAccountInFirebaseDatabase(emailEdit.getText().toString(),"Provided By 3rd Party",nameEdit.getText().toString(),vinEdit.getText().toString(),photoUri);
                         startMainActivity();
                     }
                 }

                 else
                 {
                     Toast.makeText(appCompatActivity, "Please check your vehicle Identification Number", Toast.LENGTH_LONG).show();
                 }

             }
         });
         popupWindow.showAtLocation(popupView, Gravity.NO_GRAVITY, 10, 10);
         popupWindow.setFocusable(true);
         popupWindow.update();

     }
   abstract   boolean checkPreferences();
  abstract    void startLogin();
  abstract    void activityResult(int requestCode, int resultCode, Intent data);
 public abstract  void signOut();


  public void emailLogin(final String email, String password){
      firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(appCompatActivity, new OnCompleteListener<AuthResult>() {
          @Override
          public void onComplete(@NonNull Task<AuthResult> task) {
              Log.e("---TESTING---", "sign In Successful:" + task.isSuccessful());
              // If sign in fails, display a message to the user. If sign in succeeds
              // the auth state listener will be notified and logic to handle the
              // signed in user can be handled in the listener.
              if (!task.isSuccessful()) {
               //   Log.e("---TESTING---", "signInWithEmail:failed", task.getException());
                  Toast.makeText(appCompatActivity, "Failed to Connect", Toast.LENGTH_SHORT).show();
              } else {
                  DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("Users");
                  rootRef.child(email.replace(".", ",")).addValueEventListener(new ValueEventListener() {
                      @Override
                      public void onDataChange(DataSnapshot dataSnapshot) {
                     //     Log.e("My Login Controller", dataSnapshot.child("NickName").getValue().toString());
                          Toast.makeText(appCompatActivity, "Welcome : " + dataSnapshot.child("NickName").getValue().toString(), Toast.LENGTH_LONG).show();
                          FirebaseUser user = firebaseAuth.getCurrentUser();
                       //   Log.e("LOGGED", "user: " + user.getEmail());


                          OneSignal.sendTag("User_ID", user.getEmail());
                          startMainActivity();
                          appCompatActivity.finish();
                      }

                      @Override
                      public void onCancelled(DatabaseError databaseError) {

                      }
                  });




              }
          }
      });
  }
}
