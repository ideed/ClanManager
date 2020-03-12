package com.example.clanmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.SearchView;

public class MemberActivity extends AppCompatActivity {

    private Button back;
    private Button addMember;
    private Button member1;
    private Button member2;
    private Button member3;
    private Button member4;
    private Button member5;
    private Button member6;
    private Button member7;
    private Button member8;
    private Button member9;
    private Button member10;
    private Button member11;
    private Button member12;
    private Button member13;
    private Button member14;
    private Button member15;
    private SearchView search;
    private ScrollView scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        back = (Button)findViewById(R.id.backBtn);
        addMember = (Button)findViewById(R.id.joinBtn);
        member1 = (Button)findViewById(R.id.button1);
        member2 = (Button)findViewById(R.id.button2);
        member3 = (Button)findViewById(R.id.button3);
        member4 = (Button)findViewById(R.id.button4);
        member5 = (Button)findViewById(R.id.button5);
        member6 = (Button)findViewById(R.id.button6);
        member7 = (Button)findViewById(R.id.button7);
        member8 = (Button)findViewById(R.id.button8);
        member9 = (Button)findViewById(R.id.button9);
        member10 = (Button)findViewById(R.id.button10);
        member11 = (Button)findViewById(R.id.button11);
        member12 = (Button)findViewById(R.id.button12);
        member13 = (Button)findViewById(R.id.button13);
        member14 = (Button)findViewById(R.id.button14);
        member15 = (Button)findViewById(R.id.button15);
        search = (SearchView)findViewById(R.id.searchView1);
        scroll = (ScrollView)findViewById(R.id.scrollView1);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        addMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        member1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MembersPageActivity.class));
            }
        });

        member2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MembersPageActivity.class));
            }
        });

        member3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MembersPageActivity.class));
            }
        });

        member4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MembersPageActivity.class));
            }
        });

        member5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MembersPageActivity.class));
            }
        });

        member6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MembersPageActivity.class));
            }
        });

        member7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MembersPageActivity.class));
            }
        });

        member8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MembersPageActivity.class));
            }
        });

        member9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MembersPageActivity.class));
            }
        });

        member10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MembersPageActivity.class));
            }
        });

        member11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MembersPageActivity.class));
            }
        });

        member12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MembersPageActivity.class));
            }
        });

        member13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MembersPageActivity.class));
            }
        });

        member14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MembersPageActivity.class));
            }
        });

        member15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MembersPageActivity.class));
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        scroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
