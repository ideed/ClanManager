package com.example.clanmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenuActivity extends AppCompatActivity{

    private ImageView shareImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_r);

        Button memberBn = (Button) findViewById(R.id.membersBtn);
        Button eventBn = (Button) findViewById(R.id.eventBtn);
        Button awardBn = (Button) findViewById(R.id.awardsBtn);
        Button behaviorBn= (Button) findViewById(R.id.behaviorBtn);
        Button skillsBn = (Button) findViewById(R.id.skillsBtn);
        Button detachmentBn = (Button) findViewById(R.id.detachmentBtn);

        shareImage = (ImageView) findViewById(R.id.share);

        shareImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody="This is a great App. You should try it out! http://www.clanManager.com";
                String shareSubject="Clan Manager";

                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT,shareSubject);

                startActivity(Intent.createChooser(sharingIntent, "Share via"));

            }
        });

        memberBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int1 = new Intent(MainMenuActivity.this,MemberActivity.class);
                startActivity(int1);
            }
        });

        eventBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int2 = new Intent(MainMenuActivity.this,EventActivity.class);
                startActivity(int2);
            }
        });

        awardBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int3 = new Intent(MainMenuActivity.this,AwardsActivity.class);
                startActivity(int3);
            }
        });

        behaviorBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),BehaviorActivity.class));
            }
        });

        skillsBn.setOnClickListener(new View.OnClickListener() {//ranting tester
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RatingActivity.class));
            }
        });

        detachmentBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),DetachmentsActivity.class));
            }
        });
    }

}
