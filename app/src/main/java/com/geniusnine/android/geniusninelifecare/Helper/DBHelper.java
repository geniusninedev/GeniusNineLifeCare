package com.geniusnine.android.geniusninelifecare.Helper;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
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


    //database for the doctor
    public static final String TABLE_DOCTOR_INFORMATION = "doctor_information";
    public static final String COLUMN_DOCTOR_ID = "doctor_id";
    public static final String COLUMN_DOCTOR_PROFILE_PICTURE = "doctor_profile_picture";
    public static final String COLUMN_DOCTOR_NAME = "doctor_name";
    public static final String COLUMN_DOCTOR_DEGREE = "doctor_degree";
    public static final String COLUMN_DOCTOR_SPECILIZATION = "doctor_specialization";
    public static final String COLUMN_DOCTOR_EXPERIENCE = "doctor_experience";
    public static final String COLUMN_DOCTOR_FEES = "doctor_fees";
    public static final String COLUMN_DOCTOR_ACHIEVEMENT = "doctor_achievement";
    public static final String COLUMN_DOCTOR_EMAIL = "doctor_email";
    public static final String COLUMN_DOCTOR_FAX = "doctor_fax";
    public static final String COLUMN_DOCTOR_MOBILE = "doctor_mobile";
    public static final String COLUMN_DOCTOR_PASSWORD = "doctor_password";
    public static final String COLUMN_DOCTOR_GENDER = "doctor_gender";
    public static final String COLUMN_DOCTOR_AGE = "doctor_age";
    public static final String COLUMN_DOCTOR_ADDRESS = "doctor_address";
    public static final String COLUMN_DOCTOR_CONNECTED_HOSPITAL = "doctor_connected_hospital";
    public static final String COLUMN_DOCTOR_AVAILABILITY = "doctor_availability";
    public static final String COLUMN_DOCTOR_FACEBOOK = "doctor_facebook";
    public static final String COLUMN_DOCTOR_TWITTER = "doctor_twitter";
    public static final String COLUMN_DOCTOR_REGISRTION_DATE = "date";


    //database table for the book appointment
    public static final String TABLE_BOOK_APPOINTMENT= "book_appointment_information";
    public static final String COLUMN_BOOK_APPOINTMENT_ID = "book_appointment_id";
    public static final String COLUMN_BOOK_APPOINTMENT_DATE = "book_appointment_date";
    public static final String COLUMN_BOOK_APPOINTMENT_TIME = "book_appointment_time";
    public static final String COLUMN_BOOK_APPOINTMENT_REASON = "book_appointment_reason";
    public static final String COLUMN_BOOK_APPOINTMENT_FROM_DAYS = "book_appointment_from_days";
    public static final String COLUMN_BOOK_APPOINTMENT_PATIENT_ID = "patient_id";
    public static final String COLUMN_APPOINTMENT_REGISRTION_DATE = "appointment_registered_date";



    //database table for the categories
    public static final String TABLE_CATEGORY= "category_information";
    public static final String COLUMN_CATEGORY_ID = "category_id";
    public static final String COLUMN_CATEGORY_NAME = "category_name";
    public static final String COLUMN_CATEGORY_IMAGE = "category_image";

    //database table for the Feedback
    public static final String TABLE_FEEDBACK= "feedback_information";
    public static final String COLUMN_FEEDBACK_ID = "feedback_id";
    public static final String COLUMN_FEEDBACK_PATIENT_ID = "patient_id";
    public static final String COLUMN_FEEDBACK_MESSAGE = "feedback_message";
    public static final String COLUMN_FEEDBACK_CHECKING = "feedback_checking";
    public static final String COLUMN_FEEDBACK_APP_RATING = "feedback_app_rating";
    public static final String COLUMN_FEEDBACK_SUGGESTION = "feedback_suggestion";


    private static final int DB_VERSION = 1;

    public DBHelper(Activity context1) {
        super(context1, DB_NAME, null, DB_VERSION);
    }

    private DBHelper DBHelper;
    SQLiteDatabase db;

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
        //Table for the doctor
        String sq2 = "CREATE TABLE IF NOT EXISTS " + TABLE_DOCTOR_INFORMATION
                + "(" +COLUMN_DOCTOR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_DOCTOR_PROFILE_PICTURE + " BLOB, "
                + COLUMN_DOCTOR_NAME + " VARCHAR, "
                + COLUMN_DOCTOR_DEGREE + " VARCHAR,"
                + COLUMN_DOCTOR_SPECILIZATION + " VARCHAR, "
                + COLUMN_DOCTOR_EXPERIENCE + " INTEGER, "
                + COLUMN_DOCTOR_FEES + " INTEGER, "
                + COLUMN_DOCTOR_ACHIEVEMENT + " VARCHAR, "
                + COLUMN_DOCTOR_EMAIL + " VARCHAR, "
                + COLUMN_DOCTOR_FAX + " VARCHAR, "
                + COLUMN_DOCTOR_MOBILE + " INTEGER, "
                + COLUMN_DOCTOR_PASSWORD + " VARCHAR, "
                + COLUMN_DOCTOR_GENDER + " VARCHAR, "
                + COLUMN_DOCTOR_AGE + " INTEGER, "
                + COLUMN_DOCTOR_ADDRESS + " VARCHAR, "
                + COLUMN_DOCTOR_CONNECTED_HOSPITAL + " VARCHAR, "
                + COLUMN_DOCTOR_AVAILABILITY + " DOUBLE, "
                + COLUMN_DOCTOR_FACEBOOK + " VARCHAR, "
                + COLUMN_DOCTOR_TWITTER + " VARCHAR, "
                + COLUMN_DOCTOR_REGISRTION_DATE + " DATE" + ")";
        db.execSQL(sq2);

        //Table for book appointment
        String sq3 = "CREATE TABLE IF NOT EXISTS " + TABLE_BOOK_APPOINTMENT
                + "(" +COLUMN_BOOK_APPOINTMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_BOOK_APPOINTMENT_DATE + " DATE,"
                + COLUMN_BOOK_APPOINTMENT_TIME + " DOUBLE,"
                + COLUMN_BOOK_APPOINTMENT_REASON + " VARCHAR,"
                + COLUMN_BOOK_APPOINTMENT_FROM_DAYS + " VARCHAR,"
                + COLUMN_BOOK_APPOINTMENT_PATIENT_ID + " INTEGER,"
                + COLUMN_APPOINTMENT_REGISRTION_DATE + " DATE" + ")";
        db.execSQL(sq3);


        //Table for book category
        String sq4 = "CREATE TABLE IF NOT EXISTS " + TABLE_CATEGORY
                + "(" +COLUMN_CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_CATEGORY_NAME + " VARCHAR, "
                + COLUMN_CATEGORY_IMAGE + " BLOB"  + ")";
        db.execSQL(sq4);
        //Table for  Feedback
        String sq5 = "CREATE TABLE IF NOT EXISTS " + TABLE_FEEDBACK
                + "(" +COLUMN_FEEDBACK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_FEEDBACK_PATIENT_ID + " INTEGER, "
                + COLUMN_FEEDBACK_MESSAGE + " VARCHAR, "
                +  COLUMN_FEEDBACK_CHECKING  + " INTEGER, "
                + COLUMN_FEEDBACK_APP_RATING + " INTEGER, "
                + COLUMN_FEEDBACK_SUGGESTION + " VARCHAR" + ")";
        db.execSQL(sq5);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS patient_information";
        db.execSQL(sql);

        String sq2 = "DROP TABLE IF EXISTS doctor_information";
        db.execSQL(sq2);

        String sq3 = "DROP TABLE IF EXISTS book_appointment_information";
        db.execSQL(sq3);

        String sq4 = "DROP TABLE IF EXISTS category_information";
        db.execSQL(sq4);

        String sq5 = "DROP TABLE IF EXISTS feedback_information";
        db.execSQL(sq5);
          onCreate(db);
    }
    //insertion of patient patient
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
    //insertion of doctor information
    public boolean addDoctor(String doctorname,String doctordegree,String doctorspecialization,String doctorexperience,String fees,String doctorachievement,String doctoremail,String doctorfax,String doctormobilenumber,String doctorpassword,String doctorgender,String doctorage,String doctoraddress,String doctorconnectedhospital,String doctoravalibility,String doctorfacebook,String doctortwitter,String doctorregistrationdate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_DOCTOR_NAME, doctorname);
        contentValues.put(COLUMN_DOCTOR_DEGREE, doctordegree);
        contentValues.put(COLUMN_DOCTOR_SPECILIZATION, doctorspecialization);
        contentValues.put(COLUMN_DOCTOR_EXPERIENCE, doctorexperience);
        contentValues.put(COLUMN_DOCTOR_FEES, fees);
        contentValues.put(COLUMN_DOCTOR_ACHIEVEMENT, doctorachievement);
        contentValues.put(COLUMN_DOCTOR_EMAIL, doctoremail);
        contentValues.put(COLUMN_DOCTOR_FAX, doctorfax);
        contentValues.put(COLUMN_DOCTOR_MOBILE, doctormobilenumber);
        contentValues.put(COLUMN_DOCTOR_PASSWORD, doctorpassword);
        contentValues.put(COLUMN_DOCTOR_GENDER, doctorgender);
        contentValues.put(COLUMN_DOCTOR_AGE, doctorage);
        contentValues.put(COLUMN_DOCTOR_ADDRESS, doctoraddress);
        contentValues.put(COLUMN_DOCTOR_CONNECTED_HOSPITAL, doctorconnectedhospital);
        contentValues.put(COLUMN_DOCTOR_AVAILABILITY, doctoravalibility);
        contentValues.put(COLUMN_DOCTOR_FACEBOOK, doctorfacebook);
        contentValues.put(COLUMN_DOCTOR_TWITTER, doctortwitter);
        contentValues.put(COLUMN_DOCTOR_REGISRTION_DATE, doctorregistrationdate);
        db.insert(TABLE_DOCTOR_INFORMATION, null, contentValues);
        db.close();
        return true;
    }

    //insertion of book appointment information
    public boolean addBookAppointment(String bookappointmentdate,String bookappointmenttime,String bookappointmentreason,String bookappointmentfromdays,String bookappointmentpatientid,String bookappointmentregistrationdate) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_BOOK_APPOINTMENT_DATE, bookappointmentdate);
        contentValues.put(COLUMN_BOOK_APPOINTMENT_TIME, bookappointmenttime);
        contentValues.put(COLUMN_BOOK_APPOINTMENT_REASON, bookappointmentreason);
        contentValues.put(COLUMN_BOOK_APPOINTMENT_FROM_DAYS, bookappointmentfromdays);
        contentValues.put(COLUMN_BOOK_APPOINTMENT_PATIENT_ID, bookappointmentpatientid);
        contentValues.put(COLUMN_APPOINTMENT_REGISRTION_DATE, bookappointmentregistrationdate);
        db.insert(TABLE_BOOK_APPOINTMENT, null, contentValues);
        db.close();
        return true;
    }

    //insertion of Category information
    public boolean addCategory(String categoryname,byte[] categoryimage) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CATEGORY_NAME, categoryname);
        contentValues.put(COLUMN_CATEGORY_IMAGE, categoryimage);
        db.insert(TABLE_CATEGORY, null, contentValues);
        db.close();
        return true;
    }
    //insertion of Category information
    public boolean submitfeedback(String patient_id,String message,String checking,String rating,String suggestion) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FEEDBACK_PATIENT_ID, patient_id);
        contentValues.put(COLUMN_FEEDBACK_MESSAGE, message);
        contentValues.put(COLUMN_FEEDBACK_CHECKING, checking);
        contentValues.put(COLUMN_FEEDBACK_APP_RATING, rating);
        contentValues.put(COLUMN_FEEDBACK_SUGGESTION, suggestion);
        db.insert(TABLE_FEEDBACK, null, contentValues);
        db.close();
        return true;
    }
