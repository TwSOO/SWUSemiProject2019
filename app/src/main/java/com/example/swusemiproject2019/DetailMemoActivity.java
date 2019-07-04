package com.example.swusemiproject2019;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DetailMemoActivity extends AppCompatActivity {

    private Button btnEdit, btnErase; // 수정버튼, 삭제버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_memo);

        // 수정버튼, 삭제버튼 획득
        btnEdit = findViewById(R.id.btnEdit);
        btnErase = findViewById(R.id.btnErase);

        // 수정버튼 이벤트
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // 삭제버튼 이벤트
        btnErase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
