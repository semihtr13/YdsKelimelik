package com.setsoft.ydskelimelik.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.setsoft.ydskelimelik.model.Word;
import com.setsoft.ydskelimelik.util.IConstant;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class WordDAO implements IBaseDao<Word>, IConstant.IDBConstant {

    private List<Word> wordList;
    private WordHelper wordHelper;


    public WordDAO(Context context) {
        wordList = new ArrayList<>();
        wordHelper = new WordHelper(context);
    }


    @Override
    public boolean insertWord(Word type) {
        SQLiteDatabase sqLiteDatabase = wordHelper.getWritableDatabase();
        String insertDate = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        ContentValues contentValues = new ContentValues();
        contentValues.put(TURKISH, type.getTurkish());
        contentValues.put(ENGLISH, type.getEnglish());
        contentValues.put(STATE, 0);
        contentValues.put(INSERTDATE, Integer.parseInt(insertDate));
        sqLiteDatabase.insertOrThrow(TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
        return false;
    }

    @Override
    public List<Word> allWordList() {
        wordList.clear();
        SQLiteDatabase sqLiteDatabase = wordHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME+" ORDER BY ID DESC", null);
        while (cursor.moveToNext()) {
            Word word = new Word(
                    cursor.getInt(cursor.getColumnIndex(ID)),
                    cursor.getString(cursor.getColumnIndex(TURKISH)),
                    cursor.getString(cursor.getColumnIndex(ENGLISH)),
                    cursor.getInt(cursor.getColumnIndex(STATE)),
                    cursor.getInt(cursor.getColumnIndex(LEARNDATE)),
                    cursor.getInt(cursor.getColumnIndex(INSERTDATE)));

            wordList.add(word);

        }
        return wordList;
    }

    @Override
    public boolean updateWord(Word type, int id) {

        SQLiteDatabase sqLiteDatabase = wordHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(TURKISH, type.getTurkish());
        contentValues.put(ENGLISH, type.getEnglish());
        sqLiteDatabase.update(TABLE_NAME, contentValues, ID + "=?", new String[]{String.valueOf(id)});

        sqLiteDatabase.close();

        return false;
    }

    @Override
    public boolean deleteAllWords() {

        return false;

    }

    @Override
    public boolean deleteWord(int id) {
        SQLiteDatabase sqLiteDatabase = wordHelper.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, ID + "=?", new String[]{String.valueOf(id)});

        sqLiteDatabase.close();
        return false;
    }

    @Override
    public boolean stateUpdateWord(int id, int state) {
        SQLiteDatabase sqLiteDatabase = wordHelper.getWritableDatabase();
        String learndate = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        ContentValues contentValues = new ContentValues();
        contentValues.put(STATE, state);
        contentValues.put(LEARNDATE, Integer.parseInt(learndate));
        sqLiteDatabase.update(TABLE_NAME, contentValues, ID + "=?", new String[]{String.valueOf(id)});

        sqLiteDatabase.close();
        return false;
    }

    @Override
    public List<Word> getTodayDateList() {
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        SQLiteDatabase sqLiteDatabase = wordHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + INSERTDATE + " = " + date+" ORDER BY ID DESC", null);
        wordList.clear();
        while (cursor.moveToNext()) {
            Word word = new Word(
                    cursor.getInt(cursor.getColumnIndex(ID)),
                    cursor.getString(cursor.getColumnIndex(TURKISH)),
                    cursor.getString(cursor.getColumnIndex(ENGLISH)),
                    cursor.getInt(cursor.getColumnIndex(STATE)),
                    cursor.getInt(cursor.getColumnIndex(LEARNDATE)),
                    cursor.getInt(cursor.getColumnIndex(INSERTDATE)));

            wordList.add(word);

        }
        return wordList;
    }

    @Override
    public List<Word> getWeakDateList() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.WEEK_OF_MONTH, -1);
        Date preMonthDate = cal.getTime();
        String date = format.format(preMonthDate);
        SQLiteDatabase sqLiteDatabase = wordHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + INSERTDATE + " > " + date+" ORDER BY ID DESC", null);
        wordList.clear();
        while (cursor.moveToNext()) {
            Word word = new Word(
                    cursor.getInt(cursor.getColumnIndex(ID)),
                    cursor.getString(cursor.getColumnIndex(TURKISH)),
                    cursor.getString(cursor.getColumnIndex(ENGLISH)),
                    cursor.getInt(cursor.getColumnIndex(STATE)),
                    cursor.getInt(cursor.getColumnIndex(LEARNDATE)),
                    cursor.getInt(cursor.getColumnIndex(INSERTDATE)));

            wordList.add(word);

        }
        return wordList;


    }

    @Override
    public List<Word> getMonthDateList() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        Date preMonthDate = cal.getTime();
        String date = format.format(preMonthDate);
        wordList.clear();
        SQLiteDatabase sqLiteDatabase = wordHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + INSERTDATE + " > " + date+" ORDER BY ID DESC", null);
        while (cursor.moveToNext()) {
            Word word = new Word(
                    cursor.getInt(cursor.getColumnIndex(ID)),
                    cursor.getString(cursor.getColumnIndex(TURKISH)),
                    cursor.getString(cursor.getColumnIndex(ENGLISH)),
                    cursor.getInt(cursor.getColumnIndex(STATE)),
                    cursor.getInt(cursor.getColumnIndex(LEARNDATE)),
                    cursor.getInt(cursor.getColumnIndex(INSERTDATE)));

            wordList.add(word);

        }
        return wordList;
    }

    @Override
    public List<Word> findByState(int state) {
        SQLiteDatabase sqLiteDatabase = wordHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + STATE + " = " + state+" ORDER BY ID DESC", null);
        while (cursor.moveToNext()) {
            Word word = new Word(
                    cursor.getInt(cursor.getColumnIndex(ID)),
                    cursor.getString(cursor.getColumnIndex(TURKISH)),
                    cursor.getString(cursor.getColumnIndex(ENGLISH)),
                    cursor.getInt(cursor.getColumnIndex(STATE)),
                    cursor.getInt(cursor.getColumnIndex(LEARNDATE)),
                    cursor.getInt(cursor.getColumnIndex(INSERTDATE)));

            wordList.add(word);

        }
        return wordList;
    }

    @Override
    public List<Word> findByDateAndState(int date1, int state) {
        SQLiteDatabase sqLiteDatabase = wordHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + INSERTDATE + " = " + date1 + " AND " + STATE + " = " + state, null);
        while (cursor.moveToNext()) {
            Word word = new Word(
                    cursor.getInt(cursor.getColumnIndex(ID)),
                    cursor.getString(cursor.getColumnIndex(TURKISH)),
                    cursor.getString(cursor.getColumnIndex(ENGLISH)),
                    cursor.getInt(cursor.getColumnIndex(STATE)),
                    cursor.getInt(cursor.getColumnIndex(LEARNDATE)),
                    cursor.getInt(cursor.getColumnIndex(INSERTDATE)));

            wordList.add(word);

        }
        return wordList;
    }

    @Override
    public int countAllWord() {
        SQLiteDatabase sqLiteDatabase = wordHelper.getWritableDatabase();
        int count = 0;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT COUNT(*) as total FROM " + TABLE_NAME, null);
        while (cursor.moveToNext()) {
            count = cursor.getInt(cursor.getColumnIndex("total"));
        }
        return count;
    }

    @Override
    public int countStateWord() {
        SQLiteDatabase sqLiteDatabase = wordHelper.getWritableDatabase();
        int count = 0;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT COUNT(*) as total FROM " + TABLE_NAME + " WHERE " + STATE + " = 1", null);
        while (cursor.moveToNext()) {
            count = cursor.getInt(cursor.getColumnIndex("total"));
        }
        return count;
    }

    @Override
    public int countUnstateWord() {
        SQLiteDatabase sqLiteDatabase = wordHelper.getWritableDatabase();
        int count = 0;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT COUNT(*) as total FROM " + TABLE_NAME + " WHERE " + STATE + " = 0", null);
        while (cursor.moveToNext()) {
            count = cursor.getInt(cursor.getColumnIndex("total"));
        }
        return count;
    }

    @Override
    public int levelStatus() {
        SQLiteDatabase sqLiteDatabase = wordHelper.getWritableDatabase();
        int total = 0;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT COUNT(*) as total FROM " + TABLE_NAME, null);
        while (cursor.moveToNext()) {
            total = cursor.getInt(cursor.getColumnIndex("total"));
        }
        int state = 0;
        cursor = sqLiteDatabase.rawQuery("SELECT COUNT(*) as total FROM " + TABLE_NAME + " WHERE " + STATE + " = 1", null);
        while (cursor.moveToNext()) {
            state = cursor.getInt(cursor.getColumnIndex("total"));
        }
        int level = 0;
        if (total != 0) {
            level = (100 * state) / total;
        }

        return level;
    }
}
