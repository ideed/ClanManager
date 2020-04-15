package com.example.clanmanager;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BehaviorActivity extends AppCompatActivity {

    Button submitBtn;
    EditText editComment;
    ToggleButton thumbsUp;
    ToggleButton thumbs_middle;
    ToggleButton thumbsDown;
    ImageButton searchBtn;
    EditText memberText;
    TextView memberView;
    TextView thumbView;
    DatabaseReference clanInfo;
    DatabaseReference memberInfo;
    private String thumb;
    private String clanName;
    private String memberId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior);

        clanName = getIntent().getStringExtra("clanName");
        thumbView = (TextView) findViewById(R.id.thumbView);
        submitBtn = (Button) findViewById(R.id.submitBtn);
        editComment = (EditText) findViewById(R.id.editComment);
        thumbsUp = (ToggleButton) findViewById(R.id.thumbsUp);
        thumbs_middle = (ToggleButton) findViewById(R.id.thumbsMiddle);
        thumbsDown = (ToggleButton) findViewById(R.id.thumbsDown);
        searchBtn = (ImageButton) findViewById(R.id.searchBtnTwo);
        memberText = (EditText) findViewById(R.id.memberText);
        memberView = (TextView) findViewById(R.id.textView14);
        clanInfo = FirebaseDatabase.getInstance().getReference("clans");
        memberInfo = clanInfo.child(clanName).child("Members");

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memberInfo.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Boolean checker = true;
                        for(DataSnapshot ds: dataSnapshot.getChildren()){
                            Member member = ds.getValue(Member.class);
                            if(memberText.getText().toString().trim().equals(member.memberName)){
                                memberView.setText("MEMBER SELECTED: "+member.memberName);
                                memberId = member.memberName;
                                checker = false;
                            }
                        }
                        if(checker){
                            Toast.makeText(BehaviorActivity.this,"Error Member not found please try again.",Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        thumbsUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thumbView.setText("RATING SELECTED: POSITIVE");
                thumb = "up";
            }
        });

        thumbs_middle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thumbView.setText("RATING SELECTED: NEUTRAL");
                thumb = "middle";
            }
        });

        thumbsDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thumbView.setText("RATING SELECTED: NEGATIVE");
                thumb = "down";
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(memberId)) {
                    Toast.makeText(BehaviorActivity.this, "Error: Member not selected.", Toast.LENGTH_SHORT).show();
                } else{
                    String postId = memberInfo.push().getKey();
                    SimpleDateFormat df = new SimpleDateFormat("dd.MM.YYYY");
                    Date currentDate = new Date();
                    df.format(currentDate);
                    Behavior b = new Behavior(postId,thumb,currentDate,editComment.getText().toString().trim());
                    memberInfo.child(memberId).child("Behaviors").child(postId).setValue(b);
                    Toast.makeText(BehaviorActivity.this, "Behavior reported!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}
