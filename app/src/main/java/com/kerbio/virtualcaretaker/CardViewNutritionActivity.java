package com.kerbio.virtualcaretaker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Nuwan rathnayaka on 8/20/2017.
 */

public class CardViewNutritionActivity extends AppCompatActivity {
    TextView personName;
    ImageView personPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cardview_activity);
        personName = (TextView)findViewById(R.id.person_name);
        personPhoto = (ImageView)findViewById(R.id.person_photo);

        personName.setText("Emma Wilson");
        personPhoto.setImageResource(R.drawable.med);
    }
}
