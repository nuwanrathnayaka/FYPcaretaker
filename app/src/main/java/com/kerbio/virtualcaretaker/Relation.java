package com.kerbio.virtualcaretaker;

/**
 * Created by Nuwan rathnayaka on 9/4/2017.
 */

public class Relation {
    private String firstName;
    private String lastName;
    private String address;
    private String contactNumber;
    private String relation;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    private int icon;

    public  Relation(String firstName,String lastName,String address,String contactNumber,String relation){
        this.firstName=firstName;
        this.lastName=lastName;
        this.address=address;
        this.contactNumber=contactNumber;
        this.relation=relation;
        this.icon=R.drawable.relation;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}
