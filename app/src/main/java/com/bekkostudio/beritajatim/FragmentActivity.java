package com.bekkostudio.beritajatim;

import android.app.Fragment;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.bekkostudio.compactWebview.DefaultSetting;
import com.bekkostudio.compactWebview.SmartWebViewCompact;

public class FragmentActivity extends Fragment {
    SmartWebViewCompact smartWebViewCompact = new SmartWebViewCompact();

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        smartWebViewCompact.onActivityResult(requestCode, resultCode, data);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment, container, false);

        //Permission variables
        smartWebViewCompact.ASWP_JSCRIPT     = true;     //enable JavaScript for webview
        smartWebViewCompact.ASWP_FUPLOAD     = true;     //upload file from webview
        smartWebViewCompact.ASWP_CAMUPLOAD   = true;     //enable upload from camera for photos
        smartWebViewCompact.ASWP_ONLYCAM        = false;	//incase you want only camera files to upload
        smartWebViewCompact.ASWP_MULFILE     = false;    //upload multiple files in webview
        smartWebViewCompact.ASWP_LOCATION    = false;     //track GPS locations
        smartWebViewCompact.ASWP_RATINGS     = false;     //show ratings dialog; auto configured, edit method get_rating() for customizations
        smartWebViewCompact.ASWP_PBAR        = true;     //show progress bar in app
        smartWebViewCompact.ASWP_ZOOM        = false;    //zoom control for webpages view
        smartWebViewCompact.ASWP_SFORM       = true;    //save form cache and auto-fill information
        smartWebViewCompact.ASWP_OFFLINE     = false;    //whether the loading webpages are offline or online
        smartWebViewCompact.ASWP_EXTURL      = false;     //open external url with default browser instead of app webview
        smartWebViewCompact.ASWP_ROOT        = true;    //False if you need to use webview in other intent activity
        smartWebViewCompact.ASWP_SPLASH      = false;    //enable splash screen

        //Configuration variables
        smartWebViewCompact.ASWV_URL          = "https://google.com/"; //complete URL of your website or webpage
        smartWebViewCompact.ASWV_F_TYPE       = "*/*";  //to upload any file type using "*/*"; check file type references for more

        //Rating system variables
        DefaultSetting.ASWR_DAYS            = 3;        //after how many days of usage would you like to show the dialoge
        DefaultSetting.ASWR_TIMES           = 10;       //overall request launch times being ignored
        DefaultSetting.ASWR_INTERVAL        = 2;        //reminding users to rate after days interval

        WebView webView = (WebView) view.findViewById(R.id.msw_view);
        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.msw_progress);
        RelativeLayout splashScreen = (RelativeLayout) view.findViewById(R.id.logosplash); //logosplash or fullsplash
        smartWebViewCompact.onCreate(getActivity(),webView,progressBar,splashScreen);

        return view;
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        smartWebViewCompact.onSaveInstanceState(outState);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            smartWebViewCompact.onRestoreInstanceState(savedInstanceState);
        }
    }
}
