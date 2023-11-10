package com.example.lengyel_milan_varosok;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "varosok.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "varosok";
    private static final String COL_ID = "id";
    private static final String COL_NAME = "nev";
    private static final String COL_COUNTRY = "orszag";
    private static final String COL_POPULATION = "lakossag";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " TEXT NOT NULL, " +
                COL_COUNTRY + " TEXT NOT NULL, " +
                COL_POPULATION + " INTEGER NOT NULL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean dataRecord(String name, String country, int population) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, name);
        values.put(COL_COUNTRY, country);
        values.put(COL_POPULATION, population);
        long result = db.insert(TABLE_NAME, null, values);

        return result != -1;
    }

    public Cursor dataGet() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME, new String[]{COL_ID, COL_NAME, COL_COUNTRY, COL_POPULATION},
                null, null, null, null, null);
    }

    public Cursor dataGet(String orszagok) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT " + COL_NAME + " FROM " + TABLE_NAME +
                        " WHERE " + COL_COUNTRY + " =? " ,
                new String[]{orszagok});
    }
}

