package com.dernysoftware.bolichapp.Posts;

/**
 * Created by Ivan on 14/04/2017.
 */

public class EventPost extends Post {
    private String name;
    private String start_time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    @Override
    public String toString() {
        return "EVENT " + name + " at date: " + start_time + "  " + description;
    }

    public void print(){
        System.out.println("EVENT " + name + " at date: " + start_time + "  " + description);
    }

}
