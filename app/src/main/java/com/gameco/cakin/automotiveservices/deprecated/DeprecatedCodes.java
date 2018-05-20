package com.gameco.cakin.automotiveservices.deprecated;

/**
 * Created by cakin on 12/26/2017.
 */

public class DeprecatedCodes {
 //Fragment Friendlist





//firebaseUserSearch(searchText);
//    public static class FriendsViewHolder extends RecyclerView.ViewHolder{
//        View view;
//
//        public FriendsViewHolder(View itemView) {
//            super(itemView);
//            view = itemView;
//        }
//        public void setDetails(String userName, String userPoints, String userImage){
//            TextView name = (TextView) view.findViewById(R.id.txtFriendName);
//            TextView points =(TextView) view.findViewById(R.id.txtFriendPoint);
//            ImageView imageView = (ImageView) view.findViewById(R.id.imageViewPicture);
//
//            Picasso.get().load(userImage).into(imageView);
//            name.setText(userName);
//            points.setText(userPoints);
//
//
//        }
//    }
//        FirebaseRecyclerOptions<CurrentUser> options = new FirebaseRecyclerOptions.Builder<CurrentUser>().setQuery(query,CurrentUser.class).build();
//        FirebaseRecyclerAdapter<CurrentUser,FriendsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<CurrentUser, FriendsViewHolder>(options) {
//            @Override
//            protected void onBindViewHolder(@NonNull FriendsViewHolder holder, int position, @NonNull CurrentUser model) {
//                holder.setDetails(model.getNickName(),String.valueOf(model.getPoints()),model.getPictureURI());
//            }
//
//            @NonNull
//            @Override
//            public FriendsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View view =LayoutInflater.from(getActivity()).inflate(R.layout.group_friend,parent,false);
//                return new FriendsViewHolder(view);
//            }
//        };
//        friendsRankRecyclerView.setAdapter(firebaseRecyclerAdapter);
//   private void firebaseUserSearch(String searchText){
//        Query query =databaseReference
//                .child("Friends")
//                .orderByChild("name")
//                .startAt(searchText)
//                .endAt(searchText+"\uf8ff");
//        FirebaseRecyclerOptions<Friend> options =
//                new FirebaseRecyclerOptions.Builder<Friend>()
//                        .setQuery(query, Friend.class)
//                        .build();
//        FirebaseRecyclerAdapter<Friend,FriendsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Friend, FriendsViewHolder>(options) {
//            @Override
//            protected void onBindViewHolder(@NonNull FriendsViewHolder holder, int position, @NonNull Friend model) {
//
//                holder.setDetails(getActivity().getApplicationContext(),model.getName(),model.getPoint(),model.getImage());
//
//            }
//
//            @Override
//            public FriendsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                View view =LayoutInflater.from(getActivity()).inflate(R.layout.group_friend,parent,false);
//                return new FriendsViewHolder(view);
//            }
//        };
//        friendsRankRecyclerView.setAdapter(firebaseRecyclerAdapter);
//
//
//    }












//Login Activity





//
//    private String getVINFromUser() {
//
//        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(this);
//        View mView = layoutInflaterAndroid.inflate(R.layout.popup_get_vin, null);
//        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(this);
//        alertDialogBuilderUserInput.setView(mView);
//
//        final EditText userInputDialogEditText = (EditText) mView.findViewById(R.id.userInputDialog);
//        alertDialogBuilderUserInput
//                .setCancelable(false)
//                .setPositiveButton("Send", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialogBox, int id) {
//                        // ToDo get user input here
//                        String[] tmp = new String[2];
//                        tmp[0] = "VIN";
//                        tmp[1] = userInputDialogEditText.getText().toString();
//                        VIN = userInputDialogEditText.getText().toString();
//                        if (backendHelper.tryRegister(tmp).contains("OK"))
//                            response = true;
//                        else
//                            Toast.makeText(LoginActivity.this, "Please Check your Vehicle Identification Number.",
//                                    Toast.LENGTH_SHORT).show();
//                    }
//                })
//
//                .setNegativeButton("Cancel",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialogBox, int id) {
//                                dialogBox.cancel();
//                            }
//                        });
//
//        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
//        alertDialogAndroid.show();
//
//        return VIN;
//    }
//            public void checkUserExists(AccessToken accessToken) {
//                Bundle parameters = new Bundle();
//                GraphRequest myRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
//                    @Override
//                    public void onCompleted(final JSONObject object, GraphResponse response) {
//                        try {
//                            final Profile profile = Profile.getCurrentProfile();
//                            if (profile != null) {
//
//                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
//                                reference.child(object.getString("email").replace(".", ",")).addListenerForSingleValueEvent(new ValueEventListener() {
//                                    @Override
//                                    public void onDataChange(DataSnapshot dataSnapshot) {
//                                        try {
//                                            if (!dataSnapshot.exists()) {
//                                                myFirebaseDatabase.createAccountInFirebaseDatabase((String) object.getString("email").replace(".", ","), "Provided From Facebook", (String) object.getString("name"), getVINFromUser(), profile.getProfilePictureUri(100, 100).toString());
//                                            }
//                                        } catch (Exception e) {
//                                            Log.e(TAG, e.getMessage());
//                                        }
//
//                                    }
//
//                                    @Override
//                                    public void onCancelled(DatabaseError databaseError) {
//
//                                    }
//                                });
//
//                                Log.e(TAG, profile.getProfilePictureUri(100, 100).toString());
//                                Log.e(TAG, (String) object.getString("email"));
//                                Log.e(TAG, (String) object.getString("birthday"));
//                                Log.e(TAG, (String) object.getString("name"));
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//                parameters.putString("fields", "id,name,email,gender,birthday");
//                myRequest.setParameters(parameters);
//                myRequest.executeAsync();
//
//
//            }




//    private void handleGraphRequests(final AccessToken loginResult) {
//        Bundle parameters = new Bundle();
//        GraphRequest myRequest = GraphRequest.newMeRequest(loginResult, new GraphRequest.GraphJSONObjectCallback() {
//            @Override
//            public void onCompleted(JSONObject object, GraphResponse response) {
//                try {
//                    Profile profile = Profile.getCurrentProfile();
//                    if (profile != null) {
//                        Log.e(TAG, profile.getProfilePictureUri(100, 100).toString());
//                        Log.e(TAG, (String) object.getString("email"));
//                        Log.e(TAG, (String) object.getString("birthday"));
//                        Log.e(TAG, (String) object.getString("name"));
//
//                        myFirebaseDatabase.createAccountInFirebaseDatabase((String) object.getString("email").replace(".", ","), "Provided From 3rd Party", (String) object.getString("name"), VIN, profile.getProfilePictureUri(100, 100).toString());
//
//
//
//                   //handleFacebookAccessToken(loginResult);
//                    }
//                    else{
//                        Toast.makeText(LoginActivity.this,"Failed To Connect Facebook",Toast.LENGTH_LONG);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        parameters.putString("fields", "id,name,email,gender,birthday");
//        myRequest.setParameters(parameters);
//        myRequest.executeAsync();
//
////        GraphRequest friendRequest = GraphRequest.newMyFriendsRequest(loginResult, new GraphRequest.GraphJSONArrayCallback() {
////            @Override
////            public void onCompleted(JSONArray objects, GraphResponse response) {
////                try {
////                    JSONObject friendlistObject = objects.getJSONObject(0);
////                    String friendListID = friendlistObject.getString("id");
////                    myNewGraphReq(friendListID);
////                } catch (Exception e) {
////                    e.printStackTrace();
////                }
////            }
////        });
////        parameters.putString("fields", "friendlist");
////        friendRequest.setParameters(parameters);
////        friendRequest.executeAsync();
//        // JSONArray jsonArrayFriends = objects.getJSONObject("friendlist").getJSONArray("data");
//        /// JSONObject friendlistObject = jsonArrayFriends.getJSONObject(0);
////        GraphRequest request= GraphRequest.newMeRequest(loginResult, new GraphRequest.GraphJSONObjectCallback() {
////            @Override
////            public void onCompleted(JSONObject object, GraphResponse response) {
////                Log.e("LoginActivity", response.toString());
////                try {
////
////                    createAccountInFirebaseDatabase((String)object.getString("email"),"Provided From Facebook",(String)object.getString("name"),user_vin);
////
////                } catch (JSONException e) {
////                    e.printStackTrace();
////                }
////            }
////        });
////
////        parameters.putString("fields", "friendlist");
////        request.setParameters(parameters);
////        request.executeAsync();
//    }





//    public void checkSignedUserExists(final String email, final String name, final String profileUri){
//
//        SharedPreferences pref = getSharedPreferences("Preference", Activity.MODE_PRIVATE);
//
//        if (!pref.getBoolean("firstrun",true)) {
//            myFirebaseDatabase.createAccountInFirebaseDatabase(email.replace(".", ","), "Provided From 3rd Party", name, getVINFromUser(), profileUri);
//            pref.edit().putBoolean("firstrun", true).commit();
//        }
//
//
////        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
////        Log.e(TAG,email);
////        reference.child(email.replace(".",",")).addListenerForSingleValueEvent(new ValueEventListener() {
////            @Override
////            public void onDataChange(DataSnapshot dataSnapshot) {
////                try{
////                    if (!dataSnapshot.exists()) {
////                        myFirebaseDatabase.createAccountInFirebaseDatabase(email.replace(".", ","), "Provided From Facebook", name, getVINFromUser(), profileUri);
////                    }
////                } catch (Exception e) {
////                    Log.e(TAG, e.getMessage());
////                }
////                    firebaseCallback.onCallback();
////            }
////
////            @Override
////            public void onCancelled(DatabaseError databaseError) {
////
////            }
////        });
//    }




//    private void myNewGraphReq(String friendlistId) {
//        final String graphPath = "/" + friendlistId + "/members/";
//        AccessToken token = AccessToken.getCurrentAccessToken();
//        GraphRequest request = new GraphRequest(token, graphPath, null, HttpMethod.GET, new GraphRequest.Callback() {
//            @Override
//            public void onCompleted(GraphResponse graphResponse) {
//                JSONObject object = graphResponse.getJSONObject();
//                try {
//                    JSONArray arrayOfUsersInFriendList = object.getJSONArray("data");
//                    /* Do something with the user list */
//                    /* ex: get first user in list, "name" */
//                    JSONObject user = arrayOfUsersInFriendList.getJSONObject(0);
//                    String usersName = user.getString("name");
//                    Log.e(TAG, usersName);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        Bundle param = new Bundle();
//        param.putString("fields", "name");
//        request.setParameters(param);
//        request.executeAsync();
//    }

//  handleFacebookAccessToken(loginResult.getAccessToken());
//            checkUserExists(loginResult.getAccessToken());
//               handleFacebookAccessToken(loginResult.getAccessToken());

//                GraphRequest.newMyFriendsRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONArrayCallback() {
//                    @Override
//                    public void onCompleted(JSONArray objects, GraphResponse response) {
//
//                        try {
//                            Log.e(TAG, response.toString());
//                            JSONObject jsonObject = response.getJSONObject();
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
////                        JSONArray friendsArray = objects.getJSONObject("friendlist").getJSONArray("data");
////                        JSONObject friendlistObject = friendsArray.getJSONObject(0);
//                    }
//                });



//    public void createAccountInFirebaseDatabase(String email,String password,String fullname,String VIN){
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(email.replace(".",","));
//        Car car = new Car();
//        car.setVIN(VIN);
//        reference.child("Full Name").setValue(fullname);
//        reference.child("Email").setValue(email);
//        reference.child("Password").setValue(password);
//        reference.child("Car").setValue(car);
//        reference.child("Level").setValue("Newbie");
//        reference.child("Points").setValue(0);
//        reference.child("ChallengeCount").setValue(0);
//
//
//    }
//Setting the tags for Current User.
//                    if (user != null) {
//                        LoggedIn_User_Email =user.getEmail();
//                        if(LoggedIn_User_Email.equals("cagatayakin.baser@tum.de"))
//                            user_full_name="Cagatay Baser";
//                        else
//                            user_full_name="Can Turker";
//                    }
//                    DatabaseReference reference = rootRef.child("Users").child(firebaseAuth.getCurrentUser().getEmail());


//                    if(firebaseAuth.getCurrentUser().getEmail().contains("can"))
//                        Toast.makeText(LoginActivity.this, "Welcome:" +"Can!", Toast.LENGTH_SHORT).show();
//
//                    else
//                        Toast.makeText(LoginActivity.this, "Welcome:" +"Cagatay!", Toast.LENGTH_SHORT).show();
//                Bundle parameters = new Bundle();
//                parameters.putString("fields", "id,name,email,gender, birthday");
//                request.setParameters(parameters);
//                request.executeAsync();
//               handleGraphRequests(loginResult.getAccessToken());
//               handleFacebookAccessToken(loginResult.getAccessToken());

//                 GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
//                    @Override
//                    public void onCompleted(JSONObject object, GraphResponse response) {
//                        Log.e("LoginActivity", response.toString());
//
//                        try {
//                            Profile profile = Profile.getCurrentProfile();
//                            Log.e(TAG,profile.toString());
//                            Log.e(TAG,(String)object.getString("email"));
//                            Log.e(TAG,(String)object.getString("birthday"));
//                            Log.e(TAG,(String)object.getString("name"));
//
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//        try {
//            PackageInfo info = getPackageManager().getPackageInfo(
//                    "com.gameco.cakin.automotiveservices",
//                    PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//
//        } catch (NoSuchAlgorithmException e) {
//
//        }
//        Log.d(TAG, "handleFacebookAccessToken:" + token);
//  //      response = myFirebaseAuth.doFacebookLogin(token);
//        if(response){
//            Intent i = new Intent(LoginActivity.this, MainActivity.class);
//            finish();
//            startActivity(i);
//        }
//         [START_EXCLUDE silent]
//        showProgressDialog();
//         [END_EXCLUDE]
//        response=  myFirebaseAuth.doEmailLogin(email, password, new OnGetDataListener() {
//            @Override
//            public void onSuccessAuth(boolean response) {
//                if(response){
//                  //  LoginActivity.LoggedIn_User_Email =email;
//                    if(LoggedIn_User_Email.equals("cagatayakin.baser@tum.de"))
//                        user_full_name="Cagatay Baser";
//                    else
//                        user_full_name="Can Turker";
//
//                  //  OneSignal.sendTag("User_ID", email);
//                    finish();
//                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(i);
//                }
//            }
//
//            @Override
//            public void onFailedAuth(boolean response) {
//
//            }
//        });
//  firebaseAuth = FirebaseAuth.getInstance();
//        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(task.isSuccessful()){
//                    Log.d(TAG, "signInWithEmail:success");
//                    FirebaseUser user = firebaseAuth.getCurrentUser();
//                    Log.d(TAG,user.getEmail());
//                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
//                    finish();
//                    startActivity(i);
//                }
//            }
//        });
//      response=  myFirebaseAuth.doEmailLogin(email,password);
//
//    }
// FirebaseDatabase.getInstance().setPersistenceEnabled(true);

//mDatabase.setPersistenceEnabled(true);
//                BackendHelper backendHelper = new BackendHelper();
//                String response;
//                if(emailtxt.getText().toString().equals("Cagatay")&& emailtxt.getText().toString().equals("123")){
//                    response ="admin";
//                }
//                else if(userName_edit.getText().toString().equals("Can")&& password_edit.getText().toString().equals("123")){
//                    response="admin";
//                }
//
//                    else
//                    response =   backendHelper.tryLogin("Login",userName_edit.getText().toString(),password_edit.getText().toString());
//
//
//
//             if(response.equals("admin")){
//                 SharedPreferences settings =getSharedPreferences("UserInfo",0);
//                 SharedPreferences.Editor editor = settings.edit();
//                 editor.putString("Username",userName_edit.getText().toString());
//                 editor.putString("Password",password_edit.getText().toString());
//                 editor.apply();
//                 Intent intent = new Intent(LoginActivity.this,MainActivity.class);
//                 startActivity(intent);
//             }

//Fragment Ranking

//       friendList.clear();
//        database = FirebaseDatabase.getInstance();
//        leaderBoardReference = database.getReference().child("Leaderboards");
    //final RankingAdapter rankingAdapter = new RankingAdapter(this.getActivity(),rankList);
    //rankListView.setAdapter(rankingAdapter);
//        leaderBoardReference.orderByChild("Points").addValueEventListener(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                int total = (int) dataSnapshot.getChildrenCount();
//                int i = 0;
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
//                    Rank rank = snapshot.getValue(Rank.class);
//                 //   rank.setPosition(i);
//                    Log.e("FRAGMENT RANKİNG",rank.getPoints()+"");
//                    Log.e("FRAGMENT RANKİNG",rank.getNickName());
//
//                    rankList.add(rank);
//                }
//
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });





    //      Rank rank = new Rank();
    //      rank.setNickname(dataSnapshot.getKey());
    //       rank.setPoint(Long.parseLong(dataSnapshot.getValue().toString()));
