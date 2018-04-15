package com.gameco.cakin.automotiveservices.activites.fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gameco.cakin.automotiveservices.adapters.FriendsListAdapter;
import com.gameco.cakin.automotiveservices.datamodel.Challenge;
import com.gameco.cakin.automotiveservices.datamodel.Friend;
import com.gameco.cakin.automotiveservices.firebase.MyFirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.controller.myNotificationController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cakin on 11/22/2017.
 */

public class FragmentHome extends Fragment {
   // private myFragmentController fragmentController;
    private myNotificationController controller;
    private DatabaseReference mRef;
    private FirebaseUser user;
    public static FirebaseDatabase mDatabase;
    private long challengeCount,points;
    private String remaining,fullName;
    private TextView txtScore,txtProgressCount,txtLevel,txtWelcome,headerName,headerEmail;
    private ProgressBar progressBar;
    private Fragment fragment;
    private LayoutInflater layoutInflater;
    private FirebaseAuth firebaseAuth;
    private FriendsListAdapter friendsListAdapter;
    private MyFirebaseDatabase firebaseDatabase;
    public static String user_VIN;
  //  public static FirebaseDatabase mDatabase;
 //   private DatabaseReference mRef;
    private List<Friend> friendList=new ArrayList<>();
    private String emailToSend;
    private myNotificationController notificationController;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity a;

        if (context instanceof Activity){
            a=(Activity) context;
        }

    }
public FragmentHome(){

}
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
     View view = inflater.inflate(R.layout.fragment_home,container,false);
     controller = new myNotificationController(this);
    //    fragmentController = new myFragmentController(this);
        firebaseDatabase = new MyFirebaseDatabase(this.getActivity());
      //  fragmentController.createFragment(view);
        user = FirebaseAuth.getInstance().getCurrentUser();
        RelativeLayout homeMainLayout = view.findViewById(R.id.home_mainLayout);
         txtScore = homeMainLayout.findViewById(R.id.txtScore);
         txtProgressCount = homeMainLayout.findViewById(R.id.progressCount);
         txtLevel = homeMainLayout.findViewById(R.id.txtLvl);
         progressBar = view.findViewById(R.id.progressBarLevel);
        txtWelcome = view.findViewById(R.id.txtWelcome);
        progressBar.setMax(5);
        NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
        View headerLayout = navigationView.getHeaderView(0);
        headerName= headerLayout.findViewById(R.id.userTextV);
        headerEmail = headerLayout.findViewById(R.id.userEmailV);
        //TODO: User can Add Image
       ImageView imageView = (ImageView) homeMainLayout.findViewById(R.id.profPic);
       firebaseDatabase.getImage(imageView);
       // imageView.setImageResource(R.drawable.ic_can);


     //   ((TextView) headerLayout.findViewById(R.id.userEmailV)).setText(user.getEmail());
        try {
            mDatabase =FirebaseDatabase.getInstance();
            mRef = mDatabase.getReference();

            mRef.child("Users").child(user.getEmail().replace(".",",")).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    challengeCount  = (long) dataSnapshot.child("ChallengeCount").getValue();
                    points = (long) dataSnapshot.child("Points").getValue();
                    remaining = String.valueOf(progressBar.getMax()-challengeCount);
                    txtProgressCount.setText(remaining);
                    fullName = (String)dataSnapshot.child("Nick Name").getValue();
                    txtScore.setText(String.valueOf(points));
                    txtLevel.setText((String)dataSnapshot.child("Level").getValue());
                    txtWelcome.setText("Welcome "+ fullName+" To GamECO!");
                    progressBar.setProgress((int)challengeCount);
                    headerName.setText(fullName);
                    headerEmail.setText(user.getEmail());

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            mRef.child("Challenges").child("Title").child("Daily Challenge").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Challenge challenge = dataSnapshot.getValue(Challenge.class);
                    Log.e("FRAGMENT HOME",challenge.toString());
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
         //   ((TextView) relativeLayout.findViewById(R.id.txtWelcome)).setText("Welcome"+ fullName +" To GamECO!");
          //  setUserInformation(view);
            txtProgressCount.setText(remaining);
            txtScore.setText(String.valueOf(points));



        }catch (Exception e){
            e.printStackTrace();
        }

        CardView cardView = (CardView) view.findViewById(R.id.cardview_daily_content);
        LinearLayout linearLayout = (LinearLayout) cardView.findViewById(R.id.include_daily_challenge_content);
        Button challengeDailyButton = (Button) linearLayout.findViewById(R.id.playChallengeButton);
        final int color = Color.parseColor("#2c3e50");
        challengeDailyButton.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
         controller.showPopUp("Daily Challenge!","Time : One Day",2000,"Current Consumption 8.4l /100km","Target Consumption : 7.9l /100km",color);//  frontController.showChallenge();

       }
    });
        CardView cardViewShell = (CardView) view.findViewById(R.id.cardview_shell_content);
        LinearLayout shellLayout = (LinearLayout) cardViewShell.findViewById(R.id.include_shell_challenge_content);
        Button challengeShellButton = (Button) shellLayout.findViewById(R.id.playShellChallenge);
        challengeShellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.showPopUp("Weekly Shell Challenge!","Time : One Week",2000,"Go to next Shell","Refuel and get free coffee",color);//  frontController.showChallenge();

            }
        });
    Button seeAllFriendsButton = (Button) view.findViewById(R.id.seeFriendsBtn);
    seeAllFriendsButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           showFriends();

        }
    });

