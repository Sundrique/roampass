package com.sandrovsky.roampass;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author asandrovsky@gmail.com
 */
public class PlaceholderFragment extends Fragment {

    private TextView help;

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

    public void updateHelp() {
        Settings settings = new Settings(getActivity());

        if (settings.isOn()) {
            help.setText(R.string.description_on);
        } else {
            help.setText(R.string.description_off);
        }
    }
}
