package com.gameco.cakin.automotiveservices.datamodel;

import android.widget.ImageView;

import java.util.List;

/**
 * Created by cakin on 11/27/2017.
 */

public class CurrentUser {

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public int getChallengeCount() {
        return ChallengeCount;
    }

    public void setChallengeCount(int challengeCount) {
        ChallengeCount = challengeCount;
    }

    public List<Challenge> getChallenges() {
        return challenges;
    }

    public void setChallenges(List<Challenge> challenges) {
        this.challenges = challenges;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String level) {
        Level = level;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    private Car car;
    private int ChallengeCount;
    private List<Challenge> challenges;
    private String Email;
    private String FullName;
    private String Level;
    private String Password;
    private int points;

    public CurrentUser(Car car, int challengeCount, List<Challenge> challenges, String email, String fullName, String level, String password, int points) {
        this.car = car;
        ChallengeCount = challengeCount;
        this.challenges = challenges;
        Email = email;
        FullName = fullName;
        Level = level;
        Password = password;
        this.points = points;
    }

    public CurrentUser() {
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
