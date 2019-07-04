package com.example.swusemiproject2019;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/*
    Dialog 창 띄우는 함수
 */
public class DialogUtil {

    public static void showDialog(
            Context context,
            String title, String msg, // 제목, 메시지
            String okMsg, DialogInterface.OnClickListener okListener, // 확인에 해당하는 단어, 확인 리스너
            String cancelMsg, DialogInterface.OnClickListener cancelListener // 취소에 해당하는 단어, 취소 리스너
    ){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title); // 제목 설정
        builder.setMessage(msg); // 메시지 설정

        // 사용자 선택 버튼 단어, 버튼 리스너 설정
        if(okListener != null){
            builder.setPositiveButton(okMsg, okListener);
        }
        if(cancelListener != null){
            builder.setNegativeButton(cancelMsg, cancelListener);
        }

        // 다이얼로그 표시
        builder.show();


    } // showDialog()


} // DialogUtil
