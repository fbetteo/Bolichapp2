package com.dernysoftware.bolichapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Ivan on 31/12/2016.
 */

public class BaseActivity extends AppCompatActivity {

    public static final String APP_ID = "201658760299309";
    public static final String SECRET = "83d068912542737c2f51f702ccbd28a4";
    public static final String IVAN_ID = "10157939063550366";
    public static final String ACCESS_TOKEN = "201658760299309|83d068912542737c2f51f702ccbd28a4";

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
