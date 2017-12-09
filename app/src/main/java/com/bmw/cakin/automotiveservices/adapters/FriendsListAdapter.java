package com.bmw.cakin.automotiveservices.adapters;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.support.v4.app.NotificationCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bmw.cakin.automotiveservices.R;
import com.bmw.cakin.automotiveservices.datamodel.CurrentUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by cakin on 11/23/2017.
 */

public class FriendsListAdapter extends BaseAdapter{

    private Activity context;
     private LayoutInflater layoutInflater;
    private Map<String, List<String>> friendsCollections;
        private List<String> friends;
        public FriendsListAdapter(Activity context, List<String>friends,Map<String,List<String>> friendsCollections){
            this.context = context;
            this.friends = friends;
            this.friendsCollections = friendsCollections;
        }

    @Override
    public int getCount() {
        return friends.size();
    }

    @Override
    public Object getItem(int position) {
        return friends.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.content_challenges_friends_list,null,false);
        LinearLayout mainLinearLayout = (LinearLayout) convertView.findViewById(R.id.homeLinear);

        for(int i=0;i<friends.size();i++){
            View additionView = layoutInflater.inflate(R.layout.content_challenges_friends_list,null,false);
            LinearLayout innerLineerLayout = (LinearLayout) additionView.findViewById(R.id.home_innerlayout);
            mainLinearLayout.addView(innerLineerLayout);
        }



        return convertView;
    }
    class ViewHolder{
            HorizontalScrollView horizontalScrollView;
            TextView textView;
            LinearLayout linearLayout,main_linear_layout;
    }


//
}
//@Override
//    public int getGroupCount() {
//        return friends.size();
//    }
//
//    @Override
//    public int getChildrenCount(int groupPosition) {
//        return friendsCollections.get(friends.get(groupPosition)).size();
//    }
//
//    @Override
//    public Object getGroup(int groupPosition) {
//        return friends.get(groupPosition);
//    }
//
//    @Override
//    public Object getChild(int groupPosition, int childPosition) {
//        return friendsCollections.get(friends.get(groupPosition)).get(childPosition);
//    }
//
//    @Override
//    public long getGroupId(int groupPosition) {
//        return groupPosition;
//    }
//
//    @Override
//    public long getChildId(int groupPosition, int childPosition) {
//        return childPosition;
//    }
//    @Override
//    public boolean hasStableIds() {
//        return true;
//    }
//
//    @Override
//    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
//        ViewHolder holder;
//
//        String friendName = (String) getGroup(groupPosition);
//        if (convertView == null) {
//
//   //         convertView = layoutInflater.inflate(R.layout.group_item, null);
//            holder = new ViewHolder();
//   //         holder.textView = (TextView) convertView.findViewById(R.id.group_item);
//            convertView.setTag(holder);
//        }
//        else
//            holder =(ViewHolder) convertView.getTag();
//        holder.textView.setText(getGroup(groupPosition).toString());
//  //      TextView item = (TextView) convertView.findViewById(R.id.group_item);
//        item.setTypeface(null, Typeface.BOLD);
//        item.setText(friendName);
//        return convertView;
//    }
//
//    @Override
//    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//        final String friend = (String) getChild(groupPosition, childPosition);
//        ViewHolder holder;
//
//        if (convertView == null) {
//   //         convertView = layoutInflater.inflate(R.layout.group_child_item, null);
//            holder = new ViewHolder();
//   //         holder.textView = (TextView) convertView.findViewById(R.id.child_item);
//            convertView.setTag(holder);
//        }
//        else
//            holder =(ViewHolder) convertView.getTag();
//        holder.textView.setText(getGroup(groupPosition).toString());
//
//    //    final TextView item = (TextView) convertView.findViewById(R.id.child_item);
//
//
//        item.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                builder.setMessage("Do you want to send a challenge?");
//                builder.setCancelable(false);
//                builder.setPositiveButton("Yes",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//          //                      currentUser.getChallenges().add(item.getText().toString());
//                                Notification n  = new Notification.Builder(context)
//                                        .setContentTitle("Challenge!")
//             //                           .setContentText(item.getText().toString() )
//                                        .setSmallIcon(R.drawable.ic_stat_b2b)
//                                        .setAutoCancel(true)
//                                          .build();
//                                NotificationManager notificationManager = (NotificationManager)
//                                        context.getSystemService(NOTIFICATION_SERVICE);
//                                notificationManager.notify(0, n);
////                                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context);
////                                notificationBuilder.setContentTitle("Challenge!");
////                                notificationBuilder.setContentText(item.getText().toString());
//                            }
//                        });
//                builder.setNegativeButton("No",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                dialog.cancel();
//                            }
//                        });
//                AlertDialog alertDialog = builder.create();
//                alertDialog.show();
//
//            }
//        });
//
//
//  //      item.setText(friend);
//        return convertView;
//    }
//
//
//    @Override
//    public boolean isChildSelectable(int groupPosition, int childPosition) {
//        return true;
//    }
//    private class ViewHolder{
//        TextView textView;
//    }
//    public void setCurrentUser(CurrentUser currentUser){
//        this.currentUser = currentUser;
//    }