package com.example.clanmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

public class MembersPageActivity extends AppCompatActivity {

    private Button back;
    private Button deleteMember;
    private RatingBar rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members_page);

        back = (Button) findViewById(R.id.backBtn);
        deleteMember = (Button) findViewById(R.id.button2);
        rating = (RatingBar) findViewById(R.id.ratingBar1);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        deleteMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
