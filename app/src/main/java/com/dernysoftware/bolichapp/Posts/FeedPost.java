package com.dernysoftware.bolichapp.Posts;

import static android.R.attr.name;

/**
 * Created by Ivan on 14/04/2017.
 */

public class FeedPost extends Post {
    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    private String created_time;

    public void print(){
        System.out.println("FEED posted at date: " + created_time + " " + description);
    }

}