//                Rank rank = dataSnapshot.getValue(Rank.class);
//                Log.e("FRAGMENT RANKİNG",rank.getPoint()+"");
//                rankList.add(rank);
    //  rankingAdapter.notifyDataSetChanged();



//        RankingAdapter rankingAdapter = new RankingAdapter(this.getActivity(), rankList);
//        rankListView = (ListView) view.findViewById(R.id.leaderboardListView);
//        listView2.invalidateViews();
//
//        ViewGroup header_ranking = (ViewGroup)inflater.inflate(R.layout.header_ranking_list,listView2,false);
//        listView2.addHeaderView(header_ranking);
//        listView2.setAdapter(rankingAdapter);
//
////        Friend lea = new Friend(this.getActivity().getResources().getDrawable(R.drawable.ic_lea),"Lea Jäntgen",123456);
////        friendList.add(lea);
////
////
////        Friend can = new Friend(this.getActivity().getResources().getDrawable(R.drawable.ic_can),"Can Türker",123455);
////        friendList.add(can);
////
////
////        Friend cagatay = new Friend(this.getActivity().getResources().getDrawable(R.drawable.ic_cagatay),"Cagatay Baser",123444);
////        friendList.add(cagatay);
////
////
////        Friend hampus = new Friend(this.getActivity().getResources().getDrawable(R.drawable.ic_hampus),"Hampus Olausson-Eckl",123443);
////        friendList.add(hampus);
//
//
//
//        listView = (ListView)view.findViewById(R.id.b2bListView);
//
////        ViewGroup header_friends = (ViewGroup)inflater.inflate(R.layout.header_friends_list,listView,false);
////        listView.addHeaderView(header_friends);
//
//        FriendsListAdapter friendsListAdapter = new FriendsListAdapter(this.getActivity(),friendList);
//        listView.setAdapter(friendsListAdapter);
//        rankList.clear();







    //Fragment Profile
    //        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
