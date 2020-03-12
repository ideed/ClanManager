package com.example.clanmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DetachmentsActivity extends AppCompatActivity {

    Button detachment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detachments);

        detachment1 = findViewById(R.id.detachments1Btn);

        detachment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intend = new Intent(DetachmentsActivity.this,RankingActivity.class);
                startActivity(intend);
            }
        });
    }
}
