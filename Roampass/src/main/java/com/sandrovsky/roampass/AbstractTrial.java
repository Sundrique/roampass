package com.sandrovsky.roampass;

import android.content.Context;

/**
 * @author asandrovsky@gmail.com
 */
public abstract class AbstractTrial {

    protected Context context;

    public AbstractTrial(Context context) {
        this.context = context;
    }

    public abstract boolean isExpired();
}
