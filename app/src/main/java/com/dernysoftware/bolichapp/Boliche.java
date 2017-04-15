package com.dernysoftware.bolichapp;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.Bundle;

import com.dernysoftware.bolichapp.Posts.EventPost;
import com.dernysoftware.bolichapp.Posts.FeedPost;
import com.dernysoftware.bolichapp.Posts.Post;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/**
 * Created by Ivan on 31/12/2016.
 */

public class Boliche {

    private String name;
    private String address;
    private boolean active;
    private String fb_link;
    private String Id;
    private String feedId;
    private JSONObject feedJson;
    private JSONObject eventsJson;
    private JSONArray eventDataJson;
    private JSONArray feedDataJson;
    private ArrayList<Post>  posts = new ArrayList<>();
    private MainMenu mainMenu;
    private boolean isEventJsonReady;
    private boolean isFeedJsonReady;

    public enum Location{
        CAPITAL_FEDERAL,
        BUENOS_AIRES
    }

    public Boliche (MainMenu mainMenu, String name, String address, String fb_link, Location location, String Id, String feedId, boolean active){
        this.name = name;
        this.address = address;
        this.Id = Id;
        this.feedId = feedId;
        this.active = active;
        if(active) {
            fetchInfo();
        }
        this.mainMenu = mainMenu;
    }

    public Boliche (MainMenu mainMenu, String name, String address, String Id, Location location){
        this.name = name;
        this.address = address;
        this.Id = Id;
        this.active = true;
        if(active){
            fetchInfo();
            System.out.println("fetching");
        }
        this.mainMenu = mainMenu;
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

       // Id = "17481793457"; //borrar cunado este la base bien
       // feedId ="17481793457_10154640142753458";

        AccessToken token = new AccessToken(MainMenu.ACCESS_TOKEN, MainMenu.APP_ID, MainMenu.IVAN_ID,null,null,null,null,null);
        new GraphRequest(token,
                "/" +  Id + "/events" + "?since=" + now,
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        try{
                            eventsJson = response.getJSONObject();
                            //System.out.println("facebook events response: " + response.getJSONObject().toString());
                            parseEventJson();
                        }catch(Exception e){
                            System.out.println("COULDN'T MANAGE JSON OBJECT: ");
                            e.printStackTrace();
                        }
                    }
                }
        ).executeAsync();


        Bundle params = new Bundle();
        params.putString("fields", "id,message,created_time");

        System.out.println(df.format(new Date(117,3,9)));

        new GraphRequest(token,
                "/" +  Id + "/feed" + "?since=" + df.format(new Date(117,3,9)),
                params,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        try{
                            feedJson = response.getJSONObject();
                            parseFeedJson();
                            //System.out.println("facebook feed response: " + response.getJSONObject().toString());
                        }catch(Exception e){
                            System.out.println("COULDN'T MANAGE JSON OBJECT: ");
                            e.printStackTrace();
                        }
                    }
                }
        ).executeAsync();

    }

    private void parseEventJson(){
        if(eventsJson != null){
            try {
                eventDataJson = eventsJson.getJSONArray("data");

                for(int i = 0; i < eventDataJson.length(); i++){
                    EventPost post = new EventPost();
                    post.setDescription(eventDataJson.getJSONObject(i).get("description").toString());
                    post.setName(eventDataJson.getJSONObject(i).get("name").toString());
                    post.setStart_time(eventDataJson.getJSONObject(i).get("start_time").toString());
                    posts.add(post);
                    isEventJsonReady = true;
                    if(isEventJsonReady && isFeedJsonReady){
                        mainMenu.jsonReady(posts);
                    }
                    //post.print();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void parseFeedJson(){
        if(feedJson != null){
            try {
                feedDataJson = feedJson.getJSONArray("data");

                for(int i = 0; i < feedDataJson.length(); i++){
                    if(feedDataJson.getJSONObject(i).get("id").equals(feedId)){
                        FeedPost post = new FeedPost();
                        post.setDescription(feedDataJson.getJSONObject(i).get("message").toString());
                        post.setCreated_time(feedDataJson.getJSONObject(i).get("created_time").toString());
                        posts.add(post);
                        isFeedJsonReady = true;
                        if(isEventJsonReady && isFeedJsonReady){
                            mainMenu.jsonReady(posts);
                        }
                        //post.print();
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public String toString(){
        return name;
    }

}