package com.kerbio.virtualcaretaker;

import java.util.List;

/**
 * Created by user on 9/11/2017.
 */

public class MedicationPlan {
    private String start_date;
    private List<Medication> medications;

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }
}
