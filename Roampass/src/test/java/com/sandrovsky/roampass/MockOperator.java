package com.sandrovsky.roampass;

import android.telephony.TelephonyManager;

import com.sandrovsky.roampass.Template;

import java.lang.Override;

/**
 * @author asandrovsky@gmail.com
 */
public class MockOperator extends Operator {
    private boolean roaming;
    private boolean supported;

    public MockOperator(TelephonyManager telephonyManager, TemplatesList templatesList) {
        super(telephonyManager, templatesList);
    }

    public void setRoaming(boolean roaming) {
        this.roaming = roaming;
    }

    @Override
    public boolean isRoaming() {
        return roaming;
    }

    public void setSupported(boolean supported) {
        this.supported = supported;
    }

    @Override
    public boolean isSupported() {
        return supported;
    }

    @Override
    public String getCountry() {
        return "RU";
    }

    @Override
    public Template getTemplate() {
        return new Template("*000*N#");
    }
}
