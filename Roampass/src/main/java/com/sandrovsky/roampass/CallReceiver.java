package com.sandrovsky.roampass;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.TelephonyManager;

/**
 * @author asandrovsky@gmail.com
 */
public class CallReceiver extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {
        final String number = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);

        SharedPreferences preferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);

        TemplatesList templatesList = new TemplatesList();
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        Operator operator = new Operator(telephonyManager, templatesList);

        Settings settings = new Settings(context);

        Trial trial = new Trial(context);

        CallProcessor callProcessor = new CallProcessor(settings, operator, trial);

        setResultData(callProcessor.process(number));
    }
}
