package com.dernysoftware.bolichapp;


import org.json.JSONObject;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Created by Ivan on 31/12/2016.
 */

public class Boliche {

    private String name;
    private String address;
    private boolean active;
    private String Id;
    private JSONObject feedJson;
    private JSONObject eventsJson;

    public enum Location{
        CAPITAL_FEDERAL,
        BUENOS_AIRES
    }

    public Boliche (String name, String address, String Id, Location location, boolean active){
        this.name = name;
        this.address = address;
        this.Id = Id;
        this.active = active;
        if(active){
            fetchInfo();
        }
    }

    public Boliche (String name, String address, String Id, Location location){
        this.name = name;
        this.address = address;
        this.Id = Id;
        this.active = true;
        if(active){
            fetchInfo();
            System.out.println("fetching");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
        System.out.println(name + " set to " + active);
    }

    public String getId() {
        return Id;
    }

    public void setId(String facebookPage) {
        this.Id = facebookPage;
    }

    public void fetchInfo(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String now = df.format(new Date());
        System.out.println(now);

        Id = "17481793457"; //borrar cunado este la base bien

        AccessToken token = new AccessToken(MainMenu.ACCESS_TOKEN, MainMenu.APP_ID, MainMenu.IVAN_ID,null,null,null,null,null);
        new GraphRequest(token,
                "/" +  Id + "/events" + "?since=" + now,
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        try{
                            eventsJson = response.getJSONObject();
                            System.out.println("facebook response: " + response.getJSONObject().toString());
                        }catch(Exception e){
                            System.out.println("COULDN'T MANAGE JSON OBJECT: ");
                            e.printStackTrace();
                        }
                    }
                }
        ).executeAsync();


        Bundle params = new Bundle();
        params.putString("fields", "id,message,created_time");

        new GraphRequest(token,
                "/" +  Id + "/feed" + "?since=" + now,
                params,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        try{
                            feedJson = response.getJSONObject();
                            System.out.println("facebook response: " + response.getJSONObject().toString());
                        }catch(Exception e){
                            System.out.println("COULDN'T MANAGE JSON OBJECT: ");
                            e.printStackTrace();
                        }
                    }
                }
        ).executeAsync();

    }

    @Override
    public String toString(){
        return name;
    }

}