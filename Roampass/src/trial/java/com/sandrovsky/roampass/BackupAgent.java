package com.sandrovsky.roampass;

import android.app.backup.BackupAgentHelper;
import android.app.backup.SharedPreferencesBackupHelper;

/**
 * @author asandrovsky@gmail.com
 */
public class BackupAgent extends BackupAgentHelper {
    static final String TRIAL_PREFS_BACKUP_KEY = "trial";

    public void onCreate() {
        SharedPreferencesBackupHelper helper = new SharedPreferencesBackupHelper(this, Trial.PREFS_TRIAL);
        addHelper(TRIAL_PREFS_BACKUP_KEY, helper);
    }
}