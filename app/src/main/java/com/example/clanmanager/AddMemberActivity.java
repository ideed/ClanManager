package com.example.clanmanager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddMemberActivity extends AppCompatActivity {
    private DatabaseReference clanInfo;
    private DatabaseReference userInfo;
    private FirebaseAuth mAuth;
    private String userName;
    private Button back;
    private Button addMember;
    private EditText username;
    private EditText date;
    private  EditText country;
    private String clanName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);

        clanName = getIntent().getStringExtra("clanName");
        userInfo = FirebaseDatabase.getInstance().getReference("users");
        clanInfo = FirebaseDatabase.getInstance().getReference("clans");
        mAuth = FirebaseAuth.getInstance();
        back = (Button)findViewById(R.id.backBtn);
        username = (EditText)findViewById(R.id.usernameText);
        date = (EditText)findViewById(R.id.dateText);
        country = (EditText)findViewById(R.id.countryText);
        addMember = (Button)findViewById(R.id.addBtn);
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

        addMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(username.getText().toString().trim())||TextUtils.isEmpty(date.getText().toString().trim())||TextUtils.isEmpty(country.getText().toString().trim())){
                    Toast.makeText(AddMemberActivity.this, "Please ensure that all fields are entered.", Toast.LENGTH_SHORT).show();
                }else{
                    clanInfo.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                                Clan clan = snapshot.getValue(Clan.class);
                                if(clan.owner.equals(userName)){
                                    SimpleDateFormat df = new SimpleDateFormat("dd.MM.YYYY");
                                    try {
                                        Date sendDate = df.parse(date.getText().toString().trim());
                                        Member member = new Member(username.getText().toString().trim(),sendDate,country.getText().toString().trim(),0.00,0);
                                        clanInfo.child(clan.clanName).child("Members").child(username.getText().toString().trim()).setValue(member);
                                        Toast.makeText(AddMemberActivity.this,"Member added!",Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(AddMemberActivity.this, MemberActivity.class).putExtra("clanName",clanName));
                                    } catch (ParseException e) {
                                        Toast.makeText(AddMemberActivity.this, "Error: "+e, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddMemberActivity.this, MemberActivity.class));
            }
        });
    }
}
