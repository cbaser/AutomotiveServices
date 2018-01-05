package com.gameco.cakin.automotiveservices.deprecated;

/**
 * Created by cakin on 12/26/2017.
 */

public class DeprecatedCodes {
    //--------Fragment Achievements---------//
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


    //------------Fragment Home---------------//
    //    private List<String> groupList;
//    private List<String> childList;
//    private Map<String, List<String>> friendsCollection;
//    private ExpandableListView friendsListView;
//    private ListView challengesListView;
//    private CurrentUser currentUser;
    // createGroupList();
    //     currentUser = new CurrentUser();
    //   createCollection();
//     final FriendsListAdapter friendsListAdapter = new FriendsListAdapter(getActivity(), groupList, friendsCollection);
    //   friendsListAdapter.setCurrentUser(currentUser);
    //      friendsListView.setAdapter(friendsListAdapter);

    //    friendsListView.setGroupIndicator(null);
//    private void createGroupList() {
//        groupList = new ArrayList<String>();
//        groupList.add("Can");
//        groupList.add("Lea");
//        groupList.add("Hampus");
//    }
//    private void createCollection() {
//        // preparing friends collection(child)
////        String[] canChallenges = { "Challenge 1 : Drive less than 50 km this week"};
////        String[] leaChallenges = { "Challenge 1 :Save one tree for this week"};
////        String[] hampusChallenges = { "Challenge 1 :Drive not faster than 100 km/h this week" };
//          String canScore = "23541";
//          String leaScore = "21051";
//          String hampusScore="21021";
//
//        friendsCollection = new LinkedHashMap<String, List<String>>();
//
//        for (String friend : groupList) {
//            if (friend.equals("Can")) {
//                loadChild(canScore);
//            } else if (friend.equals("Hampus"))
//                loadChild(hampusScore);
//            else if (friend.equals("Lea"))
//                loadChild(leaScore);
//            friendsCollection.put(friend, childList);
//        }
//    }
//    private void loadChild(String friend) {
//        childList = new ArrayList<>();
//        Collections.addAll(childList);
//       // for (String friend : friends)
//            childList.add(friend);
//    }
//
//    private void setGroupIndicatorToRight() {
//        /* Get the screen width */
//        DisplayMetrics dm = new DisplayMetrics();
//        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
//        int width = dm.widthPixels;
//
//        friendsListView.setIndicatorBounds(width - getDipsFromPixel(35), width - getDipsFromPixel(5));
//    }
//    public int getDipsFromPixel(float pixels) {
//        // Get the screen's density scale
//        final float scale = getResources().getDisplayMetrics().density;
//        // Convert the dps to pixels, based on density scale
//        return (int) (pixels * scale + 0.5f);
//    }


    //-------------OpeningActivity---------//

//        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f,1.0f);
//
//        alphaAnimation.setInterpolator(new BounceInterpolator());
//        alphaAnimation.setRepeatCount(3);
//        alphaAnimation.setDuration(100);
//        alphaAnimation.setRepeatMode(Animation.REVERSE);
//        RotateAnimation animation = new RotateAnimation(0f,350f,15f,15f);
//        animation.setInterpolator(new LinearInterpolator());
//        animation.setRepeatCount(Animation.INFINITE);
//        animation.setDuration(700);


    //--------FriendsListAdapter---------------//

//


//
//}
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


//-----------------------OLD CODE ----------------------
//private Activity context;
//     private LayoutInflater layoutInflater;
//    private Map<String, List<String>> friendsCollections;
//        private List<String> friends;
//        public FriendsListAdapter(Activity context, List<String>friends,Map<String,List<String>> friendsCollections){
//            this.context = context;
//            this.friends = friends;
//            this.friendsCollections = friendsCollections;
//        }
//
//    @Override
//    public int getCount() {
//        return friends.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return friends.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = layoutInflater.inflate(R.layout.content_challenges_friends_list,null,false);
//        LinearLayout mainLinearLayout = (LinearLayout) convertView.findViewById(R.id.homeLinear);
//
//        for(int i=0;i<friends.size();i++){
//            View additionView = layoutInflater.inflate(R.layout.content_challenges_friends_list,null,false);
//            LinearLayout innerLineerLayout = (LinearLayout) additionView.findViewById(R.id.home_innerlayout);
//            mainLinearLayout.addView(innerLineerLayout);
//        }
//
//
//
//        return convertView;
//    }
//    class ViewHolder{
//            HorizontalScrollView horizontalScrollView;
//            TextView textView;
//            LinearLayout linearLayout,main_linear_layout;
//    }



