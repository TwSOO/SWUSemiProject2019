package com.example.swusemiproject2019.model;

import android.net.Uri;

import java.io.Serializable;
import java.util.List;

public class Member implements Serializable {
    private String imgUri;
    private String id;
    private String pwd;
    private String name;
    private List<Memo> memos;

    //Constructor
    public Member(){
        imgUri = null;
        id = "";
        pwd = "";
        name = "";
        memos = null;
    };
    public Member(String id, String name, String pwd, String imgUri) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.imgUri = imgUri;
    }

    // getter and setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getImgUri() {
        return imgUri;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }

    public List<Memo> getMemos() {
        return memos;
    }

    public void setMemos(List<Memo> memos) {
        this.memos = memos;
    }

    //todo method
    // memo 하나 관련 메소드
    public void addMemo(Memo memo){
        memos.add(0,memo);

    }

    // memo 삭제
    public void removeMemo(int index){
        memos.remove(index);
    }

    // memo 하나 수정
    public void editMemo(int index, Memo memo){
        memos.set(index, memo);
    }

    // memo 하나 획득
    public Memo getMemo(int index){
        return memos.get(index);
    }

    //toString()
    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", pwd='" + pwd + '\'' +
                ", imgUri=" + imgUri +
                '}';
    }



} // class
