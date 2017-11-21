package com.example.ryan.firstassignment.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.codetroopers.betterpickers.datepicker.DatePickerBuilder;
import com.example.ryan.firstassignment.CustomerDisplay;
import com.example.ryan.firstassignment.R;
import com.example.ryan.firstassignment.Realm.RealmBackend;
import com.example.ryan.firstassignment.Realm.RealmController;
import com.example.ryan.firstassignment.signup;
import com.codetroopers.betterpickers.datepicker.DatePickerBuilder;
import com.codetroopers.betterpickers.datepicker.DatePickerDialogFragment;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.realm.Realm;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment implements DatePickerDialogFragment.DatePickerDialogHandler{

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_CODE = 1;
    private ImageView imageView;
    private EditText age, username, name, postalAddress;
    private Spinner spinner2;
    private RadioGroup gender;
    private Bitmap photoConvert;
    private Bitmap backup;
    private Button sendData, imagePicker, datePicker;
    private DataPasser myInterface;
    private Bitmap bitmap;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        myInterface = (DataPasser) getActivity();
    }

    public SignupFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        age = (EditText) view.findViewById(R.id.age);
        imageView = (ImageView) view.findViewById(R.id.image);
        username = (EditText) view.findViewById(R.id.username);
        name = (EditText) view.findViewById(R.id.name);
        postalAddress = (EditText) view.findViewById(R.id.postalAddress);
        spinner2 = (Spinner) view.findViewById(R.id.spinner2);
        gender = (RadioGroup) view.findViewById(R.id.gender) ;
        sendData = (Button) view.findViewById(R.id.signupSendData);
        final Spinner spinner2=(Spinner) view.findViewById(R.id.spinner2);
        imagePicker = (Button) view.findViewById(R.id.btnSelectPhoto);
        datePicker = (Button) view.findViewById(R.id.datePicker);

        backup = BitmapFactory.decodeResource(getResources(),
                R.mipmap.backupimage);


        String[] courses={"United Kingdom","France","Spain","Germany","Holland"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line,courses);
        spinner2.setAdapter(adapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> av, View v,
                                       int position, long itemId) {
                // TODO Auto-generated method stub
                String item=av.getItemAtPosition(position).toString();

                //Toast.makeText(getApplicationContext(), "Selected Item is "+item, Toast.LENGTH_LONG).show();
            }
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });


        initializeRealm();

        imagePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePictureIntent.resolveActivity(getContext().getPackageManager()) != null) {
                        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                    }
                }
        });

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    DatePickerBuilder dp = new DatePickerBuilder()
                            .setFragmentManager(getActivity().getSupportFragmentManager())
                            .setStyleResId(R.style.BetterPickersDialogFragment);
                    dp.show();
            }
        });

        sendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int radioButtonID = gender.getCheckedRadioButtonId();
                View radioButton = gender.findViewById(radioButtonID);
                int idx = gender.indexOfChild(radioButton);
                RadioButton r = (RadioButton)  gender.getChildAt(idx);
                String selectedtext = r.getText().toString();


                ByteArrayOutputStream stream1 = new ByteArrayOutputStream();

                if(photoConvert == null){
                    photoConvert = backup;
                    photoConvert.compress(Bitmap.CompressFormat.PNG, 1, stream1);
                }

                photoConvert.compress(Bitmap.CompressFormat.PNG, 100, stream1);

                byte[] photoConverted = stream1.toByteArray();

                RealmBackend realmCustomer = new RealmBackend(
                        name.getText().toString(),
                        username.getText().toString(),
                        age.getText().toString(),
                        spinner2.getSelectedItem().toString(),
                        selectedtext,
                        postalAddress.getText().toString(),
                        photoConverted)
                        ;


                RealmController.getInstance().saveCustomer(realmCustomer);

                myInterface.buttonClick(view);
            }
        });

    }

    public void initializeRealm(){
        RealmController.getInstance();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        InputStream stream = null;

        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK)
            if (bitmap != null) {
                bitmap.recycle();
            }

        Bitmap photo = (Bitmap) data.getExtras().get("data");
        photoConvert = photo;
        imageView.setImageBitmap(photo);


        if (stream != null)
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public void getDetail(View v)
    {

        Toast.makeText(getContext().getApplicationContext(), "Spinner- "+spinner2.getSelectedItem().toString(), Toast.LENGTH_LONG).show();

    }

    @SuppressLint("StringFormatMatches")
    @Override
    public void onDialogDateSet(int reference, int year, int monthOfYear, int dayOfMonth) {

        age.setText(getString(R.string.date_picker,year,monthOfYear,dayOfMonth));

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        if (imageView != null) {

            Bitmap picture = photoConvert;
            outState.putParcelable("image", picture);
            super.onSaveInstanceState(outState);

        }
    }

}
