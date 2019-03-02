package com.example.dell.safebox.Presenter.Contact;

import android.content.Context;

import com.example.dell.safebox.Model.ContactModel;
import com.example.dell.safebox.Object.Contact;
import com.example.dell.safebox.Object.DateObject;
import com.example.dell.safebox.Object.EmailObject;
import com.example.dell.safebox.Object.PhoneObject;
import com.example.dell.safebox.View.Contact.ContactViewImp;

import java.util.List;

public class ContactPresenterLogic implements ContactPresenterImp {
    ContactViewImp contactViewImp;
    ContactModel contactModel;

    public ContactPresenterLogic(ContactViewImp contactViewImp) {
        this.contactViewImp = contactViewImp;
        contactModel = new ContactModel();
    }

    @Override
    public void addContact(Context context, Contact contact, List<PhoneObject> list, List<EmailObject> emailObjectList, List<DateObject> dateObjectList) {
        contactModel.openConnectSQL(context);
        boolean result = contactModel.addContact(contact, list, emailObjectList, dateObjectList);
        contactViewImp.receiveResultAddContact(result);
    }

    @Override
    public void getContactList(Context context) {
        contactModel.openConnectSQL(context);
        List<Contact> contactList = contactModel.getListContact();
        if (contactList.size() > 0) {
            contactViewImp.receiveContactList(contactList);
        }
    }

}
