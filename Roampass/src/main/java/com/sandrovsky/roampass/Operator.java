package com.sandrovsky.roampass;

import android.telephony.TelephonyManager;

/**
 * @author asandrovsky@gmail.com
 */
public class Operator {
    private TelephonyManager telephonyManager;
    private TemplatesList templatesList;

    public Operator(TelephonyManager telephonyManager, TemplatesList templatesList) {
        this.telephonyManager = telephonyManager;
        this.templatesList = templatesList;
    }

    public String getId() {
        return telephonyManager.getNetworkOperator();
    }

    public boolean isSupported() {
        return templatesList.containsKey(getId());
    }

    public boolean isRoaming() {
        return telephonyManager.isNetworkRoaming() &&
                (telephonyManager.getNetworkCountryIso() != telephonyManager.getSimCountryIso());
    }

    public String getCountry() {
        return telephonyManager.getSimCountryIso();
    }

    public Template getTemplate() {
        if (!isSupported()) {
            return null;
        }
        return templatesList.getByOperatorId(getId());
    }
}
