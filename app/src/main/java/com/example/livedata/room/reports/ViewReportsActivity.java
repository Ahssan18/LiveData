package com.example.livedata.room.reports;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.livedata.R;
import com.example.livedata.room.addExcercise.AddExcerciseActivity;
import com.example.livedata.room.addExcercise.Excercise;
import com.example.livedata.room.addExcercise.ExcerciseViewModel;
import com.example.livedata.room.database.MyDataBase;
import com.example.livedata.room.database.PersonDatabase;
import com.example.livedata.room.helper.Helper;
import com.example.livedata.room.login.LoginActivity;
import com.example.livedata.room.reports.adapter.ReportAdapter;
import com.example.livedata.room.workout.WorkOut;
import com.example.livedata.room.workout.WorkoutActivity;
import com.example.livedata.room.workout.WorkoutViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ViewReportsActivity extends AppCompatActivity {

    Spinner spinner;
    Button btnReport;
    TextView tvBurnCal;
    RecyclerView recyclerView;
    public static String TAG = "ViewReportsActivity";
    BottomNavigationView bottomNavigationView;
    List<WorkOut> workOutsList;
    List<Excercise> exerciseList;
    WorkoutViewModel workoutViewModel;
    ExcerciseViewModel excerciseViewModel;

    int totalCalories = 0;

    @Override
    protected void onResume() {
        super.onResume();
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateformat = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss aa");
        String datetime = dateformat.format(c.getTime());
        Log.e(TAG, "date --" + datetime);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reports);
        initWidget();
        setAdapter();
        clickListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.report_menu, menu);
        return true;
    }

    private void clickListener() {
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.add_workout:
                        startActivity(new Intent(ViewReportsActivity.this, WorkoutActivity.class));
                        break;
                    case R.id.add_excercise:
                        startActivity(new Intent(ViewReportsActivity.this, AddExcerciseActivity.class));
                        break;

                }
                return true;
            }
        });
        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalCalories = 0;
                String date = null, startdate = null;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String day = (String) spinner.getSelectedItem();
                date = sdf.format(new Date());
                if (day.equalsIgnoreCase("Today")) {
                    date = sdf.format(new Date());
                    startdate = date;
                } else if (day.equalsIgnoreCase("7 Days")) {
                    date = sdf.format(new Date());
                    startdate = getPreviousDate(7);
                } else {
                    startdate = getPreviousDate(3);
                }
                Log.e(TAG, "start date is" + startdate);

                workoutViewModel.getWorkout(Helper.getLogin(ViewReportsActivity.this).getId(), date, startdate).observe(ViewReportsActivity.this, new Observer<List<WorkOut>>() {
                    @Override
                    public void onChanged(List<WorkOut> workOuts) {
                        workOutsList = workOuts;
                    }
                });
                excerciseViewModel.getAllExcercises().observe(ViewReportsActivity.this, new Observer<List<Excercise>>() {
                    @Override
                    public void onChanged(List<Excercise> excercises) {
                        exerciseList = excercises;
                    }
                });
                for (WorkOut workOut : workOutsList) {
                    for (Excercise excercise : exerciseList) {
                        if ((workOut.getExcer_id() == excercise.getId())) {
                            int calories = 0;
                            workOut.setExerciseName(excercise.getName());
                            calories = Integer.parseInt(excercise.getCalBurn()) * workOut.getSteps();
                            totalCalories = totalCalories + calories;
                        }
                    }
                }
                tvBurnCal.setText("you burn total calories " + totalCalories);
                setRecycleAdapter();


            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String data = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setRecycleAdapter() {
        ReportAdapter adapter = new ReportAdapter(workOutsList, ViewReportsActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private String getPreviousDate(int i) {
        Calendar cal = Calendar.getInstance();
        //subtracting a day
        cal.add(Calendar.DATE, -i);
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        String result = s.format(new Date(cal.getTimeInMillis()));
        return result;
    }

    private void setAdapter() {
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item);
        List<String> list = new ArrayList<>();
        list.add("Today");
        list.add("3 Days");
        list.add("7 Days");
        arrayAdapter.addAll(list);
        spinner.setAdapter(arrayAdapter);
    }

    private void initWidget() {
        exerciseList = new ArrayList<>();
        workOutsList=new ArrayList<>();
        excerciseViewModel = new ViewModelProvider(this).get(ExcerciseViewModel.class);
        workoutViewModel = new ViewModelProvider(this).get(WorkoutViewModel.class);
        bottomNavigationView = findViewById(R.id.tv_botton_navigation);
        tvBurnCal = findViewById(R.id.tv_burn_calories);
        spinner = findViewById(R.id.spinner_report);
        btnReport = findViewById(R.id.btn_report);
        recyclerView = findViewById(R.id.recycle);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tv_logout:
                Helper.Logout(this);
                startActivity(new Intent(ViewReportsActivity.this, LoginActivity.class));
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}