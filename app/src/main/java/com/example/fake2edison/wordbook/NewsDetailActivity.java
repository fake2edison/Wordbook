package com.example.fake2edison.wordbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by fake2edison on 2017/10/24.
 */

public class NewsDetailActivity extends AppCompatActivity {

    private Button wordBut;
    private Button changeBut;
    private Button newsBut;
    private String title;
    private String detail;
    private TextView tv_title;
    private TextView tv_detail;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_detail);
        wordBut = (Button)findViewById(R.id.Word);
        changeBut = (Button)findViewById(R.id.Change);
        newsBut = (Button)findViewById(R.id.News);
        tv_detail = (TextView)findViewById(R.id.newsDetail);
        tv_title = (TextView)findViewById(R.id.newsTitle);
        Intent intent = getIntent();
        title = intent.getStringExtra("newsTitle");
        detail = intent.getStringExtra("newsDetail");
        tv_title.setText(title);
        tv_detail.setText(detail);
        tv_detail.setMovementMethod(ScrollingMovementMethod.getInstance());
        wordBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(NewsDetailActivity.this,MainActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        });
        changeBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(NewsDetailActivity.this,SecondActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        });
        newsBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(NewsDetailActivity.this,NewsActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        });



    }}
