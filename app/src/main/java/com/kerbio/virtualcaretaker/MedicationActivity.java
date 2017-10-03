package com.kerbio.virtualcaretaker;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andexert.expandablelayout.library.ExpandableLayoutListView;
import com.roughike.swipeselector.OnSwipeItemSelectedListener;
import com.roughike.swipeselector.SwipeItem;
import com.roughike.swipeselector.SwipeSelector;

import org.json.JSONException;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MedicationActivity extends AppCompatActivity {

    private List<Medication> medications;
    private List<MedicationSet> medicationsComming;

    private CircleImageView icon;

    private RecyclerView rv;
    APICaller api;
    RelativeLayout leftLayOut;
    SwipeSelector swipeSelector;
    private String[] nextTitles;
    String nowMedicatinID;
    ImageView iconImage;
    ExpandableLayoutListView expandableLayoutListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication);
        final String[] out = {""};
        rv=(RecyclerView)findViewById(R.id.rv);
        leftLayOut= (RelativeLayout) findViewById(R.id.med_left_lo);

        nextTitles=new String[6];

        expandableLayoutListView = (ExpandableLayoutListView) findViewById(R.id.listview);
        iconImage= (ImageView) findViewById(R.id.med_icon);

        Date d=new Date();
        Toast.makeText(getApplicationContext(),d.toString(),Toast.LENGTH_LONG).show();
        swipeSelector = (SwipeSelector) findViewById(R.id.swipeSelector);
        swipeSelector.setOnItemSelectedListener(new OnSwipeItemSelectedListener() {
            @Override
            public void onItemSelected(SwipeItem item) {
                SwipeItem selectedItem = swipeSelector.getSelectedItem();
                String url=medications.get((Integer) selectedItem.value).getUrl();
                Toast.makeText(getApplicationContext(), url, Toast.LENGTH_LONG).show();
                new DownLoadImageTask(iconImage).execute(url);
            }
        });

        //alarm
//        Intent intent = new Intent(this, Alarm.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 0, intent, 0);
//
//        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//
//        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+10000,pendingIntent );
//        Toast.makeText(this, "Alarm Set.", Toast.LENGTH_LONG).show();
        //end here

        /**
        api=new APICaller(this,"nuwan@gmail.com","%E&DUDTDYIUT");
        api.getMedication(new VolleyCallback(){
            @Override
            public void onSuccess(String result){
                out[0] =result;
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                //do all the stuff here
                Decoder d=new Decoder();
                try {
                    //Toast.makeText(getApplicationContext(),"med!",Toast.LENGTH_LONG).show();
                    medications=d.getCurrentMedicationPlan(MedicationActivity.this,out[0]);
                    setSlider(medications);
                    //setHeader(medications);
                    //initializeAdapter();
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),"no!",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });**/
//        LinearLayoutManager llm = new LinearLayoutManager(this);
//        rv.setLayoutManager(llm);
//        rv.setHasFixedSize(true);

        try {
            initializeData();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //initializeAdapter();

    }

    private void initializeData() throws ParseException, JSONException {
        Decoder d=new Decoder();
//        try {
//            //Toast.makeText(getApplicationContext(),"med!",Toast.LENGTH_LONG).show();
//            medications=d.medicationDecoder(MedicationActivity.this);
//        } catch (JSONException e) {
//            Toast.makeText(getApplicationContext(),"no!",Toast.LENGTH_LONG).show();
//            e.printStackTrace();
//        }
//        medications = new ArrayList<>();
//        medications.add(new Medication("Emma Wilson", "",2));
//        medications.add(new Medication("Emma Wilson", "",2));
//        medications.add(new Medication("Emma Wilson", "",2));
//        medications.add(new Medication("Emma Wilson", "",2));
//        medications.add(new Medication("Emma Wilson", "",2));

            //medicationsComming=d.getTitles(MedicationActivity.this);
        //medicationsComming=d.getNextMedication(MedicationActivity.this);
        medicationsComming=d.getTitles(MedicationActivity.this);
        medications=medicationsComming.get(0).getMedications();
        setSlider(medications);
        for (int i=0;i<medications.size();i++){
            Toast.makeText(getApplicationContext(),medications.get(i).getMed_name(),Toast.LENGTH_LONG).show();
        }
        setNextMedList(medicationsComming);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.view_row, R.id.header_text, nextTitles);
        expandableLayoutListView.setAdapter(arrayAdapter);

    }

    private void initializeAdapter(){
        RVMedicationAdapter adapter = new RVMedicationAdapter(medications);
        rv.setAdapter(adapter);
    }

    public void setHeader(List<Medication> medications){
        LinearLayout parent=new LinearLayout(this);
        parent.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        parent.setOrientation(LinearLayout.VERTICAL);
        int length=medications.size();
        for (final Medication medication : medications){
            parent.addView(setTitleTextView(medication.getMed_name()));
        }
        leftLayOut.addView(parent);
    }

    public Button setTitleTextView(String text){
        Button textView=new Button(MedicationActivity.this);
        textView.setText(text);
        textView.setPadding(0,5,0,0);
        textView.setHeight(50);
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        llp.setMargins(0, 20, 0, 0); // llp.setMargins(left, top, right, bottom);
        textView.setLayoutParams(llp);
        textView.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_shape));
        return textView;
    }

    public void setSlider(List<Medication> medications){
        int length=medications.size();
        SwipeItem[] items=new SwipeItem[length];
        int i=0;
        for (final Medication medication : medications){
            items[i]=(new SwipeItem(i,medication.getMed_name(),medication.getMed_name()));
            i++;
        }
        swipeSelector.setItems(items);
    }

    public void setNextMedList(List<MedicationSet> meds){
        for(int i=0;i<6;i++){
            String time=meds.get(i).getTitle();
            nextTitles[i]=time;
        }
        //Toast.makeText(getApplicationContext(),nextTitles.toString(),Toast.LENGTH_LONG).show();
    }

}
