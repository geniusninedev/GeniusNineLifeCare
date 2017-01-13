package com.geniusnine.android.geniusninelifecare.Helper;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pravin on 11/29/2016.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final int VERSION = 1;
    public static final String DB_NAME = "geniusninelifecare";
    public static final String TABLE_PATIENT_INFORMATION = "patient_information";
    public static final String COLUMN_PATIENT_ID = "patient_id";
    public static final String COLUMN_PATIENT_PROFILE_PICTURE = "patient_profile_picture";
    public static final String COLUMN_PATIENT_NAME = "patient_name";
    public static final String COLUMN_PATIENT_MOBILE = "patient_mobile";
    public static final String COLUMN_PATIENT_PASSWORD = "patient_password";
    public static final String COLUMN_PATIENT_EMAIL = "patient_email";
    public static final String COLUMN_PATIENT_GENDER = "patient_gender";
    public static final String COLUMN_PATIENT_AGE = "patient_age";
    public static final String COLUMN_PATIENT_HEIGHT = "patient_height";
    public static final String COLUMN_PATIENT_WEIGHT = "patient_weight";
    public static final String COLUMN_PATIENT_BLOOD_GROUP = "patient_blood_group";
    public static final String COLUMN_PATIENT_ADDRESS = "patient_address";
    public static final String COLUMN_PATIENT_PINCODE = "patient_pincode";
    public static final String COLUMN_PATIENT_REGISRTION_DATE = "date";

    private DBHelper DBHelper;

    private static final int DB_VERSION = 1;
    SQLiteDatabase db;

    public DBHelper(Activity context1) {
        super(context1, DB_NAME, null, DB_VERSION);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_PATIENT_INFORMATION
                + "(" +COLUMN_PATIENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_PATIENT_PROFILE_PICTURE + " BLOB, "
                + COLUMN_PATIENT_NAME + " VARCHAR, "
                + COLUMN_PATIENT_MOBILE + " INTEGER,"
                + COLUMN_PATIENT_PASSWORD + " VARCHAR,"
                + COLUMN_PATIENT_EMAIL + " VARCHAR,"
                + COLUMN_PATIENT_GENDER + " VARCHAR,"
                + COLUMN_PATIENT_AGE + " INTEGER,"
                + COLUMN_PATIENT_HEIGHT + " DOUBLE,"
                + COLUMN_PATIENT_WEIGHT + " DOUBLE,"
                + COLUMN_PATIENT_BLOOD_GROUP  + " VARCHAR,"
                + COLUMN_PATIENT_ADDRESS + " VARCHAR,"
                + COLUMN_PATIENT_PINCODE + " INTEGER,"
                + COLUMN_PATIENT_REGISRTION_DATE + " DATE" + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS patient_information";
        db.execSQL(sql);
        //  onCreate(db);
    }
    public boolean addUser(String patientname,String patientmobilenumber,String patientpassword,String patientemail,String patientgender,String patientage,String patientheight,String patientweight,String patientbloodgroup,String patientaddress,String patientpincode,String patientregistrationdate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PATIENT_NAME, patientname);
        contentValues.put(COLUMN_PATIENT_MOBILE, patientmobilenumber);
        contentValues.put(COLUMN_PATIENT_PASSWORD, patientpassword);
        contentValues.put(COLUMN_PATIENT_EMAIL, patientemail);
        contentValues.put(COLUMN_PATIENT_GENDER, patientgender);
        contentValues.put(COLUMN_PATIENT_AGE, patientage);
        contentValues.put(COLUMN_PATIENT_HEIGHT, patientheight);
        contentValues.put(COLUMN_PATIENT_WEIGHT, patientweight);
        contentValues.put(COLUMN_PATIENT_BLOOD_GROUP, patientbloodgroup);
        contentValues.put(COLUMN_PATIENT_ADDRESS, patientaddress);
        contentValues.put(COLUMN_PATIENT_PINCODE, patientpincode);
        contentValues.put(COLUMN_PATIENT_REGISRTION_DATE, patientregistrationdate);
        db.insert(TABLE_PATIENT_INFORMATION, null, contentValues);
        db.close();
        return true;
    }

    public boolean UpdateProfile(int patient_id, byte[] imageBytes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PATIENT_PROFILE_PICTURE, imageBytes);
        db.update(TABLE_PATIENT_INFORMATION,contentValues,COLUMN_PATIENT_ID+"="+"'"+patient_id+"'",null);
        db.close();
        return true;
    }

    // Insert the image to the Sqlite DB
    public void insertImage(int patient_id, byte[] imageBytes) {
       /* SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PATIENT_PROFILE_PICTURE, imageBytes);
        db .insert(TABLE_PATIENT_INFORMATION, null, contentValues);*/
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PATIENT_PROFILE_PICTURE, imageBytes);
        db.update(TABLE_PATIENT_INFORMATION,contentValues,COLUMN_PATIENT_ID+"="+"'"+patient_id+"'",null);
        db.close();
    }

    // Get the image from SQLite DB
    // We will just get the last image we just saved for convenience...
    public byte[] retreiveImageFromDB(int patient_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.query(true, TABLE_PATIENT_INFORMATION, new String[]{COLUMN_PATIENT_PROFILE_PICTURE,}, null, null, null, null, COLUMN_PATIENT_ID , String.valueOf(patient_id));
        if (cur.moveToFirst()) {
            byte[] blob = cur.getBlob(cur.getColumnIndex(COLUMN_PATIENT_PROFILE_PICTURE));
            cur.close();
            return blob;
        }
        cur.close();
        return null;
    }
    //---opens the database---
    public DBHelper open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---
    public void close()
    {
        DBHelper.close();
    }
}




