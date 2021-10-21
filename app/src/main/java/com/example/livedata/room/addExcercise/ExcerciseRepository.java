package com.example.livedata.room.addExcercise;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.livedata.room.database.MyDataBase;
import com.example.livedata.room.database.PersonDatabase;

import java.util.List;

public class ExcerciseRepository {
    private Context context;
    private PersonDatabase database;

    public ExcerciseRepository(Context context) {
        this.context = context;
        database = MyDataBase.getInstance(context);
    }

    public void addExcercise(Excercise excercise) {
        database.excerciseDao().insertCal(excercise);
    }

    public LiveData<List<Excercise>> getAllExcerciseList() {
      return  database.excerciseDao().getAllExcerciseList();
    }
}
