package com.sandrovsky.roampass;

import android.app.Application;

/**
 * @author asandrovsky@gmail.com
 */
public class Roampass extends Application {

    private AbstractTrial trial;

    public AbstractTrial getTrial() {
        if (trial == null) {
            trial = new Trial(this);
        }

        return trial;
    }

    public void setTrial(AbstractTrial trial) {
        this.trial = trial;
    }
}
