package com.example.clanmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Spinner;

public class AwardsActivity extends AppCompatActivity {

    private Button back;
    private Button addAward;
    private ImageButton addImageAward;
    private ScrollView awardList;
    private Spinner memberList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awards);

        back = (Button)findViewById(R.id.backBtn);
        addAward = (Button)findViewById(R.id.button2);
        addImageAward = (ImageButton)findViewById(R.id.imageButton);
        awardList = (ScrollView)findViewById(R.id.scrollView1);
        memberList = (Spinner)findViewById(R.id.spinner);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        addAward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        addImageAward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(awardList.getVisibility()==View.VISIBLE&&memberList.getVisibility()==View.VISIBLE){
                    awardList.setVisibility(View.INVISIBLE);
                    memberList.setVisibility(View.INVISIBLE);

                }else{
                    awardList.setVisibility(View.VISIBLE);
                    memberList.setVisibility(View.VISIBLE);
                }
            }
        });

        awardList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        ArrayAdapter<String> MyAdapter = new ArrayAdapter<String>(AwardsActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));

        MyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        memberList.setAdapter(MyAdapter);
    }
}
