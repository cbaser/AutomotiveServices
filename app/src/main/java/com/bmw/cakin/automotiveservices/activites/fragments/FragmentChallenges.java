package com.bmw.cakin.automotiveservices.activites.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.bmw.cakin.automotiveservices.R;
import com.bmw.cakin.automotiveservices.activites.MainActivity;
import com.bmw.cakin.automotiveservices.adapters.FriendsListAdapter;
import com.bmw.cakin.automotiveservices.datamodel.CurrentUser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cakin on 11/22/2017.
 */

public class FragmentChallenges extends Fragment {
    private List<String> groupList;
    private List<String> childList;
    private Map<String, List<String>> friendsCollection;
    private ExpandableListView friendsListView;
    private ListView challengesListView;
    private CurrentUser currentUser;

public FragmentChallenges(){

}
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
    View root = inflater.inflate(R.layout.fragment_challenges,container,false);


        createGroupList();
        currentUser = new CurrentUser();
        createCollection();

return root;

    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        friendsListView = (ExpandableListView) view.findViewById(R.id.friends_list);
        challengesListView = (ListView) view.findViewById(R.id.challenges_listview);
        currentUser.getChallenges().add("HOP");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,android.R.id.text1,new String[]{"Send a Challenge!"});
        challengesListView.setAdapter(adapter);

        final FriendsListAdapter friendsListAdapter = new FriendsListAdapter(getActivity(), groupList, friendsCollection);
        friendsListView.setAdapter(friendsListAdapter);
        friendsListAdapter.setCurrentUser(currentUser);

        friendsListView.setGroupIndicator(null);


    }

    private void createGroupList() {
        groupList = new ArrayList<String>();
        groupList.add("Can");
        groupList.add("Lea");
        groupList.add("Hampus");
    }
    private void createCollection() {
        // preparing friends collection(child)
        String[] canChallenges = { "Challenge 1 : Drive less than 50 km this week"};
        String[] leaChallenges = { "Challenge 1 :Save one tree for this week"};
        String[] hampusChallenges = { "Challenge 1 :Drive not faster than 100 km/h this week" };


        friendsCollection = new LinkedHashMap<String, List<String>>();

        for (String friend : groupList) {
            if (friend.equals("Can")) {
                loadChild(canChallenges);
            } else if (friend.equals("Hampus"))
                loadChild(hampusChallenges);
            else if (friend.equals("Lea"))
                loadChild(leaChallenges);
            friendsCollection.put(friend, childList);
        }
    }
    private void loadChild(String[] friends) {
        childList = new ArrayList<>();
        Collections.addAll(childList);
        for (String friend : friends)
            childList.add(friend);
    }

    private void setGroupIndicatorToRight() {
        /* Get the screen width */
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;

        friendsListView.setIndicatorBounds(width - getDipsFromPixel(35), width - getDipsFromPixel(5));
    }
    public int getDipsFromPixel(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }

}
