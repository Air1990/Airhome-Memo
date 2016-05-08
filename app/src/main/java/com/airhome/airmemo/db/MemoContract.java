package com.airhome.airmemo.db;

import android.provider.BaseColumns;

/**
 * Created by airhome on 2016/5/8.
 */
public final class MemoContract {
    public MemoContract() {
    }

    public static abstract class MemoEntry implements BaseColumns {
        public static final String TABLE_NAME = "air_memo";
        public static final String COLUMN_TIME = "time";
        public static final String COLUMN_CONTENT = "content";
    }
}
