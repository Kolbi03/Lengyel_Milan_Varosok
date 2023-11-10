package com.example.lengyel_milan_varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SearchResult extends AppCompatActivity {

    private TextView textViewResult;
    private Button buttonVissza;
    private DBHelper DBHelper;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        init();

        buttonVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SearchResult.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void init()
    {
        textViewResult = findViewById(R.id.textViewResult);
        buttonVissza = findViewById(R.id.buttonSearchVissza);

        sharedPreferences = getSharedPreferences("Data",
                Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        String helper = sharedPreferences.getString("Data", "Nincs adat");

        Cursor datas = DBHelper.dataGet(helper);


        if(datas.getCount()==0)
        {
            textViewResult.setText(helper);
        }
        else
        {
            StringBuilder sb = new StringBuilder();
            while (datas.moveToNext()) {
                sb.append("Város: ").append(datas.getString(0)).append("\n");

                textViewResult.setText(sb);
            }
        }
    }
}