package com.example.udhaya.logindemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;

public class SportsActivity extends AppCompatActivity {

    private ImageView iv1,iv2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports);

        iv1= (ImageView)findViewById(R.id.volly);
        iv2= (ImageView)findViewById(R.id.sports);
    }


}
