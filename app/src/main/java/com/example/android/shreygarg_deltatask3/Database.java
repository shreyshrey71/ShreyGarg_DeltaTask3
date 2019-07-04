package com.example.android.shreygarg_deltatask3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Favourite.db";
    public static final String TABLE_NAME = "Favourite_table";
    public static final String id = "ID";
    public static final String category = "Category";
    public static final String date = "Date";
    public static final String latitude = "Latitude";
    public static final String longitude = "Longitude";
    public static final String street = "Location";
    public static final String ltype = "LocationType";
    public static final String pid = "PID";
    public static final String mode = "Mode";
    Context context;

    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table " + TABLE_NAME + "(ID String Primary Key Unique, Category String, Date String, Latitude String, Longitude String, Location String, LocationType String, Pid String, Mode String)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + TABLE_NAME);
        onCreate(db);
    }

    public long insertData(String identity, String cat, String dat, String lat, String lng, String strt, String loctype, String persid,String mo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(id, identity);
        contentValues.put(category, cat);
        contentValues.put(date, dat);
        contentValues.put(latitude, lat);
        contentValues.put(longitude, lng);
        contentValues.put(street, strt);
        contentValues.put(ltype, loctype);
        contentValues.put(pid, persid);
        contentValues.put(mode, mo);
        long l = db.insert(TABLE_NAME, null, contentValues);
        if (l == -1) {
            Toast.makeText(context, "Already a favourite crime", Toast.LENGTH_SHORT).show();

        } else {

            Toast.makeText(context, "Added to favourite crimes", Toast.LENGTH_SHORT).show();
        }
        return l;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public Cursor check(String ID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" where ID = ?",new String[] {ID},null);
        return res;

    }
    public long Deleterow(String ID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long l = db.delete(TABLE_NAME,"ID = ?",new String[] {ID});

        if(l>0)
            Toast.makeText(context,"Deleted from favourites",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context,"Not found in favourites",Toast.LENGTH_SHORT).show();
        return l;

    }
}
