package com.gameco.cakin.automotiveservices.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.datamodel.Rank;

import java.util.List;

/**
 * Created by cakin on 12/27/2017.
 */

public class RankingAdapter extends BaseAdapter{
    private LayoutInflater layoutInflater;
    private List<Rank> rankList;
    private Activity activity;

    public RankingAdapter( Activity activity,List<Rank> rankList) {

        this.rankList = rankList;
        this.activity = activity;
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);}

    @Override
    public int getCount() {
        return rankList.size();
    }

    @Override
    public Object getItem(int position) {
        return rankList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.group_ranking,null);
        TextView tvNick = (TextView) view.findViewById(R.id.textViewNick);
        TextView tvPoint = (TextView) view.findViewById(R.id.textViewPoints);
        TextView tvPos =(TextView) view.findViewById(R.id.textViewPosition);


        Rank rank = rankList.get(position);
        tvPos.setText(String.valueOf(position+1));
        tvNick.setText(rank.getNickName());
        tvPoint.setText(String.valueOf(rank.getPoints()));

        return view;
    }
}