    //------myJSONParser------------//
    //      if(telematicValues.get(i).contains("bmwcardata_mileage")){
//       currentCar.setMileage( getTelematicValue("bmwcardata_mileage",telematicValues.get(i)));
//                   int position = telematicValues.get(i).indexOf("bmwcardata_mileage");
//                   int valuePos = telematicValues.get(i).indexOf("value",position);
//                   int endPos = telematicValues.get(i).indexOf("}",valuePos);
//                   String mileage = telematicValues.get(i).substring(valuePos,endPos);
//                   currentCar.setMileage(mileage);
//     }
//                if(telematicValues.get(i).contains("bmwcardata_averageDistance")){
//                    int position = telematicValues.get(i).indexOf("bmwcardata_averageDistance");
//                    int valuePos = telematicValues.get(i).indexOf("value",position);
//                    int endPos = telematicValues.get(i).indexOf("}",valuePos);
//                    String averageDistance = telematicValues.get(i).substring(valuePos,endPos);
//                    currentCar.setAverage_distance_per_week(averageDistance);
//                }
//                if(telematicValues.get(i).contains("bmwcardata_remainingFuel")){
//                    int position = telematicValues.get(i).indexOf("bmwcardata_remainingFuel");
//                    int valuePos = telematicValues.get(i).indexOf("value",position);
//                    int endPos = telematicValues.get(i).indexOf("}",valuePos);
//                    String remainingFuel = telematicValues.get(i).substring(valuePos,endPos);
//                    currentCar.setRemaining_fuel(remainingFuel);
//                }

//                if(telematicValues.get(i).contains("bmwcardata_batteryVoltage")){
//                    int position = telematicValues.get(i).indexOf("bmwcardata_batteryVoltage");
//                    int valuePos = telematicValues.get(i).indexOf("value",position);
//                    int endPos = telematicValues.get(i).indexOf("}",valuePos);
//                    String batteryVoltage = telematicValues.get(i).substring(valuePos,endPos);
//                    currentCar.setBatteryVoltage(batteryVoltage);
//                }
//                if(telematicValues.get(i).contains("bmwcardata_nextServiceDistance")){
//                    int position = telematicValues.get(i).indexOf("bmwcardata_nextServiceDistance");
//                    int valuePos = telematicValues.get(i).indexOf("value",position);
//                    int endPos = telematicValues.get(i).indexOf("}",valuePos);
//                    String nextService = telematicValues.get(i).substring(valuePos,endPos);
//                    currentCar.setNextServiceDistance(nextService);
//                }
//                if(telematicValues.get(i).contains("bmwcardata_SegmentLastTripECOTimeOfActivation")){
//                    int position = telematicValues.get(i).indexOf("bmwcardata_SegmentLastTripECOTimeOfActivation");
//                    int valuePos = telematicValues.get(i).indexOf("value",position);
//                    int endPos = telematicValues.get(i).indexOf("}",valuePos);
//                    String ecoTrip = telematicValues.get(i).substring(valuePos,endPos);
//                    currentCar.setActive_time_of_eco_mode(ecoTrip);
//                }
//                if(telematicValues.get(i).contains("bmwcardata_SegmentLastTripFuelConsumption")){
//                    int position = telematicValues.get(i).indexOf("bmwcardata_SegmentLastTripFuelConsumption");
//                    int valuePos = telematicValues.get(i).indexOf("value",position);
//                    int endPos = telematicValues.get(i).indexOf("}",valuePos);
//                    String fuelConsumption = telematicValues.get(i).substring(valuePos,endPos);
//                    currentCar.setFuel_consumption(fuelConsumption);
//                }


//            String rawString = bmwCarData[lastIndex].getTelematics();
//
//          if(!rawString.endsWith("}")){
//              rawString = rawString.substring(0,rawString.lastIndexOf("}"));
//              rawString = rawString+"}]}";
//          }
//            JsonObject telematicObject = parser.parse(rawString).getAsJsonObject();
//            JsonElement telematicElement = telematicObject.get("telematicKeyValues");
//             telematics = gson.fromJson(telematicElement,Telematics[].class);
//
//             if(telematics!=null)
//             currentCar.setTelematicsList(Arrays.asList(telematics));


    //   private TransitionHelper_deprecated transitionHelper;
    //   private UserHelper_deprecated userHelper;
    // userHelper = new UserHelper_deprecated();
    //  transitionHelper = new TransitionHelper_deprecated();
    //  transitionHelper.setFragment(this);
    //  transitionHelper.showDailyChallenge();
    //    FragmentFriends fragmentFriends = new FragmentFriends();
    //    FragmentManager fm = getActivity().getSupportFragmentManager();
    //   fragmentFriends.show(fm,"Dialog Fragment");
    //   transitionHelper.showFriends();
    //  userHelper.setUserInformation(view,getActivity().getSharedPreferences("UserInfo",0));
}
