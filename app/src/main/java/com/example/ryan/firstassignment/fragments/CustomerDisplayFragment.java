package com.example.ryan.firstassignment.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ryan.firstassignment.R;
import com.example.ryan.firstassignment.Realm.RealmBackend;
import com.example.ryan.firstassignment.Realm.RealmController;
import com.example.ryan.firstassignment.customerAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomerDisplayFragment extends Fragment {

    ArrayList<RealmBackend> realmCustomers;
    private RecyclerView recyclerView;
    private TextView emailPassed;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public CustomerDisplayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_customer_display, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        realmCustomers = RealmController.getInstance().getCustomerList();
        recyclerView =(RecyclerView) view.findViewById(R.id.rvCustomer);

        initializeRecyclerView();

//        Bundle bundle = getIntent().getExtras();
//        String email = getIntent().getStringExtra("message");
//        emailPassed = (TextView) view.findViewById(R.id.emailPassed);
//        emailPassed.setText(email);


    }

    private void initializeRecyclerView() {

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext().getApplicationContext()));
        recyclerView.setAdapter(new customerAdapter(realmCustomers, R.layout.row_customer, getContext().getApplicationContext()));

    }
}
