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

    //set current user
    public static void setCurrentUser(Context context, String user){
        SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceFileKey, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("current_user", user);
        editor.apply();
    }

    public static String getCurrentCategory(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceFileKey, Context.MODE_PRIVATE);
        return sharedPreferences.getString("current_category", null);
    }


    //get current user
    public static String getCurrentUser(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceFileKey, Context.MODE_PRIVATE);
        return sharedPreferences.getString("current_user", null);

    public static void setCurrentToy(Context context, int id){
        SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceFileKey, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("current_toy_id", id);
        editor.apply();
    }

    public static int getCurrentToy(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceFileKey, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("current_toy_id", 0);

    }


}
