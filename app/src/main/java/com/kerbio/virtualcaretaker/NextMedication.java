package com.kerbio.virtualcaretaker;

/**
 * Created by Nuwan rathnayaka on 10/3/2017.
 */

public class NextMedication {
    private String title;
    private String description;

    public NextMedication(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
