package com.gameco.cakin.automotiveservices.activites.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.adapters.RankingAdapter;
import com.gameco.cakin.automotiveservices.datamodel.Rank;
import com.gameco.cakin.automotiveservices.firebase.MyFirebaseDatabase;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by cakin on 12/25/2017.
 */

public class FragmentRanking extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ranking,container,false);
        MyFirebaseDatabase  myFirebaseDatabase = new MyFirebaseDatabase(this.getActivity());
        List<Rank>  rankList = myFirebaseDatabase.getRankingFromPreferences();
        RankingAdapter    rankingAdapter = new RankingAdapter(this.getActivity(),rankList);

        ListView   rankListView = (ListView) view.findViewById(R.id.leaderboardListView);

        ViewGroup header_ranking = (ViewGroup)inflater.inflate(R.layout.header_ranking_list,rankListView,false);
        rankListView.addHeaderView(header_ranking);
        rankListView.setAdapter(rankingAdapter);
        return view;
    }
}