//            filePath = data.getData();
//            try {
//                Picasso.with(this.getActivity()).load(filePath).fit().centerCrop().into(imageView);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
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
// addToDatabase(bitmap);
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getActivity().getContentResolver(), filePath);
//                imageView.setImageBitmap(bitmap);
//                Picasso.get().load(filePath).fit().centerCrop().into(imageView);
//                if(filePath!=null)
//                    addToDatabase();

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









    //Fragment Challenge Categories
    //        // ListView listView = view.findViewById(R.id.challengesList);
//        controller = new myNotificationController(this);
//        try{
//            getChallengesFromFirebase();
//        }catch (Exception e){
//            Log.e("PROBLEM",e.getMessage());
//        }
    //  private void getChallengesFromFirebase(){
//        challengeList = new ArrayList<>();
//        //      challengeList.clear();
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        mDatabase = FirebaseDatabase.getInstance();
//        //   mRef = mDatabase.getReference().child("Users").child(LoginActivity.user_full_name);
//       // mRef = mDatabase.getReference().child("Users").child(user.getEmail().replace(".",","));
//        mRef = mDatabase.getReference();
//        mRef.child("Challenges").child("Title").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                try{
//
//                    Iterable<DataSnapshot> children = dataSnapshot.getChildren();
//                    for(DataSnapshot childrenShot : children){
//                        Challenge challenge = childrenShot.getValue(Challenge.class);
//                        challengeList.add(challenge);
//                    }
//
//
////                    for(DataSnapshot childrenShot : children){
////                        Iterable<DataSnapshot>innerChildren = childrenShot.getChildren();
////                        for (DataSnapshot challengeShot : innerChildren){
////                            Challenge challenge =challengeShot.getValue(Challenge.class);
////                            challengeList.add(challenge);
//                     //   }
//                    //}
//                    setRecyclerView();
//                }catch (Exception e){
//                    Log.e("ERRORRR",e.getMessage());
//                    // e.printStackTrace();
//                }
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//
//        });
//
//    }

