package com.trecoops.onway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fede987.statusbaralert.StatusBarAlert;
import com.trecoops.onway.Validator.Validator;

public class Activity_signup_verifyOTP extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_verify_o_t_p);

        final EditText txt_contact_no = findViewById(R.id.txt_contact_no);
        final Button login_btn_next = findViewById(R.id.login_btn_next);
        final TextView txt_signIn = findViewById(R.id.txt_signIn);

        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        login_btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Validator.checkEmpty(txt_contact_no.getText().toString().trim())){
                    new StatusBarAlert.Builder(Activity_signup_verifyOTP.this)
                            .autoHide(true)
                            .withDuration(2000)
                            .withText("Mobile number required!")
                            .withAlertColor(R.color.AlertRed)
                            .build();

                    return;
                }

                if(!Validator.checkMobile(txt_contact_no.getText().toString().trim())) {
                    new StatusBarAlert.Builder(Activity_signup_verifyOTP.this)
                            .autoHide(true)
                            .withDuration(2000)
                            .withText("Invalid Contact Number!")
                            .withAlertColor(R.color.AlertRed)
                            .build();

                    return;
                }

                startActivity(new Intent(Activity_signup_verifyOTP.this, OTPActivity.class).putExtra("NUMBER", txt_contact_no.getText().toString().trim()));
            }
        });

        txt_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}