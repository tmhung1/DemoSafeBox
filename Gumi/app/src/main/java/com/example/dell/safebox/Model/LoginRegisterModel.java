package com.example.dell.safebox.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dell.safebox.Object.Account;

public class LoginRegisterModel {
    SQLiteDatabase sqLiteDatabase;

    public void openConnectSQL(Context context) {
        DBSafeBox dbSafeBox = new DBSafeBox(context);
        sqLiteDatabase = dbSafeBox.getWritableDatabase();
    }

    public boolean registerAccount(Account account) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBSafeBox.TB_ACCOUNT_PASSWORD, account.getPassCode());
        contentValues.put(DBSafeBox.TB_ACCOUNT_IMEINUMBER, account.getImeiNumber());
        long id = sqLiteDatabase.insert(DBSafeBox.TB_ACCOUNT, null, contentValues);
        if (id > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkImeiNumber(String imeiNumber) {
        Cursor cursor = sqLiteDatabase.query(DBSafeBox.TB_ACCOUNT, null, DBSafeBox.TB_ACCOUNT_IMEINUMBER + " = ?",
                new String[]{imeiNumber}, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        if (cursorCount > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean loginApp(String passCode) {
        Cursor cursor = sqLiteDatabase.query(DBSafeBox.TB_ACCOUNT, null, DBSafeBox.TB_ACCOUNT_PASSWORD + " = ?",
                new String[]{passCode}, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        if (cursorCount > 0) {
            return true;
        } else {
            return false;
        }
    }
}
