package com.example.livedata.room;

import android.app.Person;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PersonDao {
    @Insert
    void addRecord(Student s);

    @Delete
    void deleteRecord(Student s);

    @Query("SELECT * FROM STUDENT")
    List<Student> getRecord();

    @Update
    void upDateRecord(Student student);
}
