package com.dernysoftware.bolichapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.dernysoftware.bolichapp.Posts.Post;

import java.util.ArrayList;

import static com.dernysoftware.bolichapp.MainMenu.dbBoliches;

/**
 * Created by Ivan on 13/04/2017.
 */

public class HomeFragment extends Fragment implements View.OnClickListener {

    private View v;
    private ListView viewBoliches;
    public ArrayAdapter adapter;
    public ArrayList<Post> posts;


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

        viewBoliches = (ListView) v.findViewById(R.id.viewBoliches);

        if(posts != null){
            showBoliches(posts);
        }

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

    public void showBoliches(ArrayList<Post> posts){
        this.posts = posts;
        adapter = new ArrayAdapter<Post>(getActivity(),android.R.layout.simple_list_item_multiple_choice, posts);
        viewBoliches.setAdapter(adapter);
    }

    private void refresh(){

    }
}
