package com.sandrovsky.roampass;

import android.telephony.TelephonyManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author asandrovsky@gmail.com
 */
public class OperatorTest {
    private TelephonyManager telephonyManager;
    private TemplatesList templatesList;
    private Operator operator;

    @Before
    public void setUp() {
        telephonyManager = mock(TelephonyManager.class);
        when(telephonyManager.getSimOperator()).thenReturn("10001");

        templatesList = new TemplatesList();
        templatesList.clear();

        operator = new Operator(telephonyManager, templatesList);
    }

    @Test
    public void testGetId() {
        assertEquals("10001", operator.getId());
    }

    @Test
    public void testIsSupported() {

        assertFalse(operator.isSupported());

        templatesList.add("10001", "*000*N#");

        assertTrue(operator.isSupported());
    }

    @Test
    public void shouldReturnFalseIfIsNotRoaming() throws Exception {

        when(telephonyManager.isNetworkRoaming()).thenReturn(false);

        assertFalse(operator.isRoaming());
    }

    @Test
    public void shouldReturnFalseIfCountriesMatch() {
        when(telephonyManager.isNetworkRoaming()).thenReturn(true);

        when(telephonyManager.getSimCountryIso()).thenReturn("AT");
        when(telephonyManager.getNetworkCountryIso()).thenReturn("AT");

        assertFalse(operator.isRoaming());
    }

    @Test
    public void shouldReturnTrue() {
        when(telephonyManager.isNetworkRoaming()).thenReturn(true);

        when(telephonyManager.getSimCountryIso()).thenReturn("RU");
        when(telephonyManager.getNetworkCountryIso()).thenReturn("AT");

        assertTrue(operator.isRoaming());
    }

    @Test
    public void shouldReturnNull() {
        templatesList.clear();

        assertNull(operator.getTemplate());
    }

    @Test
    public void shouldReturnTemplate() {
        templatesList.add("10001", "*000*N#");
        assertEquals((new Template("*000*N#")), operator.getTemplate());
    }
}
