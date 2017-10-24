package com.example.fake2edison.wordbook;

import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button changBut;
    private EditText inputTex;
    private ListView wordList;
    private EnToChDatabase dbHelper;
    private List<Word> wordlist = new ArrayList<Word>();
    private List<wordLand> wordlandlist = new ArrayList<wordLand>();
    private String str;//存储当前edittext中的文本
    private SQLiteDatabase db;
    private Button newsBut;
    private TextView WordTransLand;
    private Button delButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int mCurrentOrientation = getResources().getConfiguration().orientation;

        if (mCurrentOrientation == Configuration.ORIENTATION_PORTRAIT) {
            changBut = (Button)findViewById(R.id.Change);
            wordList = (ListView)findViewById(R.id.wordList);
            inputTex = (EditText)findViewById(R.id.inputWord);
            newsBut = (Button)findViewById(R.id.News);
            dbHelper = new EnToChDatabase(this, "En.db", null, 1);
            db = dbHelper.getWritableDatabase();
            Cursor cursor;
            cursor = db.rawQuery("select * from En",null);
            int tmp = 0;
            if(cursor.moveToFirst()){
                do{
                    String word1 = cursor.getString(cursor.getColumnIndex("english"));
                    String word2 = cursor.getString(cursor.getColumnIndex("chinese"));
                    Word wordtemp = new Word(word1,word2);
                    wordlist.add(wordtemp);
                    tmp++;
                }while (cursor.moveToNext());
            }
            WordAdapter adapter = new WordAdapter(MainActivity.this,R.layout.wordlist_item,wordlist);
            if(tmp!=0) {
                wordList.setAdapter(adapter);
            }
            cursor.close();

            //监听输入框
            inputTex.addTextChangedListener(textWatcher);

            changBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this,SecondActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                }
            });
            newsBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this,NewsActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                }
            });

        } else if (mCurrentOrientation == Configuration.ORIENTATION_LANDSCAPE) {//横屏
            WordTransLand = (TextView)findViewById(R.id.wordTransLand);
            wordList = (ListView)findViewById(R.id.wordListLand);
            delButton = (Button)findViewById(R.id.delButtom);
            dbHelper = new EnToChDatabase(this, "En.db", null, 1);
            db = dbHelper.getWritableDatabase();
            Cursor cursor;
            cursor = db.rawQuery("select * from En",null);
            int tmp = 0;
            if(cursor.moveToFirst()){
                do{
                    String word1 = cursor.getString(cursor.getColumnIndex("english"));
                    wordLand wordtemp = new wordLand(word1);
                    wordlandlist.add(wordtemp);
                    tmp++;
                }while (cursor.moveToNext());
            }
            wordLandAdapter adapter = new wordLandAdapter(MainActivity.this,R.layout.wordlistland_item,wordlandlist);
            if(tmp!=0) {
                wordList.setAdapter(adapter);
            }
            cursor.close();

            wordList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    wordLand wordland = wordlandlist.get(position);
                    String chineseword = "";
                    Cursor cursor1;
                    cursor1 = db.rawQuery("select * from En where english = \""+wordland.getWord()+"\"",null);
                    if(cursor1.moveToFirst()){
                        do{
                            chineseword = cursor1.getString(cursor1.getColumnIndex("chinese"));
                        }while(cursor1.moveToNext());
                    }
                    WordTransLand.setText(chineseword);
                }
            });


            delButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String sql = "delete from En where chinese = \""+WordTransLand.getText().toString()+"\"";
                    db.execSQL(sql);
                    wordlandlist.clear();
                    Cursor cursor;
                    cursor = db.rawQuery("select * from En",null);
                    int tmp = 0;
                    if(cursor.moveToFirst()){
                        do{
                            String word1 = cursor.getString(cursor.getColumnIndex("english"));
                            wordLand wordtemp = new wordLand(word1);
                            wordlandlist.add(wordtemp);
                            tmp++;
                        }while (cursor.moveToNext());
                    }
                    wordLandAdapter adapter = new wordLandAdapter(MainActivity.this,R.layout.wordlistland_item,wordlandlist);
                    if(tmp!=0) {
                        wordList.setAdapter(adapter);
                    }
                    cursor.close();
                    WordTransLand.setText("");
                }
            });


        }

    }

    //监听文本变化
    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void afterTextChanged(Editable s) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            str = inputTex.getText().toString();
            Log.i("info",str);
            Log.i("info",inputTex.getText().toString());
            initWord(str);
        }
    };

    private void initWord(String str){
        Cursor cursor;
        cursor = db.rawQuery("select * from En where english like '"+str+"%'",null);
        wordlist.clear();
        if(cursor.moveToFirst()){
            do{
                String word1 = cursor.getString(cursor.getColumnIndex("english"));
                String word2 = cursor.getString(cursor.getColumnIndex("chinese"));
                Word wordtemp = new Word(word1,word2);
                wordlist.add(wordtemp);
            }while (cursor.moveToNext());
        }
        WordAdapter adapter = new WordAdapter(MainActivity.this,R.layout.wordlist_item,wordlist);
        wordList.setAdapter(adapter);
        cursor.close();
    }
}
