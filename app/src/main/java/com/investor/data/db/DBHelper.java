package com.investor.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.autoax.driver.db.tables.AdsCount_class;
import com.autoax.driver.db.tables.TimeEntry_class;


/**
 * Created by Nandhu on 5/12/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "AutoExDb";
    public static final int DBVERSION = 2;

    SQLiteDatabase CurrentDB = null;
    Boolean isCreating = false;


    public DBHelper(Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        isCreating = true;
        CurrentDB = db;

        AdsCount_class.onCreate(db);
        TimeEntry_class.onCreate(db);
        isCreating = false;
        CurrentDB = null;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        isCreating = true;
        CurrentDB = db;

        AdsCount_class.onUpgrade(db,oldVersion, newVersion);
        TimeEntry_class.onUpgrade(db,oldVersion,newVersion);

        isCreating=false;
        CurrentDB = null;

    }


    @Override
    public SQLiteDatabase getReadableDatabase()
    {
        if(isCreating && CurrentDB!=null)
            return CurrentDB;
        return super.getReadableDatabase();
    }

    @Override
    public SQLiteDatabase getWritableDatabase()
    {
        if(isCreating && CurrentDB!=null)
            return CurrentDB;
        return super.getWritableDatabase();
    }

}
