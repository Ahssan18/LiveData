package com.example.livedata.room.addExcercise;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Excercise")
public class Excercise {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "name_excersize")
    String name;
    @ColumnInfo(name = "cal_burn")
    String calBurn;


    @Ignore
    public Excercise(String name, String calBurn) {
        this.name = name;
        this.calBurn = calBurn;
    }
    public Excercise(int id, String name, String calBurn) {
        this.id = id;
        this.name = name;
        this.calBurn = calBurn;
    }

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

    public String getCalBurn() {
        return calBurn;
    }

    public void setCalBurn(String calBurn) {
        this.calBurn = calBurn;
    }

    @Override
    public String toString() {
        return  getName();
    }
}
