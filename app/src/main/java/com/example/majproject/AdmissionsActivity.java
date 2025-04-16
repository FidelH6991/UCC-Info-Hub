package com.example.majproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;

public class AdmissionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admissions);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());


        TextView internationalEntryText = findViewById(R.id.internationalEntry);
        String text = "ENTRY REQUIREMENTS FOR INTERNATIONAL STUDENTS\n\nClick here to view our admission requirements for international students.";
        SpannableString spannable = new SpannableString(text);

        ClickableSpan internationalSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                String url = "https://ucc.edu.jm/programmes/undergraduate/requirements-for-international-students";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                widget.getContext().startActivity(intent);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(true);
                ds.setColor(ContextCompat.getColor(AdmissionsActivity.this, android.R.color.holo_blue_dark));
            }
        };

        int start = text.indexOf("Click here");
        int end = start + "Click here".length();
        spannable.setSpan(internationalSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        internationalEntryText.setText(spannable);
        internationalEntryText.setMovementMethod(LinkMovementMethod.getInstance());


        TextView applyNowLink = findViewById(R.id.applyNowLink);
        SpannableString spannableLink = new SpannableString(applyNowLink.getText());

        ClickableSpan applySpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                String url = "https://ucc.edu.jm/apply";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                widget.getContext().startActivity(intent);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(true);
                ds.setColor(ContextCompat.getColor(AdmissionsActivity.this, android.R.color.holo_blue_dark));
            }
        };

        spannableLink.setSpan(applySpan, 0, spannableLink.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        applyNowLink.setText(spannableLink);
        applyNowLink.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
