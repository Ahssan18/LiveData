package com.example.livedata.room.database;

import android.content.Context;

import androidx.room.Room;

public abstract class MyDataBase{
    public static PersonDatabase Instance;
    public static String DB_NAME="person";
    public static synchronized PersonDatabase getInstance(Context context)
    {
        if(Instance==null)
        {
           Instance=Room.databaseBuilder(context,
                   PersonDatabase.class,
                   DB_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return Instance;
    }

}
