package com.dernysoftware.bolichapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Created by Ivan on 13/04/2017.
 */

public class HomeFragment extends Fragment implements View.OnClickListener {

    private View v;

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("resuming home fragment");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_home_menu, container, false);

        ImageButton ib = (ImageButton) v.findViewById(R.id.refreshButton);
        ib.setOnClickListener(this);



        return v;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.refreshButton:
                refresh();
                break;
        }
    }

    private void refresh(){

    }
}
