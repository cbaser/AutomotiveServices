package com.gameco.cakin.automotiveservices.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.datamodel.Challenge;
import com.gameco.cakin.automotiveservices.datamodel.Friend;


import java.util.List;

/**
 * Created by cakin on 2/2/2018.
 */

public class MyChallengesAdapter extends RecyclerView.Adapter<MyChallengesAdapter.MyViewHolder> {
    private List<Challenge> challengeList;
    private Activity activity;
    private TextView txtTime,txtFriendName,txtTitle,txtWinner;
    private ImageView profPic;
    private Button detailsButton;
    public class MyViewHolder extends RecyclerView.ViewHolder{

        public MyViewHolder(View itemView){
            super(itemView);
            LinearLayout profileLinear = itemView.findViewById(R.id.profileLinear1);
         txtTime = profileLinear.findViewById(R.id.txtTime1);
         txtFriendName = profileLinear.findViewById(R.id.txtFriendName1);
         profPic = profileLinear.findViewById(R.id.profPic_challenge);

        LinearLayout descriptionLinear = itemView.findViewById(R.id.descriptionLinear1);
         txtTitle = descriptionLinear.findViewById(R.id.txtDescription1);
         txtWinner = descriptionLinear.findViewById(R.id.txtwin);
            detailsButton = descriptionLinear.findViewById(R.id.expandableButton1);
        }

    }
    public MyChallengesAdapter(Activity activity,List<Challenge>challengeList){
        this.activity = activity;
        this.challengeList = challengeList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.group_my_challenge_item, parent, false);

        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Challenge challenge = challengeList.get(position);
        txtTime.setText(challenge.getTime());
        txtFriendName.setText(challenge.getFriendName());
        txtTitle.setText(challenge.getChallengeTitle());
//        if(txtFriendName.getText().toString().contains("Can")||txtFriendName.getText().toString().contains("can"))
//            profPic.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_can));
//        else
//            profPic.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_cagatay));

        if(challenge.isWinner()){
            txtWinner.setText("Congratulations you are on the lead");
            txtWinner.setTextColor(activity.getResources().getColor(R.color.colorLeaGreen));
        }
        else
        {
            txtWinner.setText("You are loosing! Keep up the hard work!");
            txtWinner.setTextColor(activity.getResources().getColor(R.color.orange));
        }
        detailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDetails(challenge);
            }
        });
    }


    @Override
    public int getItemCount() {
        return challengeList.size();
    }






    public void showDetails(Challenge challenge)
    {
        View detailView = activity.getLayoutInflater().inflate(R.layout.popup_challenge_details,null);
        final PopupWindow detailWindow = new PopupWindow(detailView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        detailWindow.showAtLocation(detailView, Gravity.CENTER,10,10);
        TextView txtDetailTime = (TextView) detailView.findViewById(R.id.txtdetailTime);
        txtDetailTime.setText(challenge.getTime());
        TextView txtDetailTitle = (TextView) detailView.findViewById(R.id.txtdetailTitle);
        txtDetailTitle.setText(challenge.getChallengeTitle());
        TextView txtDetailMy = (TextView) detailView.findViewById(R.id.txtdetailMy);
        txtDetailMy.setText(challenge.getCurrent());
        TextView txtDetailFriend = (TextView) detailView.findViewById(R.id.txtdetailFriend);
        txtDetailFriend.setText(challenge.getFriendStatus());
        TextView txtDetailFriendName = (TextView) detailView.findViewById(R.id.txtdetailFriendName);
        txtDetailFriendName.setText(challenge.getFriendName());
        TextView txtWinning = (TextView) detailView.findViewById(R.id.txtdetailWinning);
        if(challenge.isWinner())
        {
            txtWinning.setText("Congratulations! You are on the lead ! Keep up the good work");
            txtWinning.setTextColor(activity.getResources().getColor(R.color.colorLeaGreen));
        }
        else{
            txtWinning.setText("You are loosing. Try harder!");
            txtWinning.setTextColor(activity.getResources().getColor(R.color.orange));
        }



        ImageView friendImage = (ImageView) detailView.findViewById(R.id.txtdetailFriendPic);
//        if(txtDetailFriendName.getText().toString().contains("Can")||txtDetailFriendName.getText().toString().contains("can"))
//        friendImage.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_can));
//        else
//            friendImage.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_cagatay));

        FloatingActionButton exitmyChallenge = (FloatingActionButton) detailView.findViewById(R.id.exitdetail);
        exitmyChallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detailWindow.dismiss();
            }
        });
    }
}
//    private LayoutInflater layoutInflater;
//    private List<Challenge> list;
//    private Activity activity;
//    public MyChallengesAdapter(Activity activity,List<Challenge> list){
//        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        this.list = list;
//        this.activity = activity;
//    }
//    @Override
//    public int getCount() {
//        return 0;
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup viewGroup) {
//        View view = layoutInflater.inflate(R.layout.group_my_challenge_item,null);
//        LinearLayout profileLinear = view.findViewById(R.id.profileLinear1);
//        TextView txtTime = profileLinear.findViewById(R.id.txtTime1);
//        TextView txtFriendName = profileLinear.findViewById(R.id.txtFriendName1);
//        ImageView profPic = profileLinear.findViewById(R.id.profPic_challenge);
//
//        LinearLayout descriptionLinear = view.findViewById(R.id.descriptionLinear1);
//        TextView txtTitle = descriptionLinear.findViewById(R.id.txtDescription1);
//        TextView txtwinner = descriptionLinear.findViewById(R.id.txtwin);
//
//
//
//        Challenge challenge = list.get(position);
//        txtTime.setText(challenge.getTime());
//        txtFriendName.setText(challenge.getFriendName());
//        txtTitle.setText(challenge.getChallengeTitle());
//        profPic.setImageDrawable(challenge.getFriendPicture());
//        if(challenge.isWinner()){
//            txtwinner.setText("Congratulations you are on the lead");
//            txtwinner.setTextColor(Color.GREEN);
//        }
//        else
//        {
//            txtwinner.setText("You are loosing! Keep up the hard work!");
//            txtwinner.setTextColor(Color.YELLOW);
//        }





//        ImageView imRank = (ImageView) view.findViewById(R.id.imageViewPicture);
//        TextView tvName =(TextView) view.findViewById(R.id.textViewName);
//        TextView tvPoint =(TextView) view.findViewById(R.id.textViewPoint);


//        imRank.setImageDrawable(challenge.getFriendPicture());
//        tvName.setText(challenge.getFriendName());
//        tvPoint.setText(String.valueOf(challenge.getChallengeTitle()));
//if(challenge.isWinner())




//  return view;
// }