package com.example.swusemiproject2019;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class DetailMemoActivity extends AppCompatActivity {

    private Button btnEdit, btnErase; // 수정버튼, 삭제버튼
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_memo);


        // 수정버튼, 삭제버튼 획득
        btnEdit = findViewById(R.id.btnEdit);
        btnErase = findViewById(R.id.btnErase);
        // 탭 레이아웃, 뷰페이저 획득
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        //Tab 생성
        tabLayout.addTab(tabLayout.newTab().setText("글쓰기"));
        tabLayout.addTab(tabLayout.newTab().setText("글쓰기"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //뷰페이저 어댑터 생성 및 설정
        TabAdapter adapter = new TabAdapter (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        // 뷰페이저, 탭레이아웃 리스너 설정
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            // 선택된 Tab에 대응 되는 프레그먼트를 뷰페이저에 띄워줌
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
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


    } // onCreate

    class TabAdapter extends FragmentPagerAdapter{
        int tabSize; // 탭수

        // Constructor
        public TabAdapter (FragmentManager fm, int count) {
            super(fm);
            this.tabSize = count;
        }

        // 프레그먼트 생성
        @Override
        public Fragment getItem(int position) {
            switch (position){
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
            return this.tabSize;
        }
    } // TabAdapter
} // class
