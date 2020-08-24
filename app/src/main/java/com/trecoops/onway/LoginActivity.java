package com.trecoops.onway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final TextView txt_main_text = findViewById(R.id.txt_main_text);
        final TextView txt_footer = findViewById(R.id.txt_footer);
        final Button login_btn_next = findViewById(R.id.login_btn_next);

        final String headingString = "Get moving with ONWAY";
        final SpannableString spanHeadingString = new SpannableString(headingString);
        spanHeadingString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorBlack)), 0, 15, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanHeadingString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorHelloTextOnly)), 15, 21, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        txt_main_text.setText(spanHeadingString);

        final String footerString = "By creating an account, you agree to our Terms of Service and Privacy Policy.";
        final SpannableString spanFooterString = new SpannableString(footerString);
        spanFooterString.setSpan(new StyleSpan(Typeface.BOLD),40,57,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanFooterString.setSpan(new StyleSpan(Typeface.BOLD),62,76,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        txt_footer.setText(spanFooterString);

        login_btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, OTPActivity.class));
            }
        });

    }
}