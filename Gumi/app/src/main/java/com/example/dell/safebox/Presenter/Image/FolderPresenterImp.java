package com.example.dell.safebox.Presenter.Image;

import android.content.Context;

import com.example.dell.safebox.Object.FolderObject;

import java.util.List;

public interface FolderPresenterImp {
    void addFolder(Context context, FolderObject folderObject);

    void getFolderList(Context context);

    void addImage(Context context, byte[]image,int id);

    void getFolderDetail(Context context,String id);
}
