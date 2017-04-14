package com.dernysoftware.bolichapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

public class MainMenu extends FragmentActivity {

    public static final String APP_ID = "201658760299309";
    public static final String SECRET = "83d068912542737c2f51f702ccbd28a4";
    public static final String IVAN_ID = "10157939063550366";
    public static final String ACCESS_TOKEN = "201658760299309|83d068912542737c2f51f702ccbd28a4";
    TextView myText;

    public static DBexterna dbBoliches; //Declaras objeto DBexterna

    public FragmentManager fm;
    public Fragment homeFragment;
    public Fragment bolichesFragment;
    public Fragment configFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        System.out.println("MainMenu created");

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplication());

        homeFragment = new HomeFragment();
        bolichesFragment = new BolichesFragment();
        configFragment = new ConfigFragment();

        fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.fragmentLayout, homeFragment).commit();

        dbBoliches = new DBexterna(this);  //entiendo que aca le asignas la base de datos al objeto

        dbBoliches.populateArray(); //metodo que pone en un array los nombres de los boliches para ver que funcione

        dbBoliches.getBoliches(); //te devuelve el objeto y tenes que printearlo como string

        printArrayPrevio();  //Funciona y te tira los nombres de los dos boliches de la db leidos del array de populateArray

        }

   public void printArrayPrevio() {
        System.out.println("loop de arrayprevio");
        for (Boliche boliche : dbBoliches.bolichesArray) {
            System.out.println(boliche.getName());
            System.out.println(boliche.getId());
        }
    }

    public void homeButton(View v){
        System.out.println("swapping fragment");
        fm.beginTransaction().replace(R.id.fragmentLayout, homeFragment).commit();
    }

    public void bolichesButton(View v){
        System.out.println("swapping fragment");
        fm.beginTransaction().replace(R.id.fragmentLayout, bolichesFragment).commit();
    }

    public void configButton(View v){
        System.out.println("swapping fragment");
        fm.beginTransaction().replace(R.id.fragmentLayout, configFragment).commit();
    }



}








