package com.sandrovsky.roampass;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;

/**
 * @author asandrovsky@gmail.com
 */
public class CallReceiver extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {
        final String number = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);

        TemplatesList templatesList = new TemplatesList();
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        Operator operator = new Operator(telephonyManager, templatesList);

        Settings settings = new Settings(context);

        AbstractTrial trial = ((Roampass) context.getApplicationContext()).getTrial();

        CallProcessor callProcessor = new CallProcessor(settings, operator, trial);

        String resultNumber = callProcessor.process(number);

        if (resultNumber != null) {
            setResultData(resultNumber);
        }
    }
}
