package com.example.ryan.firstassignment.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ryan.firstassignment.R;
import com.example.ryan.firstassignment.Realm.RealmController;
import com.example.ryan.firstassignment.Realm.RealmLogin;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private DataPasser myInterface;
    private EditText email, password;
    ArrayList<RealmLogin> realmlogins;
    private CheckBox saveLoginCheckBox;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;
    private Button login;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        myInterface = (DataPasser) getActivity();
    }

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        email = (EditText) view.findViewById(R.id.emailInput);
        password = (EditText) view.findViewById(R.id.passwordInput);
        saveLoginCheckBox = (CheckBox)view.findViewById(R.id.saveLoginCheckBox);
        loginPreferences = getContext().getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
        login = (Button) view.findViewById(R.id.nextButton);

        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            email.setText(loginPreferences.getString("email", ""));
            password.setText(loginPreferences.getString("password", ""));
            saveLoginCheckBox.setChecked(true);
        }

        initializeRealm();
        realmlogins = RealmController.getInstance().getLoginList();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkUser(email.getText().toString(), password.getText().toString())){

                    InputMethodManager imm = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
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

                    myInterface.buttonClick(view);

                }

                else{

                    Toast.makeText(getContext(), "Login details do not exist", Toast.LENGTH_LONG).show();

                }
            }
        });

    }


    public void initializeRealm(){

        RealmController.getInstance();

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



}
