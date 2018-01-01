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
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.rankList = rankList;
        this.activity = activity;
    }

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
        TextView tvPos = (TextView) view.findViewById(R.id.textViewPosition);
        TextView tvReg =(TextView) view.findViewById(R.id.textViewRegion);

        Rank rank = rankList.get(position);
        tvPos.setText(String.valueOf(rank.getPosition()));
        tvReg.setText(String.valueOf(rank.getRegion()));



        return view;
    }
}
