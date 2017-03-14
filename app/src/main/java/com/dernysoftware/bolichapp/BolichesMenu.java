package com.dernysoftware.bolichapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;


public class BolichesMenu extends BaseActivity {

    BolicheManager bolicheManager = new BolicheManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boliches_menu);
        bolicheManager.populate();
        setList();
    }

    private void setList(){

        ListView listView = (ListView)findViewById(R.id.bolicheListView);

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        ListAdapter adapter = new ArrayAdapter<Boliche>(this,android.R.layout.simple_list_item_multiple_choice, bolicheManager.getBoliches());

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new ListView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((Boliche) parent.getItemAtPosition(position)).setActive(!((Boliche) parent.getItemAtPosition(position)).isActive());
            }
        });
    }
}
