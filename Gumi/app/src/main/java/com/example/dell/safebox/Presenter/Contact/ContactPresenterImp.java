package com.example.dell.safebox.Presenter.Contact;

import android.content.Context;

import com.example.dell.safebox.Object.Contact;
import com.example.dell.safebox.Object.DateObject;
import com.example.dell.safebox.Object.EmailObject;
import com.example.dell.safebox.Object.PhoneObject;

import java.util.List;

public interface ContactPresenterImp {
    void addContact(Context context, Contact contact, List<PhoneObject> list, List<EmailObject> emailObjectList, List<DateObject> dateObjectList);

    void getContactList(Context context);
}
