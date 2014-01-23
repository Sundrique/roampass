package com.sandrovsky.roampass;

import android.content.Context;

/**
 * @author asandrovsky@gmail.com
 */
public class Trial extends AbstractTrial {

    public Trial(Context context) {
        super(context);
    }

    @Override
    public boolean isExpired() {
        return false;
    }
}
