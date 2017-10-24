package com.example.fake2edison.wordbook;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * Created by fake2edison on 2017/10/17.
 */

public class SecondActivity extends AppCompatActivity {
    private Button transBut;
    private EditText englishEd;
    private TextView chineseText;
    private Button wordBut;
    private Button newsBut;
    private Button addBut;
    private SQLiteDatabase db;
    private EnToChDatabase dbHelper;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_main);
        transBut = (Button)findViewById(R.id.transBut);
        englishEd = (EditText)findViewById(R.id.wordEdit);
        chineseText = (TextView)findViewById(R.id.chineseText);
        wordBut = (Button)findViewById(R.id.Word);
        newsBut = (Button)findViewById(R.id.News);
        addBut = (Button)findViewById(R.id.addBut);
        final SockClient sockClient = new SockClient(chineseText);
        sockClient.start();

        dbHelper = new EnToChDatabase(this, "En.db", null, 1);
        db = dbHelper.getWritableDatabase();

        addBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String english = englishEd.getText().toString();
                String chinese = chineseText.getText().toString();
                int tmp = 0;
                Cursor cursor;
                cursor = db.rawQuery("select * from En where english = \""+english+"\"",null);
                if(cursor.moveToFirst()){
                    do{
                        tmp++;
                    }while (cursor.moveToNext());
                }
                if(tmp==0 && !chinese.equals("未能找到") && !chinese.equals("")) {
                    ContentValues cValue = new ContentValues();
                    cValue.put("english", english);
                    cValue.put("chinese", chinese);
                    db.insert("En", null, cValue);
                }

            }
        });

        transBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (R.id.transBut){
                    case R.id.transBut:
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                String content = englishEd.getText().toString();
                                if(!content.equals("")){
                                    sockClient.setOutput(content);
                                }
                            }
                        }).start();
                        break;
                    default:
                        break;
                }

            }
        });
        wordBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(SecondActivity.this,MainActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        });
        newsBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(SecondActivity.this,NewsActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        });


    }
}
