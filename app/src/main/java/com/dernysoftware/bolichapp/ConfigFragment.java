package com.dernysoftware.bolichapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Ivan on 13/04/2017.
 */

public class ConfigFragment extends Fragment {

    private View v;

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("resuming config fragment");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_config_menu, container, false);
        return v;
    }

}
