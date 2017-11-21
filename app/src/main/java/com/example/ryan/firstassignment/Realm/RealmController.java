package com.example.ryan.firstassignment.Realm;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Ryan on 17/11/2017.
 */

public class RealmController {

    private static RealmController instance = null;

    public static RealmController getInstance(){

        synchronized (RealmController.class){

            if(instance == null){

                synchronized (RealmController.class){

                    instance = new RealmController(Realm.getDefaultInstance());

                }
            }
        }

        return instance;
    }

    private Realm realm;
    private Realm realm1;

    public RealmController(Realm realm) {

        this.realm = realm;
    }

    public void saveCustomer(final RealmBackend realmBackend){

        realm.executeTransaction(new Realm.Transaction() {

            @Override
            public void execute(Realm realm) {

                realm.copyToRealm(realmBackend);

            }
        });
    }

    /*
    Returns list of all customers.
     */

    public ArrayList<RealmBackend> getCustomerList(){

        ArrayList<RealmBackend> customers = new ArrayList<>();

        RealmResults<RealmBackend> realmBackendRealmResults = realm.where(RealmBackend.class).findAll();

        for(RealmBackend realmBackend: realmBackendRealmResults){
            customers.add(realmBackend);
        }

        return customers;

    }

    public void saveLogin (final RealmLogin realmLogin){

        realm.executeTransaction(new Realm.Transaction() {

            @Override
            public void execute(Realm realm) {

                realm.copyToRealm(realmLogin);

            }
        });


    }

    public ArrayList<RealmLogin> getLoginList(){

        ArrayList<RealmLogin> logins = new ArrayList<>();

        RealmResults<RealmLogin> realmBackendRealmResults = realm.where(RealmLogin.class).findAll();

        for(RealmLogin realmLogin: realmBackendRealmResults){
            logins.add(realmLogin);
        }

        return logins;

    }

}
