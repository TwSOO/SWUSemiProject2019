package com.example.swusemiproject2019;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swusemiproject2019.model.Memo;

import java.util.ArrayList;
import java.util.List;

public class MemoListFragment extends Fragment {
    private Button btnNewMemo; // 새메모작성 버튼
    private List<Memo> memos = new ArrayList<>(); // 메모목록에 들어갈 원본데이터 리스트
    private RecyclerView recycler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_memo, container, false);

        // 새메모작성 버튼 획득
        btnNewMemo = view.findViewById(R.id.btnNewMemo);
        // 새메모작성 버튼 이벤트
        btnNewMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), WriteActivity.class);
                startActivity(intent);
            }
        });


        // 리사이클러뷰 획득
        recycler = view.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getContext())); //리사이클러 뷰 매니저 등록

        ///////////////////////////////////////////////// 원본 예비 데이터
        memos.add(new Memo("메모메모메모메모메모메모메모"));
        ////////////////////////////////////////////////

        // Adapter 생성
        MyAdapter myAdapter = new MyAdapter(memos);

        // 리사이클러 뷰에 어댑터 연결
        recycler.setAdapter(myAdapter);




        return view;
    }

    // Adapter 클래스
    class MyAdapter extends RecyclerView.Adapter<MyAdapter.CustomViewHolder>{

        // 화면에 뿌릴 원본 데이터
        private List<Memo> memos;


        // Constructor
        public MyAdapter(List<Memo>memos){
            this.memos = memos;
        }



        // 리사이클러 뷰 홀더 클래스
        public class CustomViewHolder extends  RecyclerView.ViewHolder{

            // 메모뷰 구성요소
            protected ImageView imgMemoPhoto;
            protected TextView txtvMemo;
            protected Button btnEdit;
            protected Button btnErase;
            protected Button btnDetail;
            protected TextView txtvDate;



            // 메모뷰 구성 요소 바인딩
            public CustomViewHolder(@NonNull View itemView) {
                super(itemView);
                this.imgMemoPhoto = itemView.findViewById(R.id.imgvMemoPhoto);
                this.txtvMemo = itemView.findViewById(R.id.txtvMemo);
                this.btnEdit = itemView.findViewById(R.id.btnEdit);
                this.btnErase = itemView.findViewById(R.id.btnErase);
                this.btnDetail= itemView.findViewById(R.id.btnDetail);
                this.txtvDate= itemView.findViewById(R.id.txtvDate);


            }
        } // ViewHolder Class

        @NonNull
        @Override
        public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


            //UI를 확산함
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.memo_item,parent, false);

            // 커스텀 뷰홀더 생성
            CustomViewHolder viewHolder = new CustomViewHolder(view);

            // 커스텀 뷰홀더 반환
            return viewHolder;
        }

        // 메모 뷰의 각 요소에 데이터 설정
        @Override
        public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
            // 원본데이터에서 position번재 데이터 가져옴
            Memo memo = memos.get(position);

            // holder의 각 요소에 데이터 설정
            //holder.imgMemoPhoto

            holder.txtvMemo.setText(memo.getText());
            holder.txtvDate.setText("2019.07.04");

            // holder의 버튼 이벤트
            holder.btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), DetailMemoActivity.class);
                    startActivity(intent);
                }
            });

            //holder.btnErase

            holder.btnDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), DetailMemoActivity.class);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return memos.size();
        }

    } // MyAdapter class
} // class
