package com.example.a500011d;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {
    private final static String sharedPrefFile = "mainsharedpref";
    private final static String USERNAME_KEY = "username";

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE);
    }

    public static String getUsernamePref(Context context) {
        return getPrefs(context).getString(USERNAME_KEY, "");
    }

    public static void setUsernamePref(Context context, String username) {
        getPrefs(context).edit().putString(USERNAME_KEY, username).apply();
    }
}
