package com.example.ryan.firstassignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ryan.firstassignment.Realm.RealmController;
import com.example.ryan.firstassignment.Realm.RealmLogin;

import java.util.ArrayList;

import io.realm.Realm;

public class createaccount extends AppCompatActivity {

    ImageButton back;
    EditText emailInput, passInput, passInput2;
    private Realm realm;
    private RealmController realmController;
    private ArrayList<RealmLogin> realmlogins1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createaccount);

        back = (ImageButton) findViewById(R.id.backButton);
        emailInput = (EditText) findViewById(R.id.passwordInput);
        passInput = (EditText) findViewById(R.id.emailInput);
        passInput2 = (EditText) findViewById(R.id.passwordInput2);

        emailInput.addTextChangedListener(new TextValidator(emailInput) {
            @Override public void validate(TextView textView, String text) {

                if(!isValidEmail(emailInput.getText().toString())) {
                    emailInput.setError( "Invalid Email!");
                }

            }
        });

        passInput.addTextChangedListener(new TextValidator(passInput) {
            @Override public void validate(TextView textView, String text) {

                if(!isValidPassword(passInput.getText().toString())) {
                    passInput.setError( "Invalid Password!");
                }

            }
        });

        passInput2.addTextChangedListener(new TextValidator(passInput2) {
            @Override public void validate(TextView textView, String text) {

                if(!isValidPassword(passInput2.getText().toString())) {
                    passInput2.setError( "Invalid Password!");

                }

                if(isValidPassword(passInput2.getText().toString())) {

                    if(!passInput.getText().toString().equals(passInput2.getText().toString())){
                        passInput2.setError( "Passwords do not match!");
                    }
                }
            }
        });

        initializeRealm();

            }

    public void initializeRealm(){

        RealmController.getInstance();

    }



    public void clickButtonEvents (View v){

        if (v.getId()==R.id.backButton){

            Intent intent = new Intent(createaccount.this, MainActivity.class);
            startActivity(intent);
            finish();

        }

        else if (v.getId()==R.id.nextButton){

            RealmLogin realmlogins = new RealmLogin(
                    emailInput.getText().toString(),
                    passInput.getText().toString())
                    ;


            RealmController.getInstance().saveLogin(realmlogins);

            Intent intent = new Intent(createaccount.this, signup.class);
            startActivity(intent);

            }
        }
    }





