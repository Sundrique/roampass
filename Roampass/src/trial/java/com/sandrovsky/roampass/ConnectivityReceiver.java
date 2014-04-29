package com.sandrovsky.roampass;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * @author asandrovsky@gmail.com
 */
public class ConnectivityReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        CheckTask checkTask = new CheckTask(context);
        checkTask.execute();
    }
}
