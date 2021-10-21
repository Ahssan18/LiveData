package com.example.livedata.room.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.livedata.room.login.Student;
import com.google.gson.Gson;

public class Helper {
    public static String Pref_name = "db";

    public static SharedPreferences getPref(Context context) {
        return context.getSharedPreferences(Pref_name, Context.MODE_PRIVATE);
    }

    public static void saveLogin(Student student, Context context) {
        Gson gson = new Gson();
        String login = gson.toJson(student);
        SharedPreferences preferences = getPref(context);
        preferences.edit().putString("login", login).apply();
    }

    public static Student getLogin(Context context) {
        SharedPreferences preferences = getPref(context);
        String data = preferences.getString("login", null);
        if (data != null) {
            Gson gson = new Gson();
            return gson.fromJson(data, Student.class);
        }
        return null;
    }

    public static void Logout(Context context) {
        SharedPreferences preferences = getPref(context);
        preferences.edit().remove("login").apply();
    }
}
