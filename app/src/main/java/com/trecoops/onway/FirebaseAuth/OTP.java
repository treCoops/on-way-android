package com.trecoops.onway.FirebaseAuth;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OTP {

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
    Context context;
    String verificationCode;
    FirebaseAuth mFirebaseAuth;
    OnAuthenticationListener mOnAuthenticationListener;

    public OTP(final Context context, OnAuthenticationListener onAuthenticationListener){
        this.context = context;
        mFirebaseAuth = FirebaseAuth.getInstance();
        this.mOnAuthenticationListener = onAuthenticationListener;
        mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                Log.i("FIREBASE_AUTH","Verification Code Sent");
                mOnAuthenticationListener.onAuthenticationStarted();
                verificationCode = s;
            }

            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                String authCode = phoneAuthCredential.getSmsCode();
                Log.i("FIREBASE_AUTH","Verification Code Processing");
                if(authCode!=null)
                    verifyCode(authCode);
                else
                    Log.e("FIREBASE_AUTH","AUTH CODE RETURNED NULL");
//                mOnAuthenticationListener.onAuthenticationSuccess();
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Log.e("FIREBASE_AUTH","Verification Failed :" + e.getMessage());
                Toast.makeText(context,"Verification Failed",Toast.LENGTH_SHORT).show();
            }
        };
    }

    public void sendVerification(String number){
        Log.i("FIREBASE_AUTH","Start sending verification");
        PhoneAuthProvider.getInstance().verifyPhoneNumber(number,60, TimeUnit.SECONDS, TaskExecutors.MAIN_THREAD,mCallback);
    }

    public void verifyCode(String code){
        Log.i("FIREBASE_AUTH","Verification started");
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode,code);
        signInWithCred(credential);
    }

    private void signInWithCred(PhoneAuthCredential credential) {
        mFirebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.i("FIREBASE_AUTH","Verification Successful");
                    mOnAuthenticationListener.onAuthenticationSuccess();
                }else{
                    mOnAuthenticationListener.onAuthenticationFailed();
                    Log.e("FIREBASE_AUTH",task.getException().getMessage());
                }
            }
        });
    }

    public interface OnAuthenticationListener{
        void onAuthenticationStarted();
        void onAuthenticationSuccess();
        void onAuthenticationFailed();
    }

}
