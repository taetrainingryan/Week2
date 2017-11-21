package com.example.ryan.firstassignment;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ryan.firstassignment.fragments.CustomerDisplayFragment;
import com.example.ryan.firstassignment.fragments.DataPasser;
import com.example.ryan.firstassignment.fragments.CreateAccountFragment;
import com.example.ryan.firstassignment.fragments.LoginFragment;
import com.example.ryan.firstassignment.fragments.MainFragment;
import com.example.ryan.firstassignment.fragments.SignupFragment;

public class InitialActivity extends AppCompatActivity implements DataPasser {

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

        if (v.getId()==R.id.button){

            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, new CreateAccountFragment())
                    .addToBackStack("MainFragment")
                    .commit();
        }

        else if (v.getId()== R.id.login){


            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();

            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, new LoginFragment())
                    .addToBackStack("MainFragment")
                    .commit();
        }

        else if (v.getId()==R.id.nextButton){

            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, new CustomerDisplayFragment())
                    .addToBackStack("LoginFragment")
                    .commit();
        }

        else if (v.getId()==R.id.saveDetails){

            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, new SignupFragment())
                    .addToBackStack("CreateAccountFragment")
                    .commit();


        }

        else if (v.getId()==R.id.signupSendData){
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, new CustomerDisplayFragment())
                    .addToBackStack("SignupFragment")
                    .commit();

        }

        }

    @Override
    public void credentialsPass(String email, String password) {

    }

}

