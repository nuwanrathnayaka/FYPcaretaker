package com.kerbio.virtualcaretaker;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class AddNewRelationDialog extends DialogFragment {

    Spinner countryCodeSpinner;
    Spinner relationTypeSpinner;

    public static AddNewRelationDialog newInstance() {
        AddNewRelationDialog addNewRelationDialog = new AddNewRelationDialog();
        Bundle bundle = new Bundle();
        addNewRelationDialog.setArguments(bundle);
        return addNewRelationDialog;
    }

    /**
     * Initialize instance variables with data from bundle
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomTheme_Dialog);
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.fragment_add_new_relation_dialog, null);
        countryCodeSpinner = (Spinner) rootView.findViewById(R.id.spinner_country_code);
        relationTypeSpinner = (Spinner) rootView.findViewById(R.id.spinner_relation);
        setUpCountryCodeSpinner();
        setUpRelationTypeSpinner();
        builder.setView(rootView);
        return builder.create();
    }


    private void setUpCountryCodeSpinner() {
        ArrayAdapter countryCodeSpinnerAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.array_country_code, android.R.layout.simple_spinner_item);
        countryCodeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        countryCodeSpinner.setAdapter(countryCodeSpinnerAdapter);
    }

    private void setUpRelationTypeSpinner() {
        ArrayAdapter relationTypeSpinnerAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.array_relation_type, android.R.layout.simple_spinner_item);
        relationTypeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        relationTypeSpinner.setAdapter(relationTypeSpinnerAdapter);
    }


}
