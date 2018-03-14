package com.android.rvkmr.task4.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.rvkmr.task4.Models.LibraryPOJO;

/**
 * Created by rvkmr on 10-03-2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "datum_db";
    private static final String TABLE_NAME = "datum_table";
    private static final String ID = "id";
    private static final String LETTER = "letter";
    private static final String DATA = "data";



    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( "
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
            + LETTER + " TEXT , "
            + DATA + " TEXT )";
    private static String DB_PATH = null;

    Context context;

    public DatabaseHelper(Context context) throws Exception {
        super(context, DB_NAME, null, 1);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

    }

    public void insertDetailsInDatabase(LibraryPOJO libraryPOJO) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(LETTER, libraryPOJO.getLetter());
        values.put(DATA, libraryPOJO.getData());
        db.insert(TABLE_NAME, null, values);
        db.close();

    }
    public void deleteDatabase() {
        SQLiteDatabase db = getReadableDatabase();
        db.delete(TABLE_NAME, null, null);
    }
}
