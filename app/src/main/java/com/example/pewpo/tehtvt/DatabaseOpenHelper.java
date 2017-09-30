package com.example.pewpo.tehtvt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Created by Samppa on 30.9.2017.
 */

public class DatabaseOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "PTM_database3";
    private final String DATABASE_TABLE = "shop";
    private final String PRODUCT = "product";
    private final String PRICE = "price";
    private final String COUNT = "itemcount";

    public DatabaseOpenHelper(Context context) {
        // Context, database name, optional cursor factory, database version
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create a new table

        db.execSQL("CREATE TABLE "+DATABASE_TABLE+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+PRODUCT+" TEXT, "+PRICE+" REAL, "+COUNT+" REAL);");
        // create sample data
        ContentValues values = new ContentValues();
        values.put(PRODUCT, "Omena");
        values.put(PRICE, 5.0);
        values.put(COUNT, 3.0);
        // insert data to database, name of table, "Nullcolumnhack", values
        db.insert(DATABASE_TABLE, null, values);
        // a more data...
        values.put(PRODUCT, "Omppu");
        values.put(PRICE, 2.0);
        values.put(COUNT, 7.0);
        db.insert(DATABASE_TABLE, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
        onCreate(db);
    }
}
/*public class DatabaseOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "CustomDb";
    private final String DATABASE_TABLE = "Shoplist";
    private final String PRODUCT = "product";
    private final String COUNT = "count";
    private final String PRICE =  "price";
    SQLiteDatabase db;
    ListView listView;
    Cursor cursor;

    public DatabaseOpenHelper(Context context) {
        // Context, database name, optional cursor factory, database version
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // create a new table
        db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
       db.execSQL("CREATE TABLE "+DATABASE_TABLE+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+PRODUCT+" TEXT, "+COUNT+" REAL"+PRICE+" REAL);");
        db.exe2
        // create sample data
        ContentValues values = new ContentValues();
        values.put(PRODUCT, "Omena");
        values.put(COUNT, 1);
        values.put(PRICE, 0.2);
        // insert data to database, name of table, "Nullcolumnhack", values
        db.insert(DATABASE_TABLE, null, values);
        // a more data...
        values.put(PRODUCT, "banaani");
        values.put(COUNT, 3);
        values.put(PRICE, 2.0);

        db.insert(DATABASE_TABLE, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
        onCreate(db);
    }



}
*/