package com.example.myapplication;

import android.graphics.Bitmap;

/**
 * Created by Hp on 3/17/2016.
 */
public class Scenic {
    private String name,month,phone,device,open,transportation,parking,position,info;
    private int id;
    private byte[] img,img1,img2,img3;

    public Scenic(String name, String month, String phone, String device, String open, String transportation, String parking, String position, String info, int id, byte[] img,byte[] img1,byte[] img2,byte[] img3) {
        this.name = name;
        this.month = month;
        this.phone = phone;
        this.device = device;
        this.open = open;
        this.transportation = transportation;
        this.parking = parking;
        this.position = position;
        this.info = info;
        this.id = id;
        this.img = img;
        this.img1 =img1;
        this.img2 = img2;
        this.img3 = img3;

    }

    public byte[] getImg1() {
        return img1;
    }

    public void setImg1(byte[] img1) {
        this.img1 = img1;
    }

    public byte[] getImg2() {
        return img2;
    }

    public void setImg2(byte[] img2) {
        this.img2 = img2;
    }

    public byte[] getImg3() {
        return img3;
    }

    public void setImg3(byte[] img3) {
        this.img3 = img3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getTransportation() {
        return transportation;
    }

    public void setTransportation(String transportation) {
        this.transportation = transportation;
    }

    public String getParking() {
        return parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
