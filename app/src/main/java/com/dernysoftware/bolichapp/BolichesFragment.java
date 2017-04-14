package com.dernysoftware.bolichapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import static com.dernysoftware.bolichapp.MainMenu.dbBoliches;

/**
 * Created by Ivan on 13/04/2017.
 */

public class BolichesFragment extends Fragment{

    public ArrayAdapter adapter;
    View v;

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("resuming boliches fragment");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_boliches_menu, container, false);
        setList();
        return v;
    }

    private void setList(){

        ListView listView = (ListView) v.findViewById(R.id.bolicheListView);

        System.out.println("listview created");

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


        adapter = new ArrayAdapter<Boliche>(getActivity(),android.R.layout.simple_list_item_multiple_choice, dbBoliches.getBoliches());

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new ListView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((Boliche) parent.getItemAtPosition(position)).setActive(!((Boliche) parent.getItemAtPosition(position)).isActive());
            }
        });

        AppCompatEditText filter = (AppCompatEditText) v.findViewById(R.id.filter);
        filter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

}
