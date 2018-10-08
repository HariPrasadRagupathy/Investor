package com.investor.data.shared;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * Created by DS on 3/14/2018.
 */

public class SessionManager {


    // Shared pref mode
    private static final int PRIVATE_MODE = 0;
    // Shared preferences file name
    private static final String PREF_NAME = "Investor";




    //String
    public static void saveSessionString(Context context, String keyName, String value)
    {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE).edit();
        editor.putString(keyName,value);
        editor.commit();
    }

    public static String getSessionString(Context context, String keyname, String defValue)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        return sharedPreferences.getString(keyname,defValue);

    }

    //Boolean
    public static void saveSessionBoolean(Context context, String keyName, Boolean value)
    {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE).edit();
        editor.putBoolean(keyName,value);
        editor.commit();
    }

    public static Boolean getSessionBoolean(Context context, String keyname, Boolean defValue)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        return sharedPreferences.getBoolean(keyname,defValue);

    }


    //Store Object
    public static void saveObjectToSharedPreference(Context context, String keyname, Object object) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
        final Gson gson = new Gson();
        String serializedObject = gson.toJson(object);
        sharedPreferencesEditor.putString(keyname, serializedObject);
        sharedPreferencesEditor.apply();
    }

    public static <GenericClass> GenericClass getSavedObjectFromPreference(Context context, String keyname, Class<GenericClass> classType) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        if (sharedPreferences.contains(keyname)) {
            final Gson gson = new Gson();
            return gson.fromJson(sharedPreferences.getString(keyname, ""), classType);
        }
        return null;
    }

    public static void clearSession(Context context)
    {
        SharedPreferences.Editor editor=context.getSharedPreferences(PREF_NAME,PRIVATE_MODE).edit();
        editor.clear();
        editor.commit();
    }

}
