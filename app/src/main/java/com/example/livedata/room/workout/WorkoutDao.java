package com.example.livedata.room.workout;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WorkoutDao {

    @Insert
    public void insertExcercise(WorkOut workOut);

    @Delete
    public void deleteExcersice(WorkOut workOut);

    @Update
    public void updateExcercise(WorkOut workOut);

    @Query("SELECT * FROM WorkOut")
     LiveData<List<WorkOut>> getWorkout();

    @Query("SELECT * FROM WorkOut WHERE p_id=:id  AND ( date_workout  BETWEEN  :startDate AND :date )")
    public LiveData<List<WorkOut>> getWorkoutReport(Integer id, String date, String startDate);
}
