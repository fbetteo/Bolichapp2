package com.dernysoftware.bolichapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


import java.util.ArrayList;

/**
 * Created by Franco on 15/03/2017.
 */

public class DBexterna extends SQLiteAssetHelper{

    private static final String DATABASE_NAME = "Bolichapp.db"; //nombre de la base en main/src/assets
    private static final int DATABASE_VERSION = 1;
    private MainMenu mainMenu;

    public DBexterna(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mainMenu = (MainMenu)context;
    }


    public ArrayList<Boliche> bolichesArray = new ArrayList<Boliche>();
    private String name;
    private String address;
    private String facebookPage;
    private Boliche.Location location;
    private String id ;
    private String feedId;
    private boolean active;

    public void populateArray() {

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + "boliches" + " WHERE 1"; //boliches es nombre de la tabla, estas cosas deberian generalizarse con alguna variable

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("Nombre")) != null) {
                this.name = c.getString(c.getColumnIndex("Nombre"));
                this.address = c.getString(c.getColumnIndex("Direccion"));
                this.facebookPage = c.getString(c.getColumnIndex("Link_fb"));
                this.location = Boliche.Location.valueOf(c.getString(c.getColumnIndex("Provincia")));
                this.id = c.getString((c.getColumnIndex("Id_fb")));
                this.feedId = c.getString((c.getColumnIndex("feedId")));
                this.active = c.getInt((c.getColumnIndex("Activo"))) == 1;


                bolichesArray.add(new Boliche(mainMenu, name, address, facebookPage, location, id, feedId, active));


            }
            c.moveToNext();
        }
        db.close();
        c.close();
    }
    public ArrayList<Boliche> getBoliches() {
        return bolichesArray;
    }

    public void setBoliches(ArrayList<Boliche> boliches2) {
        this.bolichesArray = boliches2;
    }


    // esto es para manejar lo estatico pero chequearlo

    /* private static DBexterna bolicheManager;
    private DBexterna(){
    }

    public static BolicheManager getInstance(){
        if(bolicheManager == null) {
            bolicheManager = getSync();
        }
        return bolicheManager;
    }

    private static synchronized BolicheManager getSync(){
        if(bolicheManager == null) {
            bolicheManager = new BolicheManager();
        }
        return bolicheManager;
    } */



}
