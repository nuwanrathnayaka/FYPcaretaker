package com.kerbio.virtualcaretaker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Nuwan rathnayaka on 10/2/2017.
 */

public class MedicationSet {
    private List<Medication> medications;
    private Date date;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(Date date) {
        SimpleDateFormat newFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
        String title=newFormat.format(date);
        this.title = title;
    }

    public MedicationSet(List<Medication> medications, Date date) {
        this.medications = medications;
        this.date = date;
        this.setTitle(this.date);
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
        setTitle(this.date);
    }
    public void addMedication(Medication med){
        this.medications.add(med);
    }
}
