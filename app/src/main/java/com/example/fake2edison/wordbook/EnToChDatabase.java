package com.example.fake2edison.wordbook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by fake2edison on 2017/10/17.
 */

public class EnToChDatabase extends SQLiteOpenHelper {
    public static final String CREATE_En = "create table En ("
            +"english text,"
            +"chinese text)";

    private Context mContext;

    public EnToChDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_En);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}