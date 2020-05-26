package com.setsoft.ydskelimelik.model.worddao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.setsoft.ydskelimelik.util.IConstant;

public class WordHelper extends SQLiteOpenHelper implements IConstant.IDBConstant {


    public WordHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME+" " +
                "(ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "TURKISH TEXT, " +
                "ENGLISH TEXT, " +
                "STATE INTEGER, " +
                "LEARNDATE INTEGER, " +
                "INSERTDATE INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS words");
        onCreate(sqLiteDatabase);

    }
}
