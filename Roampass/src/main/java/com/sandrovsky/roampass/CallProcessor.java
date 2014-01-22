package com.sandrovsky.roampass;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

/**
 * @author asandrovsky@gmail.com
 */
public class CallProcessor {
    private Operator operator;
    private Settings settings;

    public CallProcessor(Settings settings, Operator operator) {
        this.settings = settings;
        this.operator = operator;
    }

    public String process(String number) {
        String processedNumber = null;
        if (settings.isOn() && operator.isRoaming() && operator.isSupported()) {
            Template template = operator.getTemplate();
            processedNumber = template.process(normalize(number));
        }
        return processedNumber;
    }

    private String normalize(String number) {
        String normalizedNumber = number;
        try {
            final PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
            Phonenumber.PhoneNumber phoneNumber = phoneUtil.parse(number, operator.getCountry());
            normalizedNumber = Integer.toString(phoneNumber.getCountryCode()) + Long.toString(phoneNumber.getNationalNumber());
        } catch (NumberParseException e) {
            //
        }
        return normalizedNumber;
    }
}
