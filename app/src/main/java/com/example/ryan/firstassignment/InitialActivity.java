package com.example.ryan.firstassignment;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class InitialActivity extends AppCompatActivity {

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        fragmentManager = getSupportFragmentManager();

        if(savedInstanceState==null){

            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, new MainActivity())
                    .commit();

        }
    }
}
