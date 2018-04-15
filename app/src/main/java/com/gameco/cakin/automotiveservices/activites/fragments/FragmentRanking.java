package com.gameco.cakin.automotiveservices.activites.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.adapters.FriendsListAdapter;
import com.gameco.cakin.automotiveservices.adapters.RankingAdapter;
import com.gameco.cakin.automotiveservices.datamodel.Friend;
import com.gameco.cakin.automotiveservices.datamodel.Rank;
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
 * Created by cakin on 12/25/2017.
 */

public class FragmentRanking extends Fragment {
    private List<Friend> friendList = new ArrayList<>();
    private List<Rank> rankList = new ArrayList<>();
    private FirebaseDatabase database;
    private DatabaseReference leaderBoardReference;
    private ListView rankListView,friendsListView;
    public FragmentRanking(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ranking,container,false);
        friendList.clear();
        database = FirebaseDatabase.getInstance();
        leaderBoardReference = database.getReference().child("Leaderboards");
        final RankingAdapter rankingAdapter = new RankingAdapter(this.getActivity(),rankList);
        rankListView = (ListView) view.findViewById(R.id.leaderboardListView);
        ViewGroup header_ranking = (ViewGroup)inflater.inflate(R.layout.header_ranking_list,rankListView,false);
        rankListView.addHeaderView(header_ranking);
        rankListView.setAdapter(rankingAdapter);
        leaderBoardReference.orderByChild("Points").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int total = (int) dataSnapshot.getChildrenCount();
                int i = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Rank rank = snapshot.getValue(Rank.class);
                    rank.setPosition(i);
                    Log.e("FRAGMENT RANKİNG",rank.getPoint()+"");
                    Log.e("FRAGMENT RANKİNG",rank.getNickname());

                    rankList.add(rank);
                }
          //      Rank rank = new Rank();
          //      rank.setNickname(dataSnapshot.getKey());
         //       rank.setPoint(Long.parseLong(dataSnapshot.getValue().toString()));
//                Rank rank = dataSnapshot.getValue(Rank.class);
//                Log.e("FRAGMENT RANKİNG",rank.getPoint()+"");
//                rankList.add(rank);
                rankingAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
















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
        return view;
    }
}
