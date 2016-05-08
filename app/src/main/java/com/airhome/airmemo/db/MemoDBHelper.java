package com.airhome.airmemo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by airhome on 2016/5/8.
 */
public class MemoDBHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "air_memo.db";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + MemoContract.MemoEntry.TABLE_NAME + " (" +
                    MemoContract.MemoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    MemoContract.MemoEntry.COLUMN_TIME + " TEXT," +
                    MemoContract.MemoEntry.COLUMN_CONTENT + " TEXT" +
                    " )";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + MemoContract.MemoEntry.TABLE_NAME;

    public MemoDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
