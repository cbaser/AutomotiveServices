package com.gameco.cakin.automotiveservices.activites.fragments;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.gameco.cakin.automotiveservices.R;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cakin on 11/23/2017.
 */

public class FragmentAchievements extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_achievements,container,false);
        BarChart barChart = (BarChart) view.findViewById(R.id.efficiency_chart);
        Description description = new Description();
        description.setText("Monthly Fuel Efficiency Change");
        barChart.setDescription(description);
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 70));
        entries.add(new BarEntry(1f, 81));
        entries.add(new BarEntry(2f, 92));



        BarDataSet dataset = new BarDataSet(entries, " Fuel Efficiency Rate");
        dataset.setAxisDependency(YAxis.AxisDependency.LEFT);

        final String[] labels = new String[]{
          "January","February","March"

        };

        List<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(dataset);


        BarData lineData = new BarData(dataSets);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS); //





        IAxisValueFormatter formatter = new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return labels[(int) value];
            }

            // we don't draw numbers, so no decimal digits needed

            public int getDecimalDigits() {  return 0; }
        };

        XAxis xAxis = barChart.getXAxis();
        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);
      //  dataset.setDrawFilled(true);



        barChart.setData(lineData);
        barChart.invalidate();
        View.OnClickListener onClickListener= new View.OnClickListener(){
            public void onClick(View v){
                showImageDescription(v,(Integer)v.getTag());
            }


        };

        ImageView image1 = (ImageView) view.findViewById(R.id.image1);
        image1.setOnClickListener(onClickListener);
        image1.setTag(R.drawable.badge_newbie);

        ImageView image2 = (ImageView) view.findViewById(R.id.image2);
        image2.setOnClickListener(onClickListener);
        image2.setTag(R.drawable.badge_10_winner);

        ImageView image3 = (ImageView) view.findViewById(R.id.image3);
        image3.setOnClickListener(onClickListener);
        image3.setTag(R.drawable.badge_25_winner);

        ImageView image4 = (ImageView) view.findViewById(R.id.image4);
        image4.setOnClickListener(onClickListener);
        image4.setTag(R.drawable.badge_unlock);

        ImageView image5 = (ImageView) view.findViewById(R.id.image5);
        image5.setOnClickListener(onClickListener);
        image5.setTag(R.drawable.badge_efficient);

        ImageView image6 = (ImageView) view.findViewById(R.id.image6);
        image6.setOnClickListener(onClickListener);
        image6.setTag(R.drawable.badge_not_efficient);

        ImageView image7 = (ImageView) view.findViewById(R.id.image7);
        image7.setOnClickListener(onClickListener);
        image7.setTag(R.drawable.badge_unlock);

        ImageView image8 = (ImageView) view.findViewById(R.id.image8);
        image8.setOnClickListener(onClickListener);
        image8.setTag(R.drawable.badge_unlock);





        return view;
    }

    public void showImageDescription(View view,Integer tag){


        View popupView = this.getActivity().getLayoutInflater().inflate(R.layout.popup_badge_description,null);
        final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.showAtLocation(popupView, Gravity.CENTER,0,0);
        ImageView badgeImage = (ImageView) popupView.findViewById(R.id.badgeImage);
        TextView badgeName = (TextView) popupView.findViewById(R.id.badgeName);
        TextView badgeDescription =(TextView) popupView.findViewById(R.id.badgeDescription);
     //   badgeImage.setImageResource(incomingImage);


     //   ImageView incomingImage = (ImageView) view;
        //assert(R.id.image1 == incomingImage.getId());
       // Integer integer = (Integer) incomingImage.getRe
       // integer = integer == null ? 0 : integer;
        switch(tag) {
            case R.drawable.badge_newbie:
                badgeImage.setImageResource(R.drawable.badge_newbie);
                badgeImage.setTag(R.drawable.badge_newbie);
                badgeName.setText("Newbie");
                badgeDescription.setText("You played one challenge and unlocked this badge!");
                break;
            case R.drawable.badge_10_winner:
                badgeImage.setImageResource(R.drawable.badge_10_winner);
                badgeImage.setTag(R.drawable.badge_10_winner);
                badgeName.setText("Rookie");
                badgeDescription.setText("You sent ten challenges and unlocked this badge!");
                break;
            case R.drawable.badge_25_winner:
                badgeImage.setImageResource(R.drawable.badge_25_winner);
                badgeImage.setTag(R.drawable.badge_25_winner);
                badgeName.setText("Rookie");
                badgeDescription.setText("You sent 25 challenge and unlocked this badge!");
                break;
            case R.drawable.badge_unlock:
                badgeImage.setImageResource(R.drawable.badge_unlock);
                badgeImage.setTag(R.drawable.badge_unlock);
                badgeName.setText("Locked");
                break;
            case R.drawable.badge_efficient:
                badgeImage.setImageResource(R.drawable.badge_efficient);
                badgeImage.setTag(R.drawable.badge_efficient);
                badgeName.setText("Efficient");
                badgeDescription.setText("You drove so efficient last month and unlocked this badge!");
                break;
            case R.drawable.badge_not_efficient:
                badgeImage.setImageResource(R.drawable.badge_not_efficient);
                badgeImage.setTag(R.drawable.badge_not_efficient);
                badgeName.setText("Not Efficient");
                badgeDescription.setText("You drove not so efficient last month and unlocked this badge!");
                break;

        }


        FloatingActionButton floatingActionButton = (FloatingActionButton) popupView.findViewById(R.id.exitFAB);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

}
