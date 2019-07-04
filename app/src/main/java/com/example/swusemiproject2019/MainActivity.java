package com.example.swusemiproject2019;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText editId, editPwd; // 아이디입력칸, 패스워드입력칸
    private Button btnLogin, btnSignup; // 로그인버튼, 회원가입버튼

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

        // 회원가입버튼 이벤트
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });





    }
}
