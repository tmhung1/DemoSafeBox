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
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.safebox.Object.FolderObject;
import com.example.dell.safebox.R;
import com.example.dell.safebox.View.Image.ListImageActivity;

import org.w3c.dom.Text;

import java.util.List;

public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.FolderViewHolder> {
    List<FolderObject> folderObjectList;
    Context context;

    public FolderAdapter(Context context, List<FolderObject> folderObjectList) {
        this.context = context;
        this.folderObjectList = folderObjectList;
    }

    @NonNull
    @Override
    public FolderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_line_folder, parent, false);
        FolderViewHolder viewHolder = new FolderViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FolderViewHolder holder, int position) {
        final FolderObject folderObject = folderObjectList.get(position);
        holder.txtName.setText(folderObject.getFolderName());
        holder.constraintLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent iListImage = new Intent(context, ListImageActivity.class);
                iListImage.putExtra("folder_id",folderObject.getId());
                iListImage.putExtra("folder_name",folderObject.getFolderName());
                context.startActivity(iListImage);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return folderObjectList.size();
    }

    public class FolderViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;
        ConstraintLayout constraintLayout;

        public FolderViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtFolderName);
            constraintLayout = itemView.findViewById(R.id.line_folder);
        }
    }
}
