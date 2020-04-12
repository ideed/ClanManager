package com.example.clanmanager;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.ScrollView;
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

public class RatingActivity extends AppCompatActivity {

    private ScrollView scrollview;
    private EditText memberText;
    private ImageButton searchbtn;
    private Button addbtn;
    private DatabaseReference clanInfo;
    private DatabaseReference memberInfo;
    private DatabaseReference skillInfo;
    private String clanName;
    private String memberName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        clanName = getIntent().getStringExtra("clanName");
        scrollview = (ScrollView)findViewById(R.id.scrollView3);
        memberText = (EditText)findViewById(R.id.memberText);
        searchbtn = (ImageButton)findViewById(R.id.SearchBtn);
        addbtn = (Button)findViewById(R.id.addbutton);
        clanInfo = FirebaseDatabase.getInstance().getReference("clans");
        memberInfo = clanInfo.child(clanName).child("Members");


        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memberInfo.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Boolean checker = false;
                        for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                            final Member member = dataSnapshot1.getValue(Member.class);
                            if(member.memberName.equals(memberText.getText().toString().trim())){
                                checker = true;
                                TextView myText = new TextView(RatingActivity.this);
                                memberName = member.memberName.trim();
                                skillInfo = memberInfo.child(member.memberName).child("skills");

                                myText.setText(member.memberName+":");
                                myText.setTextSize(30);
                                myText.setTypeface(Typeface.DEFAULT_BOLD);

                                LinearLayout ll = (LinearLayout)findViewById(R.id.layout);
                                ll.removeAllViews();
                                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                                ll.addView(myText, lp);
                                skillInfo.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshotTwo) {
                                      for(DataSnapshot dataSnapshot3: dataSnapshotTwo.getChildren()){
                                          LinearLayout ll = (LinearLayout)findViewById(R.id.layout);
                                          LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                          final Skill skill = dataSnapshot3.getValue(Skill.class);
                                          TextView mySkillText = new TextView(RatingActivity.this);
                                          RatingBar myRatingBar = new RatingBar(RatingActivity.this);

                                          mySkillText.setText(skill.skillName);
                                          mySkillText.setTextSize(20);
                                          myRatingBar.setNumStars(5);
                                          myRatingBar.setRating(skill.skillRate);
                                          myRatingBar.setStepSize(1);
                                          myRatingBar.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT));
                                          myRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                                              @Override
                                              public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                                                  skillInfo.child(skill.skillName).child("skillRate").setValue(rating);
                                              }
                                          });

                                          ll.addView(mySkillText,lp);
                                          ll.addView(myRatingBar);
                                      }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }
                        }
                        if(!checker){
                            Toast.makeText(RatingActivity.this,"Member not found! Try again!",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater li = LayoutInflater.from(RatingActivity.this);
                View promptsView = li.inflate(R.layout.prompts, null);

                AlertDialog.Builder builder = new AlertDialog.Builder(RatingActivity.this);
                builder.setCancelable(false);
                builder.setView(promptsView);
                final EditText userInput = (EditText)promptsView.findViewById(R.id.editTextDialogUserInput);
                builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        memberInfo.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for(DataSnapshot dataSnapshot2: dataSnapshot.getChildren()){
                                    Member member = dataSnapshot2.getValue(Member.class);
                                    Skill skill = new Skill(userInput.getText().toString().trim(),0);
                                    memberInfo.child(member.memberName).child("skills").child(userInput.getText().toString().trim()).setValue(skill);
                                    finish();
                                    startActivity(getIntent());
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        Toast.makeText(RatingActivity.this, "Skill Created!",Toast.LENGTH_SHORT).show();
                    }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                    });
                AlertDialog ad = builder.create();
                ad.show();
            }
        });
    }
}
