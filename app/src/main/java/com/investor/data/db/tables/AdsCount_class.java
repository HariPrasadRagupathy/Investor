package com.investor.data.db.tables;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.autoax.driver.db.model.Ads_Details;
import com.autoax.driver.db.model.IsDataAvailableTbl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nandhu on 5/12/2017.
 */

public class AdsCount_class {

    //CREATE TABLE "Movie" ("_id" INTEGER PRIMARY KEY  NOT NULL , "moviename" TEXT, "description" TEXT)

    public static final String TABLE_NAME = "AdsCount";
    //Column Table
    public static final String ID_COLUMN = "_id";
    public static final String ADID_COLUMN = "adId";
    public static final String ADNAME_COLUMN = "adName";
    public static final String ADPLAYEDDATE_COLUMN = "playedDate";
    public static final String ADPLAYEDTIME_COLUMN = "playedTime";
    //SQL QUERY
    //public static final String CREATE_TABLE = "CREATE TABLE \"" + TABLE_NAME + "\" (\"" + ID_COLUMN + "\" INTEGER PRIMARY KEY  NOT NULL , \"" + ADID_COLUMN + "\" TEXT, \"" + ADNAME_COLUMN + "\" TEXT)";

    public static final String CREATE_TABLE = "CREATE TABLE `"+TABLE_NAME+"` (`"+ID_COLUMN+"` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,`"+ADID_COLUMN+"` TEXT NOT NULL, `"+ADNAME_COLUMN+"`TEXT NOT NULL,`"+ADPLAYEDDATE_COLUMN+"` TEXT NOT NULL,`"+ADPLAYEDTIME_COLUMN+"` TEXT NOT NULL);";


    public static void onCreate(SQLiteDatabase db) {
        //create the table
        db.execSQL(CREATE_TABLE);
    }

    public static void onUpgrade(SQLiteDatabase db, int newversion, int oldversion) {

        //Drop Table If exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        //call on create
        onCreate(db);
    }

    //Insert data into table
    public static long addIntoMovieTable(SQLiteDatabase db, Ads_Details ads) {
        long result = 0;

        ContentValues values = new ContentValues();
        //values.put(ID_COLUMN,"");
        values.put(ADID_COLUMN, ads.getAdId());
        values.put(ADNAME_COLUMN, ads.getAdName());
        values.put(ADPLAYEDDATE_COLUMN, ads.getPlayedDate());
        values.put(ADPLAYEDTIME_COLUMN, ads.getPlayedTime());

        result = db.insert(TABLE_NAME, null, values);


        return result;

    }


    //Update data in the table
    /*public static long updateMovieTable(SQLiteDatabase db, Movie movie) {
        long result = 0;


        return result;
    }*/

    //delete data from the table
    public static long deleteData_AdsTables(SQLiteDatabase db, String query) {
        long result = 0;
        String Query = "delete from "+TABLE_NAME+" "+query;
        Log.e("isAvailable",Query+"");
        Cursor cursor = db.rawQuery(Query,null);
        if(cursor.getCount()>0)
        {
           result =1;
        }

        return result;
    }


    //fetch single record from the table
    /*public static Movie fetchsingle_MovieTable(SQLiteDatabase db, int movie_id) {
        Movie movie = new Movie();

        return movie;
    }*/


    public static List<Ads_Details> fetch_AdsTables(SQLiteDatabase db, String query) {
        List<Ads_Details> adsList = new ArrayList<Ads_Details>();
        Ads_Details adDetail = new Ads_Details();

        String Query = "select * from "+TABLE_NAME+" "+query;
        Log.e("isAvailable",Query+"");
        Cursor cursor = db.rawQuery(Query,null);
        if(cursor.getCount()>0)
        {
            cursor.moveToFirst();
            do {
                adDetail = new Ads_Details();
                adDetail.setId(cursor.getInt(0));
                adDetail.setAdId(cursor.getString(1));
                adDetail.setAdName(cursor.getString(2));
                adDetail.setPlayedDate(cursor.getString(3));
                adDetail.setPlayedTime(cursor.getString(4));

                adsList.add(adDetail);
            cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }


        return adsList;

    }


    public static List<Ads_Details> fetch_AdsTables_wholequery(SQLiteDatabase db, String firstquery, String secondquery) {
        List<Ads_Details> adsList = new ArrayList<Ads_Details>();
        Ads_Details adDetail = new Ads_Details();

        String Query = "select "+firstquery+" from "+TABLE_NAME+" "+secondquery;
        Log.e("isAvailable",Query+"");
        Cursor cursor = db.rawQuery(Query,null);
        if(cursor.getCount()>0)
        {
            cursor.moveToFirst();
            do {
                adDetail = new Ads_Details();
                adDetail.setId(cursor.getInt(0));
                adDetail.setAdId(cursor.getString(1));
                adDetail.setAdName(cursor.getString(2));
                adDetail.setPlayedDate(cursor.getString(3));
                adDetail.setPlayedTime(cursor.getString(4));
                adDetail.setTotalPlayCount(cursor.getInt(5));

                adsList.add(adDetail);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }


        return adsList;

    }


    //fetch multiple record from the table
    /*public static List<Movie> fetch_MovieTables(SQLiteDatabase db, String query) {
        List<Movie> movieList = new ArrayList<Movie>();
        Movie movie = new Movie();

        String Query = "select * from "+TABLE_NAME+" "+query;
        Log.e("isAvailable",Query+"");
        Cursor cursor = db.rawQuery(Query,null);
        if(cursor.getCount()>0)
        {
            cursor.moveToFirst();
            do {
                movie = new Movie();
                movie.setMovieId(cursor.getInt(0));
                movie.setMovieName(cursor.getString(1));
                movie.setMovieDescription(cursor.getString(2));
                movieList.add(movie);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }


        return movieList;
    }*/

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


}
