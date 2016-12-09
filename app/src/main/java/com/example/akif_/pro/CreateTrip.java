package com.example.akif_.pro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CreateTrip extends AppCompatActivity {
      String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_trip);
        Intent i=getIntent();
        i.getExtras().get("email");

    }
}