//    ExpandableRelativeLayout expandableLayout1, expandableLayout2, expandableLayout3, expandableLayout4, expandableLayout5;
//   private myNotificationController notificationController;
//   private String title,time,current,target;
//   private long point;
//   private int color;
//    public FragmentChallengeCategories(){
//
//    }
//    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_challenge_categories, container, false);
//        notificationController = new myNotificationController(this);
//
//
//
//
//
//
//
//        Button expandbleBtn1 = (Button) view.findViewById(R.id.expandableButton1);
//        Button expandbleBtn2 = (Button) view.findViewById(R.id.expandableButton2);
//        Button expandbleBtn3 = (Button) view.findViewById(R.id.expandableButton3);
//        Button expandbleBtn4 = (Button) view.findViewById(R.id.expandableButton4);
//
//        expandableLayout1 = (ExpandableRelativeLayout) view.findViewById(R.id.expandableLayout1);
//        expandableLayout2 = (ExpandableRelativeLayout) view.findViewById(R.id.expandableLayout2);
//        expandableLayout3 = (ExpandableRelativeLayout) view.findViewById(R.id.expandableLayout3);
//        expandableLayout4 = (ExpandableRelativeLayout) view.findViewById(R.id.expandableLayout4);
//        expandbleBtn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                expandableLayout1.toggle();
//            }
//        });
//        expandbleBtn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                expandableLayout2.toggle();
//            }
//        });
//        expandbleBtn3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                expandableLayout3.toggle();
//            }
//        });
//        expandbleBtn4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                expandableLayout4.toggle();
//            }
//        });
//
//        View.OnClickListener onClickListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                switch((Integer) view.getTag()){
//                    case 1:
//                        title = "REWE Special Challenge!";
//                        time = "Time : One week";
//                        point = 500;
//                   //     point = "500 Points";
//                        current = ((TextView) expandableLayout1.findViewById(R.id.rewe_cardview_description)).getText().toString() ;
//                        target = ((TextView) expandableLayout1.findViewById(R.id.reweTextView)).getText().toString()+" "+((TextView) expandableLayout1.findViewById(R.id.rewe2TextView)).getText().toString();
//                        color = Color.parseColor("#2c3e50");
//                        break;
//                    case 2:
//                        title = "CO2 Challenge!";
//                        time = "Time :One week";
//                        point = 500;
//                     //   point = "Points to get :500 Points";
//                        current = ((TextView) expandableLayout2.findViewById(R.id.description_co2_description)).getText().toString()+ " "+((TextView) expandableLayout2.findViewById(R.id.currentCo2Textview)).getText().toString() ;
//                        target = ((TextView) expandableLayout2.findViewById(R.id.targetCo2TextView)).getText().toString();
//                        color = Color.parseColor("#2c3e50");
//                        break;
//                    case 3:
//                        title = "Fuel Challenge!";
//                        time = "Time : Two weeks";
//                        point = 1000;
//                 //       point = "Points to get :1000 Points";
//                        current = ((TextView) expandableLayout3.findViewById(R.id.fuel_cardview_description)).getText().toString()+ " "+((TextView) expandableLayout3.findViewById(R.id.fuelTextView)).getText().toString() ;
//                        target = ((TextView) expandableLayout3.findViewById(R.id.fuel2TextView)).getText().toString();
//                        color = Color.parseColor("#2c3e50");
//                        break;
//                    case 4:
//                        title = "Driving Style Challenge!";
//                        time = "Time : Two weeks";
//                    //    point = "Points to get : 1000 Points";
//                        point = 1000;
//                        current = ((TextView) expandableLayout4.findViewById(R.id.driving_cardview_description)).getText().toString()+" "+ ((TextView) expandableLayout4.findViewById(R.id.drivingTextView)).getText().toString() ;
//                        target = ((TextView) expandableLayout4.findViewById(R.id.driving2TextView)).getText().toString();
//                        color = Color.parseColor("#2c3e50");
//                        break;
//
//
//                }
//
//
//             notificationController.showPopUp(title,time,point,current,target,color);
//            }
//        };
//
//
//        Button reweBtn = (Button) view.findViewById(R.id.btn_rewe);
//        reweBtn.setTag(1);
//        reweBtn.setOnClickListener(onClickListener);
//
//        Button co2Btn = (Button) view.findViewById(R.id.btn_co2);
//        co2Btn.setTag(2);
//        co2Btn.setOnClickListener(onClickListener);
//
//        Button fuelBtn = (Button) view.findViewById(R.id.btn_fuel);
//        fuelBtn.setTag(3);
//        fuelBtn.setOnClickListener(onClickListener);
//
//        Button drivingBtn = (Button) view.findViewById(R.id.btn_driving_style);
//        drivingBtn.setTag(4);
//        drivingBtn.setOnClickListener(onClickListener);
//       return view;

//}

//Fragment Home //



//    public void showFriends(){
//        View popupView = getActivity().getLayoutInflater().inflate(R.layout.popup_friends,null);
//        final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//
//        ListView listView = (ListView)popupView.findViewById(R.id.friendsListview);
//        Friend can;
//        friendList.clear();
////        if(!LoginActivity.LoggedIn_User_Email.contains("can"))
////         can = new Friend(fragment.getResources().getDrawable(R.drawable.ic_can),"Can Turker",123454);
////        else
////             can = new Friend(fragment.getResources().getDrawable(R.drawable.ic_cagatay),"Cagatay Baser",123454);
//
//
//        //friendList.add(can);
//        if(friendList.size()>0){
//            friendsListAdapter = new FriendsListAdapter(fragment.getActivity(),friendList);
//            listView.setAdapter(friendsListAdapter);
//            friendsListAdapter.notifyDataSetChanged();
//        }
//
//        Button addFriendBtn = (Button) popupView.findViewById(R.id.addFriendBtn);
//        addFriendBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(fragment.getActivity(), "Will be implemented as Adding new friends", Toast.LENGTH_SHORT).show();
//            }
//        });
//        Button invitePeopleBtn = (Button)popupView.findViewById(R.id.invitePeopleBtn);
//        invitePeopleBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Intent intent = new AppInviteInvitation.IntentBuilder("Invite To GameECO")
////                        .setMessage("Check out this new app GamECO!"))
////                        .setDeepLink(Uri.parse(getString(R.string.invitation_deep_link)))
////                        .setCustomImage(Uri.parse(getString(R.string.invitation_custom_image)))
////                        .setCallToActionText(getString(R.string.invitation_cta))
////                        .build();
////                startActivityForResult(intent, REQUEST_INVITE);
//
//            }
//        });
////        Button createGroupBtn = (Button) popupView.findViewById(R.id.createGroupBtn);
////        createGroupBtn.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Toast.makeText(fragment.getActivity(), "Will be implemented as Creating Group among friends", Toast.LENGTH_SHORT).show();
////            }
////        });
////        Button importGroupBtn = (Button) popupView.findViewById(R.id.importGroupBtn);
////        importGroupBtn.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Toast.makeText(fragment.getActivity(), "Will be implemented as Importing friends from Facebook", Toast.LENGTH_SHORT).show();
////            }
////        });
//
//        FloatingActionButton floatingActionButton = (FloatingActionButton) popupView.findViewById(R.id.exitFAB);
//        floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                popupWindow.dismiss();
//            }
//        });
//
//
//        popupWindow.showAtLocation(popupView, Gravity.CENTER,10,10);
//
//    }
    //                View friendView = fragment.getActivity().getLayoutInflater().inflate(R.layout.popup_add_new_friend,null);
