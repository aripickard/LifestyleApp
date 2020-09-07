package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.Nullable;

import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;



public class UserInfo3 extends Activity implements View.OnClickListener{
    TextView mSnapSelfie;
    ImageView mProfilePicture;
    Button mCreateButton;

    //Define a request code for the camera
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_3);

        mSnapSelfie = findViewById(R.id.snapSelfie);
        mProfilePicture = findViewById(R.id.userImage);
        mCreateButton = findViewById(R.id.createButton);

        mSnapSelfie.setOnClickListener((View.OnClickListener) this);
        mCreateButton.setOnClickListener((View.OnClickListener) this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.snapSelfie: {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(cameraIntent.resolveActivity(getPackageManager())!=null){
                    startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap thumbnailImage = (Bitmap) extras.get("data");
//            mIvPic = (ImageView) findViewById(R.id.iv_pic);
            mProfilePicture.setImageBitmap(thumbnailImage);
        }
    }
}
