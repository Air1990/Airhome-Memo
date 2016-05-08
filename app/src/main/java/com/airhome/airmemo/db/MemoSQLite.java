package com.airhome.airmemo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.airhome.airmemo.model.Memo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by airhome on 2016/5/8.
 */
public class MemoSQLite {
    private MemoDBHelper mDBHelper;

    public MemoSQLite(Context context) {
        mDBHelper = new MemoDBHelper(context);
    }

    public long insert(String time, String content) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MemoContract.MemoEntry.COLUMN_TIME, time);
        values.put(MemoContract.MemoEntry.COLUMN_CONTENT, content);
        return db.insert(MemoContract.MemoEntry.TABLE_NAME, null, values);
    }

    public List<Memo> getAllMemos() {
        List<Memo> memoList = null;
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        String[] projection = {
                MemoContract.MemoEntry._ID,
                MemoContract.MemoEntry.COLUMN_TIME,
                MemoContract.MemoEntry.COLUMN_CONTENT
        };
        Cursor cursor = db.query(
                MemoContract.MemoEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                MemoContract.MemoEntry._ID + " DESC"
        );
        if (cursor != null) {
            memoList = new ArrayList<>();
            int idIndex = cursor.getColumnIndex(MemoContract.MemoEntry._ID);
            int timeIndex = cursor.getColumnIndex(MemoContract.MemoEntry.COLUMN_TIME);
            int contentIndex = cursor.getColumnIndex(MemoContract.MemoEntry.COLUMN_CONTENT);
            while (cursor.moveToNext()) {
                Memo memo = new Memo();
                memo.setId(cursor.getInt(idIndex));
                memo.setTime(cursor.getString(timeIndex));
                memo.setContent(cursor.getString(contentIndex));
                memoList.add(memo);
            }
            cursor.close();
        }
        return memoList;
    }

    public Memo getMemoById(int id) {
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        Memo memo = null;
        String[] projection = {
                MemoContract.MemoEntry._ID,
                MemoContract.MemoEntry.COLUMN_TIME,
                MemoContract.MemoEntry.COLUMN_CONTENT
        };
        Cursor cursor = db.query(
                MemoContract.MemoEntry.TABLE_NAME,
                projection,
                MemoContract.MemoEntry._ID + "=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null
        );
        if (cursor != null && cursor.moveToFirst()) {
            memo = new Memo();
            memo.setId(cursor.getInt(cursor.getColumnIndex(MemoContract.MemoEntry._ID)));
            memo.setTime(cursor.getString(cursor.getColumnIndex(MemoContract.MemoEntry.COLUMN_TIME)));
            memo.setContent(cursor.getString(cursor.getColumnIndex(MemoContract.MemoEntry.COLUMN_CONTENT)));
            cursor.close();
        }
        return memo;
    }

    public long delete(int id) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        long result = db.delete(MemoContract.MemoEntry.TABLE_NAME,
                MemoContract.MemoEntry._ID + "=?",
                new String[]{String.valueOf(id)});
        return result;
    }
}
