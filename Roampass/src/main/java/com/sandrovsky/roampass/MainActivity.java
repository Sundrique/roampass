package com.sandrovsky.roampass;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.jraf.android.backport.switchwidget.Switch;

/**
 * @author asandrovsky@gmail.com
 */
public class MainActivity extends ActionBarActivity {

    private Settings settings;
    private TextView help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        settings = new Settings(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem switchItem = menu.findItem(R.id.action_switch);
        Switch switcher = (Switch) ((RelativeLayout) MenuItemCompat.getActionView(switchItem)).getChildAt(0);
        switcher.setChecked(settings.isOn());
        switcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    settings.on();
                } else {
                    settings.off();
                }
                updateHelp();
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    private class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            help = (TextView) rootView.findViewById(R.id.help);

            updateHelp();

            return rootView;
        }
    }

    private void updateHelp() {
        if (settings.isOn()) {
            help.setText(R.string.description_on);
        } else {
            help.setText(R.string.description_off);
        }
    }
}
