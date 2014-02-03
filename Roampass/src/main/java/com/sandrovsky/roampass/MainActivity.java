package com.sandrovsky.roampass;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;

import org.jraf.android.backport.switchwidget.Switch;

/**
 * @author asandrovsky@gmail.com
 */
public class MainActivity extends ActionBarActivity {

    private Settings settings;
    private PlaceholderFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment = new PlaceholderFragment();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, fragment)
                    .commit();
        }

        settings = new Settings(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Trial trial = new Trial(this);

        if (trial.isExpired()) {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.expired_title)
                    .setMessage(R.string.expired_description)
                    .setPositiveButton(R.string.expired_buy, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.sandrovsky.roampass"));
                            startActivity(intent);
                            MainActivity.this.finish();
                        }

                    })
                    .setNegativeButton(R.string.expired_quit, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.this.finish();
                        }

                    })
                    .setOnCancelListener(new DialogInterface.OnCancelListener() {

                        @Override
                        public void onCancel(DialogInterface dialog) {
                            MainActivity.this.finish();
                        }

                    })
                    .show();
        }
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
                fragment.updateHelp();
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
