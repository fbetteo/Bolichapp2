package com.dernysoftware.bolichapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.TextView;

import com.dernysoftware.bolichapp.Posts.Post;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import java.util.ArrayList;

public class MainMenu extends FragmentActivity {

    public static final String APP_ID = "201658760299309";
    public static final String SECRET = "83d068912542737c2f51f702ccbd28a4";
    public static final String IVAN_ID = "10157939063550366";
    public static final String ACCESS_TOKEN = "201658760299309|83d068912542737c2f51f702ccbd28a4";

    public static DBexterna dbBoliches; //Declaras objeto DBexterna

    public FragmentManager fm;
    public Fragment homeFragment;
    public Fragment bolichesFragment;
    public Fragment configFragment;
    public int amountOfBolichesActive;
    public int amountOfBolichesReady;
    ArrayList<Post> posts = new ArrayList<>();


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

        this.deleteDatabase("Bolichapp.db"); //Resetea la base de datos cada vez que abris la app, Para DEBUGEAR, QUITAR DE LA FINAL

        dbBoliches = new DBexterna(this);  //entiendo que aca le asignas la base de datos al objeto

        dbBoliches.populateArray(); //metodo que pone en un array los nombres de los boliches para ver que funcione

        amountOfBolichesActive = dbBoliches.getBolichesActive();

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


    public void jsonReady(ArrayList<Post> posts) {
        this.posts.addAll(posts);
        amountOfBolichesReady++;
        if(amountOfBolichesActive == amountOfBolichesReady){
            ((HomeFragment)homeFragment).showBoliches(this.posts);
        }

    }
}








