package com.example.aimanrahmat.uccservicerating;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    // Database Version
    public static final int DATABASE_VERSION = 2;

    // Database Name
    public static final String DATABASE_NAME = "RateDB";

    // Record table name
    public static final String TABLE_RATE = "ratetable";

    // Record Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_COUNTER = "counter";
    public static final String KEY_STAR = "star";
    public static final String KEY_COMMENT = "comment";
    public static final String KEY_DATE = "date";

    public DBHandler(PageMain context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DBHandler(PageRating context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DBHandler(Dailyrating context) {
        super(context.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DBHandler(Overallrating context) {
        super(context.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_RATE_TABLE = "CREATE TABLE " + TABLE_RATE + "(" + KEY_ID + " INTEGER PRIMARY KEY," +KEY_COUNTER+ " INTEGER,"+ KEY_STAR
                + " REAL,"+KEY_COMMENT+ " TEXT,"+ KEY_DATE + " TEXT)";
        db.execSQL(CREATE_RATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RATE);

        // Creating tables again
        onCreate(db);
    }

    public void addRating(Record record) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_COUNTER, record.getCounter());
        values.put(KEY_STAR, record.getStar());
        values.put(KEY_COMMENT, record.getComment());
        values.put(KEY_DATE, record.getDate());

        // Inserting Row
        db.insert(TABLE_RATE, null, values);
        db.close(); // Closing database connection
    }

    public List<Record> getAllRecords() {
        List<Record> recordList = new ArrayList<Record>();

        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_RATE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Record record = new Record();
                record.setId(Integer.parseInt(cursor.getString(0)));
                record.setCounter(Integer.parseInt(cursor.getString(1)));
                record.setStar(Float.parseFloat(cursor.getString(2)));
                record.setComment(cursor.getString(3));
                record.setDate(cursor.getString(4));

                // Adding record to list
                recordList.add(record);
            } while (cursor.moveToNext());
        }

        // return record list
        return recordList;
    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteQuery = "DELETE FROM " + TABLE_RATE;
        db.execSQL(deleteQuery);
        db.close();   // Closing database connection
    }

    public float getAvg(SQLiteDatabase db, int counter,String date) {
        float avg=0;

        String selectQuery = "SELECT AVG("+KEY_STAR+") FROM " + TABLE_RATE + " WHERE " + KEY_COUNTER + " = " +counter+ " AND "+ KEY_DATE
                + " LIKE '"+date+ "%'";

        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                avg = Float.parseFloat(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        return avg;
    }

    public float totalAvg(SQLiteDatabase db, int counter) {
        float avg=0;

        String selectQuery = "SELECT AVG("+KEY_STAR+") FROM " + TABLE_RATE + " WHERE " + KEY_COUNTER + " = " +counter;

        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                avg = Float.parseFloat(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        return avg;
    }

    public int getTotal(SQLiteDatabase db,String date) {
        int total=0;

        String selectQuery = "SELECT COUNT(*) FROM " + TABLE_RATE + " WHERE " + KEY_DATE + " LIKE '"+date+ "%'";

        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                total = Integer.parseInt(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        return total;
    }

    public int totalRate(SQLiteDatabase db) {
        int total=0;

        String selectQuery = "SELECT count(*) FROM "+TABLE_RATE;

        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                total = Integer.parseInt(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        return total;
    }

}
