package com.sandrovsky.roampass.test;

import android.content.Context;

import com.sandrovsky.roampass.AbstractTrial;

/**
 * @author asandrovsky@gmail.com
 */
public class MockTrial extends AbstractTrial {

    private boolean expired = false;

    public MockTrial(Context context, boolean expired) {
        super(context);
        this.expired = expired;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }
}
