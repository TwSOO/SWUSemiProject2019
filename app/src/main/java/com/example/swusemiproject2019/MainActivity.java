package com.example.swusemiproject2019;

import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.swusemiproject2019.database.Database;
import com.example.swusemiproject2019.model.Member;

public class MainActivity extends AppCompatActivity {
    private EditText editId, editPwd; // 아이디입력칸, 패스워드입력칸
    private Button btnLogin, btnSignup; // 로그인버튼, 회원가입버튼
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 아이디입력칸, 패스워드입력칸 획득
        editId = findViewById(R.id.editId);
        editPwd = findViewById(R.id.editPwd);

        // 로그인버튼, 회원가입버튼 획득
        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignUp);

        // 로그인버튼 이벤트
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = editId.getText().toString();
                String pwd = editPwd.getText().toString();

                Member loginMember = null;
                loginMember = db.getMember(id);
                if(loginMember != null){
                    if(pwd.equals(loginMember.getPwd())){
                        Intent intent = new Intent(getApplicationContext(), Memo_MemberInfoActivity.class);
                        intent.putExtra("LOGIN", loginMember);
                        startActivity(intent);
                    }else{
                        showDialog();
                    }
                }else{
                    showDialog();
                }


            }
        });
        // 회원가입버튼 이벤트
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });


        // 로드하기
        db = Database.getInstance(this);
        Database.members =db.loadMembers();



    } //onCreate()

    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("로그인 실패");
        builder.setMessage("존재하지 않는 회원이거나 아이디 또는 비밀번호가 일치하지 않습니다.");
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });


        //다이얼로그 생성
        AlertDialog alertDialog = builder.create();
        builder.show();
    }
}
