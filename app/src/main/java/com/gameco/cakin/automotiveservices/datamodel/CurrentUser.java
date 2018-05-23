package com.gameco.cakin.automotiveservices.datamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by cakin on 11/27/2017.
 */

public class CurrentUser implements Serializable{

    private Car Car;
    private int ChallengeCount;
    private String Email;
    private String NickName;
    private String Level;
    private String Password;

    public void setCar(com.gameco.cakin.automotiveservices.datamodel.Car car) {
        Car = car;
    }

    public void setChallengeCount(int challengeCount) {
        ChallengeCount = challengeCount;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public void setLevel(String level) {
        Level = level;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setPictureURI(String pictureURI) {
        PictureURI = pictureURI;
    }

    public void setPoints(int points) {
        Points = points;
    }

    private String PictureURI;
    private String CarURI;
    private int Points;
    private ArrayList<Challenge> challengeList;
    private HashMap<String,String> Friends;

    public CurrentUser() {
    }
    public CurrentUser(com.gameco.cakin.automotiveservices.datamodel.Car car, int challengeCount, String email, String nickName, String level, String password, String pictureURI, String carURI, int points, ArrayList<Challenge> challengeList, HashMap<String, String> friends) {
        Car = car;
        ChallengeCount = challengeCount;
        Email = email;
        NickName = nickName;
        Level = level;
        Password = password;
        PictureURI = pictureURI;
        CarURI = carURI;
        Points = points;
        this.challengeList = challengeList;
        Friends = friends;
    }

    public String getCarURI() {
        return CarURI;
    }

    public void setCarURI(String carURI) {
        CarURI = carURI;
    }

    public Car getCar() {
        return Car;
    }

    public int getChallengeCount() {
        return ChallengeCount;
    }

    public String getEmail() {
        return Email;
    }

    public String getNickName() {
        return NickName;
    }

    public String getLevel() {
        return Level;
    }

    public String getPassword() {
        return Password;
    }

    public String getPictureURI() {
        return PictureURI;
    }

    public int getPoints() {
        return Points;
    }
    public ArrayList<Challenge> getChallengeList() {
        return challengeList;
    }

    public void setChallengeList(ArrayList<Challenge> challengeList) {
        this.challengeList = challengeList;
    }

    public HashMap<String,String> getFriends() {
        return Friends;
    }

    public void setFriends(HashMap<String,String> Friends) {
        this.Friends = Friends;
    }


//
//    private String FullName;
//    private String Email;
//    private String password;
//    private Car car;
//    private List<Challenge> challenges;
//    private List<Friend> friendList;
//    private List<Rank> rankList;
//    private String Level;
//    private int Points;
//    private int ChallengeCount;
//
//    public CurrentUser() {
//    }
//
//    public CurrentUser(String fullName, String email, String password, Car car, List<Challenge> challenges, List<Friend> friendList, List<Rank> rankList, String level, int points, int challengeCount, int score) {
//        FullName = fullName;
//        this.email = email;
//        this.password = password;
//        this.car = car;
//        this.challenges = challenges;
//        this.friendList = friendList;
//        this.rankList = rankList;
//        Level = level;
//        Points = points;
//        ChallengeCount = challengeCount;
//        Score = score;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//    public String getFullName() {
//        return FullName;
//    }
//
//    public void setFullName(String fullName) {
//        this.FullName = fullName;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public Car getCar() {
//        return car;
//    }
//
//    public void setCar(Car car) {
//        this.car = car;
//    }
//
//    public List<Friend> getFriendList() {
//        return friendList;
//    }
//
//    public void setFriendList(List<Friend> friendList) {
//        this.friendList = friendList;
//    }
//
//    public List<Rank> getRankList() {
//        return rankList;
//    }
//
//    public void setRankList(List<Rank> rankList) {
//        this.rankList = rankList;
//    }
//
//    public String getLevel() {
//        return Level;
//    }
//
//    public void setLevel(String Level) {
//        this.Level = Level;
//    }
//
//    public int getScore() {
//        return Score;
//    }
//
//    public void setScore(int score) {
//        Score = score;
//    }
//
//    private int Score;
}
