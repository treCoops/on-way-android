package com.trecoops.onway;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class OTPActivity extends AppCompatActivity {

    String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_t_p);

        phoneNumber = getIntent().getStringExtra("NUMBER");


    }
}