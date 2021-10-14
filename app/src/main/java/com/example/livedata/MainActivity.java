package com.example.livedata;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.livedata.room.DataActivity;
import com.example.livedata.room.MyDataBase;
import com.example.livedata.room.PersonData;
import com.example.livedata.room.Student;

public class MainActivity extends AppCompatActivity {

    private Button count;
    private TextView tvRecord;
    private EditText etname, etage;
    MyViewModel myViewModel;
    int id;

    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        setData();
        clickListener();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String name = intent.getStringExtra("name");
        String age = intent.getStringExtra("age");
        id = intent.getIntExtra("id", -1);
        etname.setText(name);
        etage.setText(age);
        count.setText("Update");

    }

    private void clickListener() {
        tvRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DataActivity.class));

            }
        });
    }

    private void setData() {
        myViewModel.getData().observe(this, integer -> {
        });
        count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                myViewModel.getData().setValue(i++);
                String name = etname.getText().toString();
                String age = etage.getText().toString();
                PersonData personData = MyDataBase.getInstance(MainActivity.this);
                if (id != -1) {
                    personData.personDao().upDateRecord(new Student(id, name, age));
                } else {
                    personData.personDao().addRecord(new Student(name, age));
                }
            }
        });
    }

    private void initWidgets() {
        tvRecord = findViewById(R.id.tv_record);
        count = findViewById(R.id.btn_add);
        etage = findViewById(R.id.et_age);
        etname = findViewById(R.id.et_name);
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
    }
}