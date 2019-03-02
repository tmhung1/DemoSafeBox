package com.example.dell.safebox.View.Image;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.dell.safebox.Object.FolderObject;
import com.example.dell.safebox.Presenter.Image.FolderPresenterLogic;
import com.example.dell.safebox.R;

import java.util.ArrayList;
import java.util.List;

public class ShowImageActivity extends AppCompatActivity implements View.OnClickListener, ImageViewImp {
    ViewFlipper viewFlipper;
    ImageView btnNext, brnPrevious;
    FolderPresenterLogic folderPresenterLogic;
    List<byte[]> imageList;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_image_layout);
        addControl();
        addEvent();

        Intent intent = getIntent();
        int folder_id = intent.getIntExtra("folder_id", 0);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ImageActivity.class));
            }
        });

        folderPresenterLogic.getFolderDetail(this, String.valueOf(folder_id));
    }

    private void cloneImage(List<byte[]> list) {
        for (int i = 0; i < list.size(); i++) {
            View v = LayoutInflater.from(ShowImageActivity.this).inflate(R.layout.clone_image_layout, null);
            ImageView imageView = v.findViewById(R.id.imgImageClone);
            Bitmap bitmap = BitmapFactory.decodeByteArray(list.get(i), 0, list.get(i).length);
            imageView.setImageBitmap(bitmap);
            viewFlipper.addView(v);
        }

    }

    private void addEvent() {
        btnNext.setOnClickListener(this);
        brnPrevious.setOnClickListener(this);
    }

    private void addControl() {
        viewFlipper = findViewById(R.id.viewFlipper2);
        brnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);
        folderPresenterLogic = new FolderPresenterLogic(this);
        imageList = new ArrayList<>();
        toolbar = findViewById(R.id.toolbar2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPrevious:
                viewFlipper.showPrevious();
                break;
            case R.id.btnNext:
                viewFlipper.showNext();
                break;
        }
    }

    //don't use
    @Override
    public void receiveResult(Boolean result) {

    }

    @Override
    public void receiveFolderList(List<FolderObject> folderObjectList) {

    }

    @Override
    public void receiveListImage(List<byte[]> list) {
        imageList = list;
        cloneImage(list);
    }
}
