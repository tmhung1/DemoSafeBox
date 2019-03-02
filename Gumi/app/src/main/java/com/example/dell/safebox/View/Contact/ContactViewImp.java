package com.example.dell.safebox.View.Contact;

import com.example.dell.safebox.Object.Contact;

import java.util.List;

public interface ContactViewImp {
    void receiveResultAddContact(Boolean b);

    void receiveContactList(List<Contact> contactList);
}
