package com.gameco.cakin.automotiveservices.datamodel;

/**
 * Created by cakin on 12/27/2017.
 */

public class Rank {


    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public long getPoints() {
        return Points;
    }

    public void setPoints(long points) {
        Points = points;
    }

    private String NickName;
    private long Points;

    public Rank() {
    }
    public Rank(String nickName, long points) {
        NickName = nickName;
        Points = points;
    }

//    private String nickname;
//    private long point;
//    private long position;
//
//    public Rank() {
//    }
//
//    public Rank(String nickname, long point,long position) {
//        this.nickname = nickname;
//        this.point = point;
//        this.position = position;
//    }
//
//    public String getNickname() {
//        return nickname;
//    }
//
//    public void setNickname(String nickname) {
//        this.nickname = nickname;
//    }
//
//    public long getPoint() {
//        return point;
//    }
//
//    public void setPoint(long point) {
//        this.point = point;
//    }
//
//    public long getPosition() {
//        return position;
//    }
//
//    public void setPosition(long position) {
//        this.position = position;
//    }
}
