package com.example.livedata.room.workout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.livedata.R;
import com.example.livedata.room.login.Student;
import com.example.livedata.room.addExcercise.AddExcerciseActivity;
import com.example.livedata.room.database.MyDataBase;
import com.example.livedata.room.database.PersonDatabase;
import com.example.livedata.room.addExcercise.Excercise;
import com.example.livedata.room.helper.Helper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class WorkoutActivity extends AppCompatActivity {

    private static final String TAG = "WorkoutActivity";
    private Spinner spinner;
    private EditText etcounts;
    private Button btnSave;
    private TextView textView;
    private WorkoutViewModel workoutViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Add Workout");
        actionBar.setDisplayHomeAsUpEnabled(true);
        initViews();
        setData();
        clickListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void setData() {
        PersonDatabase database = MyDataBase.getInstance(this);
        List<Excercise> list = database.excerciseDao().getAllExcercise();
        ArrayAdapter<Excercise> adapter = new ArrayAdapter<Excercise>(this,
                android.R.layout.simple_spinner_item, list);
        spinner.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void clickListener() {

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Excercise excercise = (Excercise) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WorkoutActivity.this, AddExcerciseActivity.class));
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = null;
                int etcount = Integer.parseInt(etcounts.getText().toString());
                Excercise excercise = (Excercise) spinner.getSelectedItem();
                int excercise_id = excercise.getId();
                Student student = Helper.getLogin(WorkoutActivity.this);
                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    date = dateFormatter.format(new Date());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                workoutViewModel.addWorkout(new WorkOut(student.getId(), excercise_id, etcount, date));
                workoutViewModel.getWorkout().observe(WorkoutActivity.this, new Observer<List<WorkOut>>() {
                    @Override
                    public void onChanged(List<WorkOut> workOuts) {
                        Toast.makeText(WorkoutActivity.this, "Data Added Successfully !", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void initViews() {


        workoutViewModel = new ViewModelProvider(this).get(WorkoutViewModel.class);
        textView = findViewById(R.id.add_excercise);
        spinner = findViewById(R.id.spinner_excercise);
        etcounts = findViewById(R.id.et_counts);
        btnSave = findViewById(R.id.btn_save);
    }
}