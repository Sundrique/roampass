package com.sandrovsky.roampass.test;

import android.test.InstrumentationTestCase;

import com.sandrovsky.roampass.Settings;

/**
 * @author asandrovsky@gmail.com
 */
public class SettingsTest extends InstrumentationTestCase {
    Settings settings;

    public void setUp() throws Exception {
        super.setUp();

        settings = new Settings(getInstrumentation().getTargetContext());
    }

    public void testIsOnByDefault() throws Exception {
        assertTrue(settings.isOn());
    }


    public void testOff() throws Exception {
        settings.off();
        assertFalse(settings.isOn());
    }

    public void testOn() throws Exception {
        settings.on();
        assertTrue(settings.isOn());
    }

}
