package com.kerbio.virtualcaretaker;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nuwan rathnayaka on 8/2/2017.
 */

public class APICaller extends AppCompatActivity {
    private Activity myActivity;
    RequestQueue queue;
    Map<String, String> jsonParams;
    private String userID;
    private String token;
    private String out;

    private String result = "{ \"status\": \"success\", \"data\": [ { \"_id\": \"5921bce2f36d285f6789a5ea\", \"user_id\": 1234, \"start_date\": \"Sun May 10 20:07:35 +0000 2016\", \"end_date\": \"Sun May 30 20:07:35 +0000 2016\", \"medications\": [ { \"name\": \"DOXAZOSIN MESYLATE\", \"count\": 2, \"desc\": \"2 by six hourse\", \"time\": \"06:00:00\" } ] } ] }";
    String url = "https://0797wlmaf4.execute-api.us-east-1.amazonaws.com/dev/medication/getMedication";

    public APICaller(Activity currentActivity,String userID,String token) {
        myActivity = currentActivity;
        queue=Volley.newRequestQueue(myActivity);
        jsonParams = new HashMap<String, String>();
        jsonParams.put("user_id", userID);
        jsonParams.put("tokenId", token);
        out="";
    }



//    public void send() {
//        RequestQueue queue = Volley.newRequestQueue(this);
//        Map<String, String> jsonParams = new HashMap<String, String>();
//        jsonParams.put("user_id", "1234");
//        jsonParams.put("tokenId", "%$475786VYTYV7&5!#$67Vj)Ub");
//        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url,
//
//                new JSONObject(jsonParams),
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        JSONObject jsonObject = response;
//                        //Toast.makeText(MainActivity.this, jsonObject.toString(), Toast.LENGTH_SHORT).show();
//                        // handle response
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                        //   Handle Error
//                    }
//                }) {
//        };
//        queue.add(postRequest);
//    }

    public void getMedication(final VolleyCallback callback){
        url = "https://r0wl6iaxea.execute-api.us-east-1.amazonaws.com/dev/medication/getMedicationPlan";
        String out;
        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url,
                new JSONObject(jsonParams),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject jsonObject = response;
                        Toast.makeText(myActivity, jsonObject.toString(), Toast.LENGTH_SHORT).show();
                        // handle response
                        callback.onSuccess(jsonObject.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //   Handle Error
                        Toast.makeText(myActivity, "not sending", Toast.LENGTH_SHORT).show();
                    }
                }) {
        };
        queue.add(postRequest);
    }

    public void alert() {
        final String abc="";
        final Medication m=new Medication("n","n",1);
        this.url="https://0797wlmaf4.execute-api.us-east-1.amazonaws.com/dev/users/emegencySMSNotification";
        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url,
                new JSONObject(jsonParams),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject jsonObject = response;
                        Toast.makeText(myActivity, jsonObject.toString(), Toast.LENGTH_SHORT).show();
                        // handle response
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        //   Handle Error
                    }
                }) {
        };
        queue.add(postRequest);
    }
    public void getRelation(final VolleyCallback callback){
        url = "https://0797wlmaf4.execute-api.us-east-1.amazonaws.com/dev/users/getCurrentUser";
        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url,
                new JSONObject(jsonParams),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject jsonObject = response;
                        //Toast.makeText(myActivity, jsonObject.toString(), Toast.LENGTH_SHORT).show();
                        // handle response
                        callback.onSuccess(jsonObject.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //   Handle Error
                    }
                }) {
        };
        queue.add(postRequest);
    }
    public void currentMedication(final VolleyCallback callback){
        url = "https://r0wl6iaxea.execute-api.us-east-1.amazonaws.com/dev/medication/getMedication";
        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url,
                new JSONObject(jsonParams),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject jsonObject = response;
                        Toast.makeText(myActivity, jsonObject.toString(), Toast.LENGTH_SHORT).show();
                        // handle response
                        callback.onSuccess(jsonObject.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //   Handle Error
                        Toast.makeText(myActivity, "Error", Toast.LENGTH_SHORT).show();
                    }
                }) {
        };
        queue.add(postRequest);
    }

}
