package com.example.dell.safebox.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.safebox.Object.Contact;
import com.example.dell.safebox.R;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    Context context;
    List<Contact> contactList;

    public ContactAdapter(Context context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_contact_layout, parent, false);
        ContactViewHolder viewHolder = new ContactViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        String firstname = contact.getFirstName();
        String lastname = contact.getLastName();
        String middlename = contact.getMiddleName();
        String str = "";

        if(firstname.equals("") && !middlename.equals("")){
            str = middlename.substring(0, 1).toUpperCase();
            holder.txtFirst.setVisibility(View.GONE);
        }else if(firstname.equals("") && middlename.equals("")){
            str = lastname.substring(0, 1).toUpperCase();
            holder.txtFirst.setVisibility(View.GONE);
            holder.txtMiddle.setVisibility(View.GONE);
        }else if(!firstname.equals("")){
            str = firstname.substring(0, 1).toUpperCase();
        }
        holder.txtAlpla.setText(str);
        if (firstname != null) holder.txtFirst.setText(firstname);
        if (middlename != null) holder.txtMiddle.setText(middlename);
        if (lastname != null) holder.txtLast.setText(lastname);
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView txtAlpla, txtFirst, txtMiddle, txtLast;

        public ContactViewHolder(View itemView) {
            super(itemView);
            txtAlpla = itemView.findViewById(R.id.txtAlphabet);
            txtFirst = itemView.findViewById(R.id.txtFirstName);
            txtMiddle = itemView.findViewById(R.id.txtMiddleName);
            txtLast = itemView.findViewById(R.id.txtLastName);

        }
    }
}
