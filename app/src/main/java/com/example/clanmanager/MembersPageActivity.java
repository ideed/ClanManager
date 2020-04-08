package com.example.clanmanager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

public class MembersPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members_page);

        Button back = (Button) findViewById(R.id.backBtn);
        Button deleteMember = (Button) findViewById(R.id.button2);
        RatingBar rating = (RatingBar) findViewById(R.id.ratingBar1);


    }
}
