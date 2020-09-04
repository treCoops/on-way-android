package com.trecoops.onway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.trecoops.onway.FirebaseAuth.OTP;
import com.trecoops.onway.Util.ProgressBar;

public class OTPActivity extends AppCompatActivity implements OTP.OnAuthenticationListener {

    String phoneNumber;
    OTP otp;
    EditText txtCode;
    LinearLayout btnSendAgain;
    TextView txtSeconds;
    Button btnSubmit;
    CountDownTimer countDownTimer;
    long currentMilliSeconds = 0;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_t_p);
        progressBar = new ProgressBar();

        final RelativeLayout layout_root = findViewById(R.id.layout_root);
        final Animation anim_root = AnimationUtils.loadAnimation(this,R.anim.slide_up_enter);

        if (!getIntent().hasExtra("NUMBER")) {
            Log.i("ONWAY","Phone Number not found on Extras");
            Toast.makeText(this, "Unable to Verify Phone number at this moment", Toast.LENGTH_LONG).show();
            return;
        }

        layout_root.startAnimation(anim_root);

        txtCode = findViewById(R.id.txtCode);
        btnSendAgain = findViewById(R.id.btnSendAgain);
        txtSeconds = findViewById(R.id.txtSeconds);
        btnSubmit = findViewById(R.id.btnSubmit);

        txtCode.setEnabled(false);
        btnSendAgain.setVisibility(View.GONE);
        txtSeconds.setVisibility(View.GONE);
        btnSubmit.setEnabled(false);

        phoneNumber = getIntent().getStringExtra("NUMBER");
        otp = new OTP(this, this);
        otp.sendVerification("+94" + phoneNumber);


        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtCode.getText().toString().trim().length() != 6) {
                    Log.i("ONWAY","Invalid OTP format");
                    txtCode.setError("OTP not valid");
                    txtCode.requestFocus();
                    Toast.makeText(OTPActivity.this, "Please recheck the entered OTP", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.i("ONWAY","Started OTP verification");
                progressBar.showProgressBar(OTPActivity.this);
                otp.verifyCode(txtCode.getText().toString());
            }
        });

        btnSendAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentMilliSeconds != 0) {
                    Toast.makeText(OTPActivity.this, "Please wait until the countdown ends.", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.i("ONWAY","Started resending OTP");
                otp.sendVerification("+94" + phoneNumber);
            }
        });

    }

    public void startTimer() {
        countDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                currentMilliSeconds = (millisUntilFinished / 1000);
                txtSeconds.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                Log.i("ONWAY","Timer countdown finished");
                currentMilliSeconds = 0;
                txtSeconds.setText("0");
                btnSendAgain.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onAuthenticationStarted() {
        Log.i("ONWAY","Started Authentication");
        txtCode.setEnabled(true);
        txtSeconds.setVisibility(View.VISIBLE);
        btnSubmit.setEnabled(true);
        txtCode.requestFocus();
        btnSendAgain.setVisibility(View.GONE);
        startTimer();
    }

    @Override
    public void onAuthenticationSuccess() {
        Log.i("ONWAY","Authentication Successful");
        Toast.makeText(OTPActivity.this, "Authentication Successful", Toast.LENGTH_LONG).show();
        startActivity(new Intent(OTPActivity.this,Activity_sign_up.class)
                .putExtra("PHONE_NUMBER",phoneNumber));
        finishAffinity();
        progressBar.dismissProgressBar();
    }

    @Override
    public void onAuthenticationFailed() {
        Log.i("ONWAY","Authentication failed");
        btnSendAgain.setVisibility(View.VISIBLE);
        Toast.makeText(OTPActivity.this, "Authentication Failed, Please try again.", Toast.LENGTH_LONG).show();
        progressBar.dismissProgressBar();
    }
}