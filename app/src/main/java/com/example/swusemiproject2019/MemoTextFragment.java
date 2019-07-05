package com.example.swusemiproject2019;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MemoTextFragment extends Fragment {
    private EditText etMemo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_write_memo, container, false);
        etMemo = view.findViewById(R.id.etMemo);



        return view;
    }

    public String returnMemoString(){
        return etMemo.getText().toString();
    }
} // class
