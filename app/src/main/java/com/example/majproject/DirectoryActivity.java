package com.example.majproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;

public class DirectoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_directory_activity);

        // Apply system insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Handle Toolbar Back Arrow Only
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    // Opens default email app with Herad's email
    public void sendEmailHerad(android.view.View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto: gamby@stu.ucc.edu.jm"));
        startActivity(Intent.createChooser(intent, "Send Email"));
    }

    // Opens dialer with Herad's number
    public void callHerad(android.view.View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:18760002498"));
        startActivity(intent);
    }

    // Opens default email app with Kimberly's email
    public void sendEmailKimberly(android.view.View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:wellrose@stu.ucc.edu.jm"));
        startActivity(Intent.createChooser(intent, "Send Email"));
    }

    // Opens dialer with Kimberly's number
    public void callKimberly(android.view.View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:18768930000"));
        startActivity(intent);
    }
}
