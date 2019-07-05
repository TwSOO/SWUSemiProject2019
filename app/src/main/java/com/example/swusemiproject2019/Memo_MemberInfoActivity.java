package com.example.swusemiproject2019;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.swusemiproject2019.model.Member;
import com.google.android.material.tabs.TabLayout;

public class Memo_MemberInfoActivity extends AppCompatActivity {
    private TabLayout tabMemoMember; // 탭영역
    private ViewPager viewPager; // 탭별 프레그먼트 표시 영역

    private Member currentMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo__member_info);

        // 로그인한 멤버변수 획득
        Intent intent = getIntent();
        currentMember = (Member) intent.getSerializableExtra("LOGIN");

        // 탭, 뷰페이저 획득
        tabMemoMember = findViewById(R.id.tabMemoMember);
        viewPager = findViewById(R.id.viewPager);

        // Tab 생성
        tabMemoMember.addTab(tabMemoMember.newTab().setText("메모"));
        tabMemoMember.addTab(tabMemoMember.newTab().setText("회원정보"));
        tabMemoMember.setTabGravity(TabLayout.GRAVITY_FILL);

        // 어댑터 생성
        MemoMemberAdapter adapter = new MemoMemberAdapter(getSupportFragmentManager(), tabMemoMember.getTabCount());

        //뷰페이저에 어댑터 add
        viewPager.setAdapter(adapter);

        //뷰페이저에 리스너 등록
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabMemoMember));
        //TabLayout에 리스너 등록
        tabMemoMember.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) { // 특정 Tab 선택 이벤트
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    } // onCreate()

    // 어댑터 클래스
    private class MemoMemberAdapter extends FragmentPagerAdapter {
        int tabSize; // Tab 수

        // constructor
        public MemoMemberAdapter(FragmentManager fm, int count) {
            super(fm);
            this.tabSize = count; // tab 수 설정
        }

        // 프레그먼트 생성 및 반환
        @Override
        public Fragment getItem(int position) {

            switch(position){
                case 0:
                    return new MemoListFragment();
                case 1:
                    return new MemberInfoFragment();

            }
            return null;
        }

        // Tab 수 반환
        @Override
        public int getCount() {
            return this.tabSize;
        }
    } // Adatper class

    public Member getCurrentMember() {
        return currentMember;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch(requestCode){
            case MemoListFragment.WRITE_MEMO:

        }
    }
} // class
