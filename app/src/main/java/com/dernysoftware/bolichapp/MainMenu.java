package com.dernysoftware.bolichapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import java.util.ArrayList;

public class MainMenu extends BaseActivity {

    private LinearLayout ll;
    private static MainMenu context;

    TextView myText;
    DBexterna dbBoliches; //Declaras objeto DBexterna


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        printFruta();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplication());
        //bolicheManager = BolicheManager.getInstance();
        ll = (LinearLayout) findViewById(R.id.scrollLayout);




        dbBoliches = new DBexterna(this);  //entiendo que aca le asignas la base de datos al objeto

       // myText = (TextView) findViewById(R.id.myText);

        dbBoliches.populateArray(); //metodo que pone en un array los nombres de los boliches para ver que funcione

        dbBoliches.getBoliches(); //te deuvleve el objetos y tenes que printearlo como string

        printArrayPrevio();  //Funciona y te tira los nombres de los dos boliches de la db leidos del array de populateArray

        }

    public void printFruta(){
        System.out.println("FRUTAAAA");
    }

   public void printArrayPrevio() {
        System.out.println("loop de arrayprevio");
        for (Boliche boliche : dbBoliches.bolichesArray) {
            System.out.println(boliche.getName());

        }
    }


 }








