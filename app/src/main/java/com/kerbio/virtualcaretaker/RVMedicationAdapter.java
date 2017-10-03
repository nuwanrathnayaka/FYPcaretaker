package com.kerbio.virtualcaretaker;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Nuwan rathnayaka on 8/20/2017.
 */

public class RVMedicationAdapter extends RecyclerView.Adapter<RVMedicationAdapter.MedicationViewHolder> {
    public static class MedicationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        CardView cv;
        TextView personName;
        ImageView personPhoto;

        MedicationViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            personName = (TextView)itemView.findViewById(R.id.person_name);
            personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);

        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "Clicked Country Position = " + getPosition(), Toast.LENGTH_SHORT).show();
        }
    }

    List<Medication> medications;

    RVMedicationAdapter(List<Medication> medications){
        this.medications = medications;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public RVMedicationAdapter.MedicationViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_nutrition, viewGroup, false);
        RVMedicationAdapter.MedicationViewHolder pvh = new RVMedicationAdapter.MedicationViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(RVMedicationAdapter.MedicationViewHolder medicationViewHolder, int i) {
        medicationViewHolder.personName.setText(medications.get(i).getMed_name());
        medicationViewHolder.personPhoto.setImageResource(medications.get(i).getIcon());
    }

    @Override
    public int getItemCount() {
        return medications.size();
    }

}
