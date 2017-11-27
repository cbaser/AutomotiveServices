package com.bmw.cakin.automotiveservices.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.bmw.cakin.automotiveservices.R;
import com.bmw.cakin.automotiveservices.datamodel.BMWCarData;
import com.bmw.cakin.automotiveservices.datamodel.Telematics;

import java.util.List;

/**
 * Created by cakin on 11/19/2017.
 */

public class TelematicAdapter extends ArrayAdapter<Telematics> {
    private final Context context;
    private final List<Telematics> values;

    public TelematicAdapter(@NonNull Context context, List<Telematics> values) {
        super(context, R.layout.list_telematickey, values);
        this.context = context;
        this.values = values;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View telematicView = inflater.inflate(R.layout.list_telematickey,parent,false);
        TextView textView = (TextView) telematicView.findViewById(R.id.label);
        ImageView imageView =(ImageView) telematicView.findViewById(R.id.logo);

        textView.setText(values.get(position).toString());
        String s = values.get(position).toString();
        if(s.contains("Location"))
            imageView.setImageResource(R.drawable.ic_location);
        if(s.contains("Mileage"))
            imageView.setImageResource(R.drawable.fuel_icon);
        return telematicView;

    }
}
