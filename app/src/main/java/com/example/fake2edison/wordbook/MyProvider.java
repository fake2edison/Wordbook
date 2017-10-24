package com.example.fake2edison.wordbook;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by fake2edison on 2017/10/18.
 */

public class MyProvider extends ContentProvider {

    public static final int TABLE_DIR = 0;
    private EnToChDatabase dbHelper;
    private static UriMatcher uriMatcher;
    public static final String AUTHORITY = "com.example.fake2edison.wordbook.provider";
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,"En",TABLE_DIR);

    }

    @Override
    public boolean onCreate() {
        dbHelper = new EnToChDatabase(getContext(),"En.db",null,1);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)){
            case TABLE_DIR:
                cursor = db.query("En",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            default:
                break;
        }
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        switch ((uriMatcher.match(uri))){
            case TABLE_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.fake2edison.wordbook.provider.En";
            default:
                break;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
