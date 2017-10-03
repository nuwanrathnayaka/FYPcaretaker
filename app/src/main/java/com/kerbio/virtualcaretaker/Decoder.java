package com.kerbio.virtualcaretaker;

import android.app.Activity;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Nuwan rathnayaka on 8/31/2017.
 */

public class Decoder {

    public MedicationPlan medicationDecoder(Activity myact, String JSONString) throws JSONException {
        String myInput = JSONString;
        String status;
        String id;
        String time;
        MedicationPlan medicationPlan = new MedicationPlan();
        //String myInput="{ \"status\": \"success\", \"data\": [ { \"_id\": \"5921bce2f36d285f6789a5ea\", \"user_id\": 1234, \"start_date\": \"Sun May 10 20:07:35 +0000 2016\", \"end_date\": \"Sun May 30 20:07:35 +0000 2016\", \"medications\": [ { \"name\": \"DOXAZOSIN MESYLATE\", \"count\": 2, \"desc\": \"2 by six hourse\", \"time\": \"06:00:00\" } ] } ] }";
        //String myInput = "{\"status\":\"success\",\"data\":[  {  \"_id\":\"59a3a3e6734d1d7ab9eac63c\",\"user_id\":1234,\"start_date\":\"Sun May 10 20:07:35 +0000 2016\",\"end_date\":\"Sun May 30 20:07:35 +0000 2016\",\"day_plan\":[  {  \"time\":\"06:00:00\",\"medications\":[  {  \"name\":\"DOXAZOSIN MESYLATE\",\"count\":2},{  \"name\":\"MESYLATE\",\"count\":2},{  \"name\":\"ZOSISYLATE\",\"count\":2}]}]}]}";
        JSONObject jObject = new JSONObject(myInput);
        status = jObject.getString("status");
        List<Medication> medications = new ArrayList<>();

        JSONArray jo = jObject.getJSONArray("data");
        //Toast.makeText(myact, jo.toString(), Toast.LENGTH_SHORT).show();
        if (jo != null) {
            JSONObject data = jo.getJSONObject(0);
            String startdate = data.getString("start_date");
            JSONArray a_plans = data.getJSONArray("day_plan");
            if (a_plans != null) {
                JSONObject plans = a_plans.getJSONObject(0);
                JSONArray a_medications = plans.getJSONArray("medications");
                if (a_medications != null) {
                    for (int i = 0; i < a_medications.length(); i++) {
                        JSONObject obj = a_medications.getJSONObject(i);
                        String out = obj.getString("name");
                        medications.add(new Medication(obj.getString("name"), "", obj.getInt("count")));
                    }
                    medicationPlan.setStart_date(startdate);
                    medicationPlan.setMedications(medications);
                    //Toast.makeText(myact, out, Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(myact, startdate, Toast.LENGTH_SHORT).show();
            }
        }
        return medicationPlan;
    }

    public List<Medication> getNextMedication(Activity myact) throws JSONException, ParseException {
        List<Medication> next = new ArrayList<>();
        String js = "{\"status\":\"success\",\"data\":[{\"_id\":\"59a3a3e6734d1d7ab9eac63c\",\"user_id\":1234,\"start_date\":\"Sun May 10 20:07:35 +0000 2016\",\"end_date\":\"Sun May 30 20:07:35 +0000 2016\",\"day_plan\":[{\"time\":\"06:00:00\",\"medications\":[{\"name\":\"DOXAZOSIN MESYLATE1\",\"url\":\"http://software.rubikscube.info/icube/icube.php?stickers=yyyyggygrrygryyrrryrgrrgggg&size=200\",\"count\":2},{\"name\":\"MESYLATE\",\"url\":\"https://cdn.pixabay.com/photo/2014/04/02/10/15/smiley-303231_640.png\",\"count\":2},{\"name\":\"ZOSISYLATE\",\"url\":\"https://upload.wikimedia.org/wikipedia/commons/1/1b/Square_200x200.png\",\"count\":2}]},{\"time\":\"12:00:00\",\"medications\":[{\"name\":\"DOXAZOSIN MESYLATE2\",\"url\":\"http://software.rubikscube.info/icube/icube.php?stickers=yyyyggygrrygryyrrryrgrrgggg&size=200\",\"count\":2},{\"name\":\"MESYLATE\",\"url\":\"https://cdn.pixabay.com/photo/2014/04/02/10/15/smiley-303231_640.png\",\"count\":2},{\"name\":\"ZOSISYLATE\",\"url\":\"https://upload.wikimedia.org/wikipedia/commons/1/1b/Square_200x200.png\",\"count\":2}]},{\"time\":\"18:00:00\",\"medications\":[{\"name\":\"DOXAZOSIN MESYLATE3\",\"url\":\"http://software.rubikscube.info/icube/icube.php?stickers=yyyyggygrrygryyrrryrgrrgggg&size=200\",\"count\":2},{\"name\":\"MESYLATE\",\"url\":\"https://cdn.pixabay.com/photo/2014/04/02/10/15/smiley-303231_640.png\",\"count\":2},{\"name\":\"ZOSISYLATE\",\"url\":\"https://upload.wikimedia.org/wikipedia/commons/1/1b/Square_200x200.png\",\"count\":2}]}]}]}";
        JSONObject jObject = new JSONObject(js);

        JSONArray jo = jObject.getJSONArray("data");
//        //Toast.makeText(myact, jo.toString(), Toast.LENGTH_SHORT).show();
        if (jo != null) {
            JSONObject data = jo.getJSONObject(0);
            //Toast.makeText(myA, data.toString(), Toast.LENGTH_SHORT).show();
            String startdate = data.getString("start_date");
            String endDate = data.getString("end_date");
            JSONArray a_plans = data.getJSONArray("day_plan");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
            SimpleDateFormat newFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
            String now1 = formatter.format(new Date());
            Date now = new Date();
            if (a_plans != null) {
                for (int i = 0; i < a_plans.length(); i++) {
                    JSONObject med = a_plans.getJSONObject(i);
                    String time = now1 + " " + med.getString("time");
                    Date temp = newFormat.parse(time);
                    if (temp.after(now)) {
                        JSONArray arr = med.getJSONArray("medications");
                        for (int j = 0; j < arr.length(); j++) {
                            JSONObject medi = arr.getJSONObject(j);
                            next.add(new Medication(medi.getString("name"), medi.getString("name"), medi.getInt("count")));
                        }
                        Toast.makeText(myact, next.toString(), Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
            }
        }
        return next;
    }

    public List<Relation> getRelations(Activity myact, String JSONString) throws JSONException {
        List<Relation> relations = new ArrayList<>();
        //String myInput="{\"status\":\"success\",\"data\":[  {  \"_id\":\"59a3ba9d734d1d7ab9ead016\",\"user_id\":\"1234\",\"relations\":[  {  \"first_name\":\"nushan\",\"last_name\":\"Bandra\",\"address\":\"No 89/9 Mihin Road Colombo 3\",\"tel\":\"+94772080907\",\"relation\":\"Brother\"},{  \"first_name\":\"shan\",\"last_name\":\"Bandra\",\"address\":\"No 89/9 Mihin Road Colombo 3\",\"tel\":\"+94772080907\",\"relation\":\"Brother\"},{  \"first_name\":\"nun\",\"last_name\":\"Bandra\",\"address\":\"No 89/9 Mihin Road Colombo 3\",\"tel\":\"+94772080907\",\"relation\":\"Father\"},{  \"first_name\":\"Hhan\",\"last_name\":\"Bandra\",\"address\":\"No 89/9 Mihin Road Colombo 3\",\"tel\":\"+94772080907\",\"relation\":\"Mother\"}]}]}";
        String myInput = JSONString;
        JSONObject jObject = new JSONObject(myInput);
        String status = jObject.getString("status");

        JSONArray a_data = jObject.getJSONArray("data");
        if (a_data != null) {
            JSONObject data = a_data.getJSONObject(0);
            JSONArray a_relations = data.getJSONArray("relations");
            if (a_relations != null) {
                for (int i = 0; i < a_relations.length(); i++) {
                    JSONObject relation = a_relations.getJSONObject(i);
                    relations.add(new Relation(relation.getString("first_name"), relation.getString("last_name"), relation.getString("address"), relation.getString("tel"), relation.getString("relation")));

                }
            }
        }
        return relations;
    }

    public List<Medication> getCurrentMedicationPlan(Activity myact, String JSONString) throws JSONException {
        List<Medication> medications = new ArrayList<>();
        String myInput = JSONString;
        JSONObject jObject = new JSONObject(myInput);
        String status = jObject.getString("status");
        JSONArray data = jObject.getJSONArray("data");
        if (data != null) {
            for (int i = 0; i < data.length(); i++) {
                JSONObject med = data.getJSONObject(i);
                medications.add(new Medication(med.getString("_id"), med.getString("name"), med.getString("desc"), med.getString("date")));
            }
        }
        return medications;
    }


    //get comming medication times
    public void testTime() {
        String str = "Sun Oct 19 01:00:00 ADT 2014";

// Formatter for the input date
        SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        try {

            Date date = format.parse(str);
            System.out.println(date);
            System.out.println(format.format(date));

            Calendar calendar = Calendar.getInstance();
            Date now = new Date();

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public List<MedicationSet> getTitles(Activity myAct) throws ParseException, JSONException {
        List<String> Titles = new ArrayList<>();
        String js = "{\"status\":\"success\",\"data\":[{\"_id\":\"59a3a3e6734d1d7ab9eac63c\",\"user_id\":1234,\"start_date\":\"Sun May 10 20:07:35 +0000 2016\",\"end_date\":\"Sun May 30 20:07:35 +0000 2016\",\"day_plan\":[{\"time\":\"06:00:00\",\"medications\":[{\"name\":\"DOXAZOSIN MESYLATE1\",\"url\":\"http://software.rubikscube.info/icube/icube.php?stickers=yyyyggygrrygryyrrryrgrrgggg&size=200\",\"count\":2},{\"name\":\"MESYLATE\",\"url\":\"https://cdn.pixabay.com/photo/2014/04/02/10/15/smiley-303231_640.png\",\"count\":2},{\"name\":\"ZOSISYLATE\",\"url\":\"https://upload.wikimedia.org/wikipedia/commons/1/1b/Square_200x200.png\",\"count\":2}]},{\"time\":\"12:00:00\",\"medications\":[{\"name\":\"DOXAZOSIN MESYLATE2\",\"url\":\"http://software.rubikscube.info/icube/icube.php?stickers=yyyyggygrrygryyrrryrgrrgggg&size=200\",\"count\":2},{\"name\":\"MESYLATE\",\"url\":\"https://cdn.pixabay.com/photo/2014/04/02/10/15/smiley-303231_640.png\",\"count\":2},{\"name\":\"ZOSISYLATE\",\"url\":\"https://upload.wikimedia.org/wikipedia/commons/1/1b/Square_200x200.png\",\"count\":2}]},{\"time\":\"18:00:00\",\"medications\":[{\"name\":\"DOXAZOSIN MESYLATE3\",\"url\":\"http://software.rubikscube.info/icube/icube.php?stickers=yyyyggygrrygryyrrryrgrrgggg&size=200\",\"count\":2},{\"name\":\"MESYLATE\",\"url\":\"https://cdn.pixabay.com/photo/2014/04/02/10/15/smiley-303231_640.png\",\"count\":2},{\"name\":\"ZOSISYLATE\",\"url\":\"https://upload.wikimedia.org/wikipedia/commons/1/1b/Square_200x200.png\",\"count\":2}]}]}]}";
        JSONObject jObject = new JSONObject(js);

        List<MedicationSet> output=new LinkedList<>();

        JSONArray jo = jObject.getJSONArray("data");
//        //Toast.makeText(myact, jo.toString(), Toast.LENGTH_SHORT).show();
        if (jo != null) {
            JSONObject data = jo.getJSONObject(0);
            Toast.makeText(myAct, data.toString(), Toast.LENGTH_SHORT).show();
            String startdate = data.getString("start_date");
            String endDate = data.getString("end_date");
            JSONArray a_plans = data.getJSONArray("day_plan");
            if (a_plans != null) {
                for (int i = 0; i < a_plans.length(); i++) {
                    JSONObject med = a_plans.getJSONObject(i);
                    //Titles.add(med.getString("time"));
                    Date now=new Date();
                    Date medDate=getDate(med.getString("time"));
                    if(medDate.after(now)) {
                        output.add(new MedicationSet(medicationParser(med.getJSONArray("medications")), medDate));
                    }
                    //Toast.makeText(myAct, med.getString("time"), Toast.LENGTH_SHORT).show();
                }

                for (int i = 0; i < a_plans.length(); i++) {
                    JSONObject med = a_plans.getJSONObject(i);
                    //Titles.add(med.getString("time"));
                    Date now=new Date();
                    Date medDate=getNextDate(med.getString("time"),1);
                    if(medDate.after(now)) {
                        output.add(new MedicationSet(medicationParser(med.getJSONArray("medications")), medDate));
                    }
                    output.add(new MedicationSet(medicationParser(med.getJSONArray("medications")),medDate));
                    //Toast.makeText(myAct, med.getString("time"), Toast.LENGTH_SHORT).show();
                }
            }
        }
        Toast.makeText(myAct, output.toString(), Toast.LENGTH_SHORT).show();
        return output;
    }

    //accept medication json array and return medication list

    public List<Medication> medicationParser(JSONArray arr) throws JSONException {
        List<Medication> medications = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) {
            JSONObject obj = arr.getJSONObject(i);
            medications.add(new Medication(obj.getString("name"), obj.getString("name"),obj.getInt("count"),obj.getString("url")));
        }
        return medications;
    }

    //accept time string and return date
    //ex:06:00:00
    //06:00:00 -> 2014/04/23 06:00:00
    public Date getDate(String time) throws ParseException {
        Date dNow = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        String now1 = formatter.format(dNow) + " " + time;

        SimpleDateFormat newFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
        Date temp = newFormat.parse(now1);
        return  temp;
    }

    //accept a time and day increment and return the time after that days
    public Date getNextDate(String time, int dayIncrement) throws ParseException {
        Calendar c = Calendar.getInstance();
        Date dNow = getDate(time);
        c.setTime(dNow);
        c.add(Calendar.DATE, dayIncrement);
        Date tommorow = c.getTime();
        return tommorow;
    }
}
