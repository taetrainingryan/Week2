package com.example.ryan.firstassignment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ryan.firstassignment.Realm.RealmController;
import com.example.ryan.firstassignment.Realm.RealmLogin;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class login extends AppCompatActivity {

    private Realm realm;
    private RealmController realmController;
    private EditText email, password;
    ArrayList<RealmLogin> realmlogins;
    private CheckBox saveLoginCheckBox;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.emailInput);
        password = (EditText) findViewById(R.id.passwordInput);
        saveLoginCheckBox = (CheckBox)findViewById(R.id.saveLoginCheckBox);
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            email.setText(loginPreferences.getString("email", ""));
            password.setText(loginPreferences.getString("password", ""));
            saveLoginCheckBox.setChecked(true);
        }



        initializeRealm();
        realmlogins = RealmController.getInstance().getLoginList();
    }

    public void initializeRealm(){

        RealmController.getInstance();

    }

    public void loginClick(View view){

        if (checkUser(email.getText().toString(), password.getText().toString())){

            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(email.getWindowToken(), 0);

            String email1 = email.getText().toString();
            String password1 = password.getText().toString();

            if (saveLoginCheckBox.isChecked()) {
                loginPrefsEditor.putBoolean("saveLogin", true);
                loginPrefsEditor.putString("email", email1);
                loginPrefsEditor.putString("password", password1);
                loginPrefsEditor.commit();
            } else {
                loginPrefsEditor.clear();
                loginPrefsEditor.commit();
            }

            Intent intent = new Intent(login.this, CustomerDisplay.class);
            intent.putExtra("message", email.getText().toString());
            startActivity(intent);
        }

        else{

            Toast.makeText(getApplicationContext(), "Login details do not exist", Toast.LENGTH_LONG).show();

        }

    }

    private boolean checkUser(String email,String password) {

        for (RealmLogin RealmLogin : realmlogins) {

            if (email.toLowerCase().equals(RealmLogin.getEmail().toLowerCase()) &&
                    password.equals(RealmLogin.getPassword())) {

                return true;

            }
        }

        return false;
    }

    public void sendData(View view){

        RealmLogin realmlogins = new RealmLogin(
                email.getText().toString(),
                password.getText().toString())
                ;

        RealmController.getInstance().saveLogin(realmlogins);

        Intent intent = new Intent(login.this, CustomerDisplay.class);

    }

}
