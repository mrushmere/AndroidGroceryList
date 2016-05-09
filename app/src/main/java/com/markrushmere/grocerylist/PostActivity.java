package com.markrushmere.grocerylist;

import android.util.Log;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PostActivity extends ListActivity{
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    PostActivity example = new PostActivity();

    //String response = example.post("https://api2.bigoven.com/grocerylist/sync", json);


    String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .header("api_key", "lkEGrI9g8gXCM9P2AGxR1OeT94L3SEj8")
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    String listJson(ArrayList<String> array) {
        String jsonStr = "{\"list\": [";
        for(int i = 0; i < array.size(); i++) {
            String curItem = "{\"ItemID\": " + i + "," +
                    "\"GUID\": \"string\"," +
                    "\"RecipeID\": 0," +
                    "\"Name\": " + array.get(i) + "," +
                    "\"Notes\": \"string\"," +
                    "\"DisplayQuantity\": \"string\"," +
                    "\"Department\": \"string\"," +
                    "\"CreationDate\": \"2016-05-08T04:15:53.991Z\"," +
                    "\"IsChecked\": true," +
                    "\"LocalStatus\": \"string\"," +
                    "\"LastModified\": \"2016-05-08T04:15:53.991Z\"," +
                    "\"BigOvenObject\": \"string\"," +
                    "\"ThirdPartyURL\": \"string\"},";
            jsonStr = jsonStr.concat(curItem);
        }
        // Remote the last comma added  by the for loop
        jsonStr.substring(0, jsonStr.length()-1);

        // Add the rest of the json object
        String endJsonStr = "]}";
        jsonStr = jsonStr.concat(endJsonStr);

        Log.d("Error", jsonStr);
        return jsonStr;
    }


    /****
    public static void main(String[] args) throws IOException {
        PostActivity example = new PostActivity();
        ArrayList<String>
        String json = example.listJson(testing);
        String response = example.post("https://api2.bigoven.com/grocerylist/sync", json);
        System.out.println(response);
    }

     *********/
}