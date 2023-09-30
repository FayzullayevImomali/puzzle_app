package com.example.quize_app_org;

import android.content.Context;
import android.content.SharedPreferences;

public class AppCache {

    private static AppCache cache;

    private static SharedPreferences preferences;
    private AppCache(Context context){
        preferences = context.getSharedPreferences("puzzle_15");
    };

    public static void init(Context context) {
        if(cache == null) {
            cache = new AppCache(context);

        }
    }

    public static   AppCache getObject() {
        return cache;
    }

}
