package com.example.earthquakenotifications;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;

public class ParseXML {
    Context context;

    String url;
    RequestQueue myQueue;
    Response.Listener<String> myListener;
    Response.ErrorListener errorListener;
    StringRequest stringRequest;

    public ParseXML(Context context, String url) {
        this.context = context;
        this.url = url;
        myQueue = Volley.newRequestQueue(context);
        listenerSettings();
        getData();
    }

    void getData() {
        stringRequest = new StringRequest(Request.Method.GET, url, myListener, errorListener);
        myQueue.add(stringRequest);
    }

    void listenerSettings() {
        myListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<String> itemList = parseItems(response);
                ArrayList<HashMap<String, String>> itemMapList = parseElements(itemList);
            }
        };
        errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        };
    }

    ArrayList<String> parseItems(String document) {
        ArrayList<String> items = new ArrayList<>();
        int beginOld = 0;
        int endOld = 0;
        while (true) {
            try {
                int begin = document.indexOf("<item>", beginOld);
                beginOld = begin + ("<item>").length();
                int end = document.indexOf("</item>", endOld) + ("</item>").length();
                endOld = end;
                items.add(document.substring(begin, end));
            } catch (Exception e) {
                break;
            }
        }
        return items;
    }

    ArrayList<HashMap<String, String>> parseElements(ArrayList<String> itemList) {
        ArrayList<HashMap<String, String>> itemMapList = new ArrayList<>();
        for (int i = 0; i < itemList.size(); i++) {
            HashMap<String, String> itemMap = new HashMap<>();
            String item = itemList.get(i);
            itemMap.put("title", parseElemet(item, "title"));
            itemMap.put("description", parseElemet(item, "description"));
            itemMap.put("link", parseElemet(item, "link"));
            itemMap.put("pubDate", parseElemet(item, "pubDate"));
            itemMapList.add(itemMap);
        }
        return itemMapList;
    }

    String parseElemet(String item, String element) {
        int beginIndex = item.indexOf("<" + element + ">") + ("<" + element + ">").length();
        int endIndex = item.indexOf("</" + element + ">");
        return item.substring(beginIndex, endIndex);
    }
}
