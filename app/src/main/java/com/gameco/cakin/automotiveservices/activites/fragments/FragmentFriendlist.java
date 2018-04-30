package com.gameco.cakin.automotiveservices.activites.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.adapters.FriendsListHolder;
import com.gameco.cakin.automotiveservices.datamodel.Friend;
import com.gameco.cakin.automotiveservices.firebase.MyFirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.w3c.dom.Text;

public class FragmentFriendlist extends Fragment {

    private RecyclerView friendsRankRecyclerView,friendsRequestRecyclerView;
    private FloatingActionButton search_button;
    private EditText searchField;
    private MyFirebaseDatabase myFirebaseDatabase;
    private DatabaseReference databaseReference;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friendslist, container, false);
        friendsRankRecyclerView = view.findViewById(R.id.friendsRankListView);
        friendsRankRecyclerView.setHasFixedSize(true);
        friendsRankRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        friendsRequestRecyclerView = view.findViewById(R.id.friendRequestListView);
         search_button = view.findViewById(R.id.search_button);
         searchField = view.findViewById(R.id.editSearch);
         databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getEmail().replace(".",","));
         myFirebaseDatabase = new MyFirebaseDatabase(this.getActivity());
         search_button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String searchText = searchField.getText().toString();
                 Toast.makeText(getActivity(),"Started Search",Toast.LENGTH_LONG).show();
               firebaseUserSearch(searchText);
             }
         });
        return view;
    }
    private void firebaseUserSearch(String searchText){
        Query query =databaseReference
                .child("Friends")
                .orderByChild("name")
                .startAt(searchText)
                .endAt(searchText+"\uf8ff");
        FirebaseRecyclerOptions<Friend> options =
                new FirebaseRecyclerOptions.Builder<Friend>()
                        .setQuery(query, Friend.class)
                        .build();
        FirebaseRecyclerAdapter<Friend,FriendsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Friend, FriendsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FriendsViewHolder holder, int position, @NonNull Friend model) {

                holder.setDetails(getActivity().getApplicationContext(),model.getName(),model.getPoint(),model.getImage());

            }

            @Override
            public FriendsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view =LayoutInflater.from(getActivity()).inflate(R.layout.group_friend,parent,false);
                return new FriendsViewHolder(view);
            }
        };
        friendsRankRecyclerView.setAdapter(firebaseRecyclerAdapter);


    }
    public static class FriendsViewHolder extends RecyclerView.ViewHolder{
        View view;

        public FriendsViewHolder(View itemView) {
            super(itemView);
            view = itemView;
        }
        public void setDetails(Context context,String userName, String userPoints, String userImage){
            TextView name = (TextView) view.findViewById(R.id.txtFriendName);
            TextView points =(TextView) view.findViewById(R.id.txtFriendPoint);
            ImageView imageView = (ImageView) view.findViewById(R.id.imageViewPicture);

            name.setText(userName);
            points.setText(userPoints);
            Glide.with(context).load(userImage).into(imageView);

        }
    }

}
