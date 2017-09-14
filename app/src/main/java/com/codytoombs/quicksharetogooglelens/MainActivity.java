package com.codytoombs.quicksharetogooglelens;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if (type.startsWith("image/")) {
                handleSendImage(intent); // Handle single image being sent
            }
        }
    }

    void handleSendImage(Intent incomingIntent) {
        Uri imageUri = incomingIntent.getParcelableExtra(Intent.EXTRA_STREAM);
        if (imageUri != null) {
            Intent lensIntent = new Intent();
            lensIntent.setComponent(new ComponentName("com.google.android.apps.photos", "com.google.android.apps.photos.lens.oem.LensActivity"));
            lensIntent.setAction("com.android.camera.action.LENS");
            lensIntent.setType("image/*");
            lensIntent.setData(imageUri);
            startActivity(lensIntent);
        }
    }
}