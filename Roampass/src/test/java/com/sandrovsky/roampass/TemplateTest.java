package com.sandrovsky.roampass;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author asandrovsky@gmail.com
 */
public class TemplateTest {

    @Test
    public void shouldReturnUssd() {
        Template template = new Template("*000*N#");
        assertEquals("*000*79123456789#", template.process("79123456789"));
    }
}
