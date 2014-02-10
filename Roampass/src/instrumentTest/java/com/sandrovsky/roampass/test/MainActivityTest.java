package com.sandrovsky.roampass.test;

import android.annotation.TargetApi;
import android.os.Build;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.TextView;

import com.sandrovsky.roampass.MainActivity;
import com.sandrovsky.roampass.R;
import com.sandrovsky.roampass.Roampass;
import com.sandrovsky.roampass.Settings;
import com.sandrovsky.roampass.Trial;

import org.jraf.android.backport.switchwidget.Switch;

import static android.test.ViewAsserts.assertOnScreen;

/**
 * @author asandrovsky@gmail.com
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private MainActivity activity;
    private Switch switcher;
    private Settings settings;
    private TextView help;
    private Trial trial;

    @TargetApi(Build.VERSION_CODES.FROYO)
    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        activity = getActivity();
        switcher = (Switch) activity.findViewById(R.id.switcher);
        help = (TextView) activity.findViewById(R.id.help);
        settings = new Settings(activity);

        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        settings.clear();

        super.tearDown();
    }

    public void testSwitcherExists() {
        assertOnScreen(activity.getWindow().getDecorView(), switcher);
    }

    public void testHelpExists() {
        assertOnScreen(activity.getWindow().getDecorView(), help);
    }

    public void testCheckedByDefault() {
        assertTrue(switcher.isChecked());
    }

    public void testHelpNotEmpty() {
        assertTrue(help.getText().length() > 0);
    }

    public void testSettingsAffectSwitch() {
        settings.off();

        activity.finish();
        setActivity(null);
        activity = getActivity();

        switcher = (Switch) activity.findViewById(R.id.switcher);
        assertFalse(switcher.isChecked());
    }

    @UiThreadTest
    public void testSwitchChangeCauseSettingsChange() {
        switcher.performClick();
        assertFalse(switcher.isChecked());
        assertFalse(settings.isOn());
    }

    @UiThreadTest
    public void testSwitchChangeCauseTextChange() {
        assertEquals(help.getText(), activity.getResources().getText(R.string.description_on));
        switcher.performClick();
        assertEquals(help.getText(), activity.getResources().getText(R.string.description_off));
    }

    public void testAlertIsShowing() {
        Roampass application = (Roampass) getInstrumentation().getTargetContext().getApplicationContext();
        application.setTrial(new MockTrial(activity, true));

        activity.finish();
        setActivity(null);
        activity = getActivity();

        assertTrue(activity.getDialog().isShowing());
    }

    public void testAlertIsNotShowing() {
        Roampass application = (Roampass) getInstrumentation().getTargetContext().getApplicationContext();
        application.setTrial(new MockTrial(activity, false));

        activity.finish();
        setActivity(null);
        activity = getActivity();

        assertFalse(activity.getDialog().isShowing());
    }
}
