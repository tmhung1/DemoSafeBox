package com.example.dell.safebox.Presenter.Image;

import android.content.Context;

import com.example.dell.safebox.Model.ImageModel;
import com.example.dell.safebox.Object.FolderObject;
import com.example.dell.safebox.View.Image.ImageViewImp;

import java.util.List;

public class FolderPresenterLogic implements FolderPresenterImp {
    ImageViewImp imageViewImp;
    ImageModel imageModel;

    public FolderPresenterLogic(ImageViewImp imageViewImp) {
        this.imageViewImp = imageViewImp;
        imageModel = new ImageModel();
    }

    @Override
    public void addFolder(Context context, FolderObject folderObject) {
        imageModel.openConnectSQL(context);
        boolean result = imageModel.addFolder(folderObject);
        imageViewImp.receiveResult(result);
    }

    @Override
    public void getFolderList(Context context) {
        imageModel.openConnectSQL(context);
        List<FolderObject> folderObjectList1 = imageModel.getListFolder();
        if (folderObjectList1.size() > 0) {
            imageViewImp.receiveFolderList(folderObjectList1);
        }
    }

    @Override
    public void addImage(Context context, byte[] image, int id) {
        imageModel.openConnectSQL(context);
        imageModel.addImage(image, id);
    }

    @Override
    public void getFolderDetail(Context context, String id) {
        imageModel.openConnectSQL(context);
        List<byte[]> imageList = imageModel.getListImage(id);
        if(imageList.size()>0){
            imageViewImp.receiveListImage(imageList);
        }

    }
}
