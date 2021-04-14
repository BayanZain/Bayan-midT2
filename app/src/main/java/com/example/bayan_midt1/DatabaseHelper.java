package com.example.bayan_midt1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "users.db";
    public static final String TABLE_NAME = "users_data";
    public static final String COL1 = "ID";
    public static final String COL2 = "USER_NAME";
    public static final String COL3 = "EMAIL";
    public static final String COL4 = "PHONE";

    /* Constructor */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    /* Code runs automatically when the dB is created */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME +
                " (ID TEXT PRIMARY KEY, " +
                " USER_NAME TEXT, EMAIL TEXT, PHONE TEXT)";
        db.execSQL(createTable);
    }

    /* Every time the dB is updated (or upgraded) */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db); }
    /* Basic function to add data. REMEMBER: The fields
    here, must be in accordance with those in
    the onCreate method above.
    */
    public boolean addData(String id, String userName, String email,String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, id);
        contentValues.put(COL2, userName);
        contentValues.put(COL3, email);
        contentValues.put(COL4, phone);
        long result = db.insert(TABLE_NAME, null, contentValues);
        //if data are inserted incorrectly, it will return -1
        if(result == -1) {return false;} else {return true;}
    }
    /* Returns only one result , search for a record*/

    public boolean Delete(String text) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ? OR USER_NAME = ? OR EMAIL = ? OR PHONE = ?", new String[] {text})>0;
    }

    public Cursor find(String search){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME+ " WHERE ID = ? OR USER_NAME = ? OR EMAIL = ? OR PHONE = ?", new
                String[]{search});
        if (data != null)
            data.moveToFirst();
        return data;
    }
    // Return everything inside a specific table
    public Cursor getListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }

}
