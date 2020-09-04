package com.trecoops.onway.Controller;

import android.content.Context;

import com.trecoops.onway.Firebase.FirebaseOP;
import com.trecoops.onway.Model.User;

public class UserController {

    FirebaseOP firebaseOP;

    public void registerUser(User user, Context context, OnUserRegistrationListener onUserRegistrationListener){
        firebaseOP = new FirebaseOP(context);
        firebaseOP.registerDriver(user, onUserRegistrationListener);
    }

    //Callback Interfaces

    public interface OnUserRegistrationListener {
        void onUserAddingCompleted();

        void onUserAddingInProgress(int progress);

        void onUserAddingCancelled();

        void onUserAddingFailed(String message);
    }

    public interface OnUserVerificationListener {
        void onUserExists();

        void onUserDoesNotExists();

        void onUserCheckFailed();
    }

    public interface OnUserSignInListener {
        void onSignInSuccess(User user);

        void onUserNotFound();

        void onUserSignInFailed(String message);
    }

    public interface OnUserUpdateListener {
        void onUserUpdateSuccessful(User user);

        void onUserUpdateFailed(String message);
    }

    public interface OnUserRemoveListener {
        void onUserRemoveSuccessful();

        void onUserRemoveFailed();
    }
}
