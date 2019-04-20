package com.smartz.conexaodescontos.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "SplashActivity";


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        mAuth = FirebaseAuth.getInstance();

        //check the current user
        if (mAuth.getCurrentUser() != null)
        {
            startActivity(new Intent(SplashActivity.this, DiscountListActivity.class));
            //TODO search treta

        }else
        {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));

        }

        // close splash activity
        finish();
    }
}
