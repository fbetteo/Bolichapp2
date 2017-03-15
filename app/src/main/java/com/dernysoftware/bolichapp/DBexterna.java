package com.dernysoftware.bolichapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

/**
 * Created by Franco on 15/03/2017.
 */

public class DBexterna extends SQLiteAssetHelper{

    private static final String DATABASE_NAME = "Bolichapp.db";
    private static final int DATABASE_VERSION = 1;

    public DBexterna(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public ArrayList<Boliche> boliches2 = new ArrayList<Boliche>();
    private String name2;
    private String address2;
    private String facebookPage2;
    private Boliche.Location location2;

    public void populateArray() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + "boliches" + " WHERE 1";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("Nombre")) != null) {
                this.name2 = c.getString(c.getColumnIndex("Nombre"));
                this.address2 = c.getString(c.getColumnIndex("Direccion"));
                this.facebookPage2 = c.getString(c.getColumnIndex("Link_fb"));
                this.location2 = Boliche.Location.valueOf(c.getString(c.getColumnIndex("Provincia")));


                boliches2.add(new Boliche(name2, address2, facebookPage2, location2));

                // boliches.add(new Boliche("Boliche A", "Dirección A", "A", Boliche.Location.CAPITAL_FEDERAL));


            }
            c.moveToNext();
        }
        db.close();
    }
    public ArrayList<Boliche> getBoliches() {
        return boliches2;
    }

    public void setBoliches(ArrayList<Boliche> boliches2) {
        this.boliches2 = boliches2;
    }



}
