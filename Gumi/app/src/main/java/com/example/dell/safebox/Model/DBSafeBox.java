package com.example.dell.safebox.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBSafeBox extends SQLiteOpenHelper {
    public static String TB_ACCOUNT = "USER";
    public static String TB_ACCOUNT_PASSWORD = "PASSWORD";
    public static String TB_ACCOUNT_IMEINUMBER = "IMEINUMBER";

    public static String TB_CONTACT = "CONTACT";
    public static String TB_CONTACT_ID = "CONTACT_ID";
    public static String TB_CONTACT_FIRSTNAME = "FIRSTNAME";
    public static String TB_CONTACT_MIDDLENAME = "MIDDLENAME";
    public static String TB_CONTACT_LASTNAME = "LASTNAME";
    public static String TB_CONTACT_COMPANY = "COMPANY";
    public static String TB_CONTACT_ADDRESS = "ADDRESS";

    public static String TB_PHONE = "PHONE";
    public static String TB_PHONE_ID = "CONTACT_ID";
    public static String TB_PHONE_MOBILE = "MOBILE";
    public static String TB_PHONE_WORK = "WORK";
    public static String TB_PHONE_HOME = "HOME";
    public static String TB_PHONE_MAIN = "MAIN";
    public static String TB_PHONE_WORK_FAX = "WORK_FAX";
    public static String TB_PHONE_HOME_FAX = "HOME_FAX";

    public static String TB_EMAIL = "EMAIL";
    public static String TB_EMAIL_ID = "CONTACT_ID";
    public static String TB_EMAIL_WORK = "WORK";
    public static String TB_EMAIL_HOME = "HOME";

    public static String TB_DATE = "DATE";
    public static String TB_DATE_BIRTHDAY = "BIRTHDAY";
    public static String TB_DATE_ID = "CONTACT_ID";
    public static String TB_DATE_ANNIVENSARY = "ANNIVENSARY";

    public static String TB_FOLDER = "FOLDER";
    public static String TB_FOLDER_ID = "FOLDER_ID";
    public static String TB_FOLDER_NAME = "FOLDER_NAME";

    public static String TB_IMAGE = "FOLDER_IMAGE";
    public static String TB_FOLDER_IMAGE_ID = "FOLDER_ID";
    public static String TB_FOLDER_IMAG = "IMAGE";

    public DBSafeBox(Context context) {
        super(context, "SQLSAFEBOX", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tbAccount = "CREATE TABLE " + TB_ACCOUNT + " (" + TB_ACCOUNT_PASSWORD + " TEXT, " + TB_ACCOUNT_IMEINUMBER + " TEXT); ";

        String tbContact = "CREATE TABLE " + TB_CONTACT + " (" + TB_CONTACT_ID + " INTEGER PRIMARY KEY , "
                + TB_CONTACT_FIRSTNAME + " TEXT, " + TB_CONTACT_MIDDLENAME + " TEXT, " + TB_CONTACT_LASTNAME + " TEXT, "
                + TB_CONTACT_COMPANY + " TEXT, " + TB_CONTACT_ADDRESS + " TEXT )";

        String tbPhone = "CREATE TABLE " + TB_PHONE + " (" + TB_PHONE_ID + " INTEGER PRIMARY KEY, "
                + TB_PHONE_HOME + " TEXT, " + TB_PHONE_MOBILE + " TEXT, " + TB_PHONE_WORK + " TEXT, "
                + TB_PHONE_MAIN + " TEXT, " + TB_PHONE_WORK_FAX + " TEXT, " + TB_PHONE_HOME_FAX + " TEXT )";

        String tbEmail = "CREATE TABLE " + TB_EMAIL + " (" + TB_EMAIL_ID + " INTEGER PRIMARY KEY, "
                + TB_EMAIL_WORK + " TEXT, " + TB_EMAIL_HOME + " TEXT )";

        String tbDate = "CREATE TABLE " + TB_DATE + " (" + TB_DATE_ID + " INTEGER PRIMARY KEY , "
                + TB_DATE_ANNIVENSARY + " TEXT, " + TB_DATE_BIRTHDAY + " TEXT )" ;

        String tbFolder = "CREATE TABLE " + TB_FOLDER + " (" + TB_FOLDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + TB_FOLDER_NAME + " TEXT )" ;

        String tbImage = "CREATE TABLE " + TB_IMAGE + " (" + TB_FOLDER_IMAGE_ID + " INTEGER , "
                + TB_FOLDER_IMAG + " BLOB )" ;

        db.execSQL(tbAccount);
        db.execSQL(tbContact);
        db.execSQL(tbPhone);
        db.execSQL(tbEmail);
        db.execSQL(tbDate);
        db.execSQL(tbFolder);
        db.execSQL(tbImage);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
