package com.kerbio.virtualcaretaker;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {
    private List<Relation> relations;
    private RecyclerView rv;
    APICaller api;
    PopupWindow pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        final String[] out = {""};
        rv=(RecyclerView)findViewById(R.id.rv);

        api=new APICaller(this,"niroshan8889","%$475786VYTYV7&5!#$67Vj)Ub");
        api.getRelation(new VolleyCallback(){
            @Override
            public void onSuccess(String result){
                out[0] =result;
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                //do all the stuff here
                Decoder d=new Decoder();
                try {
                    //Toast.makeText(getApplicationContext(),"med!",Toast.LENGTH_LONG).show();
                    relations=d.getRelations(UserActivity.this,out[0]);
                    initializeAdapter();
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),"no!",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

//        initializeData();
//        initializeAdapter();
    }
    private void initializeData(){
//        Decoder d=new Decoder();
//        try {
//            //Toast.makeText(getApplicationContext(),"med!",Toast.LENGTH_LONG).show();
//            medications=d.medicationDecoder(MedicationActivity.this);
//        } catch (JSONException e) {
//            Toast.makeText(getApplicationContext(),"no!",Toast.LENGTH_LONG).show();
//            e.printStackTrace();
//        }
        relations = new ArrayList<>();
        relations.add(new Relation("nuwan","rathnayaka","abcd","222222222","brother"));
        relations.add(new Relation("nuwan","rathnayaka","abcd","222222222","brother"));
        relations.add(new Relation("nuwan","rathnayaka","abcd","222222222","brother"));
        relations.add(new Relation("nuwan","rathnayaka","abcd","222222222","brother"));
        relations.add(new Relation("nuwan","rathnayaka","abcd","222222222","brother"));
        relations.add(new Relation("nuwan","rathnayaka","abcd","222222222","brother"));
        relations.add(new Relation("nuwan","rathnayaka","abcd","222222222","brother"));
        relations.add(new Relation("nuwan","rathnayaka","abcd","222222222","brother"));
        relations.add(new Relation("nuwan","rathnayaka","abcd","222222222","brother"));
        relations.add(new Relation("nuwan","rathnayaka","abcd","222222222","brother"));

    }

    private void initializeAdapter(){
        RVRelationsAdapter adapter = new RVRelationsAdapter(relations);
        rv.setAdapter(adapter);
    }

    public void addNewRelation(View v){
//We need to get the instance of the LayoutInflater, use the context of this activity
        LayoutInflater inflater = (LayoutInflater) UserActivity.this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Inflate the view from a predefined XML layout
        View layout = inflater.inflate(R.layout.add_popup,
                (ViewGroup) findViewById(R.id.popup_element));
        // create a 300px width and 470px height PopupWindow
        pw = new PopupWindow(layout, 400, 600, true);
        // display the popup in the center
        pw.showAtLocation(v, Gravity.CENTER, 0, 0);

        Button cancelButton = (Button) layout.findViewById(R.id.pop_up_cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pw.dismiss();
            }
        });

        Button addButton=(Button) layout.findViewById(R.id.add_relation_btn);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do the api call here
            }
        });
    }
}
