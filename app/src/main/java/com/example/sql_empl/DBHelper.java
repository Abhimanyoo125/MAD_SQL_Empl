package com.example.sql_empl;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    // Database Name and Version
    private static final String DATABASE_NAME = "employee.db";
    private static final int DATABASE_VERSION = 1;

    // Table Name
    private static final String TABLE_NAME = "employee";

    // Column Names
    private static final String COLUMN_ID = "eid";
    private static final String COLUMN_NAME = "ename";
    private static final String COLUMN_DEPT = "adept";
    private static final String COLUMN_SALARY = "esalary";

    // Constructor
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_DEPT + " TEXT,"
                + COLUMN_SALARY + " REAL)";
        db.execSQL(CREATE_TABLE);
    }

    // Handle table upgrades
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Insert data into the table
    public boolean insertEmployee(String ename, String dept, double salary) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, ename);
        contentValues.put(COLUMN_DEPT, dept);
        contentValues.put(COLUMN_SALARY, salary);

        long result = db.insert(TABLE_NAME, null, contentValues);

        return result != -1;  // returns true if insert was successful, otherwise false
    }
}
