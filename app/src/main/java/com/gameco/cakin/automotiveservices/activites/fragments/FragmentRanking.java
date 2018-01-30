package com.gameco.cakin.automotiveservices.activites.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cakin on 12/25/2017.
 */

public class FragmentRanking extends Fragment {
    private ListView listView,listView2;
    private List<Friend> friendList = new ArrayList<>();
    private List<Rank> rankList = new ArrayList<>();
    public FragmentRanking(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ranking,container,false);
        friendList.clear();
        Friend lea = new Friend(this.getActivity().getResources().getDrawable(R.drawable.ic_lea),"Lea Jäntgen",123456);
        friendList.add(lea);


        Friend can = new Friend(this.getActivity().getResources().getDrawable(R.drawable.ic_can),"Can Türker",123455);
        friendList.add(can);


        Friend cagatay = new Friend(this.getActivity().getResources().getDrawable(R.drawable.ic_cagatay),"Cagatay Baser",123444);
        friendList.add(cagatay);


        Friend hampus = new Friend(this.getActivity().getResources().getDrawable(R.drawable.ic_hampus),"Hampus Olausson-Eckl",123443);
        friendList.add(hampus);



        listView = (ListView)view.findViewById(R.id.b2bListView);

//        ViewGroup header_friends = (ViewGroup)inflater.inflate(R.layout.header_friends_list,listView,false);
//        listView.addHeaderView(header_friends);

        FriendsListAdapter friendsListAdapter = new FriendsListAdapter(this.getActivity(),friendList);
        listView.setAdapter(friendsListAdapter);
        rankList.clear();
        rankList.add(new Rank(323,"Neighborhood"));
        rankList.add(new Rank(22341,"München"));
        rankList.add(new Rank(1412312,"Germany"));



        listView2 = (ListView) view.findViewById(R.id.globalListview);
        listView2.invalidateViews();
        RankingAdapter rankingAdapter = new RankingAdapter(this.getActivity(), rankList);
        ViewGroup header_ranking = (ViewGroup)inflater.inflate(R.layout.header_ranking_list,listView2,false);
        listView2.addHeaderView(header_ranking);
        listView2.setAdapter(rankingAdapter);







        return view;
    }
}
