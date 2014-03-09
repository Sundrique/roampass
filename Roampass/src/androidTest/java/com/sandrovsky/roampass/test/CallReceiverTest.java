package com.sandrovsky.roampass.test;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.test.InstrumentationTestCase;

import com.sandrovsky.roampass.CallReceiver;

import java.util.List;

/**
 * @author asandrovsky@gmail.com
 */
public class CallReceiverTest extends InstrumentationTestCase {

    public void testReceiverRegistered() {
        Intent intent = new Intent("android.intent.action.NEW_OUTGOING_CALL");

        Context context = getInstrumentation().getTargetContext();

        List<ResolveInfo> list = context.getPackageManager().queryBroadcastReceivers(intent, 0);

        boolean receiverRegestered = false;

        for (ResolveInfo resolveInfo : list) {
            if (resolveInfo.activityInfo.name.equals(CallReceiver.class.getName())) {
                receiverRegestered = true;
                break;
            }
        }

        assertTrue(receiverRegestered);
    }
}
