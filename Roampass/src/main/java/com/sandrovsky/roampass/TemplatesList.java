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
        put("25501", "*111*+N#");//MTS
        put("25503", "*105*N#");//Kyivstar
        put("25506", "*131*N#");//life:)
        //LV
        put("24701", "*100*+N#");//LMT
        //EE
        put("24801", "*111*+N#");//EMT
        //MT
        put("27801", "*121*00N#");//Vodafone
        //KZ
        put("40101", "*137*N#");//Beeline
        put("40177", "*130*N#");//Tele2
        //UZ
        put("43405", "*135*N#");//Ucell
        //RS
        put("22001", "*123*00N#");//Telenor
        put("22003", "*102*N#");//mt:s
        //ME
        put("29702", "*124*0N#");//T-Mobile
        put("29703", "*102*+N#");//m:tel
        put("29704", "*124*0N#");//T-Mobile (?)
        //BA
        put("21890", "*144*00N#");//BH Telecom
        //VN
        put("45201", "*131*N#");//MobiFone
        put("45204", "*138*N#");//Viettel Mobile
        //MY
        put("50212", "*120*N#");//Maxis
        put("50213", "*120*N#");//Celcom
        put("50216", "13300N");//DiGi
        put("50217", "*120*00N#");//Hotlink
        //KH
        put("45601", "*125*N#");//Cellcard
        //SG
        put("52503", "*138*N#");//M1
        //PS
        put("42506", "*121*00N#");//Wataniya
        //QA
        put("42701", "*101*+N#");//ooredoo
        //CN
        put("46000", "*115*001N#");//China Mobile
    }

    public Template getByOperatorId(String operatorId) {
        return new Template(get(operatorId));
    }

    public void add(String operatorId, String ussdTemplate) {
        put(operatorId, ussdTemplate);
    }
}
