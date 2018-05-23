package com.gameco.cakin.automotiveservices.datamodel;

/**
 * Created by cakin on 2/2/2018.
 */

public class Challenge {
    public Challenge(){

    }
    public String getFriendEmail() {
        return friendEmail;
    }

    public void setFriendEmail(String friendEmail) {
        this.friendEmail = friendEmail;
    }

    public String getFriendNickName() {
        return friendNickName;
    }

    public void setFriendNickName(String friendNickName) {
        this.friendNickName = friendNickName;
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

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getFriendStatus() {
        return friendStatus;
    }

    public void setFriendStatus(String friendStatus) {
        this.friendStatus = friendStatus;
    }

    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
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
    public String getDescription() {
        return description;
    }
    public String getFriendPictureURI() {
        return friendPictureURI;
    }

    public void setFriendPictureURI(String friendPictureURI) {
        this.friendPictureURI = friendPictureURI;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    private String challengeTitle;
    private String current;
    private String description;
    private String friendNickName;
    private String friendEmail;
    private String friendStatus;
    private long points;
    private String target;
    private String time;
    private String timeToLeft;
    private boolean winner;
    private String userStatus;
    private String friendPictureURI;



    


}
