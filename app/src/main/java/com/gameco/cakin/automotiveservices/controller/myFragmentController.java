package com.gameco.cakin.automotiveservices.controller;

import android.app.AlertDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.activites.LoginActivity;
import com.gameco.cakin.automotiveservices.adapters.FriendsListAdapter;
import com.gameco.cakin.automotiveservices.datamodel.Friend;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cakin on 1/2/2018.
 */

public class myFragmentController {
    private Fragment fragment;
    private LayoutInflater layoutInflater;
    private FirebaseAuth firebaseAuth;
    private FriendsListAdapter friendsListAdapter;
    public static FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private List<Friend> friendList=new ArrayList<>();
    private String emailToSend;
    private myNotificationController notificationController;

    public myFragmentController(Fragment fragment){
        this.fragment = fragment;
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference().child("Users");
        notificationController = new myNotificationController(fragment);
    }

    public void showFriends(){
        View popupView = fragment.getActivity().getLayoutInflater().inflate(R.layout.fragment_friends,null);
        final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        ListView listView = (ListView)popupView.findViewById(R.id.friendsListview);
        Friend can;
        friendList.clear();
        if(!LoginActivity.LoggedIn_User_Email.contains("can"))
         can = new Friend(fragment.getResources().getDrawable(R.drawable.ic_can),"Can Turker",123454);
        else
             can = new Friend(fragment.getResources().getDrawable(R.drawable.ic_cagatay),"Cagatay Baser",123454);


        friendList.add(can);
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
        Button createGroupBtn = (Button) popupView.findViewById(R.id.createGroupBtn);
        createGroupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(fragment.getActivity(), "Will be implemented as Creating Group among friends", Toast.LENGTH_SHORT).show();
            }
        });
        Button importGroupBtn = (Button) popupView.findViewById(R.id.importGroupBtn);
        importGroupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(fragment.getActivity(), "Will be implemented as Importing friends from Facebook", Toast.LENGTH_SHORT).show();
            }
        });

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
















    public void setUserInformation(View view){
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String firebaseUserEmail = firebaseUser.getEmail();

        //  String username = settings.getString("Username","");
        if(firebaseUserEmail.contains("can"))
        {
            NavigationView navigationView = (NavigationView) fragment.getActivity().findViewById(R.id.nav_view);

            View headerLayout = navigationView.getHeaderView(0);

            ((TextView)  headerLayout.findViewById(R.id.userTextV)).setText("Can Türker");
            ((TextView) headerLayout.findViewById(R.id.userEmailV)).setText("can.tuerker@tum.de");


            LinearLayout frameLayout = (LinearLayout) view.findViewById(R.id.home_frameLayout);
            RelativeLayout relativeLayout =(RelativeLayout) frameLayout.findViewById(R.id.home_mainLayout);

            ImageView imageView = (ImageView) relativeLayout.findViewById(R.id.profPic);
            imageView.setImageResource(R.drawable.ic_can);
            ((TextView) relativeLayout.findViewById(R.id.txtRewe)).setText("Welcome Can To GamECO!");






//



        }


    }





}
//   listView.setEmptyView(popupView.findViewById(R.id.emptyElement));
//        builder.setView(mview);
//
//        dialog= builder.create();
//        dialog.show();
//            View challengesView = fragment.getActivity().getLayoutInflater().inflate(R.layout.content_my_challenges, null);
//            LinearLayout linearLayout = (LinearLayout) challengesView.findViewById(R.id.profileLinear1);
//
//            ImageView profileImage =(ImageView) linearLayout.findViewById(R.id.profPic_Can);
//
//            profileImage.setImageResource(R.drawable.ic_cagatay);
//        final AlertDialog.Builder builder = new AlertDialog.Builder(fragment.getActivity());
//         AlertDialog dialog = null;
//        layoutInflater = LayoutInflater.from(fragment.getActivity());
//        View mview = layoutInflater.inflate(R.layout.fragment_friends, null);

//        ViewGroup header_friends = (ViewGroup)layoutInflater.inflate(R.layout.header_friends_list,listView,false);
//        listView.addHeaderView(header_friends);
//        FloatingActionButton floatingActionButton = (FloatingActionButton) mview.findViewById(R.id.exitFAB);
//        final AlertDialog finalDialog = dialog;
//        floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
//                    @Override
//                    public void onDismiss(DialogInterface dialogInterface) {
//                        finalDialog.dismiss();
//                    }
//                });
//            }
//        });
//        FloatingActionButton closeFriendsBtn = (FloatingActionButton) mview.findViewById(R.id.closeFriendsFAB);
//        closeFriendsBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
//                    @Override
//                    public void onDismiss(DialogInterface dialog) {
//                        dialog.dismiss();
//                    }
//                });
//            }
//        });



//                final View popupView = fragment.getActivity().getLayoutInflater().inflate(R.layout.popup_challenge,null);
//                final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//                popupWindow.showAtLocation(popupView, Gravity.NO_GRAVITY,10,10);
//                Button start_challenge = (Button) popupView.findViewById(R.id.start_challenge_yourself_button);
//                start_challenge.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        myNotificationController.sendNotificationToSelf(fragment.getActivity(),"Challenge Started","Duration 7 days");
//                        //   transitionHelper.sendNotification("Challenge Started!","Duration: 7 days!");
//                        popupWindow.dismiss();
//
//                    }
//                });
//                Button send_challenge = (Button) popupView.findViewById(R.id.send_challenge_to_friend_button);
//                send_challenge.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        myNotificationController.sendNotificationToFriend("Can");
//                        //     userHelper.sendNotification();
//                    }
//                });
//                FloatingActionButton floatingActionButton = (FloatingActionButton) popupView.findViewById(R.id.exitFAB);
//                floatingActionButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        popupWindow.dismiss();
//                    }
//                });
//        FloatingActionButton exitChallengeButton = (Button) view.findViewById(R.id.exitDaily);
//        exitChallengeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
//                   @Override
//                   public void onDismiss(DialogInterface dialog) {
//                    dialog.dismiss();
//                   }
//               });
//            }
//        });
//            layoutInflater =(LayoutInflater) activity.getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            //   View popupView = fragment.getActivity().getLayoutInflater().inflate(R.layout.content_daily_challenge,null);
//            View popupView = layoutInflater.inflate(R.layout.content_daily_challenge,null);
//            DrawerLayout drawerLayout = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
//            PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
//            popupWindow.showAtLocation(drawerLayout, Gravity.NO_GRAVITY,10,10);



// AlertDialog.Builder builder = new AlertDialog.Builder(fragment.getActivity());

//  AlertDialog dialog = null ;

//    LayoutInflater  layoutInflater = LayoutInflater.from(fragment.getActivity());
//  View view = layoutInflater.inflate(R.layout.content_daily_challenge, null);


//        Button challengeButton = (Button) popupView.findViewById(R.id.playChallengeButton);
//        challengeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                notificationController.showPopUp();
//
//
//            }
//        });


//   builder.setView(view);

//    dialog= builder.create();
//    dialog.setCancelable(true);
//    dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
//       @Override
//       public void onCancel(DialogInterface dialog) {
//          dialog.dismiss();
//      }
//    });
//   dialog.show();
