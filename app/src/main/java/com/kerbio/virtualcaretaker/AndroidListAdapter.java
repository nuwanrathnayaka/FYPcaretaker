package com.kerbio.virtualcaretaker;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Nuwan rathnayaka on 6/10/2017.
 */

public class AndroidListAdapter extends ArrayAdapter{
    String[] androidListViewStrings;
    Integer[] imagesId;
    Context context;
    private static LayoutInflater inflater=null;

    public AndroidListAdapter(Activity context, Integer[] imagesId, String[] textListView) {
        super(context, R.layout.medication_item_list_item, textListView);
        this.androidListViewStrings = textListView;
        this.imagesId = imagesId;
        this.context = context;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
//        LayoutInflater layoutInflater = (LayoutInflater) context
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View viewRow = layoutInflater.inflate(R.layout.medication_item_list_item, null,
//                true);
        View viewRow;
        viewRow=inflater.inflate(R.layout.medication_item_list_item, null);
        TextView mtextView = (TextView) viewRow.findViewById(R.id.text_view);
        ImageView mimageView = (ImageView) viewRow.findViewById(R.id.image_view);
        mtextView.setText(androidListViewStrings[i]);
        mimageView.setImageResource(imagesId[i]);
        return viewRow;
    }
}
