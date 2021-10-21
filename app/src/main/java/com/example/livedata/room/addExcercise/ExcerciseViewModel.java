package com.example.livedata.room.addExcercise;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.livedata.room.database.MyDataBase;

import java.util.List;

public class ExcerciseViewModel extends AndroidViewModel {
    ExcerciseRepository repository;
    private LiveData<List<Excercise>> excerciseList;
    private ExcerciseDao exceciseDao;

    public ExcerciseViewModel(@NonNull Application application) {
        super(application);
        repository = new ExcerciseRepository(application);
        exceciseDao = MyDataBase.getInstance(application).excerciseDao();
        excerciseList = exceciseDao.getAllExcerciseList();
    }

    public void addExcercise(Excercise excercise) {
        repository.addExcercise(excercise);
    }

    public LiveData<List<Excercise>> getAllExcercises() {
        return exceciseDao.getAllExcerciseList();
    }
}
