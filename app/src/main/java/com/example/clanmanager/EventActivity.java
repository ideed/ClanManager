package com.example.clanmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

public class EventActivity extends AppCompatActivity {

    private Button back;
    private Button editEvents;
    private CalendarView calender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        back = (Button)findViewById(R.id.button5);
        editEvents = (Button)findViewById(R.id.button6);
        calender = (CalendarView)findViewById(R.id.calendarView);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        editEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
