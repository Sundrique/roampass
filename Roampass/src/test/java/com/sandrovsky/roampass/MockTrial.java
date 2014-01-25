package com.sandrovsky.roampass;

import android.content.Context;

/**
 * @author asandrovsky@gmail.com
 */
public class MockTrial extends AbstractTrial {
    private boolean expired;

    public MockTrial(Context context) {
        super(context);
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    @Override
    public boolean isExpired() {
        return expired;
    }
}
