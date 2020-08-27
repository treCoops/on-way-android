package com.trecoops.onway.Validator;

import android.text.TextUtils;
import android.util.Patterns;
import android.widget.CheckBox;

import java.util.regex.Pattern;

public class Validator {

    public static boolean validateEmail(String email){
        final Pattern EMAIL_PATTERN = Patterns.EMAIL_ADDRESS;
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean checkEmpty(String text){
        return TextUtils.isEmpty(text);
    }

    public static boolean checkNIC(String NIC){
        return NIC.matches("^([0-9]{9}[x|X|v|V]|[0-9]{12})$");
    }

    public static boolean checkMobile(String Mobile){
        return Mobile.matches("^7[1,2,5,6,7,8,0][0-8]+$");
    }

    public static boolean checkTwoSame(String a, String b){
        return a.equals(b);
    }

    public static boolean textLength(String text, int lenght){
        return text.length() >= lenght ;
    }

    public static boolean checkCheked(CheckBox checkBox){
        return checkBox.isChecked();

    }
}
