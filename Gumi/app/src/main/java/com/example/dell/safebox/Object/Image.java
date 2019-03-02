package com.example.dell.safebox.Object;

import java.util.List;

public class Image {
    private int id;
    private List<byte[]> imageList;

    public Image() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<byte[]> getImageList() {
        return imageList;
    }

    public void setImageList(List<byte[]> imageList) {
        this.imageList = imageList;
    }
}
