package com.example.dell.safebox.View.Contact;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.safebox.Object.Contact;
import com.example.dell.safebox.Object.DateObject;
import com.example.dell.safebox.Object.EmailObject;
import com.example.dell.safebox.Object.PhoneObject;
import com.example.dell.safebox.Presenter.Contact.ContactPresenterLogic;
import com.example.dell.safebox.R;
import com.example.dell.safebox.View.HomePage.HomePageActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;


public class AddContactActivity extends AppCompatActivity implements ContactViewImp {
    Spinner spnEmail, spnDate;
    Toolbar toolbar;
    LinearLayout boxPhone, boxEmail, boxDate;
    EditText edtFirstName, edtLastName, edtMiddleName, edtCompany, edtAddress;
    String phoneList[] = {"Mobile", "Work", "Home", "Main", "Work Fax", "Home Fax", "Custom"};
    String emailList[] = {"Work", "Home", "Custom"};
    String dateList[] = {"Birthday", "Anniversary", "Custom"};
    List<PhoneObject> phoneObjectList;
    List<EmailObject> emailObjectList;
    List<DateObject> dateObjectList;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact_layout);
        addControl();
        addEvent();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ContactActivity.class));
            }
        });

        clonePhone();
        cloneEmail();
        cloneDate();
    }

    private void cloneDate() {
        final View view = LayoutInflater.from(AddContactActivity.this).inflate(R.layout.clone_date_layout, null);
        ImageView imgAddDate = view.findViewById(R.id.imgAddDate);
        final ImageView imgDeleteDate = view.findViewById(R.id.imgDeleteDate);
        final Spinner spinner = view.findViewById(R.id.spnDate);
        final EditText edtDate = view.findViewById(R.id.edtDate);

        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDay(edtDate);
            }
        });

        imgAddDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = edtDate.getText().toString();
                TextView textView = (TextView) spinner.getSelectedView();
                String result = textView.getText().toString();

                DateObject dateObject = new DateObject();
                dateObject.setDateType(result);
                dateObject.setDateValue(date);
                dateObjectList.add(dateObject);


                v.setVisibility(View.GONE);
                imgDeleteDate.setVisibility(View.VISIBLE);
                imgDeleteDate.setTag(dateObject);


                cloneDate();
            }
        });
        setAdapterDate(spinner);
        imgDeleteDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateObject date = (DateObject) v.getTag();
                dateObjectList.remove(date);
                boxDate.removeView(view);
            }
        });
        boxDate.addView(view);
    }

    private void selectDay(final EditText editText) {
        final Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                editText.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private void cloneEmail() {
        final View view = LayoutInflater.from(AddContactActivity.this).inflate(R.layout.clone_email_layout, null);
        ImageView imgAddEmail = view.findViewById(R.id.imgAddEmail);
        final ImageView imgDeleteEmail = view.findViewById(R.id.imgDeleteEmail);
        final Spinner spinner = view.findViewById(R.id.spnEmail);
        final EditText edtEmail = view.findViewById(R.id.edtEmail);
        imgAddEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                TextView textView = (TextView) spinner.getSelectedView();
                String result = textView.getText().toString();

                EmailObject emailObject = new EmailObject();
                emailObject.setEmailType(result);
                emailObject.setEmailName(email);
                emailObjectList.add(emailObject);


                v.setVisibility(View.GONE);
                imgDeleteEmail.setVisibility(View.VISIBLE);
                imgDeleteEmail.setTag(emailObject);


                cloneEmail();
            }
        });
        setAdapterEmail(spinner);
        imgDeleteEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmailObject emailObject = (EmailObject) v.getTag();
                emailObjectList.remove(emailObject);
                boxEmail.removeView(view);
            }
        });
        boxEmail.addView(view);
    }

    private void setAdapterPhone(Spinner spinner) {
        ArrayAdapter<String> adapterPhone = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, phoneList);
        adapterPhone.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterPhone);
        adapterPhone.notifyDataSetChanged();
    }

    private void setAdapterEmail(Spinner spinner) {
        ArrayAdapter<String> adapterEmail = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, emailList);
        adapterEmail.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterEmail);
        adapterEmail.notifyDataSetChanged();
    }

    private void setAdapterDate(Spinner spinner) {
        ArrayAdapter<String> adapterDate = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dateList);
        adapterDate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterDate);
        adapterDate.notifyDataSetChanged();
    }

    private void addEvent() {

    }

    private void clonePhone() {
        final View view = LayoutInflater.from(AddContactActivity.this).inflate(R.layout.clone_phone_layout, null);
        ImageView imgAddPhone = view.findViewById(R.id.imgAddPhone);
        final ImageView imgDeletePhone = view.findViewById(R.id.imgDeletePhone);
        final Spinner spinner = view.findViewById(R.id.spnPhone);
        final EditText edtPhone = view.findViewById(R.id.edtPhone);
        imgAddPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = edtPhone.getText().toString();
                TextView textView = (TextView) spinner.getSelectedView();
                String result = textView.getText().toString();

                PhoneObject phoneObject = new PhoneObject();
                phoneObject.setPhoneName(result);
                phoneObject.setPhoneNumber(phone);
                phoneObjectList.add(phoneObject);


                v.setVisibility(View.GONE);
                imgDeletePhone.setVisibility(View.VISIBLE);
                imgDeletePhone.setTag(phoneObject);


                clonePhone();
            }
        });
        setAdapterPhone(spinner);
        imgDeletePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneObject phone = (PhoneObject) v.getTag();
                phoneObjectList.remove(phone);
                boxPhone.removeView(view);
            }
        });
        boxPhone.addView(view);
    }

    private void addControl() {
        edtFirstName = findViewById(R.id.edtFirstName);
        edtMiddleName = findViewById(R.id.edtMiddleName);
        edtLastName = findViewById(R.id.edtLastName);
        edtCompany = findViewById(R.id.edtCompany);
        edtAddress = findViewById(R.id.edtAddress);
        spnEmail = findViewById(R.id.spnEmail);
        spnDate = findViewById(R.id.spnDate);
        boxPhone = findViewById(R.id.boxPhone);
        boxEmail = findViewById(R.id.boxEmail);
        toolbar = findViewById(R.id.toolbar);
        boxDate = findViewById(R.id.boxDate);
        phoneObjectList = new ArrayList<>();
        emailObjectList = new ArrayList<>();
        dateObjectList = new ArrayList<>();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private Contact saveContact() {
        String firstname = edtFirstName.getText().toString();
        String middlename = edtMiddleName.getText().toString().trim();
        String lastname = edtLastName.getText().toString().trim();
        String company = edtCompany.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();

        Random random = new Random();
        int contactID = random.nextInt(1000);

        Contact contact = new Contact();
        if (firstname.equals("") && middlename.equals("") && lastname.equals("")) {
            Toast.makeText(this, "The name of contact is empty", Toast.LENGTH_SHORT).show();
        } else {
            contact.setId(contactID);
            contact.setFirstName(firstname);
            contact.setMiddleName(middlename);
            contact.setLastName(lastname);
            contact.setCompany(company);
            contact.setAddress(address);
        }
        return contact;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Contact contact = saveContact();
        if (contact == null) {

        } else {
            if (phoneObjectList.size() <= 0) {
                Toast.makeText(this, "The phone number of contact is empty", Toast.LENGTH_SHORT).show();
            } else {
                ContactPresenterLogic presenterLogic = new ContactPresenterLogic(this);
                presenterLogic.addContact(this, contact, phoneObjectList, emailObjectList, dateObjectList);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void receiveResultAddContact(Boolean b) {
        if (b.booleanValue() == true) {
            Toast.makeText(this, "Add success", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void receiveContactList(List<Contact> contactList) {

    }
}
