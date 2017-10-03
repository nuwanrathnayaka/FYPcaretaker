package com.kerbio.virtualcaretaker;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.andexert.expandablelayout.library.ExpandableLayoutListView;

import com.kerbio.virtualcaretaker.R;

import java.util.ArrayList;
import java.util.List;

public class NutritionActivity extends Activity {
    private List<Nutrition> persons;
    private RecyclerView rv;
    private final String[] array = {"Hello", "World", "Android", "is", "Awesome", "World", "Android", "is", "Awesome", "World", "Android", "is", "Awesome", "World", "Android", "is", "Awesome"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);

//        rv=(RecyclerView)findViewById(R.id.rv);
//
//        LinearLayoutManager llm = new LinearLayoutManager(this);
//        rv.setLayoutManager(llm);
//        rv.setHasFixedSize(true);

        //initializeData();
        //initializeAdapter();
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.view_row, R.id.header_text, array);
        final ExpandableLayoutListView expandableLayoutListView = (ExpandableLayoutListView) findViewById(R.id.listview);

        expandableLayoutListView.setAdapter(arrayAdapter);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    private void initializeData(){
        persons = new ArrayList<>();
        persons.add(new Nutrition("Emma Wilson",   R.drawable.med_yes));
        persons.add(new Nutrition("Lavery Maiss",  R.drawable.med_yes));
        persons.add(new Nutrition("Lillie Watts",  R.drawable.med_yes));
        persons.add(new Nutrition("Nuwan rathnayaka",  R.drawable.med_yes));
        persons.add(new Nutrition("Lahiru jayakodi",  R.drawable.med_yes));
        persons.add(new Nutrition("ranapathi",  R.drawable.med_yes));
        persons.add(new Nutrition("Lillie Watts",  R.drawable.med_yes));
        persons.add(new Nutrition("Lillie Watts",  R.drawable.med_yes));
        persons.add(new Nutrition("Lillie Watts",  R.drawable.med_yes));
        persons.add(new Nutrition("Lillie Watts",  R.drawable.med_yes));
        persons.add(new Nutrition("Lillie Watts",  R.drawable.med_yes));
    }

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(persons);
        rv.setAdapter(adapter);
    }

}
