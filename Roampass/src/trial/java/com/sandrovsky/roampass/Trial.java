package com.sandrovsky.roampass;

import android.app.backup.BackupManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;

/**
 * @author asandrovsky@gmail.com
 */
public class Trial extends AbstractTrial {

    public static String PREFS_TRIAL = "trial";
    private static String FIRST_INSTALL_TIME = "first_install_time";

    private BackupManager backupManager;

    public Trial(Context context) {
        super(context);
    }

    @Override
    public boolean isExpired() {
        return (System.currentTimeMillis() - getFirstInstallTime()) > (10 * 24 * 60 * 60 * 1000); // 10 days
    }

    public long getFirstInstallTime() {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_TRIAL, Context.MODE_PRIVATE);
        long firstInstallTime = preferences.getLong(FIRST_INSTALL_TIME, 0);
        if (firstInstallTime == 0) {
            try {
                PackageManager packageManager = context.getPackageManager();
                firstInstallTime = packageManager.getPackageInfo(context.getPackageName(), 0).firstInstallTime;
            } catch (Exception e) {
                firstInstallTime =  System.currentTimeMillis();
            }
            preferences.edit().putLong(FIRST_INSTALL_TIME, firstInstallTime);
            getBackupManager().dataChanged();
        }

        return firstInstallTime;
    }

    public BackupManager getBackupManager() {
        if (backupManager == null) {
            backupManager = new BackupManager(context);
        }
        return backupManager;
    }

    public void setBackupManager(BackupManager backupManager) {
        this.backupManager = backupManager;
    }
}
