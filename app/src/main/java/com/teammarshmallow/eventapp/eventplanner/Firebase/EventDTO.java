package com.teammarshmallow.eventapp.eventplanner.Firebase;

import android.location.Location;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

/**
 * Created by theod on 13-Mar-17.
 */

@IgnoreExtraProperties
public class EventDTO {



    private String eventName;
    private Location location;
    private String description;

    private User owner;
    private ArrayList<User> participants;

    public EventDTO(){

    }

    public EventDTO(String eventName, Location location, String description, User owner, ArrayList<User> participants) {
        this.eventName = eventName;
        this.location = location;
        this.description = description;
        this.owner = owner;
        this.participants = participants;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public ArrayList<User> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<User> participants) {
        this.participants = participants;
    }
}
