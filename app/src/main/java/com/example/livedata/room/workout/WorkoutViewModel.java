package com.example.livedata.room.workout;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WorkoutViewModel extends AndroidViewModel {
    WorkoutRepository workoutRepository;

    public WorkoutViewModel(@NonNull Application application) {
        super(application);
        workoutRepository = new WorkoutRepository(application);
    }

    public void addWorkout(WorkOut workOut) {
        workoutRepository.addWorkout(workOut);
    }

    public LiveData<List<WorkOut>> getWorkout() {
        return workoutRepository.getWorkOut();
    }

    public LiveData<List<WorkOut>> getWorkout(int id, String date, String startdate) {
        return workoutRepository.getWorkOut(id,date,startdate);
    }
}
