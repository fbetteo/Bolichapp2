package com.dernysoftware.bolichapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Ivan on 31/12/2016.
 */

public class BaseActivity extends AppCompatActivity {

    public void homeButton(View v){
        startActivity(new Intent(this,MainMenu.class).setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP));
    }

    public void bolichesButton(View v){
        startActivity(new Intent(this,BolichesMenu.class).setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP));
    }

    public void configButton(View v){
        startActivity(new Intent(this,ConfigMenu.class).setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP));
    }


}
