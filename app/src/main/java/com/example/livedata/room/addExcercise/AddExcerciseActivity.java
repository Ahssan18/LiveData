package com.example.livedata.room.addExcercise;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.livedata.R;

import java.util.List;

public class AddExcerciseActivity extends AppCompatActivity {

    private EditText etname, etCalburn;
    private Button btnSave;
    private ExcerciseViewModel excerciseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_excercise);
        initViews();
        getData();
        clickListener();
    }

    private void getData() {
        excerciseViewModel.getAllExcercises().observe(this, new Observer<List<Excercise>>() {
            @Override
            public void onChanged(List<Excercise> excercises) {


            }
        });
    }

    private void clickListener() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etname.getText().toString();
                String cal = etCalburn.getText().toString();
                excerciseViewModel.addExcercise(new Excercise(name, cal));
                Toast.makeText(AddExcerciseActivity.this, "Data Added Successfully !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews() {
        excerciseViewModel = new ViewModelProvider(this).get(ExcerciseViewModel.class);
        etname = findViewById(R.id.et_add_excersize);
        etCalburn = findViewById(R.id.et_calburn);
        btnSave = findViewById(R.id.btn_save);
    }
}