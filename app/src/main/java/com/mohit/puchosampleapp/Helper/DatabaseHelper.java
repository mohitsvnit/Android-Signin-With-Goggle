package com.mohit.puchosampleapp.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.design.widget.TabLayout;

import com.mohit.puchosampleapp.Objects.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by mohit on 8/10/16.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String Database_Name = "mohit_Pucho_Sample_App";
    private static String Table_Name = "Pucho_Blog";
    private static String Col_email = "email";
    private static String Col_title = "title";
    private static String Col_details = "details";

    private static String Create_Table_Pucho_Blog = "create table if not exists " + Table_Name + "("
            + Col_email + " text, "
            + Col_title + " text, "
            + Col_details + " text "
            + ")";

    private static String Delete_Table_Pucho_Blog = "drop table if exists " + Table_Name;

    public DatabaseHelper(Context context) {
        super(context, Database_Name,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create_Table_Pucho_Blog);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(Delete_Table_Pucho_Blog);
        onCreate(db);

    }

    public void addData(Data data) {

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Col_email,data.getEmail());
        values.put(Col_title,data.getTitle());
        values.put(Col_details,data.getDetails());

        long id = database.insert(Table_Name,null,values);
        Helper.log("Database Insertion ID: " + String.valueOf(id));
        database.close();
    }

    public List<Data> getUserData(String email) {
        List<Data> dataList = new ArrayList<>();

        SQLiteDatabase database = this.getReadableDatabase();
        String sql = "select * from " + Table_Name + " where " + Col_email + " = ?";
        Cursor cursor = database.rawQuery(sql,new String[]{email});
        if(cursor.moveToFirst()) {
            do {
                dataList.add(getDataFromCursor(cursor));
            }while (cursor.moveToNext());
        }else {
            dataList = Collections.EMPTY_LIST;
        }

        return dataList;
    }

    public Data getDataFromCursor(Cursor cursor) {
        return new Data(cursor.getString(0),cursor.getString(1),cursor.getString(2));
    }
}
