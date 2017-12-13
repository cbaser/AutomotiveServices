package com.bmw.cakin.automotiveservices.activites.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.bmw.cakin.automotiveservices.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cakin on 11/23/2017.
 */

public class FragmentDriving extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_mydriving,container,false);
//        HorizontalBarChart horizontalBarChart = (HorizontalBarChart) view.findViewById(R.id.efficiency_chart);
//        ArrayList<BarEntry> entries = new ArrayList<>();
//        entries.add(new BarEntry(4f,0));
//        entries.add(new BarEntry(4f, 0));
//        entries.add(new BarEntry(8f, 1));
//        entries.add(new BarEntry(6f, 2));
//        entries.add(new BarEntry(12f, 3));
//        entries.add(new BarEntry(18f, 4));
//
//        BarDataSet dataSet = new BarDataSet(entries,"Efficiency Rate");
//
//        ArrayList<String> labels = new ArrayList<>();
//        labels.add("January");
//        labels.add("February");
//        labels.add("March");
//        labels.add("April");
//        labels.add("May");
//        labels.add("June");
//        BarData data = new BarData(dataSet);
//        horizontalBarChart.setData(data);


        LineChart lineChart = (LineChart) view.findViewById(R.id.efficiency_chart);
        Description description = new Description();
        description.setText("Monthly Efficiency Change");
        lineChart.setDescription(description);
        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0f, 70));
        entries.add(new Entry(1f, 81));
        entries.add(new Entry(2f, 92));
        entries.add(new Entry(3f, 63));
        entries.add(new Entry(4f, 54));
        entries.add(new Entry(5f, 89));


        LineDataSet dataset = new LineDataSet(entries, "Efficiency Rate");
        dataset.setAxisDependency(YAxis.AxisDependency.LEFT);

        final String[] labels = new String[]{
          "January","February","March","April","May","June"

        };

        List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(dataset);


        LineData lineData = new LineData(dataSets);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS); //





        IAxisValueFormatter formatter = new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return labels[(int) value];
            }

            // we don't draw numbers, so no decimal digits needed

            public int getDecimalDigits() {  return 0; }
        };

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);
        dataset.setDrawFilled(true);



        lineChart.setData(lineData);
        lineChart.invalidate();


        return view;
    }
}
