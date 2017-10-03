package com.kerbio.virtualcaretaker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ProfileActivity extends AppCompatActivity {
    AddNewRelationDialog addNewRelationDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }
    // show add new relation dialog when clicking on fab button
    public void showAddNewRelationPopup(View view) {
        addNewRelationDialog = AddNewRelationDialog.newInstance();
        addNewRelationDialog.show(getSupportFragmentManager(), "Add New Relaation");
    }
}