//                 final PopupWindow friendWindow = new PopupWindow(friendView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//                 friendWindow.showAtLocation(friendView, Gravity.NO_GRAVITY,10,10);
//
//
//            final    ListView addFriendList = (ListView) friendView.findViewById(R.id.friendsListview);
//                List<Friend> addfriendList = new ArrayList<>();
//                final Friend can = new Friend(fragment.getResources().getDrawable(R.drawable.ic_can),"Can Turker",123454);
//                addfriendList.add(can);
//                final FriendsListAdapter friendsListAdapter = new FriendsListAdapter(fragment.getActivity(),addfriendList);
//                addFriendList.setAdapter(friendsListAdapter);
//
//                addFriendList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                        addFriendList.getChildAt(i).setBackgroundColor(fragment.getActivity().getResources().getColor(R.color.white));
//
//                    }
//                });
//
//                FloatingActionButton closeAddFriend = (FloatingActionButton) friendView.findViewById(R.id.exitSelectFriend);
//                closeAddFriend.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        friendWindow.dismiss();
//                    }
//                });
//
//                Button addFriendBtn = (Button) friendView.findViewById(R.id.addFriendBtn);
//                addFriendBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//
//                            mRef.child("Cagatay Baser").addListenerForSingleValueEvent(new ValueEventListener() {
//                                @Override
//                                public void onDataChange(DataSnapshot dataSnapshot) {
//                                    dataSnapshot.getRef().child("Friends").child("Name").setValue(can.getName());
//                                    dataSnapshot.getRef().child("Friends").child("Points").setValue(can.getPoint());
//                                }
//
//                                @Override
//                                public void onCancelled(DatabaseError databaseError) {
//
//                                }
//                            });
//                        Toast.makeText(fragment.getActivity(), "Friend Added!", Toast.LENGTH_SHORT).show();
//                        friendWindow.dismiss();
//
//                        friendsListAdapter.notifyDataSetChanged();
//                    }
//                });
//


//    private void getFriendList(){
//
//        mRef.child(LoginActivity.user_full_name).child("Friends").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Friend  friend = dataSnapshot.getValue(Friend.class);
//                friend.setName(dataSnapshot.child("Name").getValue()+"");
//                friend.setPoint((long)dataSnapshot.child("Points").getValue());
//                if(friend.getName().contains("can"))
//                    friend.setImage(fragment.getResources().getDrawable(R.drawable.ic_can));
//                else
//                    friend.setImage(fragment.getResources().getDrawable(R.drawable.ic_cagatay));
//
//
//                friendList.add(friend);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//
//
//
//    }


//                Friend test = dataSnapshot.getValue(Friend.class);
//                test.setName(dataSnapshot.child("Name").getValue()+"");
//                Log.e("TEST--*-",test.getName());
//
//                friend = new Friend(fragment.getResources().getDrawable(R.drawable.ic_can),dataSnapshot.child("Name").getValue()+"",(long)dataSnapshot.child("Points").getValue());
//                Log.e("TEST----",dataSnapshot.getValue()+"");
//                friendList.add(friend);
////                friend = dataSnapshot.getValue(Friend.class);

//    public void setUserInformation(){
//
//    }


}
//    progressBar.setBackgroundColor(Color.WHITE);
//           Bundle bundle = new Bundle();
//           Gson gson = new Gson();
//
//            List<Challenge> challenges = new ArrayList<>();
//            Challenge challenge = new Challenge();
//            challenge.setFriendName("Cagatay");
//            challenges.add(challenge);
//           String value = gson.toJson(challenges);
//           bundle.putString("Challenge", value);
//
//
//           Log.e("-------Challenge------",bundle.getString("Challenge"));
//           setArguments(bundle);
//        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.tmpFloatAct);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                frontController.replaceFragment();
//            }
//        });



// @Override
//    public void onClick(View view) {
//        Fragment fragment = null;
//
//        switch (view.getId()) {
//            case R.id.allChallengesBtn:
//                fragment = new FragmentChallengeCategories();
//                //transitionHelper.replaceFragment(R.id.challengesFrameLayout,fragment);
//                frontController.replaceFragment(R.id.challengesFrameLayout,fragment);
//                break;
//
//            case R.id.onGoingChallengesBtn:
//                fragment = new FragmentSubYourChallenges();
//               // transitionHelper.replaceFragment(R.id.challengesFrameLayout,fragment);
//                frontController.replaceFragment(R.id.challengesFrameLayout,fragment);
//                break;
//        }









//    RelativeLayout relativeLayout1 = (RelativeLayout) view.findViewById(R.id.challengesLayoutHome);
//    Button showBtn = (Button) relativeLayout1.findViewById(R.id.seeYourChallengesBtn);
//    showBtn.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//           // myFragmentController fragmentController = new myFragmentController(fragment);
//          //  fragmentController.replaceFragment(R.id.home_frameLayout,new FragmentMyChallenges());
//            final Dialog dialog = new Dialog(fragment.getContext());
//            dialog.setContentView(R.layout.fragment_sub_yourchallenges);
//
//            dialog.setTitle("Title...");
//            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
//            dialog.show();
//
//        }
//    });
//        try {
//
//            mDatabase =FirebaseDatabase.getInstance();
//            mRef = mDatabase.getReference();
//
//            mRef.child("Users").child(user.getEmail().replace(".",",")).addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                 //   challengeCount  = (long) dataSnapshot.child("ChallengeCount").getValue();
//                 //   points = (long) dataSnapshot.child("Points").getValue();
//                   // remaining = String.valueOf(progressBar.getMax()-challengeCount);
//                 //   txtProgressCount.setText(remaining);
//                //    fullName = (String)dataSnapshot.child("NickName").getValue();
//                 //   txtScore.setText(String.valueOf(points));
//                 //   txtLevel.setText((String)dataSnapshot.child("Level").getValue());
//                 //   txtWelcome.setText("Welcome "+ fullName+" To GamECO!");
//                  //  progressBar.setProgress((int)challengeCount);
//                  //  headerName.setText(fullName);
//                  //  headerEmail.setText(user.getEmail());
//
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });
//            mRef.child("Challenges").child("Title").child("Daily Challenge").addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    Challenge challenge = dataSnapshot.getValue(Challenge.class);
//                    Log.e("FRAGMENT HOME",challenge.toString());
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });
//         //   ((TextView) relativeLayout.findViewById(R.id.txtWelcome)).setText("Welcome"+ fullName +" To GamECO!");
//          //  setUserInformation(view);
//            txtProgressCount.setText(remaining);
//            txtScore.setText(String.valueOf(points));
//
//
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }










    //-----------Fragment CarStatus////
    //
    //    setupGUI();
    //    getCarType();
    // user = FirebaseAuth.getInstance().getCurrentUser();

//        try{
//                car =  backendHelper.tryTelematics("Telematics");
//            vehicleName.setText(car.getVIN());
//             mileage = (TextView) view.findViewById(R.id.tv_mileage);
//
//            mileage.setText(car.getMileage().replace("\"",""));
//
//             average_distance = (TextView) view.findViewById(R.id.tv_av_distance);
//
//            average_distance.setText(car.getAverage_distance_per_week().replace("\"",""));
//
//             remainingFuel = (TextView) view.findViewById(R.id.tv_fuel_state);
//
//            remainingFuel.setText(car.getRemaining_fuel().replace("\"",""));
//
//
//             batteryLevel = (TextView) view.findViewById(R.id.tv_battery_state);
//
//            batteryLevel.setText(car.getBatteryVoltage().replace("\"",""));
//
//             nextService = (TextView) view.findViewById(R.id.tv_next_service);
//
//            nextService.setText(car.getNextServiceDistance().replace("\"",""));
//
//             ecoActive = (TextView) view.findViewById(R.id.tv_eco_time);
//
//            ecoActive.setText(car.getActive_time_of_eco_mode().replace("\"",""));
//
//             fuelConsumption= (TextView) view.findViewById(R.id.tv_fuel_consumption);
//
//            fuelConsumption.setText(car.getFuel_consumption().replace("\"",""));
//
//
//            updateCarData();
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
  //  private void setupGUI(){
