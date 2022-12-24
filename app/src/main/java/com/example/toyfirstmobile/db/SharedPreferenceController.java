package com.example.toyfirstmobile.db;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceController {

    private static final String preferenceFileKey = "SharedPrefDB";

    public static void setCurrentCategory(Context context, String category){
        SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceFileKey, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("current_category", category);
        editor.apply();
    }

    public static String getCurrentCategory(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceFileKey, Context.MODE_PRIVATE);
        return sharedPreferences.getString("current_category", null);
    }


}
