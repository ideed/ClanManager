package com.example.clanmanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MembersPageActivity extends AppCompatActivity {
    private TextView name;
    private TextView date;
    private TextView country;
    private TextView attendence;
    public String memberName;
    public int overallSkill ;
    private String clanName;
    public Date memberDate;
    public String memberCOO;
    public double attendencePercentage;
    private DatabaseReference clanInfo;
    private DatabaseReference memberInfo;
    private DatabaseReference skillInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members_page);

        Button back = (Button) findViewById(R.id.backBtn);
        Button deleteMember = (Button) findViewById(R.id.button19);
        final RatingBar rating = (RatingBar) findViewById(R.id.ratingBar1);
        TextView name = (TextView) findViewById(R.id.nameView);
        TextView date = (TextView) findViewById(R.id.dateView);
        TextView country = (TextView) findViewById(R.id.countryView);
        TextView attendence = (TextView) findViewById(R.id.attendenceView);

        clanName = getIntent().getStringExtra("clanName");
        memberName = getIntent().getStringExtra("memberName");
        memberCOO = getIntent().getStringExtra("memberCOO");
        overallSkill = getIntent().getIntExtra("overallSkill",overallSkill);
        attendencePercentage = getIntent().getDoubleExtra("attendencePercentage", attendencePercentage);
        memberDate = (Date)getIntent().getSerializableExtra("memberDate");
        clanInfo = FirebaseDatabase.getInstance().getReference("clans");
        memberInfo = clanInfo.child(clanName).child("Members").child(memberName);
        skillInfo = memberInfo.child("skills");

        name.setText(memberName);
        country.setText(memberCOO);
        attendence.setText(attendencePercentage+"%");
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.YYYY");
        String formatDate = df.format(memberDate);
        date.setText(formatDate);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        deleteMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MembersPageActivity.this);
                    builder.setMessage("Are you sure you want to delete "+memberName+"?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                memberInfo.removeValue();
                                Toast.makeText(MembersPageActivity.this,memberName+" deleted!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MembersPageActivity.this, MemberActivity.class).putExtra("clanName",clanName));
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                    AlertDialog ad = builder.create();
                    ad.show();
            }
        });

        skillInfo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int rate = 0;
                int noOfSkills = 0;
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Skill skill = snapshot.getValue(Skill.class);
                    if(skill.skillRate!=0){
                        rate = rate+skill.skillRate;
                        noOfSkills++;
                    }
                }
                rating.setRating(Math.round(rate/noOfSkills));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
