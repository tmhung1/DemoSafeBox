package com.example.dell.safebox.View.Contact;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.dell.safebox.Adapter.ContactAdapter;
import com.example.dell.safebox.Object.Contact;
import com.example.dell.safebox.Presenter.Contact.ContactPresenterLogic;
import com.example.dell.safebox.R;
import com.example.dell.safebox.View.HomePage.HomePageActivity;

import java.util.List;

public class ContactActivity extends AppCompatActivity implements View.OnClickListener, ContactViewImp {
    FloatingActionButton btnAddContact;
    RecyclerView recyclerContact;
    Toolbar toolbar;
    ContactAdapter contactAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_layout);
        addControl();
        addEvent();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Contact");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomePageActivity.class));
            }
        });
        ContactPresenterLogic presenterLogic = new ContactPresenterLogic(this);
        presenterLogic.getContactList(this);


    }

    private void setAdapter(List<Contact> contacts) {
        contactAdapter = new ContactAdapter(this, contacts);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ContactActivity.this);
        recyclerContact.setLayoutManager(layoutManager);
        recyclerContact.setAdapter(contactAdapter);
        contactAdapter.notifyDataSetChanged();
    }

    private void addEvent() {
        btnAddContact.setOnClickListener(this);
    }

    private void addControl() {
        btnAddContact = findViewById(R.id.btnAddContact);
        recyclerContact = findViewById(R.id.recycleContact);
        toolbar = findViewById(R.id.toolbarContact);
    }

    @Override
    public void onClick(View v) {
        Intent iAddContact = new Intent(ContactActivity.this, AddContactActivity.class);
        startActivity(iAddContact);
    }

    @Override
    public void receiveResultAddContact(Boolean b) {

    }

    @Override
    public void receiveContactList(List<Contact> contactList) {
        setAdapter(contactList);
    }

    @Override
    protected void onResume() {
        ContactPresenterLogic presenterLogic = new ContactPresenterLogic(this);
        presenterLogic.getContactList(this);
        super.onResume();
    }
}
