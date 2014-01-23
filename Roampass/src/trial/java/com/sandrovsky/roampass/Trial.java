package com.sandrovsky.roampass;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * @author asandrovsky@gmail.com
 */
public class Trial extends AbstractTrial {

    public Trial(Context context) {
        super(context);
    }

    @Override
    public boolean isExpired() {
        PackageManager packageManager = context.getPackageManager();
        try {
            long firstInstallTime = packageManager.getPackageInfo(context.getPackageName(), 0).firstInstallTime;
            long currentTime = System.currentTimeMillis();
            return (currentTime - firstInstallTime) > (10 * 24 * 60 * 60 * 1000); // 10 days
        } catch (Exception e) {
            return true;
        }
    }
}