//        mDatabase =FirebaseDatabase.getInstance();
//        mRef = mDatabase.getReference();
//        //    mRef.child("Users").child(LoginActivity.user_full_name).addListenerForSingleValueEvent(new ValueEventListener() {
//        mRef.child("Users").child(user.getEmail().replace(".",",")).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                mileage.setText((String)dataSnapshot.child("Car").child("Mileage").getValue());
//                average_distance.setText((String) dataSnapshot.child("Car").child("Average distance").getValue());
//                remainingFuel.setText((String)dataSnapshot.child("Car").child("Remaining Fuel").getValue());
//                batteryLevel.setText((String)dataSnapshot.child("Car").child("Battery Level").getValue());
//                nextService.setText((String)dataSnapshot.child("Car").child("Next Service").getValue());
//                fuelConsumption.setText((String)dataSnapshot.child("Car").child("Fuel Consumption").getValue());
//                vehicleName.setText((String)dataSnapshot.child("Car").child("vin").getValue());
//
//
////                dataSnapshot.getRef().child("Car").child("Average distance").setValue(average_distance.getText().toString());
////                dataSnapshot.getRef().child("Car").child("Remaining Fuel").setValue(remainingFuel.getText().toString());
////                dataSnapshot.getRef().child("Car").child("Next Service").setValue(nextService.getText().toString());
////                dataSnapshot.getRef().child("Car").child("Fuel Consumption").setValue(fuelConsumption.getText().toString());
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//    private void updateCarData(){
//        mDatabase =FirebaseDatabase.getInstance();
//         mRef = mDatabase.getReference();
//     //    mRef.child("Users").child(LoginActivity.user_full_name).addListenerForSingleValueEvent(new ValueEventListener() {
//        mRef.child("Users").child(user.getEmail().replace(".",",")).addListenerForSingleValueEvent(new ValueEventListener() {
//             @Override
//             public void onDataChange(DataSnapshot dataSnapshot) {
//                 dataSnapshot.getRef().child("Car").child("Mileage").setValue(mileage.getText().toString());
//                 dataSnapshot.getRef().child("Car").child("Average distance").setValue(average_distance.getText().toString());
//                 dataSnapshot.getRef().child("Car").child("Remaining Fuel").setValue(remainingFuel.getText().toString());
//                 dataSnapshot.getRef().child("Car").child("Next Service").setValue(nextService.getText().toString());
//                 dataSnapshot.getRef().child("Car").child("Fuel Consumption").setValue(fuelConsumption.getText().toString());
//             }
//
//             @Override
//             public void onCancelled(DatabaseError databaseError) {
//
//             }
//         });
//
//    }
//    private void setVehicleInfo(String carType){
//
//        switch (carType) {
//            case "BMW i3":
//                carPicture.setImageDrawable(this.getActivity().getResources().getDrawable(R.drawable.i3_picture));
//                break;
//            case "BMW 120d":
//                carPicture.setImageDrawable(this.getActivity().getResources().getDrawable(R.drawable.bmw120d));
//                break;
//            case "BMW 140i":
//                carPicture.setImageDrawable(this.getActivity().getResources().getDrawable(R.drawable.bmw140i));
//                break;
//            case "BMW M235i":
//                carPicture.setImageDrawable(this.getActivity().getResources().getDrawable(R.drawable.bmwm235i));
//                break;
//        }
//    }
//    private void getCarType(){
//
//        mDatabase =FirebaseDatabase.getInstance();
//        mRef = mDatabase.getReference();
//        user = FirebaseAuth.getInstance().getCurrentUser();
//
//     //   mRef.child("Users").child(LoginActivity.user_full_name).child("Car").addListenerForSingleValueEvent(new ValueEventListener() {
//        mRef.child("Users").child(user.getEmail().replace(".",",")).child("Car").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                String type  = (String) dataSnapshot.child("carName").getValue();
//                setVehicleInfo(type);
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//    }














    //--------Fragment Achievements---------//
    //        HorizontalBarChart horizontalBarChart = (HorizontalBarChart) view.findViewById(R.id.efficiency_chart);
//        ArrayList<BarEntry> entries = new ArrayList<>();
//        entries.add(new BarEntry(4f,0));
//        entries.add(new BarEntry(4f, 0));
//        entries.add(new BarEntry(8f, 1));
//        entries.add(new BarEntry(6f, 2));
//        entries.add(new BarEntry(12f, 3));
//        entries.add(new BarEntry(18f, 4));
//
//        BarDataSet dataSet = new BarDataSet(entries,"Efficiency Rate");
//
//        ArrayList<String> labels = new ArrayList<>();
//        labels.add("January");
//        labels.add("February");
//        labels.add("March");
//        labels.add("April");
//        labels.add("May");
//        labels.add("June");
//        BarData data = new BarData(dataSet);
//        horizontalBarChart.setData(data);


    //------------Fragment Home---------------//
    //    private List<String> groupList;
//    private List<String> childList;
//    private Map<String, List<String>> friendsCollection;
//    private ExpandableListView friendsListView;
//    private ListView challengesListView;
//    private CurrentUser currentUser;
    // createGroupList();
    //     currentUser = new CurrentUser();
    //   createCollection();
//     final FriendsListAdapter friendsListAdapter = new FriendsListAdapter(getActivity(), groupList, friendsCollection);
    //   friendsListAdapter.setCurrentUser(currentUser);
    //      friendsListView.setAdapter(friendsListAdapter);

    //    friendsListView.setGroupIndicator(null);
//    private void createGroupList() {
//        groupList = new ArrayList<String>();
//        groupList.add("Can");
//        groupList.add("Lea");
//        groupList.add("Hampus");
//    }
//    private void createCollection() {
//        // preparing friends collection(child)
////        String[] canChallenges = { "Challenge 1 : Drive less than 50 km this week"};
////        String[] leaChallenges = { "Challenge 1 :Save one tree for this week"};
////        String[] hampusChallenges = { "Challenge 1 :Drive not faster than 100 km/h this week" };
//          String canScore = "23541";
//          String leaScore = "21051";
//          String hampusScore="21021";
//
//        friendsCollection = new LinkedHashMap<String, List<String>>();
//
//        for (String friend : groupList) {
//            if (friend.equals("Can")) {
//                loadChild(canScore);
//            } else if (friend.equals("Hampus"))
//                loadChild(hampusScore);
//            else if (friend.equals("Lea"))
//                loadChild(leaScore);
//            friendsCollection.put(friend, childList);
//        }
//    }
//    private void loadChild(String friend) {
//        childList = new ArrayList<>();
//        Collections.addAll(childList);
//       // for (String friend : friends)
//            childList.add(friend);
//    }
//
//    private void setGroupIndicatorToRight() {
//        /* Get the screen width */
//        DisplayMetrics dm = new DisplayMetrics();
//        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
//        int width = dm.widthPixels;
//
//        friendsListView.setIndicatorBounds(width - getDipsFromPixel(35), width - getDipsFromPixel(5));
//    }
//    public int getDipsFromPixel(float pixels) {
//        // Get the screen's density scale
//        final float scale = getResources().getDisplayMetrics().density;
//        // Convert the dps to pixels, based on density scale
//        return (int) (pixels * scale + 0.5f);
//    }


    //-------------OpeningActivity---------//

