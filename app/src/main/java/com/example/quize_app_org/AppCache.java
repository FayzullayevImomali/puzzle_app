package com.example.quize_app_org;

import android.content.Context;
import android.content.SharedPreferences;

public class AppCache {

    private static AppCache cache;

    private static SharedPreferences preferences;

    private static final String TIME_KEY = "time_key";
    private static final String STEP_KEY = "step_key";

    private static final String USER_KEY = "user_key";
    private AppCache(Context context){
        preferences = context.getSharedPreferences("puzzle_15",Context.MODE_PRIVATE);
    };

    public static void init(Context context) {
        if(cache == null) {
            cache = new AppCache(context);

        }
    }

    public static   AppCache getObject() {
        return cache;
    }

    public void saveTime(String time){
        preferences.edit().putString(TIME_KEY, time).apply();
    }

    public void saveStep(Integer stepCount) {
        preferences.edit().putInt(STEP_KEY,stepCount).apply();
    }

    public void saveUserName (String userName) {
        preferences.edit().putString(USER_KEY, userName).apply();
    }

    public String getTime() {
        return preferences.getString(TIME_KEY,"");
    }

    public Integer getStep() {
        return preferences.getInt(STEP_KEY,0);
    }

    public String getUserName() {return preferences.getString(USER_KEY, "");}
}
