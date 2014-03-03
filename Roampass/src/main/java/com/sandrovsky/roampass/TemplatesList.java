package com.sandrovsky.roampass;

import java.util.HashMap;

/**
 * @author asandrovsky@gmail.com
 */
public class TemplatesList extends HashMap<String, String> {

    public TemplatesList() {
        super();

        //Android emulator
        put("310295", "*000*N#");
        //RU
        put("25001", "*137*N#");//MTS
        put("25005", "*147*N#");//ETK
        put("25012", "*117*N#");//Baykalwestcom
        put("25015", "*114*N#");//SMARTS
        put("25020", "*147*+N#");//Tele2
        //UA
        //put("25501", "*111*+N#");//MTS
        //put("25503", "*105*N#");//Kyivstar
        //put("25506", "*131*N#");//life:)
        //KZ
        //put("40101", "*137*N#");//Beeline
        //put("40177", "*130*N#");//Tele2
        //RS
        //put("22001", "*123*00N#");//Telenor
        //put("22003", "*102*N#");//mt:s
        //ME
        //put("29702", "*124*0N#");//T-Mobile
        //put("29703", "*102*+N#");//m:tel
        //put("29704", "*124*0N#");//T-Mobile (?)
    }

    public Template getByOperatorId(String operatorId) {
        return new Template(get(operatorId));
    }

    public void add(String operatorId, String ussdTemplate) {
        put(operatorId, ussdTemplate);
    }
}
