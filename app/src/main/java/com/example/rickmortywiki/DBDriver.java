package com.example.rickmortywiki;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DBDriver extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="contactDB";
    public static final String TABLE_NAME="characters";

    public static final String KEY_ID="_id";
    public static final String KEY_NAME="name";
    public static final String KEY_GENDER="gender";
    public static final String KEY_STATUS="status";

    public DBDriver(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+ "("+KEY_ID+" integer primary key," + KEY_NAME+
                " text," +KEY_GENDER + " text,"+KEY_STATUS + " text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists "+ TABLE_NAME);
        onCreate(db);
    }
}
