package com.example.livedata.room.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.livedata.room.login.PersonDao;
import com.example.livedata.room.login.Student;
import com.example.livedata.room.addExcercise.Excercise;
import com.example.livedata.room.addExcercise.ExcerciseDao;
import com.example.livedata.room.workout.WorkOut;
import com.example.livedata.room.workout.WorkoutDao;

@Database(entities = {Student.class, Excercise.class, WorkOut.class}, version = 6)
public abstract class PersonDatabase extends RoomDatabase {
    public abstract PersonDao personDao();

    public abstract WorkoutDao workoutDao();

    public abstract ExcerciseDao excerciseDao();

}
