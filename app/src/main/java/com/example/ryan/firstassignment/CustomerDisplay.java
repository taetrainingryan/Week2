package com.example.ryan.firstassignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryan.firstassignment.Realm.RealmBackend;
import com.example.ryan.firstassignment.Realm.RealmController;

import java.util.ArrayList;

import io.realm.Realm;

public class CustomerDisplay extends AppCompatActivity {

    RealmController realmController;
    ArrayList<RealmBackend> realmCustomers;
    private RecyclerView recyclerView;
    private TextView emailPassed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_display);

        realmCustomers = RealmController.getInstance().getCustomerList();

        initializeRecyclerView();

        Bundle bundle = getIntent().getExtras();
        String email = getIntent().getStringExtra("message");
        emailPassed = (TextView) findViewById(R.id.emailPassed);
        emailPassed.setText(email);

        }


    private void initializeRecyclerView() {

        recyclerView=(RecyclerView) findViewById(R.id.rvCustomer);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(new customerAdapter(realmCustomers, R.layout.row_customer, getApplicationContext()));

    }


}
