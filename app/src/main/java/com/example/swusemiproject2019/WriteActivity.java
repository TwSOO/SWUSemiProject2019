package com.example.swusemiproject2019;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.swusemiproject2019.model.TakePhotoFragment;
import com.google.android.material.tabs.TabLayout;

public class WriteActivity extends AppCompatActivity {
    private Button btnSaveMemo;
    private TabLayout tabWriteMemo;
    private ViewPager vpWriteMemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        // 뷰 객체 획득
        btnSaveMemo = findViewById(R.id.btnSaveMemo);
        tabWriteMemo = findViewById(R.id.tabWriteMemo);
        vpWriteMemo = findViewById(R.id.vpWriteMemo);

        //메모저장 버튼 이벤트
        btnSaveMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Tab 생성
        tabWriteMemo.addTab(tabWriteMemo.newTab().setText("글쓰기"));
        tabWriteMemo.addTab(tabWriteMemo.newTab().setText("시작하기"));
        tabWriteMemo.setTabGravity(TabLayout.GRAVITY_FILL);

        // 뷰페이저에 설정할 어댑터 생성 및 설정
        MyPageAdapter adapter = new MyPageAdapter(getSupportFragmentManager(), tabWriteMemo.getTabCount());
        vpWriteMemo.setAdapter(adapter);

        // 리스너 등록
        vpWriteMemo.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabWriteMemo));
        tabWriteMemo.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            // 선택된 탭에 해당하는 프레그 먼트를 나타냄
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpWriteMemo.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    } // onCreate

    class MyPageAdapter extends FragmentPagerAdapter{
        int tabSize; // Tab 수

        public MyPageAdapter(FragmentManager fm, int count) {
            super(fm);
            this.tabSize = count; // Tab 수 설정
        }

        // 탭 생성
        @Override
        public Fragment getItem(int position) {
            switch(position){
                case 0:
                    return new MemoTextFragment();
                case 1:
                    return new TakePhotoFragment();
            }
            return null;
        }

        // Tab 수 반환
        @Override
        public int getCount() {
            return tabSize;
        }
    }
} // class
