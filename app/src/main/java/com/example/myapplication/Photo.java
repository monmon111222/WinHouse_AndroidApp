package com.example.myapplication;

/**
 * Created by Quoc Nguyen on 13-Dec-16.
 */

public class Photo {
    private int id;
    private String gid;
    //private String price;
    private byte[] image;

    public Photo(byte[] image, int id,String gid) {
        //this.name = name;
        this.gid = gid;
        this.image = image;
        this.id = id;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
