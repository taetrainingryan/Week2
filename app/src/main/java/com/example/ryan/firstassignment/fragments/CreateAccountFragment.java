package com.example.ryan.firstassignment.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ryan.firstassignment.R;
import com.example.ryan.firstassignment.Realm.RealmController;
import com.example.ryan.firstassignment.Realm.RealmLogin;
import com.example.ryan.firstassignment.TextValidator;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateAccountFragment extends Fragment {

    ImageButton back;
    EditText emailInput, passInput, passInput2;
    Button nextButton;
    private DataPasser myInterface;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        myInterface = (DataPasser) getActivity();
    }

    public CreateAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_account, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        back = (ImageButton) view.findViewById(R.id.backButton);
        emailInput = (EditText) view.findViewById(R.id.passwordInput);
        passInput = (EditText) view.findViewById(R.id.emailInput);
        passInput2 = (EditText) view.findViewById(R.id.passwordInput2);
        nextButton = (Button) view.findViewById(R.id.saveDetails);

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

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RealmLogin realmlogins = new RealmLogin(
                        emailInput.getText().toString(),
                        passInput.getText().toString())
                        ;


                RealmController.getInstance().saveLogin(realmlogins);

                myInterface.buttonClick(view);
            }
        });


    }


    public void initializeRealm(){

        RealmController.getInstance();

    }

}
