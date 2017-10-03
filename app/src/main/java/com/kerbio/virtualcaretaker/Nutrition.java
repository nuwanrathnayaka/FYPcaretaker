package com.kerbio.virtualcaretaker;

/**
 * Created by Nuwan rathnayaka on 8/9/2017.
 */

public class Nutrition {
    String title;
    int icon;

    public Nutrition(String title, int icon){
        this.title=title;
        this.icon=icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
