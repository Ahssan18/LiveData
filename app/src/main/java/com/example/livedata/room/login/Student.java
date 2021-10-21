package com.example.livedata.room.login;

import android.os.Parcel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "student")
public class Student{
    @PrimaryKey(autoGenerate = true)
    int id;

    public Student(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Ignore
    public Student(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @ColumnInfo(name = "name")
    String name;

    @ColumnInfo(name = "email")
    String email;

    protected Student(Parcel in) {
        id = in.readInt();
        name = in.readString();
        email = in.readString();
        password = in.readString();
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ColumnInfo(name = "password")
    String password;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
