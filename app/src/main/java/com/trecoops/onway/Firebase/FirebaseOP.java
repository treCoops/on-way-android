package com.trecoops.onway.Firebase;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.hishd.lightdb.LightDB;
import com.trecoops.onway.Controller.UserController;
import com.trecoops.onway.Model.User;

public class FirebaseOP {

    FirebaseStorage firebaseStorage;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    Context context;
    LightDB lightDB;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;

    public FirebaseOP(Context context) {
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        this.context = context;
        this.lightDB = new LightDB("ONWAY",context);
    }

    //Driver(User) OP

    public void registerDriver(User user, final UserController.OnUserRegistrationListener onUserRegistration){
        databaseReference = firebaseDatabase.getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        Log.i("FIREBASE_AUTH_UID",firebaseUser.getUid());
        user.setUid(firebaseUser.getUid());

        databaseReference.child("mobile_users").child("drivers").child(firebaseUser.getUid()).setValue(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        onUserRegistration.onUserAddingCompleted();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        onUserRegistration.onUserAddingFailed("Failed to add new user");
                    }
                }).addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        onUserRegistration.onUserAddingCancelled();
                    }
                });
    }

}