return view;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }



    public void showFriends(){
        View popupView = getActivity().getLayoutInflater().inflate(R.layout.popup_friends,null);
        final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        ListView listView = (ListView)popupView.findViewById(R.id.friendsListview);
        Friend can;
        friendList.clear();
//        if(!LoginActivity.LoggedIn_User_Email.contains("can"))
//         can = new Friend(fragment.getResources().getDrawable(R.drawable.ic_can),"Can Turker",123454);
//        else
//             can = new Friend(fragment.getResources().getDrawable(R.drawable.ic_cagatay),"Cagatay Baser",123454);


        //friendList.add(can);
        if(friendList.size()>0){
            friendsListAdapter = new FriendsListAdapter(fragment.getActivity(),friendList);
            listView.setAdapter(friendsListAdapter);
            friendsListAdapter.notifyDataSetChanged();
        }

        Button addFriendBtn = (Button) popupView.findViewById(R.id.addFriendBtn);
        addFriendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(fragment.getActivity(), "Will be implemented as Adding new friends", Toast.LENGTH_SHORT).show();
            }
        });
        Button invitePeopleBtn = (Button)popupView.findViewById(R.id.invitePeopleBtn);
        invitePeopleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new AppInviteInvitation.IntentBuilder("Invite To GameECO")
//                        .setMessage("Check out this new app GamECO!"))
//                        .setDeepLink(Uri.parse(getString(R.string.invitation_deep_link)))
//                        .setCustomImage(Uri.parse(getString(R.string.invitation_custom_image)))
//                        .setCallToActionText(getString(R.string.invitation_cta))
//                        .build();
//                startActivityForResult(intent, REQUEST_INVITE);

            }
        });
//        Button createGroupBtn = (Button) popupView.findViewById(R.id.createGroupBtn);
//        createGroupBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(fragment.getActivity(), "Will be implemented as Creating Group among friends", Toast.LENGTH_SHORT).show();
//            }
//        });
//        Button importGroupBtn = (Button) popupView.findViewById(R.id.importGroupBtn);
//        importGroupBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(fragment.getActivity(), "Will be implemented as Importing friends from Facebook", Toast.LENGTH_SHORT).show();
//            }
//        });

        FloatingActionButton floatingActionButton = (FloatingActionButton) popupView.findViewById(R.id.exitFAB);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });


        popupWindow.showAtLocation(popupView, Gravity.CENTER,10,10);

    }
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

    public void setUserInformation(){

    }


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