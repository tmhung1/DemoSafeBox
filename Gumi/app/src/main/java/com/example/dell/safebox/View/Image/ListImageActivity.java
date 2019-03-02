package com.example.dell.safebox.View.Image;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.dell.safebox.Adapter.ImageAdapter;
import com.example.dell.safebox.Object.FolderObject;
import com.example.dell.safebox.Presenter.Image.FolderPresenterLogic;
import com.example.dell.safebox.R;
import com.example.dell.safebox.View.HomePage.HomePageActivity;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ListImageActivity extends AppCompatActivity implements View.OnClickListener, ImageViewImp {
    FloatingActionButton btnAddImage;
    RecyclerView recyclerView;
    Toolbar toolbar;
    int folder_id = 0;
    Dialog dialog;
    ConstraintLayout constraintLayout;
    ImageView imgTakePhoto, imgFrmStor;
    private static final int REQUEST_CODE_CAMERA = 113;
    private static final int REQUEST_CODE_FOLDER = 114;
    FolderPresenterLogic folderPresenterLogic;
    ImageAdapter imageAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_image_layout);
        addControl();
        Intent intent = getIntent();
        folder_id = intent.getIntExtra("folder_id", 0);
        String folder_name = intent.getStringExtra("folder_name");
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(folder_name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ImageActivity.class));
            }
        });

        addEvent();
        folderPresenterLogic = new FolderPresenterLogic(ListImageActivity.this);
        folderPresenterLogic.getFolderDetail(ListImageActivity.this, String.valueOf(folder_id));
    }

    private void addEvent() {
        btnAddImage.setOnClickListener(this);

    }

    private void addControl() {
        btnAddImage = findViewById(R.id.btnAddImage);
        recyclerView = findViewById(R.id.recycleImage);
        toolbar = findViewById(R.id.toolbarImage);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAddImage:
                View view = getLayoutInflater().inflate(R.layout.add_image_layout, null);
                constraintLayout = view.findViewById(R.id.choose_image);
                imgTakePhoto = view.findViewById(R.id.imageViewTakePhoto);
                imgFrmStor = view.findViewById(R.id.imageViewFromStor);
                dialog = new Dialog(ListImageActivity.this, android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
                dialog.setContentView(view);
                dialog.show();

                constraintLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        folderPresenterLogic = new FolderPresenterLogic(ListImageActivity.this);
                        folderPresenterLogic.getFolderDetail(ListImageActivity.this, String.valueOf(folder_id));
                    }
                });
                imgTakePhoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityCompat.requestPermissions(
                                ListImageActivity.this,
                                new String[]{Manifest.permission.CAMERA},
                                REQUEST_CODE_CAMERA
                        );
                    }
                });
                imgFrmStor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityCompat.requestPermissions(
                                ListImageActivity.this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                REQUEST_CODE_FOLDER
                        );
                    }
                });
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent iCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(iCamera, REQUEST_CODE_CAMERA);
                } else {
                    Toast.makeText(this, "Fail!", Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_CODE_FOLDER:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent iFolder = new Intent(Intent.ACTION_PICK);
                    iFolder.setType("image/*");
                    startActivityForResult(iFolder, REQUEST_CODE_FOLDER);
                } else {
                    Toast.makeText(this, "Fail!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data != null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            //android.graphics.Bitmap@aa36400
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
            byte[] image = byteArray.toByteArray();
            folderPresenterLogic = new FolderPresenterLogic(ListImageActivity.this);
            folderPresenterLogic.addImage(ListImageActivity.this, image, folder_id);
        }
        if (requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArray);
                byte[] image = byteArray.toByteArray();
                folderPresenterLogic = new FolderPresenterLogic(ListImageActivity.this);
                folderPresenterLogic.addImage(ListImageActivity.this, image, folder_id);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void receiveResult(Boolean result) {

    }

    private void setAdapter(List<byte[]> listImage) {
        imageAdapter = new ImageAdapter(this, listImage, folder_id);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ListImageActivity.this, 4);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(imageAdapter);
        imageAdapter.notifyDataSetChanged();
    }

    @Override
    public void receiveFolderList(List<FolderObject> folderObjectList) {

    }

    @Override
    public void receiveListImage(List<byte[]> listImage) {
        setAdapter(listImage);
    }

}
