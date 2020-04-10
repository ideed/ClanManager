package com.example.clanmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MemberActivity extends AppCompatActivity {

    private Button back;
    private Button addMember;
    private SearchView search;
    private ScrollView scroll;
    private DatabaseReference clanInfo;
    private  DatabaseReference userInfo;
    private DatabaseReference memberInfo;
    private FirebaseAuth mAuth;
    private String userName;
    private String clanName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        back = (Button)findViewById(R.id.backBtn);
        addMember = (Button)findViewById(R.id.joinBtn);
        search = (SearchView)findViewById(R.id.searchView1);
        scroll = (ScrollView)findViewById(R.id.scrollView1);
        userInfo = FirebaseDatabase.getInstance().getReference("users");
        clanInfo = FirebaseDatabase.getInstance().getReference("clans");
        memberInfo = clanInfo.child("Test Clan").child("Members");
        mAuth = FirebaseAuth.getInstance();


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

        memberInfo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    final Member member = dataSnapshot1.getValue(Member.class);
                    Button myButton = new Button(MemberActivity.this);
                    myButton.setText(member.memberName);

                    LinearLayout ll = (LinearLayout)findViewById(R.id.scrollLayout);
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    ll.addView(myButton, lp);
                    myButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MembersPageActivity mpa = new MembersPageActivity();
                            Intent intent = new Intent(MemberActivity.this, MembersPageActivity.class).putExtra("memberName",member.memberName).putExtra("overallSkill",member.overallSkill);
                            intent.putExtra("memberDate",member.memberDate).putExtra("memberCOO",member.memberCOO).putExtra("attendencePercentage",member.attendencePercentage);
                            startActivity(intent);
                        }
                    });
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MemberActivity.this, MainMenuActivity.class));
            }
        });

        addMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MemberActivity.this, AddMemberActivity.class));
            }
        });



    }
}
