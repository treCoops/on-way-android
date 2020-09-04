package com.trecoops.onway;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fede987.statusbaralert.StatusBarAlert;
import com.trecoops.onway.Util.AlertBar;
import com.trecoops.onway.Validator.Validator;

public class LoginActivity extends AppCompatActivity {

    private boolean doubleBackToExitPressedOnce = false;

    RelativeLayout layout_middle;
    Animation slide_down_enter;
    AlertBar alertBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final TextView txt_main_text = findViewById(R.id.txt_main_text);
        final Button login_btn_next = findViewById(R.id.login_btn_next);
        final EditText txt_contact_no = findViewById(R.id.txt_contact_no);
        final RelativeLayout btnSignUp = findViewById(R.id.btnSignUp);
        final ImageView img_main_logo = findViewById(R.id.img_main_logo);

        slide_down_enter = AnimationUtils.loadAnimation(this, R.anim.slide_down_enter);

        layout_middle = findViewById(R.id.layout_middle);

        final String headingString = "Get moving with ONWAY";
        final SpannableString spanHeadingString = new SpannableString(headingString);
        spanHeadingString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorBlack)), 0, 15, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanHeadingString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorHelloTextOnly)), 15, 21, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        txt_main_text.setText(spanHeadingString);
        img_main_logo.startAnimation(slide_down_enter);




        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(LoginActivity.this, layout_middle, "transition_layout_mid");
                startActivity(new Intent(LoginActivity.this,Activity_signup_verifyOTP.class),activityOptionsCompat.toBundle());
//                startActivity(new Intent(LoginActivity.this,Activity_sign_up.class));
            }
        });

        login_btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Validator.checkEmpty(txt_contact_no.getText().toString().trim())){
                    AlertBar.notifyDanger(LoginActivity.this, "Mobile number required!");
                    return;
                }

                if(!Validator.checkMobile(txt_contact_no.getText().toString().trim())) {
                    AlertBar.notifyDanger(LoginActivity.this, "Invalid Contact Number!");
                    return;
                }

//                startActivity(new Intent(LoginActivity.this, OTPActivity.class).putExtra("NUMBER", txt_contact_no.getText().toString().trim()));
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finishAffinity();
            System.exit(0);
            return;
        }

        this.doubleBackToExitPressedOnce = true;

        new StatusBarAlert.Builder(LoginActivity.this)
                .autoHide(true)
                .withDuration(2000)
                .withText("Please click BACK again to exit!")
                .withAlertColor(R.color.AlertGray)
                .build();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}