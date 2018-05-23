package com.gameco.cakin.automotiveservices.activites.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.adapters.FriendsListAdapter;
import com.gameco.cakin.automotiveservices.datamodel.CurrentUser;
import com.gameco.cakin.automotiveservices.firebase.MyFirebaseDatabase;
import com.gameco.cakin.automotiveservices.firebase.MyFirebaseDeepLink;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentFriendlist extends Fragment {

    private EditText searchField;
    private ArrayList<CurrentUser>currentUsers,friends,friendRequests;
    private View view;
    private MyFirebaseDeepLink myFirebaseDeepLink;
    private DatabaseReference databaseReference;
    private MyFirebaseDatabase myFirebaseDatabase;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_friendslist, container, false);

        myFirebaseDeepLink = new MyFirebaseDeepLink(this.getActivity());
        myFirebaseDatabase = new MyFirebaseDatabase(this.getActivity());



        Button invitePeopleBtn = view.findViewById(R.id.invitePeopleBtn);
        invitePeopleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myFirebaseDeepLink.shareShortDynamicLink(view);
            }
        });


        friends = myFirebaseDatabase.getFriendsFromPreferences();
        friendRequests = myFirebaseDatabase.getFriendRequestFromPreferences();

        FloatingActionButton   search_button = view.findViewById(R.id.search_button);
         searchField = view.findViewById(R.id.editSearch);
         databaseReference = FirebaseDatabase.getInstance().getReference();
         setFriendsView();
         setFriendRequestView(!friendRequests.isEmpty());

         search_button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String searchText = searchField.getText().toString();
                 Toast.makeText(getActivity(),"Searching..",Toast.LENGTH_LONG).show();
                 searchUsers(searchText);
             }
         });
        return view;
    }



    public void searchUsers(String email){
       currentUsers = new ArrayList<>();
        Query query = databaseReference.child("Users").orderByChild("Email").startAt(email).endAt(email+"\uf8ff").limitToFirst(10);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    CurrentUser currentUser = snapshot.getValue(CurrentUser.class);
                    currentUsers.add(currentUser);
                }
                setSearchView();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void setSearchView(){
        RecyclerView  searchRecyclerView = view.findViewById(R.id.friendsSearchListView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity().getApplicationContext());
        searchRecyclerView.setLayoutManager(mLayoutManager);
        searchRecyclerView.addItemDecoration(new DividerItemDecoration(searchRecyclerView.getContext(),DividerItemDecoration.VERTICAL));
        FriendsListAdapter friendsListAdapter = new FriendsListAdapter(this.getActivity(),currentUsers);
        friendsListAdapter.setRequestType("friend_search");
        searchRecyclerView.setAdapter(friendsListAdapter);
        friendsListAdapter.notifyDataSetChanged();

    }
    private void setFriendsView(){
        RecyclerView   friendsRankRecyclerView = view.findViewById(R.id.friendsRankListView);
        friendsRankRecyclerView.setHasFixedSize(true);
        friendsRankRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        friendsRankRecyclerView.addItemDecoration(new DividerItemDecoration(friendsRankRecyclerView.getContext(), DividerItemDecoration.VERTICAL));
        FriendsListAdapter friendsListAdapter = new FriendsListAdapter(this.getActivity(),friends);
        friendsListAdapter.setRequestType("friends");
        friendsRankRecyclerView.setAdapter(friendsListAdapter);
        friendsListAdapter.notifyDataSetChanged();
    }
    private void setFriendRequestView(boolean isfilled){
        CardView cardView = view.findViewById(R.id.cardViewFriendRequests);
        RecyclerView  searchRecyclerView = view.findViewById(R.id.friendsRequestListView);
        if(!isfilled)
            cardView.setVisibility(View.INVISIBLE);
        else
            cardView.setVisibility(View.VISIBLE);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity().getApplicationContext());
        searchRecyclerView.setLayoutManager(mLayoutManager);
        FriendsListAdapter friendsListAdapter = new FriendsListAdapter(this.getActivity(),friendRequests);
        friendsListAdapter.setRequestType("friend_request");
        searchRecyclerView.setAdapter(friendsListAdapter);
        friendsListAdapter.notifyDataSetChanged();
    }

}
