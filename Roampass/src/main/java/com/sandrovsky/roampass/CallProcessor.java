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
    private AbstractTrial trial;

    public CallProcessor(Settings settings, Operator operator, AbstractTrial trial) {
        this.settings = settings;
        this.operator = operator;
        this.trial = trial;
    }

    public String process(String number) {
        String processedNumber = null;
        if (!trial.isExpired() && settings.isOn() && operator.isRoaming() && operator.isSupported()) {
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
