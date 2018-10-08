package com.investor.data.db.tables;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.autoax.driver.db.model.IsDataAvailableTbl;
import com.autoax.driver.db.model.TimeEntry;
import com.autoax.driver.db.model.TimeEntryDaywise;
import com.autoax.volley.AppController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Vyancorp on 2/13/2018.
 */

public class TimeEntry_class {

    public static final String TABLE_NAME = "TimeEntry";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_STATE = "inputstate";
    public static final String COLUMN_DATETIME = "datetime";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_INTIME = "intime";

    public static final String CREATE_TABLE = "CREATE TABLE `"+TABLE_NAME+"` (`"+COLUMN_ID+"` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,`"+COLUMN_STATE+"` TEXT NOT NULL, `"+COLUMN_DATETIME+"`TEXT NOT NULL,`"+COLUMN_DATE+"` TEXT NOT NULL,`"+COLUMN_INTIME+"` TEXT NOT NULL);";


    public static void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE);
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public static long addIntoTimeEntryTbl(SQLiteDatabase db, TimeEntry entry)
    {
        long result =0;
        ContentValues values = new ContentValues();
        values.put(COLUMN_STATE,entry.getState());
        values.put(COLUMN_DATETIME,entry.getDatetime());
        values.put(COLUMN_DATE,entry.getDate());
        values.put(COLUMN_INTIME,entry.getIntime());
        result=db.insert(TABLE_NAME,null,values);

        return result;
    }

    public static String fetchLastRecord(SQLiteDatabase db) {


        String query = "SELECT * FROM "+TABLE_NAME+" ORDER BY "+COLUMN_ID+" DESC LIMIT 1";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.getCount()>0)
        {
            cursor.moveToFirst();
            Log.e("timeoutin",cursor.getString(cursor.getColumnIndex(COLUMN_STATE)));
            if(cursor.getString(cursor.getColumnIndex(COLUMN_STATE)).equals("S") || cursor.getString(cursor.getColumnIndex(COLUMN_STATE)).equals("I") )
            {
                Log.e("timeoutin",cursor.getString(cursor.getColumnIndex(COLUMN_DATETIME)));
                return cursor.getString(cursor.getColumnIndex(COLUMN_DATETIME));
            }
        }

        return "";
    }

    public static List<TimeEntry> fetchAllRecords(SQLiteDatabase dbobj) {
        List<TimeEntry> timeEntryList = new ArrayList<TimeEntry>();
        TimeEntry timeEntry = new TimeEntry();

        String Query = "select * from "+TABLE_NAME;
        Log.e("isAvailable",Query+"");
        Cursor cursor = dbobj.rawQuery(Query,null);
        if(cursor.getCount()>0)
        {
            cursor.moveToFirst();
            do {
                timeEntry = new TimeEntry();
                timeEntry.setId(cursor.getInt(0));
                timeEntry.setState(cursor.getString(1));
                timeEntry.setDatetime(cursor.getString(2));
                timeEntry.setDate(cursor.getString(3));
                timeEntry.setIntime(cursor.getLong(4));


                timeEntryList.add(timeEntry);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }


        return timeEntryList;

    }


    public static List<TimeEntryDaywise> fetchDayWise(SQLiteDatabase db)
    {
        List<TimeEntryDaywise> timeEntryDaywiseList = new ArrayList<>();
        TimeEntryDaywise timeEntryDaywise = new TimeEntryDaywise();

        String query = "select date,sum(intime) from "+TABLE_NAME+" group by date";
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.getCount()>0)
        {
            cursor.moveToFirst();
            do
            {
                timeEntryDaywise = new TimeEntryDaywise();
                timeEntryDaywise.setDate(cursor.getString(0));
                timeEntryDaywise.setPlayedsec(cursor.getString(1));
                timeEntryDaywiseList.add(timeEntryDaywise);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }

        return timeEntryDaywiseList;
    }



    public static String fetchDayWiseSingle(SQLiteDatabase db, AppController myApp)
    {
        List<TimeEntryDaywise> timeEntryDaywiseList = new ArrayList<>();
        TimeEntryDaywise timeEntryDaywise = new TimeEntryDaywise();

        String totTime = "0";

        Date curDate = new Date();
        SimpleDateFormat Dateformat = new SimpleDateFormat("dd-MM-yyyy");
        String datetostr = Dateformat.format(curDate);

      //  SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");

        String query = "select sum(intime) from "+TABLE_NAME+" where date = '"+datetostr+"' group by date";
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.getCount()>0)
        {
            cursor.moveToFirst();
            totTime=cursor.getString(0);

            /*do
            {
                timeEntryDaywise = new TimeEntryDaywise();
                timeEntryDaywise.setDate(cursor.getString(0));
                timeEntryDaywise.setPlayedsec(cursor.getString(1));
                timeEntryDaywiseList.add(timeEntryDaywise);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());*/
        }
       // AppController myApp = (AppController)applicationContext.getApplication();
      /*  if (!myApp.wasInBackground) {
            query = "select " + COLUMN_STATE + "," + COLUMN_DATETIME + "," + COLUMN_DATE + " from " + TABLE_NAME + " where date = '" + datetostr + "' ORDER BY " + COLUMN_ID + " DESC LIMIT 1";
            cursor = db.rawQuery(query, null);
            Log.e("intime", query);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                Log.e("intime", cursor.getString(0) + ">" + cursor.getString(1) + ">" + cursor.getString(2));
                // return cursor.getString(0);
            *//*do
            {
                timeEntryDaywise = new TimeEntryDaywise();
                timeEntryDaywise.setDate(cursor.getString(0));
                timeEntryDaywise.setPlayedsec(cursor.getString(1));
                timeEntryDaywiseList.add(timeEntryDaywise);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());*//*
            }
        }
        else
        {
            Log.e("intime", "out");
        }*/
        return totTime;
    }


    public static List<TimeEntryDaywise> fetch_AdsTables_wholequery(SQLiteDatabase db, String firstquery, String secondquery) {
        List<TimeEntryDaywise> timeEntryDaywiseList = new ArrayList<TimeEntryDaywise>();
        TimeEntryDaywise timeEntryDaywise = new TimeEntryDaywise();

        String Query = "select "+firstquery+" from "+TABLE_NAME+" "+secondquery;
        Log.e("isAvailable",Query+"");
        Cursor cursor = db.rawQuery(Query,null);
        if(cursor.getCount()>0)
        {
            cursor.moveToFirst();
            do {
                timeEntryDaywise = new TimeEntryDaywise();
                timeEntryDaywise.setDate(cursor.getString(0));
                timeEntryDaywise.setPlayedsec(cursor.getString(1));
                /*adDetail.setId(cursor.getInt(0));
                adDetail.setAdId(cursor.getString(1));
                adDetail.setAdName(cursor.getString(2));
                adDetail.setPlayedDate(cursor.getString(3));
                adDetail.setPlayedTime(cursor.getString(4));
                adDetail.setTotalPlayCount(cursor.getInt(5));*/

                timeEntryDaywiseList.add(timeEntryDaywise);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }


        return timeEntryDaywiseList;

    }

    public static IsDataAvailableTbl isDataAvaliable_AdsTable(SQLiteDatabase db, String query) {
        IsDataAvailableTbl isAvailable = new IsDataAvailableTbl();

        String Query = "select count(*) from " + TABLE_NAME+" "+query;
        Cursor cursor = db.rawQuery(Query, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            isAvailable.setAvailable(true);
            isAvailable.setCount(Integer.parseInt(cursor.getString(0)));
        }

        return isAvailable;
    }


    public static long deleteData_DayWiseTables(SQLiteDatabase dbobj, String query) {
        long result = 0;
        String Query = "delete from "+TABLE_NAME+" "+query;
        Log.e("isAvailable",Query+"");
        Cursor cursor = dbobj.rawQuery(Query,null);
        if(cursor.getCount()>0)
        {
            result =1;
        }

        return result;

    }
}
