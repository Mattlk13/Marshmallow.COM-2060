package com.teammarshmallow.eventapp.eventplanner.Firebase;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by theod on 13-Mar-17.
 */

@IgnoreExtraProperties
public class User {

    private String displayName;
    private String firstName;
    private String lastName;
    private String email;

    public User(){

    }

    public User(String displayName, String firstName, String lastName, String email) {
        this.displayName = displayName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
