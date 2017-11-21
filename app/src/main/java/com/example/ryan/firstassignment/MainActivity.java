package com.example.ryan.firstassignment;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button createacc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainscreen);

        createacc = (Button) findViewById(R.id.button);


    }

    public void clickButtonEvents (View v){

        if (v.getId()==R.id.button){

            Intent intent = new Intent(MainActivity.this, createaccount.class);
            startActivity(intent);

        }

        else if (v.getId()==R.id.login){

            Intent intent = new Intent(MainActivity.this, login.class);
            startActivity(intent);
        }

    }

    public void loginPage(View view){

        Intent intent = new Intent(MainActivity.this, login.class);
        startActivity(intent);
    }


}
