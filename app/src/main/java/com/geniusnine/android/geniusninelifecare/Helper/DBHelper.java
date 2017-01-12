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
    public static final String COLUMN_PATIENT_NAME = "patient_name";
    public static final String COLUMN_PATIENT_MOBILE = "patient_mobile";
    public static final String COLUMN_PATIENT_EMAIL = "patient_email";
    public static final String COLUMN_PATIENT_GENDER = "patient_gender";
    public static final String COLUMN_PATIENT_AGE = "patient_age";
    public static final String COLUMN_PATIENT_REGISRTION_DATE = "date";


    public static final String TABLE_CATEGORIES = "categories";
    public static final String COLUMN_CATEGORIE_ID  = "categorie_id";
    public static final String COLUMN_CATEGORIE_NAME = "categorie_name";
    public static final String COLUMN_CATEGORIE_COLOUR = "categorie_color";

    public static final String TABLE_EXPENSES = "expenses";
    public static final String COLUMN_EXPENSE_ID  = "expense_id";
    public static final String COLUMN_EXPENSE_DESCRIPTION = "expense_description";
    public static final String COLUMN_EXPENSE_HOW_MUCH = "expense_how_much";


    private DBHelper DBHelper1;

    private static final int DB_VERSION = 1;
    SQLiteDatabase db;

    public DBHelper(Activity context1) {
        super(context1, DB_NAME, null, DB_VERSION);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_PATIENT_INFORMATION
                + "(" +COLUMN_PATIENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_PATIENT_NAME + " VARCHAR, "
                + COLUMN_PATIENT_MOBILE + " INTEGER,"
                + COLUMN_PATIENT_EMAIL + " VARCHAR,"
                + COLUMN_PATIENT_GENDER + " VARCHAR,"
                + COLUMN_PATIENT_AGE + " INTEGER,"
                + COLUMN_PATIENT_REGISRTION_DATE + " DATE" + ")";
        db.execSQL(sql);
       /* String sql2 = "CREATE TABLE IF NOT EXISTS " + TABLE_CATEGORIES
                + "(" + COLUMN_CATEGORIE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +  COLUMN_CATEGORIE_NAME + " VARCHAR, "
                + COLUMN_CATEGORIE_COLOUR + " VARCHAR,"
                + COLUMN_DATE + " DATE,"
                +COLUMN_USER_ID + " VARCHAR" + ")";
        db.execSQL(sql2);
        String sql3 = "CREATE TABLE IF NOT EXISTS " + TABLE_EXPENSES
                + "(" + COLUMN_EXPENSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +  COLUMN_CATEGORIE_NAME + " VARCHAR, "
                + COLUMN_ACCOUNT_NAME + " VARCHAR,"
                +  COLUMN_EXPENSE_DESCRIPTION + " VARCHAR, "
                + COLUMN_EXPENSE_HOW_MUCH + " VARCHAR,"
                + COLUMN_DATE + " DATE,"
                +COLUMN_USER_ID + " VARCHAR" + ")";
        db.execSQL(sql3);*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS patient_information";
        db.execSQL(sql);
        //  onCreate(db);
    }
    public boolean addUser(String patientname,String patientmobilenumber,String patientemail,String patientgender,String patientage) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PATIENT_NAME, patientname);
        contentValues.put(COLUMN_PATIENT_MOBILE, patientmobilenumber);
        contentValues.put(COLUMN_PATIENT_EMAIL, patientemail);
        contentValues.put(COLUMN_PATIENT_GENDER, patientgender);
        contentValues.put(COLUMN_PATIENT_AGE, patientage);
        db.insert(TABLE_PATIENT_INFORMATION, null, contentValues);
        db.close();
        return true;
    }
    public boolean addCategories(String categorie_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CATEGORIE_NAME, categorie_name);
        db.insert(TABLE_CATEGORIES, null, contentValues);
        db.close();
        return true;
    }

    public Cursor showcategories() {
        String[] cols = { COLUMN_CATEGORIE_ID, COLUMN_CATEGORIE_NAME,COLUMN_CATEGORIE_COLOUR};
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.query(TABLE_CATEGORIES, cols, null,
                null, null, null, null);
        return c;
    }
    public Cursor getAccountdetails(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM accounts WHERE account_id ='" + id + "'";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }
    public Cursor getCategoriesdetails(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM accounts WHERE account_id ='" + id + "'";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }
   /* public boolean addExpenses(String categorie_name,String account_name,String Description,String howmuch,String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CATEGORIE_NAME, categorie_name);
        contentValues.put(COLUMN_ACCOUNT_NAME, account_name);
        contentValues.put(COLUMN_DATE, date);
        contentValues.put(COLUMN_EXPENSE_DESCRIPTION, Description);
        contentValues.put(COLUMN_EXPENSE_HOW_MUCH, howmuch);
        db.insert(TABLE_EXPENSES, null, contentValues);
        db.close();
        return true;
    }*/
 /*   public List<String> getAllLabels(){
        List<String> list = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CATEGORIES;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(1));//adding 2nd column data
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return list;
    }
    public List<String> getAllAccounts(){
        List<String> list = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ACCOUNTS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(1));//adding 2nd column data
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return list;
    }
    public boolean addAccounts(String accountname,String description,String accountype,String opeingbalence) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ACCOUNT_NAME, accountname);
        contentValues.put(COLUMN_ACCOUNT_DESCRIPTION, description);
        contentValues.put(COLUMN_ACCOUNT_TYPE, accountype);
        contentValues.put(COLUMN_OPENING_BALANCE, opeingbalence);
        contentValues.put(COLUMN_AVAILABLE_BALANCE, opeingbalence);
        db.insert(TABLE_ACCOUNTS, null, contentValues);
        db.close();
        return true;
    }

    public Cursor showAccounts() {
        String[] cols = { COLUMN_ACCOUNT_ID, COLUMN_ACCOUNT_NAME,COLUMN_ACCOUNT_DESCRIPTION};
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.query(TABLE_ACCOUNTS, cols, null,
                null, null, null, null);
        return c;
    }
    public Cursor getCurrentBalence(String accountname) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT available_balance FROM accounts WHERE account_name ='" + accountname + "'";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }
    public boolean addTransation(String account_name,String totalbalence) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_AVAILABLE_BALANCE, totalbalence);
        db.update(TABLE_ACCOUNTS,contentValues,COLUMN_ACCOUNT_NAME+"="+"'"+account_name+"'",null);
        db.close();
        return true;
    }*/
    public Cursor getTotalTransationAmountToday(String accountname,String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql =  "SELECT SUM(expense_how_much) FROM expenses WHERE account_name ='" + accountname + "' and date ='" + date + "' ";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }
    public Cursor getTotalTransationAmountWeek(String accountname,String currentdate,String weekdate) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql =  "SELECT SUM(expense_how_much) FROM expenses WHERE account_name ='" + accountname + "' and date between'" +weekdate+ "'and'" +currentdate+ "' ";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }
    public Cursor getTotalTransationAmountMonth(String accountname,String currentdate,String monthdate) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql =  "SELECT SUM(expense_how_much) FROM expenses WHERE account_name ='" + accountname + "' and date between'" +monthdate + "'and'" +currentdate+ "' ";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public List<String> getAccounttype(String id){
        List<String> labels = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT account_type FROM accounts WHERE account_id ='" + id + "'";

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
    }


    //---opens the database---
    public DBHelper open() throws SQLException
    {
        db = DBHelper1.getWritableDatabase();
        return this;
    }

    //---closes the database---
    public void close()
    {
        DBHelper1.close();
    }
}




