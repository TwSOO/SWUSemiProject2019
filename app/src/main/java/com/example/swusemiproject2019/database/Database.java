package com.example.swusemiproject2019.database;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.swusemiproject2019.model.Member;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    SingletonePattern
    어플리케이션 닫기 직전에 SharedPreferences에 저장함
    어플리케이션 열 때 다시 loadMember해줌
 */
public class Database {
    public final static String TBL_MEMBERS = "MEMBERS"; //SharedPreferences에 저장할 때 사용할 키
    public static Database inst;
    public static SharedPreferences sf;
    public static List<Member> members = null; // 멤버들 리스트

    // getInstance()
    public static Database getInstance(Context context){
        if(sf == null){
            sf = context.getSharedPreferences("MEMO", Activity.MODE_PRIVATE);
            // SharedPreferecnes 이름 : MEMO
        }

        if(inst == null){
            inst = new Database();
        }

        if(members == null){
            members = new ArrayList<>();
        }

        return inst;
    } // getInstance()

    // Member 추가
    public void saveMember(Member member){
        members.add(member);
    }

    // 수정해야할 것 같음
    // Member 한명 획득
    public Member getMember(String id){
        Member tempMember = null;
        for(int i=0; i < members.size(); i++){
            Log.d("Database", ""+members.size());
            tempMember = members.get(i);
            if(tempMember.getId().equals(id)){
                return tempMember;
            }
        }
        return null;
    }

    // 중복 멤버 존재 여부 검사
    public boolean checkMember(String id){
        boolean isTrue = false;
        Member tempMember = inst.getMember(id);
        if(tempMember == null){
            return isTrue;
        }else{
            isTrue = true;
            return isTrue;
        }
    }

    // List<Member> members 획득
    public List<Member> loadMembers(){
        // SharedPreferences로 부터 가져오기
        String membersString = sf.getString(TBL_MEMBERS,"");

        if(!membersString.isEmpty()){
            // 배열로 가져옴
            Member[] memberArray = new Gson().fromJson(membersString, Member[].class);

            //배열 -> ArrayList
            members = new ArrayList<>(Arrays.asList(memberArray));
        }

        return members;
    }

    // List<Member> SharedPreferences에 업데이트
    public void updateMembers(){
        // Json 포맷으로 바꾸기
        String membersString = new GsonBuilder().serializeNulls().create().toJson(members);

        // 저장
        SharedPreferences.Editor editor = sf.edit();
        editor.putString(TBL_MEMBERS,membersString);
        editor.commit();
    }







} // class
