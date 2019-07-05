package com.example.swusemiproject2019.model;

import android.net.Uri;

public class Memo {
    String text;
    String time;
    String photo;

    //constructor
    public Memo(){
        this.text="메모";
        this.time="시간";
        this.photo=null;
    }

    public Memo(String text, String time, String photo) {
        this.text = text;
        this.time = time;
        this.photo = photo;
    }

    //getter and setter
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }




} // class
