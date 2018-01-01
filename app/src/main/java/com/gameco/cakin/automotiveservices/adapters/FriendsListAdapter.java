package com.gameco.cakin.automotiveservices.adapters;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.datamodel.Friend;

import java.util.List;

/**
 * Created by cakin on 11/23/2017.
 */

public class FriendsListAdapter extends BaseAdapter {
   private LayoutInflater layoutInflater;
    private List<Friend> list;
    private Activity activity;
    public FriendsListAdapter(Activity activity,List<Friend> list) {
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.list = list;
        this.activity = activity;

    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.group_friend,null);
        TextView tvRank = (TextView)view.findViewById(R.id.textViewPosition);
        TextView tvName =(TextView) view.findViewById(R.id.textViewName);
        TextView tvPoint =(TextView) view.findViewById(R.id.textViewPoint);

        Friend friend = list.get(position);
        tvRank.setText(String.valueOf(position+1));
        tvName.setText(friend.getName());
        tvPoint.setText(String.valueOf(friend.getPoint()));





        return view;

    }
}
