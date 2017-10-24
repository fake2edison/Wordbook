package com.example.fake2edison.wordbook;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fake2edison on 2017/10/18.
 */

public class NewsActivity extends AppCompatActivity {

    private Button wordBut;
    private Button changeBut;
    private ListView listNews = null;
    private NewsAdapter adapter = null;
    private ArrayList<News> newsArrayList = new ArrayList<News>();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_main);
        wordBut = (Button)findViewById(R.id.Word);
        changeBut = (Button)findViewById(R.id.Change);
        listNews = (ListView)findViewById(R.id.newsList);
        adapter = new NewsAdapter(NewsActivity.this,R.layout.newslist_item,newsArrayList);


        NewsInfo newsInfo = new NewsInfo(listNews,adapter,newsArrayList);
        newsInfo.getNewsInfo();
        listNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News newsone = newsArrayList.get(position);
                Intent intent = new Intent();
                intent.putExtra("newsTitle", newsone.getListNews1());
                intent.putExtra("newsDetail",newsone.getListNews2());
                intent.setClass(NewsActivity.this,NewsDetailActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        });


//        wordList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                wordLand wordland = wordlandlist.get(position);
//                String chineseword = "";
//                Cursor cursor1;
//                cursor1 = db.rawQuery("select * from En where english = \""+wordland.getWord()+"\"",null);
//                if(cursor1.moveToFirst()){
//                    do{
//                        chineseword = cursor1.getString(cursor1.getColumnIndex("chinese"));
//                    }while(cursor1.moveToNext());
//                }
//                WordTransLand.setText(chineseword);
//            }
//        });

        wordBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(NewsActivity.this,MainActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        });
        changeBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(NewsActivity.this,SecondActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        });



    }
}
