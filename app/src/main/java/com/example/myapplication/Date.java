package com.example.myapplication;

public class Date {
    private  String time,eat,pnum,sid,uid,check;
    private  int id;

    public Date(int id,String time, String eat, String pnum,String sid,String uid,String check) {
        this.time = time;
        this.eat = eat;
        this.pnum = pnum;
        this.sid = sid;
        this.id = id;
        this.uid = uid;
        this.check = check;
    }

    public Date() {
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEat() {
        return eat;
    }

    public void setEat(String eat) {
        this.eat = eat;
    }

    public String getPnum() {
        return pnum;
    }

    public void setPnum(String pnum) {
        this.pnum = pnum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