//        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f,1.0f);
//
//        alphaAnimation.setInterpolator(new BounceInterpolator());
//        alphaAnimation.setRepeatCount(3);
//        alphaAnimation.setDuration(100);
//        alphaAnimation.setRepeatMode(Animation.REVERSE);
//        RotateAnimation animation = new RotateAnimation(0f,350f,15f,15f);
//        animation.setInterpolator(new LinearInterpolator());
//        animation.setRepeatCount(Animation.INFINITE);
//        animation.setDuration(700);


    //--------FriendsListAdapter---------------//

//


//
//}
//@Override
//    public int getGroupCount() {
//        return friends.size();
//    }
//
//    @Override
//    public int getChildrenCount(int groupPosition) {
//        return friendsCollections.get(friends.get(groupPosition)).size();
//    }
//
//    @Override
//    public Object getGroup(int groupPosition) {
//        return friends.get(groupPosition);
//    }
//
//    @Override
//    public Object getChild(int groupPosition, int childPosition) {
//        return friendsCollections.get(friends.get(groupPosition)).get(childPosition);
//    }
//
//    @Override
//    public long getGroupId(int groupPosition) {
//        return groupPosition;
//    }
//
//    @Override
//    public long getChildId(int groupPosition, int childPosition) {
//        return childPosition;
//    }
//    @Override
//    public boolean hasStableIds() {
//        return true;
//    }
//
//    @Override
//    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
//        ViewHolder holder;
//
//        String friendName = (String) getGroup(groupPosition);
//        if (convertView == null) {
//
//   //         convertView = layoutInflater.inflate(R.layout.group_item, null);
//            holder = new ViewHolder();
//   //         holder.textView = (TextView) convertView.findViewById(R.id.group_item);
//            convertView.setTag(holder);
//        }
//        else
//            holder =(ViewHolder) convertView.getTag();
//        holder.textView.setText(getGroup(groupPosition).toString());
//  //      TextView item = (TextView) convertView.findViewById(R.id.group_item);
//        item.setTypeface(null, Typeface.BOLD);
//        item.setText(friendName);
//        return convertView;
//    }
//
//    @Override
//    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//        final String friend = (String) getChild(groupPosition, childPosition);
//        ViewHolder holder;
//
//        if (convertView == null) {
//   //         convertView = layoutInflater.inflate(R.layout.group_child_item, null);
//            holder = new ViewHolder();
//   //         holder.textView = (TextView) convertView.findViewById(R.id.child_item);
//            convertView.setTag(holder);
//        }
//        else
//            holder =(ViewHolder) convertView.getTag();
//        holder.textView.setText(getGroup(groupPosition).toString());
//
//    //    final TextView item = (TextView) convertView.findViewById(R.id.child_item);
//
//
//        item.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                builder.setMessage("Do you want to send a challenge?");
//                builder.setCancelable(false);
//                builder.setPositiveButton("Yes",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//          //                      currentUser.getChallenges().add(item.getText().toString());
//                                Notification n  = new Notification.Builder(context)
//                                        .setContentTitle("Challenge!")
//             //                           .setContentText(item.getText().toString() )
//                                        .setSmallIcon(R.drawable.ic_stat_b2b)
//                                        .setAutoCancel(true)
//                                          .build();
//                                NotificationManager notificationManager = (NotificationManager)
//                                        context.getSystemService(NOTIFICATION_SERVICE);
//                                notificationManager.notify(0, n);
////                                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context);
////                                notificationBuilder.setContentTitle("Challenge!");
////                                notificationBuilder.setContentText(item.getText().toString());
//                            }
//                        });
//                builder.setNegativeButton("No",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                dialog.cancel();
//                            }
//                        });
//                AlertDialog alertDialog = builder.create();
//                alertDialog.show();
//
//            }
//        });
//
//
//  //      item.setText(friend);
//        return convertView;
//    }
//
//
//    @Override
//    public boolean isChildSelectable(int groupPosition, int childPosition) {
//        return true;
//    }
//    private class ViewHolder{
//        TextView textView;
//    }
//    public void setCurrentUser(CurrentUser currentUser){
//        this.currentUser = currentUser;
//    }


//-----------------------OLD CODE ----------------------
//private Activity context;
//     private LayoutInflater layoutInflater;
//    private Map<String, List<String>> friendsCollections;
//        private List<String> friends;
//        public FriendsListAdapter(Activity context, List<String>friends,Map<String,List<String>> friendsCollections){
//            this.context = context;
//            this.friends = friends;
//            this.friendsCollections = friendsCollections;
//        }
//
//    @Override
//    public int getCount() {
//        return friends.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return friends.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = layoutInflater.inflate(R.layout.content_challenges_friends_list,null,false);
//        LinearLayout mainLinearLayout = (LinearLayout) convertView.findViewById(R.id.homeLinear);
//
//        for(int i=0;i<friends.size();i++){
//            View additionView = layoutInflater.inflate(R.layout.content_challenges_friends_list,null,false);
//            LinearLayout innerLineerLayout = (LinearLayout) additionView.findViewById(R.id.home_innerlayout);
//            mainLinearLayout.addView(innerLineerLayout);
//        }
//
//
//
//        return convertView;
//    }
//    class ViewHolder{
//            HorizontalScrollView horizontalScrollView;
//            TextView textView;
//            LinearLayout linearLayout,main_linear_layout;
//    }



    //------myJSONParser------------//
    //      if(telematicValues.get(i).contains("bmwcardata_mileage")){
//       currentCar.setMileage( getTelematicValue("bmwcardata_mileage",telematicValues.get(i)));
//                   int position = telematicValues.get(i).indexOf("bmwcardata_mileage");
//                   int valuePos = telematicValues.get(i).indexOf("value",position);
//                   int endPos = telematicValues.get(i).indexOf("}",valuePos);
//                   String mileage = telematicValues.get(i).substring(valuePos,endPos);
//                   currentCar.setMileage(mileage);
//     }
//                if(telematicValues.get(i).contains("bmwcardata_averageDistance")){
//                    int position = telematicValues.get(i).indexOf("bmwcardata_averageDistance");
//                    int valuePos = telematicValues.get(i).indexOf("value",position);
//                    int endPos = telematicValues.get(i).indexOf("}",valuePos);
//                    String averageDistance = telematicValues.get(i).substring(valuePos,endPos);
//                    currentCar.setAverage_distance_per_week(averageDistance);
//                }
//                if(telematicValues.get(i).contains("bmwcardata_remainingFuel")){
//                    int position = telematicValues.get(i).indexOf("bmwcardata_remainingFuel");
//                    int valuePos = telematicValues.get(i).indexOf("value",position);
//                    int endPos = telematicValues.get(i).indexOf("}",valuePos);
//                    String remainingFuel = telematicValues.get(i).substring(valuePos,endPos);
//                    currentCar.setRemaining_fuel(remainingFuel);
//                }

