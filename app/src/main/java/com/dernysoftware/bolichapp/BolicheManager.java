package com.dernysoftware.bolichapp;

import java.util.ArrayList;

/**
 * Created by Ivan on 31/12/2016.
 */

public class BolicheManager {
    private ArrayList<Boliche> boliches = new ArrayList<Boliche>();

    public void populate(){
        boliches.add(new Boliche("Boliche A", "Dirección A", "A", Boliche.Location.CAPITAL_FEDERAL));
        boliches.add(new Boliche("Boliche A", "Dirección A", "B", Boliche.Location.BUENOS_AIRES));
    }

    public ArrayList<Boliche> getBoliches() {
        return boliches;
    }

    public void setBoliches(ArrayList<Boliche> boliches) {
        this.boliches = boliches;
    }

}
