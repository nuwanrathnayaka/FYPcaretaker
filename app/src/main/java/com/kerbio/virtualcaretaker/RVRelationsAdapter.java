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
 * Created by Nuwan rathnayaka on 9/5/2017.
 */

public class RVRelationsAdapter extends RecyclerView.Adapter<RVRelationsAdapter.RelationViewHolder> {
    public static class RelationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        CardView cv;
        TextView personName;
        ImageView personPhoto;

        RelationViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.card_relation);
            personName = (TextView)itemView.findViewById(R.id.person_name);
            personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);

        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "Clicked Country Position = " + getPosition(), Toast.LENGTH_SHORT).show();
        }
    }

    List<Relation> relations;

    RVRelationsAdapter(List<Relation> relations){
        this.relations = relations;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public RVRelationsAdapter.RelationViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_relation, viewGroup, false);
        RVRelationsAdapter.RelationViewHolder pvh = new RVRelationsAdapter.RelationViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(RVRelationsAdapter.RelationViewHolder medicationViewHolder, int i) {
        medicationViewHolder.personName.setText(relations.get(i).getFirstName());
        medicationViewHolder.personPhoto.setImageResource(relations.get(i).getIcon());
    }

    @Override
    public int getItemCount() {
        return relations.size();
    }

}
