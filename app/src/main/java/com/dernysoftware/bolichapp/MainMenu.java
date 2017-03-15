package com.dernysoftware.bolichapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainMenu extends BaseActivity {

    TextView myText;
    TextView myArray;
    DBbolichapp dbBoliches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        printFruta();
        myText = (TextView) findViewById(R.id.myText);
       //myArray = (TextView) findViewById(R.id.myArray);

        dbBoliches = new DBbolichapp(this, null, null, 1);

        //dbBoliches.truncates();

        dbBoliches.populate();

        dbBoliches.truncate();

        dbBoliches.populate();



        printDatabase();



        dbBoliches.populateArray();

        //printArray();

        dbBoliches.getBoliches();

        printArrayPrevio();



        //printArrayList();




           }

    public void printFruta(){
        System.out.println("FRUTAAAA");
    }

    public void printDatabase()
    {
        String dbstring =  dbBoliches.databaseToString();
        myText.setText(dbstring);
            }


   public void printArrayPrevio() {
        System.out.println("loop de arrayprevio");
        for (Boliche boliche : dbBoliches.boliches2) {
            System.out.println(boliche.getName());

        }
    }

   /* public void printArray() {
        System.out.println("loop de array");
        for (Boliche boliche : dbBoliches.boliches2) {
            myArray.setText(boliche.getName());
        }
    }*/

    /* public void printArrayList() {
        System.out.println(dbBoliches.getBoliches());
        } */



 }








