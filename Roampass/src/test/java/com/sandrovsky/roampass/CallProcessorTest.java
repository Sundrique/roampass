package com.sandrovsky.roampass;

import android.content.Context;
import android.telephony.TelephonyManager;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * @author asandrovsky@gmail.com
 */
public class CallProcessorTest {
    TelephonyManager telephonyManager;
    CallProcessor callProcessor;
    TemplatesList templatesList;
    MockOperator operator;
    MockSettings settings;

    @Before
    public void setUp() {
        telephonyManager = mock(TelephonyManager.class);

        templatesList = new TemplatesList();
        templatesList.clear();
        templatesList.add("10001", "*000*N#");

        operator = new MockOperator(telephonyManager, templatesList);

        settings = new MockSettings(mock(Context.class));

        callProcessor = new CallProcessor(settings, operator);
    }

    @Test
    public void shouldReturnNullIfIsOff() throws Exception {
        operator.setRoaming(true);
        operator.setSupported(true);
        settings.off();

        assertEquals(null, callProcessor.process("+79123456789"));
    }

    @Test
    public void shouldReturnNullIfIsNotRoaming() throws Exception {
        operator.setRoaming(false);
        operator.setSupported(true);
        settings.on();

        assertEquals(null, callProcessor.process("+79123456789"));
    }

    @Test
    public void shouldReturnNullIfOperatorNotSupported() {
        operator.setRoaming(true);
        operator.setSupported(false);
        settings.on();

        assertEquals(null, callProcessor.process("+79123456789"));
    }

    @Test
    public void shouldReturnUssd() throws Exception {
        operator.setRoaming(true);
        operator.setSupported(true);
        settings.on();

        assertEquals("*000*79123456789#", callProcessor.process("89123456789"));
    }
}
