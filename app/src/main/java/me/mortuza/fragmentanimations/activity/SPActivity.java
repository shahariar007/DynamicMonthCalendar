package me.mortuza.fragmentanimations.activity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.TimeZone;

import me.mortuza.fragmentanimations.R;


public class SPActivity extends AppCompatActivity {

    String addressLoc = "";
    boolean x = true;
    boolean y = false;
    String productID;
    private String doctorID;
    private Intent service;

    private String lat = "null";
    private String lon = "null";
    public long inTime;
    public long outTime;

    boolean isPresentation = false;

    public String doctorName = "";

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                lat = bundle.getString("lat");
                lon = bundle.getString("lon");
                Log.d("SlidePresentation", "onReceive: " + lat + lon);
            }
        }
    };

    private WebView webView;

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_presentaion);
        initViews();


        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowContentAccess(true);
        settings.setAllowFileAccess(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setAppCacheEnabled(true);
        settings.getAllowFileAccess();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            settings.setAllowUniversalAccessFromFileURLs(true);
            settings.setAllowFileAccessFromFileURLs(true);
            settings.setMediaPlaybackRequiresUserGesture(true);

        }
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setAppCacheEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setAppCacheMaxSize(1024 * 1024 * 100);
        settings.setAppCachePath(this.getCacheDir().getPath());
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);

        webView.addJavascriptInterface(new WebAppInterface(this), "Android");

        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // chromium, enable hardware acceleration
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            // older android version, disable hardware acceleration
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.d("SPActivity", "onPageStarted: ");
            }

            @Override
            public void onPageFinished(final WebView view, String url) {
                super.onPageFinished(view, url);
                Log.d("SPActivity", "onPageFinished: ");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        webView.loadUrl("javascript:doctorName('" + doctorName + "')");
                        //webView.loadUrl("javascript:(function () { doctorName('Hello World!');})()");
                    }
                }, 1000);

            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
                handler.proceed();
            }
        });

        webView.loadUrl("file:///android_asset/index.html");
    }

    private void initViews() {
        webView = (WebView) findViewById(R.id.web);
        Log.d("SPActivity", "initViews: "+ TimeZone.getDefault());

    }

    public class WebAppInterface {
        Context mContext;

        WebAppInterface(Context argContext) {
            mContext = argContext;
        }

        @JavascriptInterface
        public void showToast(int[] xTime, String[] xPageType) {
            Log.d("WebAppInterface", "showToast: " + xTime.length + "L" + xPageType.length);

            try {
                JSONArray jsonArray = new JSONArray();
                JSONObject detailJson = new JSONObject();
                for (int i = 1; i <= xTime.length; i++) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("PageNo", i);
                    jsonObject.put("PageType", xPageType[i - 1]);
                    jsonObject.put("PromotionTime", xTime[i - 1]);
                    jsonArray.put(jsonObject);
                }
                detailJson.put("Detail", jsonArray);
                Log.d("JsonTest", "createJson: " + detailJson.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

}
