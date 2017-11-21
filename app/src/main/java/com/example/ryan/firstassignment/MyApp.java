package com.example.ryan.firstassignment;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Base64;

import java.security.SecureRandom;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Ryan on 17/11/2017.
 */

public class MyApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        /*
        3 steps
         */

        SharedPreferences pref = getApplicationContext().getSharedPreferences("storedKey", 0);
        SharedPreferences.Editor editor = pref.edit();

        byte[] key = new byte[64];

        if (pref.getString("key_name", null) == null){
            new SecureRandom().nextBytes(key);
            editor.putString("key_name", Base64.encodeToString(key, Base64.DEFAULT));
            editor.commit();
        }

        else{
            key = Base64.decode(pref.getString("key_name", null), Base64.DEFAULT);
        }

        Realm.init(getApplicationContext());

        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .encryptionKey(key)
                .build();

        Realm.setDefaultConfiguration(configuration);

    }
}
