package com.sandrovsky.roampass;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;

/**
 * @author asandrovsky@gmail.com
 */
public class Trial extends AbstractTrial {

    public static String PREFS_TRIAL = "trial";
    public static String INSTALL_TIME = "installtime";
    public static String CHECK_TIME = "checktime";

    private static long TEN_DAYS = 10 * 24 * 60 * 60 * 1000;

    public Trial(Context context) {
        super(context);
    }

    @Override
    public boolean isExpired() {
        return (System.currentTimeMillis() - getFirstInstallTime()) > TEN_DAYS;
    }

    public long getFirstInstallTime() {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_TRIAL, Context.MODE_PRIVATE);
        long installTime = preferences.getLong(INSTALL_TIME, 0);
        if (installTime == 0) {
            executeCheck();
            try {
                PackageManager packageManager = context.getPackageManager();
                installTime = packageManager.getPackageInfo(context.getPackageName(), 0).firstInstallTime;
            } catch (Exception e) {
                installTime = System.currentTimeMillis();
            }
        }

        return installTime;
    }

    public void executeCheck() {
        CheckTask checkTask = new CheckTask(context);
        checkTask.execute();
    }
}
