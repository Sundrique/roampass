package com.sandrovsky.roampass;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author asandrovsky@gmail.com
 */
public class Settings {

    SharedPreferences preferences;

    public Settings(Context context) {
        preferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }

    public boolean isOn() {
        return preferences.getBoolean("on", true);
    }

    public void on() {
        preferences.edit().putBoolean("on", true).commit();
    }

    public void off() {
        preferences.edit().putBoolean("on", false).commit();
    }

    public void clear() {
        preferences.edit().clear().commit();
    }
}
