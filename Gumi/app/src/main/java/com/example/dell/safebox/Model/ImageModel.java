package com.example.dell.safebox.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.dell.safebox.Object.Contact;
import com.example.dell.safebox.Object.FolderObject;

import java.util.ArrayList;
import java.util.List;

public class ImageModel {
    SQLiteDatabase sqLiteDatabase;

    public void openConnectSQL(Context context) {
        DBSafeBox dbSafeBox = new DBSafeBox(context);
        sqLiteDatabase = dbSafeBox.getWritableDatabase();
    }

    public boolean addFolder(FolderObject folderObject) {
        ContentValues values = new ContentValues();
        values.put(DBSafeBox.TB_FOLDER_NAME, folderObject.getFolderName());
        long id = sqLiteDatabase.insert(DBSafeBox.TB_FOLDER, null, values);
        if (id > 0) {
            return true;
        } else {
            return false;
        }
    }
    public boolean addImage(byte[]image,int id)
    {
        Log.d("hung",image+"-"+id);
        ContentValues values=new ContentValues();
        values.put(DBSafeBox.TB_FOLDER_IMAG,image);
        values.put(DBSafeBox.TB_FOLDER_IMAGE_ID,id);
        long check = sqLiteDatabase.insert(DBSafeBox.TB_IMAGE, null, values);
        if(check>0)
        {
            return true;

        }else{
            return false;
        }
    }
    public List<byte[]> getListImage(String id){
        List<byte[]>imageList=new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(DBSafeBox.TB_IMAGE, null, DBSafeBox.TB_FOLDER_IMAGE_ID + " = ?",
                new String[]{id}, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            byte[]image=cursor.getBlob(cursor.getColumnIndex(DBSafeBox.TB_FOLDER_IMAG));

            cursor.moveToNext();
            imageList.add(image);
        }
        return imageList;
    }
    public List<FolderObject> getListFolder() {
        List<FolderObject> folderList = new ArrayList<>();
        String query = "SELECT * FROM " + DBSafeBox.TB_FOLDER;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex(DBSafeBox.TB_FOLDER_ID));
            String folderName = cursor.getString(cursor.getColumnIndex(DBSafeBox.TB_FOLDER_NAME));
            FolderObject folderObject = new FolderObject();
            folderObject.setId(id);
            folderObject.setFolderName(folderName);
            cursor.moveToNext();
            folderList.add(folderObject);
        }
        return folderList;
    }
}
