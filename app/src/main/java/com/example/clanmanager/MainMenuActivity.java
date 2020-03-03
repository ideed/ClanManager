package com.example.clanmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class MainMenuActivity extends AppCompatActivity{
    private Button behaviorBn;
    private Button rankingBn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_r);

        //Button memberBn = (Button) findViewById(R.id.membersBtn);
      //  Button eventBn = (Button) findViewById(R.id.eventBtn);
       // Button awardBn = (Button) findViewById(R.id.awardsBtn);
        behaviorBn= (Button) findViewById(R.id.behaviorBtn);
       // Button helpBn = (Button) findViewById(R.id.helpBtn);
        rankingBn = (Button) findViewById(R.id.rankingBtn);

        /*memberBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int1 = new Intent(MainMenuActivity.this,MembersActivity.class);
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
*/
        behaviorBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),BehaviorActivity.class));
            }
        });

      /*  helpBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int5 = new Intent(MainMenuActivity.this,helpActivity.class);
                startActivity(int5);
            }
        });
*/
        rankingBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RankingActivity.class));
            }
        });
    }

}
