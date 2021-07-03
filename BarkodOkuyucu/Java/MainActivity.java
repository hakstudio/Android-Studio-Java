package com.hakstudio.barkodokuyucu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {
    IntentIntegrator integrator;
    Button oku;
    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        integrator = new IntentIntegrator(this);
        integrator.setPrompt("Barkod Tara");
        integrator.setBeepEnabled(false);

        oku = findViewById(R.id.oku);
        resultText=findViewById(R.id.resultText);
        oku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                integrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            resultText.setText(result.getContents());
            if (result.getContents() != null) {
            } else {
                System.out.println("HATA");
            }
        }
    }
}