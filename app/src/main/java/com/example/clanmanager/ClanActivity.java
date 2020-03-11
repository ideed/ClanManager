package com.example.clanmanager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ClanActivity extends AppCompatActivity {
    private EditText clanName;
    private Button join;
    private Button create;
    private Button delete;
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clan);

        clanName = (EditText)findViewById(R.id.editText1);
        create = (Button)findViewById(R.id.createBtn);
        join = (Button)findViewById(R.id.joinBtn);
        delete = (Button)findViewById(R.id.deleteBtn);


        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference("clan");


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(clanName.toString().trim())){
                    Toast.makeText(getApplicationContext(),"Please type in a clan.",Toast.LENGTH_SHORT);
                }else{
                    String createClan = clanName.getText().toString().trim();
                    String keyId = mDatabase.push().getKey();
                    mDatabase.child(keyId).setValue(createClan);
                    Toast.makeText(getApplicationContext(),"Clan Created",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),MainMenuActivity.class));
                }
            }
        });

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(clanName.toString().trim())){
                    Toast.makeText(getApplicationContext(),"Please type in a clan.",Toast.LENGTH_SHORT).show();
                }else {
                    String joinClan = clanName.getText().toString().trim();
                    startActivity(new Intent(getApplicationContext(),MainMenuActivity.class));
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(clanName.toString().trim())){
                    Toast.makeText(getApplicationContext(),"Please type in a clan.",Toast.LENGTH_SHORT);
                }else {
                    String deleteClan = clanName.getText().toString().trim();
                    startActivity(new Intent(getApplicationContext(),MainMenuActivity.class));
                }
            }
        });
    }
}
