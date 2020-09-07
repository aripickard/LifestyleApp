package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

public class UserInfo2 extends Activity {
    Button mContinueButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_2);


        mContinueButton = findViewById(R.id.continueButton);


        mContinueButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                continueToUserInfo3();
            }
        });
    }

    private void continueToUserInfo3() {
        Intent intent = new Intent(this, UserInfo3.class);
        startActivity(intent);
    }
}