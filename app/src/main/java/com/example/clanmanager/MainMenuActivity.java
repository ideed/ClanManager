package com.example.clanmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainMenuActivity extends AppCompatActivity{

    private ImageView shareImage;
    DatabaseReference userInfo;
    FirebaseAuth mAuth;
    String userName = "";
    private DatabaseReference clanInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_r);
        final TextView clanView = (TextView) findViewById(R.id.clanView);
        Button memberBn = (Button) findViewById(R.id.membersBtn);
        Button eventBn = (Button) findViewById(R.id.eventBtn);
        Button awardBn = (Button) findViewById(R.id.awardsBtn);
        Button behaviorBn= (Button) findViewById(R.id.behaviorBtn);
        Button skillsBn = (Button) findViewById(R.id.skillsBtn);
        Button detachmentBn = (Button) findViewById(R.id.detachmentBtn);
        userInfo = FirebaseDatabase.getInstance().getReference("users");
        clanInfo = FirebaseDatabase.getInstance().getReference("clans");
        mAuth = FirebaseAuth.getInstance();
        shareImage = (ImageView) findViewById(R.id.share);

        userInfo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    if(user.email.equals(mAuth.getCurrentUser().getEmail())){
                        userName = user.userName.trim();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        clanInfo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Clan clan = snapshot.getValue(Clan.class);
                    if(clan.owner.equals(userName)){
                        clanView.setText(clan.clanName);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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
                startActivity(new Intent(MainMenuActivity.this,MemberActivity.class));
            }
        });
        //Works:
        eventBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenuActivity.this,EventActivity.class));
            }
        });

        awardBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenuActivity.this,AwardsActivity.class));
            }
        });
        //Works:
        behaviorBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenuActivity.this,BehaviorActivity.class));
            }
        });

        skillsBn.setOnClickListener(new View.OnClickListener() {//ranting tester
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenuActivity.this,RatingActivity.class));
            }
        });

        //Works:
        detachmentBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenuActivity.this,DetachmentsActivity.class));
            }
        });
    }

}
