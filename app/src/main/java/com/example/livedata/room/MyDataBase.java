package com.example.livedata.room;

import android.content.Context;

import androidx.room.Room;

public abstract class MyDataBase{
    public static PersonData Instance;
    public static String DB_NAME="person";
    public static synchronized PersonData getInstance(Context context)
    {
        if(Instance==null)
        {
           Instance=Room.databaseBuilder(context,
                   PersonData.class,
                   DB_NAME).allowMainThreadQueries().build();
        }
        return Instance;
    }

}
