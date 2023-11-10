package com.example.lengyel_milan_varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextOrszag;
    private Button buttonKeres;
    private Button buttonInsert;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        buttonKeres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String orszag = editTextOrszag.getText().toString();
                if(orszag.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Nem lehet üres", Toast.LENGTH_SHORT).show();
                }
                else {

                    SharedPreferences sharedPreferences = getSharedPreferences("Data",
                            Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("Data", orszag);
                    editor.apply();








                    Intent intent = new Intent(MainActivity.this, SearchResult.class);

                    startActivity(intent);
                    finish();
                }
            }
        });

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent(MainActivity.this, Insert.class);

                    startActivity(intent);
                    finish();
            }
        });
    }

    private void init()
    {
        editTextOrszag = findViewById(R.id.editTextMainOrszag);
        buttonKeres = findViewById(R.id.buttonKereses);
        buttonInsert = findViewById(R.id.buttonUjFelvetel);
    }
}