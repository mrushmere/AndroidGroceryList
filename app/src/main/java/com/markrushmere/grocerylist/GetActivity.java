package com.markrushmere.grocerylist;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Handler;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class GetActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);


        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void ... Params) {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("https://api2.bigoven.com/grocerylist?api_key=lkEGrI9g8gXCM9P2AGxR1OeT94L3SEj8")
                        .header("type", "GET")
                        .addHeader("dataType", "json")
                        .addHeader("Authorization", "Basic bWFyazY3OTA6dGVkZHlyMDE=")
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    String jsonData = response.body().string();
                    try {
                        JSONObject Jobject = new JSONObject(jsonData);
                        JSONArray Jarray = Jobject.getJSONArray("Items");
                        List<String> groceryListGet = new ArrayList<String>(Jarray.length());
                        for (int i = 0; i < Jarray.length(); i++) {
                            JSONObject object     = Jarray.getJSONObject(i);
                            String listItem = object.getString("Name");
                            groceryListGet.add(listItem);
                            Collections.sort(groceryListGet);
                            Log.d("ITEMS", object.toString());
                        }
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    Log.d("RES", response.body().string());
                    return response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();

        /*****************
        String jsonData = response.body().string();
        JSONObject Jobject = new JSONObject(jsonData);
        JSONArray Jarray = Jobject.getJSONArray("Items");
        List<String> groceryListGet = new ArrayList<String>(Jarray.length());
        for (int i = 0; i < Jarray.length(); i++) {
            JSONObject object     = Jarray.getJSONObject(i);
            String listItem = object.getString("Name");
            groceryListGet.add(listItem);
            Collections.sort(groceryListGet);
            Log.d("ITEMS", object.toString());
        }

        ListView myListView = (ListView) findViewById(R.id.list_view2);
        myListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, groceryListGet));
        ************************/


    }
}
