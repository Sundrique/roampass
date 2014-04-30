package com.sandrovsky.roampass;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author asandrovsky@gmail.com
 */
public class PlaceholderFragment extends Fragment {

    private TextView help;
    private Button share;

    public PlaceholderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        help = (TextView) rootView.findViewById(R.id.help);

        updateHelp();

        share = (Button) rootView.findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.share_text));
                sharingIntent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.share_link));
                Intent intent = Intent.createChooser(sharingIntent, getResources().getString(R.string.app_name));
                startActivity(intent);
            }
        });

        return rootView;
    }

    public void updateHelp() {
        Settings settings = new Settings(getActivity());

        if (settings.isOn()) {
            help.setText(R.string.description_on);
        } else {
            help.setText(R.string.description_off);
        }
    }
}
