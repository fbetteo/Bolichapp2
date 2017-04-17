package com.dernysoftware.bolichapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.List;

import static com.dernysoftware.bolichapp.MainMenu.dbBoliches;

/**
 * Created by Ivan on 13/04/2017.
 */

public class BolichesFragment extends Fragment{

    private RecyclerView mBolicheRecyclerView;
    private BolicheAdapter mAdapter;

    /*public ArrayAdapter adapter;
    View v;*/

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("resuming boliches fragment");
        mBolicheRecyclerView.setAdapter(mAdapter); //no deberia tener que hacer esto pero no carga el adapter por algun motivo
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_boliches_menu, container, false);

        mBolicheRecyclerView = (RecyclerView) view.findViewById(R.id.boliche_recycler_view);
        mBolicheRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


      /*  AppCompatEditText filter = (AppCompatEditText) view.findViewById(R.id.filter);
        filter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/
        /*setList();*/
        updateUI();
        return view;
    }

    private void updateUI(){
        List<Boliche> boliches  = dbBoliches.getBoliches();

            if (mAdapter == null){
            mAdapter = new BolicheAdapter(boliches);
            mBolicheRecyclerView.setAdapter(mAdapter);
            System.out.println("BaseNueva");
             } else {
            mAdapter.notifyDataSetChanged();
            System.out.println("nofitfyDataSetChanged");
       }


    }





    private class BolicheHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {
        //ViewHolder para item

        private TextView mNameTextView;
        private TextView mAddressTextView;
        private CheckBox mActiveCheckBox;
        private Boliche mBoliche;

        public BolicheHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mNameTextView = (TextView) itemView.findViewById(R.id.list_item_boliche_name_text_view);
            mAddressTextView = (TextView) itemView.findViewById(R.id.list_item_boliche_address_text_view);
            mActiveCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_boliche_active_check_box);

        }

        public void bindBoliche(Boliche boliche) {
            mBoliche = boliche;
            mNameTextView.setText(mBoliche.getName());
            mAddressTextView.setText(mBoliche.getAddress());
            mActiveCheckBox.setChecked(mBoliche.isActive());

            mActiveCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    //Set the crime's solved property
                    mBoliche.setActive(isChecked);
                }
            });

        }



        @Override
        public void onClick(View v) {

        }
    }

        private class BolicheAdapter extends  RecyclerView.Adapter<BolicheHolder>{  //Adapter para los viewHolder

            private  List<Boliche> mBoliches;

            public BolicheAdapter(List<Boliche> boliches) { mBoliches = boliches ; }

            @Override
            public BolicheHolder onCreateViewHolder(ViewGroup parent, int viewType){
                LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                View view = layoutInflater.inflate(R.layout.list_item_boliche, parent, false);

                return new BolicheHolder(view);
            }

            @Override
            public void onBindViewHolder(BolicheHolder holder, int position){
                Boliche boliche = mBoliches.get(position);
                holder.bindBoliche(boliche);
            }

            @Override
            public int getItemCount() {return mBoliches.size();}
        }


         }



















/*
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


} */
