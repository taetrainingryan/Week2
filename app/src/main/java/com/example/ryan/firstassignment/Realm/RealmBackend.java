package com.example.ryan.firstassignment.Realm;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import io.realm.RealmObject;

/**
 * Created by Ryan on 17/11/2017.
 */

public class RealmBackend extends RealmObject {

    String name, username, age, locale, postalAddress, gender;
    byte[] image;


    public RealmBackend() {

    }


    public RealmBackend(String name, String username, String age, String locale,  String gender, String postalAddress, byte[] image) {
        this.name = name;
        this.username = username;
        this.age = age;
        this.gender = gender;
        this.locale = locale;
        this.postalAddress = postalAddress;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    public Bitmap getImage() {

        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);

        return bitmap;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
