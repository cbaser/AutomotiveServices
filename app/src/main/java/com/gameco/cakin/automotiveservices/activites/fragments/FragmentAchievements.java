package com.gameco.cakin.automotiveservices.activites.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.gameco.cakin.automotiveservices.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
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
        description.setText("Monthly Efficiency Change");
        barChart.setDescription(description);
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 70));
        entries.add(new BarEntry(1f, 81));
        entries.add(new BarEntry(2f, 92));



        BarDataSet dataset = new BarDataSet(entries, "Efficiency Rate");
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


        return view;
    }
}
