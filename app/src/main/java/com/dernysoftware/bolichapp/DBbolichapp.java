package com.dernysoftware.bolichapp;

/**
 * Created by Franco on 21/01/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DBbolichapp extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "boliches.db";
    public static final String TABLA_BOLICHES = "boliches";
    private static final String COLUMN_ID = "id_fb";
    private static final String COLUMN_NOMBRE = "boliche_nombre";
    private static final String COLUMN_DIRECCION = "boliche_direccion";
    private static final String COLUMN_PROVINCIA = "boliche_provincia";
    private static final String COLUMN_LINK_FB   = "boliche_link_fb";
    private static final String COLUMN_ACTIVO    = "boliche_activo";

    public DBbolichapp(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String query =  "CREATE TABLE " + TABLA_BOLICHES + " (" + COLUMN_ID + " INTEGER, " + COLUMN_NOMBRE + " TEXT, " +
                COLUMN_DIRECCION + " TEXT, " + COLUMN_PROVINCIA + " TEXT, " + COLUMN_LINK_FB + " TEXT, " + COLUMN_ACTIVO + " INTEGER );" ;
        db.execSQL(query);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLA_BOLICHES);
        onCreate(db);
    }

    public void populate(){ //cuando quise insertar toddo en un solo comando creo que rompio, dicen que depende el sqlite
                               // vamos uno por uno y a la mierda

        SQLiteDatabase db = getWritableDatabase(); // esto llama a la base de datos

       db.execSQL("INSERT INTO " + TABLA_BOLICHES + " (" + COLUMN_NOMBRE + ", " + COLUMN_DIRECCION + ", " + COLUMN_PROVINCIA + ", " + COLUMN_ACTIVO + ")" + " VALUES ("  //INSERT
       + "'BRANCH', 'calle falsa', 'CAPITAL_FEDERAL', 1 )"  );           // DATA A MANO
          //     ", ('boliche2','calle refalsa',1);"
        db.execSQL("INSERT INTO " + TABLA_BOLICHES + " (" + COLUMN_NOMBRE + ", " + COLUMN_DIRECCION + ", " + COLUMN_PROVINCIA + ", " + COLUMN_ACTIVO + ")" + " VALUES ("  //INSERT
                       + "'PEPPEXXXXX', 'calle REfalsa', 'BUENOS_AIRES', 1 )"
       );
    }

    public void truncate(){

        SQLiteDatabase db = getWritableDatabase(); // esto llama a la base de datos

        db.delete(TABLA_BOLICHES, null,null);
    }

    public String databaseToString()
    {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLA_BOLICHES + " WHERE 1";

        Cursor c= db.rawQuery(query, null);
        c.moveToFirst();

        while (!c.isAfterLast()){

            if(c.getString(c.getColumnIndex("boliche_nombre")) != null)
            {
                dbString+=c.getString(c.getColumnIndex("boliche_nombre"));
                dbString+=c.getString(c.getColumnIndex("boliche_direccion"));
                dbString+=c.getString(c.getColumnIndex("boliche_provincia"));
                dbString+=c.getString(c.getColumnIndex("boliche_activo"));
                dbString+= "\n";

            }
            c.moveToNext();
        }
        db.close();
        return dbString;



    }

    public ArrayList<Boliche> boliches2 = new ArrayList<Boliche>();
    private String name2;
    private String address2;
    private String facebookPage2;
    private Boliche.Location location2;

    public void populateArray() {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLA_BOLICHES + " WHERE 1";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("boliche_nombre")) != null) {
                this.name2 = c.getString(c.getColumnIndex("boliche_nombre"));
                this.address2 = c.getString(c.getColumnIndex("boliche_direccion"));
                this.facebookPage2 = c.getString(c.getColumnIndex("boliche_link_fb"));
                this.location2 = Boliche.Location.valueOf(c.getString(c.getColumnIndex("boliche_provincia")));


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
