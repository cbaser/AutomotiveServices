package com.gameco.cakin.automotiveservices.adapters;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gameco.cakin.automotiveservices.R;

/**
 * Created by cakin on 11/23/2017.
 */

public class FriendsListHolder extends RecyclerView.ViewHolder {
    public TextView name,points;
    public ImageView imageView;

    public FriendsListHolder(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.txtFriendName);
        points = (TextView) itemView.findViewById(R.id.txtFriendPoint);
        imageView = (ImageView) itemView.findViewById(R.id.imageViewPicture);

    }
    }

//private LayoutInflater layoutInflater;
//    private List<Friend> list;
//    private Activity activity;
//    public FriendsListAdapter(Activity activity,List<Friend> list) {
//        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        this.list = list;
//        this.activity = activity;
//
//    }
//    @Override
//    public int getCount() {
//        return list.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return list.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View view = layoutInflater.inflate(R.layout.group_friend,null);
//        ImageView imRank = (ImageView) view.findViewById(R.id.imageViewPicture);
//        TextView tvName =(TextView) view.findViewById(R.id.textViewName);
//        TextView tvPoint =(TextView) view.findViewById(R.id.textViewPoint);
//
//        Friend friend = list.get(position);
//    //   imRank.setImageDrawable(friend.getImage());
//       tvName.setText(friend.getName());
//       tvPoint.setText(String.valueOf(friend.getPoint()));
//
//
//        return view;
