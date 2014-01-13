package com.sandrovsky.roampass;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author asandrovsky@gmail.com
 */
public class TemplatesListTest {

    @Test
    public void testAddAndGetById() {
        TemplatesList templatesList = new TemplatesList();
        templatesList.clear();

        templatesList.add("10001", "*000*N#");
        assertEquals((new Template("*000*N#")), templatesList.getByOperatorId("10001"));
    }
}
