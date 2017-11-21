package com.example.ryan.firstassignment;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ryan.firstassignment.fragments.Buttons;
import com.example.ryan.firstassignment.fragments.LoginFragment;
import com.example.ryan.firstassignment.fragments.MainFragment;

public class InitialActivity extends AppCompatActivity implements Buttons{

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        fragmentManager = getSupportFragmentManager();

        if(savedInstanceState==null){

            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, new MainFragment())
                    .commit();

        }
    }

    @Override
    public void buttonClick(View v) {

        //Toast.makeText(this, "a button has been clicked", Toast.LENGTH_SHORT).show();

        if (v.getId()==R.id.button){


//            Intent intent = new Intent(MainActivity.this, createaccount.class);
//            startActivity(intent);

        }

        else if (v.getId()== R.id.login){


            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();

            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, new LoginFragment())
                    .commit();
        }

//            Intent intent = new Intent(MainActivity.this, login.class);
//            startActivity(intent);
        }

    }

