package com.example.dell.safebox.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dell.safebox.Object.Contact;
import com.example.dell.safebox.Object.DateObject;
import com.example.dell.safebox.Object.EmailObject;
import com.example.dell.safebox.Object.PhoneObject;

import java.util.ArrayList;
import java.util.List;

public class ContactModel {
    SQLiteDatabase sqLiteDatabase;

    public void openConnectSQL(Context context) {
        DBSafeBox dbSafeBox = new DBSafeBox(context);
        sqLiteDatabase = dbSafeBox.getWritableDatabase();
    }

    public List<Contact> getListContact() {
        List<Contact> contactList = new ArrayList<>();
        String query = "SELECT * FROM " + DBSafeBox.TB_CONTACT;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int id = cursor.getInt(cursor.getColumnIndex(DBSafeBox.TB_CONTACT_ID));
            String firstname = cursor.getString(cursor.getColumnIndex(DBSafeBox.TB_CONTACT_FIRSTNAME));
            String middlename = cursor.getString(cursor.getColumnIndex(DBSafeBox.TB_CONTACT_MIDDLENAME));
            String lastname = cursor.getString(cursor.getColumnIndex(DBSafeBox.TB_CONTACT_LASTNAME));
            Contact contact = new Contact();
            contact.setFirstName(firstname);
            contact.setMiddleName(middlename);
            contact.setLastName(lastname);
            contact.setId(id);
            cursor.moveToNext();
            contactList.add(contact);
        }
        return contactList;
    }

    public boolean addContact(Contact contact, List<PhoneObject> phoneObjectList, List<EmailObject> emailObjectList, List<DateObject> dateObjectList) {
        ContentValues contentValues = new ContentValues();
        int contactID = contact.getId();
        contentValues.put(DBSafeBox.TB_CONTACT_ID, contact.getId());
        contentValues.put(DBSafeBox.TB_CONTACT_FIRSTNAME, contact.getFirstName());
        contentValues.put(DBSafeBox.TB_CONTACT_MIDDLENAME, contact.getMiddleName());
        contentValues.put(DBSafeBox.TB_CONTACT_LASTNAME, contact.getLastName());
        contentValues.put(DBSafeBox.TB_CONTACT_COMPANY, contact.getCompany());
        contentValues.put(DBSafeBox.TB_CONTACT_ADDRESS, contact.getAddress());
        long id = sqLiteDatabase.insert(DBSafeBox.TB_CONTACT, null, contentValues);
        if (id > 0) {
            ContentValues valuePhone = new ContentValues();
            valuePhone.put(DBSafeBox.TB_PHONE_ID, contactID);

            ContentValues valuesDate = new ContentValues();
            valuesDate.put(DBSafeBox.TB_DATE_ID, contactID);

            ContentValues valuesEmail = new ContentValues();
            valuesEmail.put(DBSafeBox.TB_EMAIL_ID, contactID);

            if (phoneObjectList.size() > 0) {
                for (PhoneObject phone : phoneObjectList) {
                    switch (phone.getPhoneName()) {
                        case "Mobile":
                            valuePhone.put(DBSafeBox.TB_PHONE_MOBILE, phone.getPhoneNumber());
                            break;
                        case "Work":
                            valuePhone.put(DBSafeBox.TB_PHONE_WORK, phone.getPhoneNumber());
                            break;
                        case "Home":
                            valuePhone.put(DBSafeBox.TB_PHONE_HOME, phone.getPhoneNumber());
                            break;
                        case "Main":
                            valuePhone.put(DBSafeBox.TB_PHONE_MAIN, phone.getPhoneNumber());
                            break;
                        case "Work Fax":
                            valuePhone.put(DBSafeBox.TB_PHONE_WORK_FAX, phone.getPhoneNumber());
                            break;
                        case "Home Fax":
                            valuePhone.put(DBSafeBox.TB_PHONE_HOME_FAX, phone.getPhoneNumber());
                            break;
                    }
                }
                sqLiteDatabase.insert(DBSafeBox.TB_PHONE, null, valuePhone);
            }
            if (emailObjectList.size() > 0) {
                for (EmailObject email : emailObjectList) {
                    switch (email.getEmailType()) {
                        case "Work":
                            valuesEmail.put(DBSafeBox.TB_EMAIL_WORK, email.getEmailName());
                            break;
                        case "Home":
                            valuesEmail.put(DBSafeBox.TB_EMAIL_HOME, email.getEmailName());
                            break;
                    }
                }
                sqLiteDatabase.insert(DBSafeBox.TB_EMAIL, null, valuesEmail);
            }

            if (dateObjectList.size() > 0) {
                for (DateObject dateObject : dateObjectList) {
                    switch (dateObject.getDateType()) {
                        case "Birthday":
                            valuesDate.put(DBSafeBox.TB_DATE_BIRTHDAY, dateObject.getDateValue());
                            break;
                        case "Anniversary":
                            valuesDate.put(DBSafeBox.TB_DATE_ANNIVENSARY, dateObject.getDateValue());
                            break;
                    }
                }
                sqLiteDatabase.insert(DBSafeBox.TB_DATE, null, valuesDate);
            }

            return true;
        } else {
            return false;
        }
    }
}
