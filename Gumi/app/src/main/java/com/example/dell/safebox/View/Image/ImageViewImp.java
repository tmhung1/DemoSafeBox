package com.example.dell.safebox.View.Image;

import com.example.dell.safebox.Object.FolderObject;
import com.example.dell.safebox.Object.Image;

import java.util.List;

public interface ImageViewImp {
    void receiveResult(Boolean result);

    void receiveFolderList(List<FolderObject> folderObjectList);

    void receiveListImage(List<byte[]>list);

}