//                if(telematicValues.get(i).contains("bmwcardata_batteryVoltage")){
//                    int position = telematicValues.get(i).indexOf("bmwcardata_batteryVoltage");
//                    int valuePos = telematicValues.get(i).indexOf("value",position);
//                    int endPos = telematicValues.get(i).indexOf("}",valuePos);
//                    String batteryVoltage = telematicValues.get(i).substring(valuePos,endPos);
//                    currentCar.setBatteryVoltage(batteryVoltage);
//                }
//                if(telematicValues.get(i).contains("bmwcardata_nextServiceDistance")){
//                    int position = telematicValues.get(i).indexOf("bmwcardata_nextServiceDistance");
//                    int valuePos = telematicValues.get(i).indexOf("value",position);
//                    int endPos = telematicValues.get(i).indexOf("}",valuePos);
//                    String nextService = telematicValues.get(i).substring(valuePos,endPos);
//                    currentCar.setNextServiceDistance(nextService);
//                }
//                if(telematicValues.get(i).contains("bmwcardata_SegmentLastTripECOTimeOfActivation")){
//                    int position = telematicValues.get(i).indexOf("bmwcardata_SegmentLastTripECOTimeOfActivation");
//                    int valuePos = telematicValues.get(i).indexOf("value",position);
//                    int endPos = telematicValues.get(i).indexOf("}",valuePos);
//                    String ecoTrip = telematicValues.get(i).substring(valuePos,endPos);
//                    currentCar.setActive_time_of_eco_mode(ecoTrip);
//                }
//                if(telematicValues.get(i).contains("bmwcardata_SegmentLastTripFuelConsumption")){
//                    int position = telematicValues.get(i).indexOf("bmwcardata_SegmentLastTripFuelConsumption");
//                    int valuePos = telematicValues.get(i).indexOf("value",position);
//                    int endPos = telematicValues.get(i).indexOf("}",valuePos);
//                    String fuelConsumption = telematicValues.get(i).substring(valuePos,endPos);
//                    currentCar.setFuel_consumption(fuelConsumption);
//                }


//            String rawString = bmwCarData[lastIndex].getTelematics();
//
//          if(!rawString.endsWith("}")){
//              rawString = rawString.substring(0,rawString.lastIndexOf("}"));
//              rawString = rawString+"}]}";
//          }
//            JsonObject telematicObject = parser.parse(rawString).getAsJsonObject();
//            JsonElement telematicElement = telematicObject.get("telematicKeyValues");
//             telematics = gson.fromJson(telematicElement,Telematics[].class);
//
//             if(telematics!=null)
//             currentCar.setTelematicsList(Arrays.asList(telematics));


    //   private TransitionHelper_deprecated transitionHelper;
    //   private UserHelper_deprecated userHelper;
    // userHelper = new UserHelper_deprecated();
    //  transitionHelper = new TransitionHelper_deprecated();
    //  transitionHelper.setFragment(this);
    //  transitionHelper.showDailyChallenge();
    //    FragmentFriends fragmentFriends = new FragmentFriends();
    //    FragmentManager fm = getActivity().getSupportFragmentManager();
    //   fragmentFriends.show(fm,"Dialog Fragment");
    //   transitionHelper.showFriends();
    //  userHelper.setUserInformation(view,getActivity().getSharedPreferences("UserInfo",0));
//}



//Notification Controller
//        challenge.setFriendPicture(fragment.getActivity().getResources().getDrawable(R.drawable.ic_cagatay));
//     challenge.setFriendPicture(fragment.getActivity().getResources().getDrawable(R.drawable.ic_can));
//    mRef.child(LoginActivity.user_full_name).child("Challenges").setValue(challenges);

//                mRef.child("cagatay baser").addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        dataSnapshot.getRef("Challenge")
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });
//





//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getTime() {
//        return time;
//    }
//
//    public void setTime(String time) {
//        this.time = time;
//    }
//
//    public String getPoints() {
//        return points;
//    }
//
//    public void setPoints(String points) {
//        this.points = points;
//    }
//
//    public String getCurrent() {
//        return current;
//    }
//
//    public void setCurrent(String current) {
//        this.current = current;
//    }
//
//    public String getTarget() {
//        return target;
//    }
//
//    public void setTarget(String target) {
//        this.target = target;
//    }

//  private String title,time,points,current,target;


//        Button join_regional = (Button) popupView.findViewById(R.id.join_regional_challenge_button);
//        join_regional.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sendNotificationToSelf(fragment.getActivity(),"Regional Challenge : München","Duration : " +time);
//            }
//        });
//
//  final AlertDialog.Builder builder = new AlertDialog.Builder(fragment.getActivity());
//                 dialog = null;

//                View friendsView = fragment.getActivity().getLayoutInflater().inflate(R.layout.popup_select_friend,null);
//                Button sendBtn = (Button) friendsView.findViewById(R.id.sendChallengeBtn);
//                sendBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        sendNotificationToSelf(fragment.getActivity(),"Hello","Test");
//                    }
//                });
//
//                Button closeBtn = (Button) friendsView.findViewById(R.id.closeChallengeBtn);
//                closeBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
//                            @Override
//                            public void onDismiss(DialogInterface dialogInterface) {
//                             dialog.cancel();
//                            }
//                        });
//                    }
//                });
//
//                builder.setView(friendsView);
//
//                dialog= builder.create();
//                dialog.show();








//    sendNotificationToFriend("Challenge from : "+LoginActivity.LoggedIn_User_Email);
//     userHelper.sendNotification();










//                        View progressView = fragment.getActivity().getLayoutInflater().inflate(R.layout.popup_progress_bar,null);
//                        final PopupWindow progressWindow = new PopupWindow(progressView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                        progressWindow.showAtLocation(progressView, Gravity.CENTER,10,10);
//                        final ProgressBar progressBar = progressView.findViewById(R.id.progressBar2);
//                        progressBar.setProgress(0);
//                        progressBar.setIndeterminate(false);
//
//                        final int totalProgressTime = 10;
//                        final Thread t = new Thread() {
//                            @Override
//                            public void run() {
//                                int jumpTime = 0;
//
//                                while(jumpTime < totalProgressTime) {
//                                    try {
//                                        sleep(100);
//                                        jumpTime += 5;
//                                        progressBar.setProgress(jumpTime);
//                                    } catch (InterruptedException e) {
//                                        // TODO Auto-generated catch block
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }
//                        };
//                        t.start();
//  + "\"buttons\": [{\"id\":\"btnAccept\",\"text\":\"Accept\",\"icon\":\"\"},{\"id\":\"btnDecline\",\"text\":\"Decline\",\"icon\":\"\"}],"
//  + "\"data\": {\"foo\": \"bar\"},"
//   + "\"contents\": {\"en\": \"Challenge from: "+name+ "\"}"
//    public String getStrJsonBody(){
//
//        return strJsonBody;
//
//    }

//                        String strJsonBody = "{"
//                                + "\"app_id\": \"4a5d1740-b98b-4569-9107-2de771ddc07d\","
//
//                                + "\"filters\": [{\"field\": \"tag\", \"key\": \"User_ID\", \"relation\": \"=\", \"value\": \"" + email + "\"}],"
//
//                                + "\"buttons\": [{\"id\":\"btnAccept\",\"text\":\"Accept\",\"icon\":\"\"},{\"id\":\"btnDecline\",\"text\":\"Decline\",\"icon\":\"\"}],"
//                              //  + "\"data\": {\"foo\": \"bar\"},"
//                             //   + "\"contents\": {\"en\": \"Challenge from: "+name+ "\"}"
//                                + "\"contents\": {\"en\":\" "+ content +"\"}"
//
//                                + "}";
//                    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
//                    DatabaseReference reference = rootRef.getRef().child("Cagatay");
//                    DatabaseReference sendRef = reference.child("Email");
//                    sendRef.addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot dataSnapshot) {
//                            emailToSend =(String) dataSnapshot.getValue();
//                        }
//
//                        @Override
//                        public void onCancelled(DatabaseError databaseError) {
//
//                        }
//                    });
//                    Log.d("Tag",sendRef.toString());
