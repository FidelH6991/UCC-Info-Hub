package com.example.majproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

public class SocialMediaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        ImageView facebook = findViewById(R.id.facebookIcon);
        ImageView twitter = findViewById(R.id.twitterIcon);
        ImageView instagram = findViewById(R.id.instagramIcon);

        facebook.setOnClickListener(v -> openWeb("https://www.facebook.com/uccjamaica/"));
        twitter.setOnClickListener(v -> openWeb("https://twitter.com/UCCjamaica"));
        instagram.setOnClickListener(v -> openWeb("https://www.instagram.com/uccjamaica/"));
    }

    private void openWeb(String url) {
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }
}
