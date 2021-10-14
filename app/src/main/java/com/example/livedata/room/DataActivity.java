package com.example.livedata.room;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.livedata.R;
import com.example.livedata.StudentAdapter;

import java.util.ArrayList;
import java.util.List;

public class DataActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    List<Student> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        initView();
        setData();
    }

    private void setData() {
        list = MyDataBase.getInstance(this).personDao().getRecord();
        StudentAdapter adapter = new StudentAdapter(list, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initView() {
        list = new ArrayList<>();
        recyclerView = findViewById(R.id.recycle);
    }
}