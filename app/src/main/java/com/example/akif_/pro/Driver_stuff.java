package com.example.akif_.pro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Driver_stuff extends AppCompatActivity {
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_stuff);
        Intent i=getIntent();
        email=i.getExtras().getString("email");
    }






    public void onTrip(View view)
    {
        Intent i=new Intent(getApplicationContext(),CreateTrip.class);
        i.putExtra("email",email);
        startActivity(i);
    }


















}




