package com.example.ryan.firstassignment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.codetroopers.betterpickers.datepicker.DatePickerBuilder;
import com.codetroopers.betterpickers.datepicker.DatePickerDialogFragment;
import com.example.ryan.firstassignment.Realm.RealmBackend;
import com.example.ryan.firstassignment.Realm.RealmController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.realm.Realm;

public class signup extends AppCompatActivity implements DatePickerDialogFragment.DatePickerDialogHandler{

    @Override
    public <T extends View> T findViewById(int id) {
        return super.findViewById(id);
    }

    private static final int CAMERA_REQUEST = 1888;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_CODE = 1;
    private Bitmap bitmap;
    private ImageView imageView;
    private EditText age, username, name, postalAddress;
    private Spinner spinner2;
    private Realm realm;
    private RealmController realmController;
    private RadioGroup gender;
    private Bitmap photoConvert;
    private Bitmap backup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        age = (EditText) findViewById(R.id.age);
        imageView = (ImageView) findViewById(R.id.image);
        username = (EditText) findViewById(R.id.username);
        name = (EditText) findViewById(R.id.name);
        postalAddress = (EditText) findViewById(R.id.postalAddress);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        gender = (RadioGroup) findViewById(R.id.gender) ;

        backup = BitmapFactory.decodeResource(getResources(),
                R.mipmap.backupimage);


        String[] courses={"United Kingdom","France","Spain","Germany","Holland"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,courses);
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

        if(savedInstanceState != null){
            Bitmap bitmap = savedInstanceState.getParcelable("image");
            photoConvert = bitmap;
            imageView.setImageBitmap(bitmap);
        }

    }

    public void initializeRealm(){
        RealmController.getInstance();
    }

    public void sendData(View view){

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

        Intent intent = new Intent(signup.this, CustomerDisplay.class);
        intent.putExtra("message", name.getText().toString());
        startActivity(intent);


    }


    public void pickImage(View View) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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
        Spinner spinner2=(Spinner)findViewById(R.id.spinner2);

        Toast.makeText(getApplicationContext(), "Spinner- "+spinner2.getSelectedItem().toString(), Toast.LENGTH_LONG).show();

    }

    public void onDateClick(View v) {
        DatePickerBuilder dp = new DatePickerBuilder()
                .setFragmentManager(getSupportFragmentManager())
                .setStyleResId(R.style.BetterPickersDialogFragment);
        dp.show();

    }

    @SuppressLint("StringFormatMatches")
    @Override
    public void onDialogDateSet(int reference, int year, int monthOfYear, int dayOfMonth) {

        age.setText(getString(R.string.date_picker,year,monthOfYear,dayOfMonth));

    }

    @Override
    public void onSaveInstanceState(Bundle outState){

        if (imageView !=null){

            Bitmap picture = photoConvert;
            outState.putParcelable("image", picture);
            super.onSaveInstanceState(outState);

        }



    }
}