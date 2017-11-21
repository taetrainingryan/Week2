package com.example.ryan.firstassignment.Realm;

import io.realm.RealmObject;

/**
 * Created by Ryan on 19/11/2017.
 */

public class RealmLogin extends RealmObject {

    String email, password;

    public RealmLogin() {

    }

    public RealmLogin(String email, String password){

        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
