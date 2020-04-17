package com.example.clanmanager;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EventActivity extends AppCompatActivity {

    private Button back;
    private Button attendence;
    private CalendarView calender;
    private Button event;
    private String date;
    private Boolean eventToday;
    private DatabaseReference clanInfo;
    private  String clanName;
    private  Date dateSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        back = (Button)findViewById(R.id.button5);
        attendence = (Button)findViewById(R.id.attendenceBtn);
        calender = (CalendarView)findViewById(R.id.calendarView);
        event = (Button)findViewById(R.id.eventBtn);
        clanName = getIntent().getStringExtra("clanName");
        clanInfo = FirebaseDatabase.getInstance().getReference("clans").child(clanName);
        eventToday = false;

        calender.setFirstDayOfWeek(Calendar.MONDAY);

        calender.setMinDate(System.currentTimeMillis() - 1000);
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.YYYY");
        final Date currentDate = new Date();
        date="Do you wish to create an event for "+df.format(currentDate)+"?";

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        attendence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(eventToday=true){

                }else{
                    Toast.makeText(EventActivity.this,"There are no events today!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                SimpleDateFormat df = new SimpleDateFormat("dd.MM.YYYY");
                String dateIn = dayOfMonth+"."+month+"."+year;
                date="Do you wish to create an event for "+dayOfMonth+"."+month+"."+year+"?";
                try {
                    dateSelected = df.parse(dateIn);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder adb = new AlertDialog.Builder(EventActivity.this);
                adb.setMessage(date);
                adb.setCancelable(false);
                adb.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        AlertDialog.Builder build = new AlertDialog.Builder(EventActivity.this);
                        LayoutInflater li = LayoutInflater.from(EventActivity.this);
                        View promptsView = li.inflate(R.layout.prompts, null);
                        build.setCancelable(false);
                        build.setView(promptsView);
                        final TextView textDisplay = (TextView)promptsView.findViewById(R.id.textView1);
                        final EditText userInput = (EditText)promptsView.findViewById(R.id.editTextDialogUserInput);
                        textDisplay.setText("Enter Your Event Name And Time:");
                        build.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String postId = clanInfo.push().getKey();
                                clanInfo.child("Events").child(postId).setValue(userInput.getText().toString().trim());
                            }
                        });
                        build.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        AlertDialog adTwo = build.create();
                        adTwo.show();
                    }
                });
                adb.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog ad = adb.create();
                ad.show();
            }
        });
    }
}
