package com.example.livedata;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends AndroidViewModel {
    public MutableLiveData<Integer> getData() {
        return data;
    }

    public void setData(MutableLiveData<Integer> data) {
        this.data = data;
    }

    MutableLiveData<Integer> data=new MutableLiveData<>();

    public MyViewModel(@NonNull Application application) {
        super(application);
    }


}
