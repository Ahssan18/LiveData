package com.example.livedata.room.workout;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.livedata.room.database.MyDataBase;
import com.example.livedata.room.database.PersonDatabase;

import java.util.List;

public class WorkoutRepository {
    Context context;
    PersonDatabase dataBase;
    WorkoutDao workoutDao;

    public WorkoutRepository(Context context) {
        this.context = context;
        dataBase = MyDataBase.getInstance(context);
        workoutDao = dataBase.workoutDao();
    }


    public LiveData<List<WorkOut>> getWorkOut() {
        return workoutDao.getWorkout();
    }

    public void addWorkout(WorkOut workOut) {
        workoutDao.insertExcercise(workOut);
    }

    public LiveData<List<WorkOut>> getWorkOut(int id, String date, String startdate) {
        return workoutDao.getWorkoutReport(id, date, startdate);
    }
}
