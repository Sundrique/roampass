package com.sandrovsky.roampass;

/**
 * @author asandrovsky@gmail.com
 */
public class Template {
    private String template;

    public Template(String template) {
        this.template = template;
    }

    public String process(String number) {
        return this.template.replace("N", number);
    }

    public String getTemplate() {
        return this.template;
    }

    @Override
    public boolean equals(Object object){
        return template == ((Template) object).getTemplate();
    }
}
