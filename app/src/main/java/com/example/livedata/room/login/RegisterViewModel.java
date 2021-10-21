package com.example.livedata.room.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.livedata.room.database.MyDataBase;

public class RegisterViewModel extends AndroidViewModel {
    PersonDao personDao;

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        personDao = MyDataBase.getInstance(application).personDao();
    }

    public void registerAccount(Student student) {
        personDao.addRecord(student);
    }
    public Student getLogin(String email,String password)
    {
       return personDao.getRecord(email,password);
    }
}
