package com.example.vinoteca.views;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.example.vinoteca.interfaces.HelpInterface;
import com.example.vinoteca.presenters.HelpPresenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.vinoteca.R;

public class HelpActivity extends AppCompatActivity implements HelpInterface.View{
    private Context sContext;
    String TAG = "Vinoteca/views/HelpActivity";
    private WebView wv;
    private String helper;
    private HelpInterface.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d(TAG,"Oncreate HelpActivity");
        setContentView(R.layout.activity_help);
        sContext=this;
        presenter = new HelpPresenter(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(R.string.HelpActivity);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Back Button pressed");
                onBackPressed();
            }
        });
        helper= getIntent().getStringExtra("helper");
        ConnectivityManager cM=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nI = cM.getActiveNetworkInfo();
        wv= findViewById(R.id.wV);
        if(nI.isConnected()){
            wv.getSettings().setJavaScriptEnabled(true);
            wv.setWebViewClient(new WebViewClient(){
                @SuppressWarnings("deprecation")
                @Override
                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                    Toast.makeText(HelpActivity.this, description, Toast.LENGTH_SHORT).show();
                }
                @TargetApi(android.os.Build.VERSION_CODES.M)
                @Override
                public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                    // Redirect to deprecated method, so you can use it in all SDK versions
                    onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
                }
            });
            switch (helper){
                case "":
                    wv.loadUrl("https://alortegadam.github.io/Vinoteca");
                    break;
                case "list":
                    wv.loadUrl("https://alortegadam.github.io/Vinoteca/pages/list");
                    break;
                case "form":
                    wv.loadUrl("https://alortegadam.github.io/Vinoteca/pages/form");
                    break;
                case "search":
                    wv.loadUrl("https://alortegadam.github.io/Vinoteca/pages/search");
            }
        }else{
            presenter.error();
        }
    }
    @Override
    public void errorConnection(){
        AlertDialog.Builder builder = new AlertDialog.Builder(sContext);
        builder.setTitle(R.string.ErrorConnection);
        builder.setMessage(R.string.error_message);

        //Accept Button
        builder.setPositiveButton(R.string.Aceptar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}