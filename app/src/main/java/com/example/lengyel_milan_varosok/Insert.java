package com.example.lengyel_milan_varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Insert extends AppCompatActivity {

    private EditText editTextNev;
    private EditText editTextOrszag;
    private EditText editTextLakossag;
    private Button buttonFelvetel;
    private Button buttonVissza;
    private DBHelper DBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        init();

        buttonVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Insert.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonFelvetel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextNev.getText().toString();
                String country = editTextOrszag.getText().toString();
                String population = editTextLakossag.getText().toString();

                if (name.isEmpty() || country.isEmpty() || population.isEmpty()) {
                    Toast.makeText(Insert.this, "Mindent kötelező kitölteni!", Toast.LENGTH_SHORT).show();
                } else {
                    int alcoholInt = Integer.parseInt(population);
                    if (DBHelper.dataRecord(name, country, alcoholInt)) {
                        Toast.makeText(Insert.this,
                                "Sikeres adatfelvétel", Toast.LENGTH_SHORT).show();

                        editTextReset();
                    } else {
                        Toast.makeText(Insert.this,
                                "Sikertelen adatfelvétel", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    private void init()
    {
        editTextLakossag = findViewById(R.id.editTextLakossag);
        editTextNev = findViewById(R.id.editTextNev);
        editTextOrszag = findViewById(R.id.editTextOrszag);
        buttonFelvetel = findViewById(R.id.buttonFelvetel);
        buttonVissza = findViewById(R.id.buttonInsertVissza);
        DBHelper = new DBHelper(Insert.this);
    }

    public void editTextReset()
    {
        editTextLakossag.setText("");
        editTextOrszag.setText("");
        editTextNev.setText("");
    }
}