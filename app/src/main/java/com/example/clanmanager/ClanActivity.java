package com.example.clanmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ClanActivity extends AppCompatActivity {
    private EditText clanName;
    private Button join;
    private Button create;
    private Button delete;
    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clan);

        clanName = (EditText)findViewById(R.id.editText1);
        join = (Button)findViewById(R.id.button);
        create = (Button)findViewById(R.id.button2);
        delete = (Button)findViewById(R.id.button3);
        login = (Button)findViewById(R.id.button3);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String createClan = clanName.getText().toString().trim();
            }
        });

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String joinClan = clanName.getText().toString().trim();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String deleteClan = clanName.getText().toString().trim();
            }
        });

        login .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginCLan = clanName .getText().toString().trim();
            }
        });
    }
}
