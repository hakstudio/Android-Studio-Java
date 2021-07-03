package com.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserFormActivity extends AppCompatActivity {
    EditText name, surname;
    Button register, listAll;
    SQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_form);

        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        register = findViewById(R.id.register);
        listAll = findViewById(R.id.listAll);
        db = new SQLiteHelper(getApplicationContext());

        db.onUpgrade(db.getWritableDatabase(), 1, 2);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.addUser(new User(name.getText().toString(), surname.getText().toString()));
                Toast.makeText(getApplicationContext(), "Kayıt Edildi", Toast.LENGTH_SHORT).show();
            }
        });

        listAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserFormActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });
    }
}