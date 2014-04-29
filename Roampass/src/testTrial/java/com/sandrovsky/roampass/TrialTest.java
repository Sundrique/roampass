package com.sandrovsky.roampass;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.sandrovsky.roampass.AbstractTrial;
import com.sandrovsky.roampass.Trial;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;

/**
 * @author asandrovsky@gmail.com
 */

public class TrialTest {

    private PackageInfo packageInfo;
    private Trial trial;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Before
    public void setUp() throws Exception {
        Context context = mock(Context.class);
        when(context.getPackageName()).thenReturn("com.sandrovsky.roampass");

        packageInfo = mock(PackageInfo.class);

        PackageManager packageManager = mock(PackageManager.class);
        when(packageManager.getPackageInfo(context.getPackageName(), 0)).thenReturn(packageInfo);

        when(context.getPackageManager()).thenReturn(packageManager);

        editor = mock(SharedPreferences.Editor.class);
        when(editor.putLong(anyString(), anyLong())).thenReturn(editor);

        preferences = mock(SharedPreferences.class);
        when(preferences.edit()).thenReturn(editor);

        when(context.getSharedPreferences(Trial.PREFS_TRIAL, Context.MODE_PRIVATE)).thenReturn(preferences);

        trial = spy(new Trial(context));
        doNothing().when(trial).executeCheck();
    }

    @Test
    public void shouldReturnFalse() {
        packageInfo.firstInstallTime = System.currentTimeMillis();

        assertFalse(trial.isExpired());
    }

    @Test
    public void shouldReturnTrue() {
        packageInfo.firstInstallTime = System.currentTimeMillis() - (10 * 24 * 60 * 60 * 1000 + 1); // minus 10 days and 1 millisecond

        assertTrue(trial.isExpired());
    }

    //TODO test stored install time
}
