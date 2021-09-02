package com.techbonkers.arihantjain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        webView = findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Hang on!");

        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        webSettings.setUseWideViewPort(true);
        webView.setInitialScale(1);
        webView.getSettings().setDomStorageEnabled(true);

        webView.loadUrl("http://techyarihant.me/");

        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress < 100) {
                    progressDialog.show();
                }
                if (progress == 100) {
                    progressDialog.dismiss();
                }
            }
        });

        webView.setDownloadListener(new DownloadListener() {

            @Override
            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimetype,
                                        long contentLength) {

//                downloadFile();
                Toast.makeText(getApplicationContext(), "Please use a browser to download", //To notify the Client that the file is being downloaded
                        Toast.LENGTH_LONG).show();

            }
        });
    }
    public void downloadFile(){
        Uri uri = Uri.parse("http://techyarihant.me/assets/pdf/Resume.pdf");
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setTitle("My File");
        request.setDescription("Downloading");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setVisibleInDownloadsUi(false);
        request.setDestinationUri(Uri.parse("file://resume.pdf"));
        DownloadManager downloadmanager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
//        downloadmanager.enqueue(request);
    }
}