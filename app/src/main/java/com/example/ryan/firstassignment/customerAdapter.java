package com.example.ryan.firstassignment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ryan.firstassignment.Realm.RealmBackend;

import java.util.ArrayList;

/**
 * Created by Ryan on 18/11/2017.
 */

public class customerAdapter extends RecyclerView.Adapter<customerAdapter.MyViewHolder>{

    private ArrayList<RealmBackend> realmCustomers;
    private int row_customer;
    private Context applicationContext;

    public customerAdapter(ArrayList<RealmBackend> realmCustomers, int row_customer, Context applicationContext) {
        this.realmCustomers = realmCustomers;
        this.row_customer = row_customer;
        this.applicationContext = applicationContext;
    }


    @Override
    public customerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(applicationContext).inflate(row_customer, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(customerAdapter.MyViewHolder holder, int position) {

        holder.tvName.setText(realmCustomers.get(position).getName());
        holder.tvUsername.setText(realmCustomers.get(position).getUsername());
        holder.tvAge.setText(realmCustomers.get(position).getAge());
        holder.tvLocale.setText(realmCustomers.get(position).getLocale());
        holder.tvGender.setText(realmCustomers.get(position).getGender());
        holder.tvAddress.setText(realmCustomers.get(position).getPostalAddress());
        holder.btImage.setImageBitmap(realmCustomers.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return realmCustomers.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvUsername, tvAge, tvLocale, tvGender, tvAddress;
        ImageView btImage;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvName=(TextView)itemView.findViewById(R.id.tvName);
            tvUsername=(TextView)itemView.findViewById(R.id.tvUsername);
            tvAge=(TextView)itemView.findViewById(R.id.tvAge);
            tvLocale=(TextView)itemView.findViewById(R.id.tvLocale);
            tvGender=(TextView)itemView.findViewById(R.id.tvGender);
            tvAddress=(TextView)itemView.findViewById(R.id.tvAddress);
            btImage = (ImageView)itemView.findViewById(R.id.ivPhoto);
        }
    }
}
