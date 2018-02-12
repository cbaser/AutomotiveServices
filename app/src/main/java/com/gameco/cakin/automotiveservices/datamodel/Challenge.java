package com.gameco.cakin.automotiveservices.datamodel;

import android.graphics.drawable.Drawable;

/**
 * Created by cakin on 2/2/2018.
 */

public class Challenge {

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getChallengeTitle() {
        return challengeTitle;
    }

    public void setChallengeTitle(String challengeTitle) {
        this.challengeTitle = challengeTitle;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }



    public String getTimeToLeft() {
        return timeToLeft;
    }

    public void setTimeToLeft(String timeToLeft) {
        this.timeToLeft = timeToLeft;
    }

    public String getYourStatus() {
        return yourStatus;
    }

    public void setYourStatus(String yourStatus) {
        this.yourStatus = yourStatus;
    }

    public String getFriendStatus() {
        return friendStatus;
    }

    public void setFriendStatus(String friendStatus) {
        this.friendStatus = friendStatus;
    }



    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    private String challengeTitle;
    private String time;
    private String timeToLeft;
    private String current;
    private String target;
    private String points;
    private boolean winner;
    private String friendName;


    private String yourStatus;
    private String friendStatus;


}
