package com.kerbio.virtualcaretaker;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Nuwan rathnayaka on 8/4/2017.
 */

public class Medication {
    private String med_name;
    private String med_desc;
    private String med_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private int med_count;
    private int icon;
    private boolean taken;
    private String id;
    private String url;

    public int getIcon() {
        return icon;
    }

    public void setIcon() {
        if (this.taken) {
            this.icon = R.drawable.med_taken;
        } else {
            this.icon = R.drawable.med_not_taken;
        }
    }

    public Medication(String med_name, String med_time, int med_count) {
        this.med_name = med_name;
        this.med_count = med_count;
        this.med_time = med_time;
        this.taken = false;
        this.setIcon();
    }
    public Medication(String med_name, String med_time, int med_count,String url){
        this.med_name = med_name;
        this.med_count = med_count;
        this.med_time = med_time;
        this.taken = false;
        this.setIcon();
        this.url=url;

    }

    public Medication(String id, String name, String desc, String medTime) {
        this.id = id;
        this.med_name = name;
        this.med_desc = desc;
        this.med_time = medTime;
    }
    //constructor overloading

    public String getMed_name() {
        return med_name;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public void setMed_name(String med_name) {
        this.med_name = med_name;
    }

    public String getMed_desc() {
        return med_desc;
    }

    public void setMed_desc(String med_desc) {
        this.med_desc = med_desc;
    }

    public String getMed_time() {
        return med_time;
    }

    public void setMed_time(String med_time) {
        this.med_time = med_time;
    }

    public int getMed_count() {
        return med_count;
    }

    public void setMed_count(int med_count) {
        this.med_count = med_count;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
