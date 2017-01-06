package com.xaut.example.myapi.webs;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xaut.example.myapi.R;
import com.xaut.example.myapi.utils.AppConstants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends AppCompatActivity {
    @BindView(R.id.webview)
    com.xaut.example.myapi.webs.ProgressWebView webview;

    private String webviewUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        // Let's display the progress in the activity title bar, like the
        // browser app does.

        webviewUrl = this.getIntent().getStringExtra(AppConstants.WEB_URL);
        webview.loadUrl(webviewUrl);
        webview.setFocusable(true);
        webview.setFocusableInTouchMode(true);
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
//        webview.setWebViewClient(
//                new WebViewClient() {
//
//                    @Override
//                    public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                        super.onPageStarted(view, url, favicon);
//                    }
//
//                    @Override
//                    public void onPageFinished(WebView view, String url) {
//                        super.onPageFinished(view, url);
////                        LogUtil.d("onPageFinished=");
//                    }
//
//                    @Override
//                    public void onPageCommitVisible(WebView view, String url) {
//                        super.onPageCommitVisible(view, url);
//                    }
//
//                    @Override
//                    public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
//                        super.onReceivedHttpError(view, request, errorResponse);
////                        LogUtil.d("onReceivedHttpError");
//                    }
//
//                    @Override
//                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                        webview.setVisibility(View.VISIBLE);
////                        LogUtil.d("shouldOverrideUrlLoading");
//                        view.loadUrl(url);
//                        return super.shouldOverrideUrlLoading(view, url);
////                        return true;
//                    }
//
//                    @Override
//                    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
//                        // Handle the error
////                        LogUtil.d("onReceivedError errorCode=" + errorCode);
//                        webview.setVisibility(View.GONE);
//
//                    }
//
//                    @Override
//                    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
//                        super.onReceivedError(view, request, error);
//                        //表示此方法无效
//                    }
//                }
//        );
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((KeyEvent.KEYCODE_BACK == keyCode) && webview.canGoBack()) {
            webview.goBack();
            return true;
        }
//        if (KeyEvent.)
        finish();
        return super.onKeyDown(keyCode, event);
    }
}