// showcategories
public Cursor getCategory() {
    String[] cols = { COLUMN_CATEGORY_ID, COLUMN_CATEGORY_NAME,COLUMN_CATEGORY_IMAGE};
    SQLiteDatabase db = this.getWritableDatabase();
    Cursor c = db.query(TABLE_CATEGORY, cols, null,
            null, null, null, null);
    return c;
}
   /* public List<String> getCategory(){
        List<String> labels = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT * FROM category_information ";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {

            labels.add(cursor.getString(0));
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return labels;
    }*/
    // Adding new UpdateProfile
    public void UpdateProfile(String patient_id,byte[] imageBytes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PATIENT_PROFILE_PICTURE, imageBytes);
        db.update(TABLE_PATIENT_INFORMATION,contentValues,COLUMN_PATIENT_ID+"="+"'"+patient_id+"'",null);
        db.close();

    }

    public boolean UpdateProfileDetails(String patient_id,String patientname,String patientmobilenumber,String patientpassword,String patientemail,String patientgender,String patientage,String patientheight,String patientweight,String patientbloodgroup,String patientaddress,String patientpincode) {
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
        db.update(TABLE_PATIENT_INFORMATION,contentValues,COLUMN_PATIENT_ID+"="+"'"+patient_id+"'",null);
        db.close();
        return true;
    }
    // Get the image from SQLite DB
    // We will just get the last image we just saved for convenience...
    public byte[] retreiveImageFromDB(String  patient_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db .query(true, TABLE_PATIENT_INFORMATION, new String[]{COLUMN_PATIENT_PROFILE_PICTURE,},
                null, null, null, null, COLUMN_PATIENT_ID , patient_id);
        if (cur.moveToFirst()) {
            byte[] blob = cur.getBlob(cur.getColumnIndex(COLUMN_PATIENT_PROFILE_PICTURE));
            cur.close();
            return blob;
        }
        cur.close();
        return null;
    }
    //
    public Cursor getPatientData(String patient_mobile) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM patient_information WHERE patient_mobile ='" + patient_mobile + "'";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }
    //---opens the database---
    public DBHelper open() throws SQLException
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return this;
    }

    //---closes the database---
    public void close()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.close();
    }


}




