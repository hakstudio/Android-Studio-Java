package com.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<User> users;
    SQLiteHelper db;
    ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = findViewById(R.id.listView);
        db = new SQLiteHelper(getApplicationContext());
        users = db.getUsers();
        list = new ArrayList<>();

        try {
            for (int i = 0; i < users.size(); i++) {
                list.add(i, users.get(i).getName());
            }
        }catch (Exception e){
            if(e.getMessage().contains("null object reference")){
                Toast.makeText(getApplicationContext(),"Veritabanı Boş",Toast.LENGTH_SHORT).show();
                finish();
            }
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }
}