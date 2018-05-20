package com.gameco.cakin.automotiveservices.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gameco.cakin.automotiveservices.R;

public class SlideViewPagerAdapter extends PagerAdapter {
    private Activity activity;
    private String[] titles,descriptions;
    public SlideViewPagerAdapter(Activity activity,String[] titles,String[] descriptions){
        this.activity = activity;
        this.titles = titles;
        this.descriptions = descriptions;
    }
    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((RelativeLayout) object);
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        // Declare Variables
        TextView txtTitle;
        TextView txtDescription;



        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.activity_opening_slider, container,
                false);

        // Locate the TextViews in viewpager_item.xml
        txtTitle = (TextView) itemView.findViewById(R.id.txt_slider_title);
        txtDescription = (TextView) itemView.findViewById(R.id.txt_slider_description);


        // Capture position and set to the TextViews
        txtTitle.setText(titles[position]);
        txtDescription.setText(descriptions[position]);



        // Add viewpager_item.xml to ViewPager
        ((ViewPager) container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager
        ((ViewPager) container).removeView((RelativeLayout) object);

    }
}
