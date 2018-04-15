package com.gameco.cakin.automotiveservices.activites.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.activites.MainActivity;
import com.gameco.cakin.automotiveservices.firebase.MyFirebaseDatabase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

import static android.app.Activity.RESULT_OK;

public class FragmentProfile extends Fragment {
    private static final int PICK_IMAGE_REQUEST = 123;
    private String TAG = "FRAGMENTPROFILE";
    private Uri filePath;
    private ImageView imageView;
    private MyFirebaseDatabase firebaseDatabase;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_userdetails, container, false);
        CardView cardView = view.findViewById(R.id.cardView_profile);
        firebaseDatabase = new MyFirebaseDatabase(this.getActivity());
        imageView = (ImageView) cardView.findViewById(R.id.profPic_Profile);
       Log.e(TAG,imageView.toString());
        firebaseDatabase.getImage(imageView);
        FloatingActionButton buttonSelectImage = view.findViewById(R.id.floatingSelectImage);
//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
//            filePath = data.getData();
//            try {
//                Picasso.with(this.getActivity()).load(filePath).fit().centerCrop().into(imageView);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

        buttonSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
               startActivityForResult(Intent.createChooser(intent, "Please Select a Picture"), PICK_IMAGE_REQUEST);

            }
        });

        return view;
    }
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
//            filePath = data.getData();
//
//        }
//    }
//    private void getImage(final ImageView imageView){
//        mStorageRef = FirebaseStorage.getInstance().getReference();
//         user = FirebaseAuth.getInstance().getCurrentUser();
//
//         picsRef = mStorageRef.child("images/"+ user.getEmail().replace(".",","));
//        try {
//            final File localFile = File.createTempFile("images", "jpg");
//            picsRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener< FileDownloadTask.TaskSnapshot >() {
//                @Override
//                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
//                    imageView.setImageBitmap( BitmapFactory.decodeFile(localFile.getAbsolutePath()));
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//         try {
//             File localFile = File.createTempFile("images", "jpg");
//             picsRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
//                 @Override
//                 public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
//                    imageView.setImageResource(taskSnapshot.);
//                 }
//             }).addOnFailureListener(new OnFailureListener() {
//                 @Override
//                 public void onFailure(@NonNull Exception e) {
//
//                 }
//             });
//         }catch (Exception e){
//             Log.e(TAG,e.getMessage());
//         }
@Override
public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

        filePath = data.getData();
        Log.e(TAG,filePath.getEncodedPath());
        try {
            InputStream inputStream = getActivity().getContentResolver().openInputStream(data.getData());
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
           firebaseDatabase.uploadImage(bitmap);
            // addToDatabase(bitmap);
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getActivity().getContentResolver(), filePath);
//                imageView.setImageBitmap(bitmap);
//                Picasso.get().load(filePath).fit().centerCrop().into(imageView);
//                if(filePath!=null)
//                    addToDatabase();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


    }
//    private void uploadImage(Bitmap bitmap){
//        mStorageRef = FirebaseStorage.getInstance().getReference();
//        user = FirebaseAuth.getInstance().getCurrentUser();
//        picsRef = mStorageRef.child("images/"+ user.getEmail().replace(".",","));
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
//        byte[] data = baos.toByteArray();
//        UploadTask uploadTask = picsRef.putBytes(data);
//        uploadTask.addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception exception) {
//                // Handle unsuccessful uploads
//            }
//        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                Uri downloadUrl = taskSnapshot.getDownloadUrl();
//                Log.d(TAG,downloadUrl.getEncodedPath());
//            }
//        });
//    }



//    public void addToDatabase(){
//        mStorageRef = FirebaseStorage.getInstance().getReference();
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        StorageReference picsRef = mStorageRef.child("images/"+ user.getEmail().replace(".",","));
//        Log.e(TAG,picsRef.toString());
//        picsRef.putFile(filePath)
//                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//                        Toast.makeText(getContext(),"Picture Added",Toast.LENGTH_SHORT);
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//
//                        Toast.makeText(getContext(),"Picture couldn't be posted",Toast.LENGTH_SHORT);
//                    }
//                })
//                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//                        //calculating progress percentage
//                        double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
//
//                        //displaying percentage in progress dialog
//
//                    }
//                });
//    }

//}
