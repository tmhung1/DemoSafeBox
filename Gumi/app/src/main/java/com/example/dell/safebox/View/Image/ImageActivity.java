package com.example.dell.safebox.View.Image;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.safebox.Adapter.FolderAdapter;
import com.example.dell.safebox.Object.FolderObject;
import com.example.dell.safebox.Presenter.Image.FolderPresenterLogic;
import com.example.dell.safebox.R;
import com.example.dell.safebox.View.Contact.ContactActivity;
import com.example.dell.safebox.View.HomePage.HomePageActivity;

import java.util.List;

public class ImageActivity extends AppCompatActivity implements ImageViewImp {
    Toolbar toolbar;
    RecyclerView recyclerView;
    FolderAdapter folderAdapter;
    int size_list_image = 0;
    FolderPresenterLogic folderPresenterLogic;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_layout);
        addControl();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Image");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomePageActivity.class));
            }
        });
        folderPresenterLogic = new FolderPresenterLogic(this);
        folderPresenterLogic.getFolderList(this);
    }

    private void addControl() {
        toolbar = findViewById(R.id.toolbarImage);
        recyclerView = findViewById(R.id.recycleImage);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_image, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.itemAddFolder:
                final Dialog d = new Dialog(ImageActivity.this);
                d.setCancelable(false);
                d.setContentView(R.layout.add_folder_layout);
                final EditText editText = d.findViewById(R.id.edtFolderName);
                Button btnAdd = d.findViewById(R.id.btnOkAddFolder);
                Button btnCancel = d.findViewById(R.id.btnCancelAddFolder);

                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String folderName = editText.getText().toString();
                        if (!folderName.equals("")) {
                            FolderObject folderObject = new FolderObject();
                            folderObject.setFolderName(folderName);
                            FolderPresenterLogic folderPresenterLogic = new FolderPresenterLogic(ImageActivity.this);
                            folderPresenterLogic.addFolder(ImageActivity.this, folderObject);
                            d.cancel();
                        } else {
                            Toast.makeText(ImageActivity.this, "YOU FORGOT INPUT NAME FOLDER", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        d.cancel();
                    }
                });
                d.show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void receiveResult(Boolean result) {
        if (result.booleanValue() == true) {
            Toast.makeText(this, "Add folder success", Toast.LENGTH_SHORT).show();
            folderPresenterLogic = new FolderPresenterLogic(ImageActivity.this);
            folderPresenterLogic.getFolderList(ImageActivity.this);
        }
    }

    private void setAdapter(List<FolderObject> contacts) {
        folderAdapter = new FolderAdapter(this, contacts);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ImageActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(folderAdapter);
        folderAdapter.notifyDataSetChanged();
    }

    @Override
    public void receiveFolderList(List<FolderObject> folderObjectList) {
        setAdapter(folderObjectList);
    }

    @Override
    public void receiveListImage(List<byte[]> listImage) {
        size_list_image = listImage.size();
    }
}
