package com.example.livedata.room.workout;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "WorkOut")
public class WorkOut {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "p_id")
    int person_id;
    @ColumnInfo(name = "ex_name")
    int excer_id;
    @ColumnInfo(name = "steps")
    int steps;
    @ColumnInfo(name = "date_workout")
    String date;

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    String exerciseName;

    public WorkOut(int id, int person_id, int excer_id, int steps, String date) {
        this.id = id;
        this.person_id = person_id;
        this.excer_id = excer_id;
        this.steps = steps;
        this.date = date;
    }

    @Ignore
    public WorkOut(int person_id, int excer_id, int steps, String date) {
        this.person_id = person_id;
        this.excer_id = excer_id;
        this.steps = steps;
        this.date = date;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public int getExcer_id() {
        return excer_id;
    }

    public void setExcer_id(int excer_id) {
        this.excer_id = excer_id;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }
}
