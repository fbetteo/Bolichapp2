package com.dernysoftware.bolichapp;

/**
 * Created by Ivan on 31/12/2016.
 */

public class Boliche {

    private String name;
    private String address;
    private String facebookPage;
    private boolean active;

    public enum Location{
        CAPITAL_FEDERAL,
        BUENOS_AIRES
    }

    public Boliche (String name, String address, String facebookPage, Location location, boolean active){
        this.name = name;
        this.address = address;
        this.facebookPage = facebookPage;
        this.active = active;
    }

    public Boliche (String name, String address, String facebookPage, Location location){
        this.name = name;
        this.address = address;
        this.facebookPage = facebookPage;
        this.active = false;
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

    public String getFacebookPage() {
        return facebookPage;
    }

    public void setFacebookPage(String facebookPage) {
        this.facebookPage = facebookPage;
    }

    @Override
    public String toString(){
        return name;
    }

}