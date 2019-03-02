package com.example.dell.safebox.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.safebox.Object.MenuMain;
import com.example.dell.safebox.R;
import com.example.dell.safebox.View.Contact.ContactActivity;
import com.example.dell.safebox.View.Image.ImageActivity;

import java.util.List;

public class MenuMainAdapter extends RecyclerView.Adapter<MenuMainAdapter.MenuViewHolder> {
    Context context;
    List<MenuMain> menuMainList;

    public MenuMainAdapter(Context context, List<MenuMain> menuMainList) {
        this.context = context;
        this.menuMainList = menuMainList;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.main_layout, parent, false);
        MenuViewHolder viewHolder = new MenuViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MenuViewHolder holder, int position) {
        final MenuMain menuMain = menuMainList.get(position);
        holder.txtTitle.setText(menuMain.getTitle());
        holder.imgLogo.setImageResource(menuMain.getImage());
        holder.constraintLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (menuMain.getTitle().equals("Contact")) {
                    Intent iContact = new Intent(context, ContactActivity.class);
                    context.startActivity(iContact);
                } else if (menuMain.getTitle().equals("Image")) {
                    Intent iImage = new Intent(context, ImageActivity.class);
                    context.startActivity(iImage);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return menuMainList.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        ImageView imgLogo;
        TextView txtTitle;
        ConstraintLayout constraintLayout;

        public MenuViewHolder(View itemView) {
            super(itemView);
            imgLogo = itemView.findViewById(R.id.imgLogo);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            constraintLayout = itemView.findViewById(R.id.constrain_main);
        }
    }
}
