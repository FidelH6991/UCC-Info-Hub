package com.example.majproject;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.appbar.MaterialToolbar;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        // Toolbar back arrow support
        MaterialToolbar toolbar = findViewById(R.id.webToolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        webView = findViewById(R.id.webView);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return handleUrl(view, request.getUrl().toString());
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return handleUrl(view, url);
            }

            private boolean handleUrl(WebView view, String url) {
                if (url.startsWith("http") || url.startsWith("https")) {
                    return false; // load in WebView
                } else {
                    try {
                        Intent intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
                        startActivity(intent);
                        return true;
                    } catch (Exception e) {
                        try {
                            Uri fallbackUrl = Uri.parse(getFallbackUrl(url));
                            Intent fallbackIntent = new Intent(Intent.ACTION_VIEW, fallbackUrl);
                            startActivity(fallbackIntent);
                            return true;
                        } catch (ActivityNotFoundException fallbackEx) {
                            Toast.makeText(WebViewActivity.this, "Unable to open link.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    return true;
                }
            }

            private String getFallbackUrl(String intentUrl) {
                try {
                    Intent intent = Intent.parseUri(intentUrl, Intent.URI_INTENT_SCHEME);
                    return intent.getStringExtra("browser_fallback_url");
                } catch (Exception e) {
                    return "https://www.google.com";
                }
            }
        });

        String url = getIntent().getStringExtra("url");
        if (url != null) {
            webView.loadUrl(url);
        }
    }

    // Handle physical back button for WebView navigation
    @Override
    public void onBackPressed() {
        if (webView != null && webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
