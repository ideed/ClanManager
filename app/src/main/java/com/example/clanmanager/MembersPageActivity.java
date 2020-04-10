package com.example.clanmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MembersPageActivity extends AppCompatActivity {
    private TextView name;
    private TextView date;
    private TextView country;
    private TextView attendence;
    public String memberName;
    public int overallSkill ;
    public Date memberDate;
    public String memberCOO;
    public double attendencePercentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members_page);

        Button back = (Button) findViewById(R.id.backBtn);
        Button deleteMember = (Button) findViewById(R.id.button2);
        RatingBar rating = (RatingBar) findViewById(R.id.ratingBar1);
        TextView name = (TextView) findViewById(R.id.nameView);
        TextView date = (TextView) findViewById(R.id.dateView);
        TextView country = (TextView) findViewById(R.id.countryView);
        TextView attendence = (TextView) findViewById(R.id.attendenceView);

        memberName = getIntent().getStringExtra("memberName");
        memberCOO = getIntent().getStringExtra("memberCOO");
        overallSkill = getIntent().getIntExtra("overallSkill",overallSkill);
        attendencePercentage = getIntent().getDoubleExtra("attendencePercentage", attendencePercentage);
        memberDate = (Date)getIntent().getSerializableExtra("memberDate");
        name.setText(memberName);
        country.setText(memberCOO);
        attendence.setText(""+attendencePercentage);
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.YYYY");
        String formatDate = df.format(memberDate);
        date.setText(formatDate);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MembersPageActivity.this, MemberActivity.class));
            }
        });
    }
}
