package com.bekkostudio.beritajatim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.bekkostudio.compactWebview.DefaultSetting;
import com.bekkostudio.compactWebview.SmartWebViewCompact;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    SmartWebViewCompact smartWebViewCompact = new SmartWebViewCompact();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        smartWebViewCompact.onActivityResult(requestCode, resultCode, intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Permission variables
        smartWebViewCompact.ASWP_JSCRIPT     = true;     //enable JavaScript for webview
        smartWebViewCompact.ASWP_FUPLOAD     = true;     //upload file from webview
        smartWebViewCompact.ASWP_CAMUPLOAD   = true;     //enable upload from camera for photos
        smartWebViewCompact.ASWP_ONLYCAM     = false;	//incase you want only camera files to upload
        smartWebViewCompact.ASWP_MULFILE     = false;    //upload multiple files in webview
        smartWebViewCompact.ASWP_LOCATION    = false;     //track GPS locations
        smartWebViewCompact.ASWP_RATINGS     = false;     //show ratings dialog; auto configured, edit method get_rating() for customizations
        smartWebViewCompact.ASWP_PBAR        = true;     //show progress bar in app
        smartWebViewCompact.ASWP_ZOOM        = true;    //zoom control for webpages view
        smartWebViewCompact.ASWP_SFORM       = true;    //save form cache and auto-fill information
        smartWebViewCompact.ASWP_OFFLINE     = false;    //whether the loading webpages are offline or online
        smartWebViewCompact.ASWP_EXTURL      = true;     //open external url with default browser instead of app webview
        smartWebViewCompact.ASWP_ROOT        = true;    //False if you need to use webview in other intent activity
        smartWebViewCompact.ASWP_SPLASH      = true;    //enable splash screen

        //Configuration variables
        smartWebViewCompact.ASWV_URL          = "http://beritajatim.com/"; //complete URL of your website or webpage
        smartWebViewCompact.ASWV_F_TYPE       = "*/*";  //to upload any file type using "*/*"; check file type references for more

        //Rating system variables
        DefaultSetting.ASWR_DAYS            = 3;        //after how many days of usage would you like to show the dialoge
        DefaultSetting.ASWR_TIMES           = 10;       //overall request launch times being ignored
        DefaultSetting.ASWR_INTERVAL        = 2;        //reminding users to rate after days interval

        WebView webView = (WebView) findViewById(R.id.msw_view);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.msw_progress);
        RelativeLayout splashScreen = (RelativeLayout) findViewById(R.id.fullsplash); //logosplash or fullsplash


        if (getIntent().getStringExtra("link")!=null) {
            if (getIntent().getStringExtra("link").length()>5){
                smartWebViewCompact.ASWV_URL = getIntent().getStringExtra("link");
            }
        }

        smartWebViewCompact.onCreate(this,webView,progressBar,splashScreen);

        //firebase
        FirebaseMessaging.getInstance().subscribeToTopic("beritajatim");

    }

    @Override
    public void onBackPressed() {
        smartWebViewCompact.onBackPressed();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState ){
        super.onSaveInstanceState(outState);
        smartWebViewCompact.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        smartWebViewCompact.onRestoreInstanceState(savedInstanceState);
    }
}
