package com.sandrovsky.roampass;

import android.content.Context;

import com.sandrovsky.roampass.Settings;

/**
 * @author asandrovsky@gmail.com
 */
public class MockSettings extends Settings {
    private boolean isOn = true;

    public MockSettings(Context context) {
        super(context);
    }

    public boolean isOn() {
        return isOn;
    }

    public void on() {
        isOn = true;
    }

    public void off() {
        isOn = false;
    }

    public void clear() {
        isOn = true;
    }
}
