package com.example.livedata.room.login;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface PersonDao {
    @Insert
    void addRecord(Student s);

    @Delete
    void deleteRecord(Student s);

    @Query("SELECT * FROM STUDENT WHERE email=:email AND password =:password ")
    Student getRecord(String email,String password);

    @Update
    void upDateRecord(Student student);
}
