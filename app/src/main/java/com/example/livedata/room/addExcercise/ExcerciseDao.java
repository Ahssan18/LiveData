package com.example.livedata.room.addExcercise;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.livedata.room.addExcercise.Excercise;

import java.util.List;

@Dao
public interface ExcerciseDao {
    @Insert
     void insertCal(Excercise excercise);

    @Delete
     void deleteExcercise(Excercise excercise);

    @Query("SELECT * FROM Excercise")
    LiveData<List<Excercise>> getAllExcerciseList();

    @Query("SELECT * FROM Excercise")
    List<Excercise> getAllExcercise();

    @Update
     void updateExcercise(Excercise excercise);

}
