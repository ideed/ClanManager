package com.example.clanmanager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ClanActivity extends AppCompatActivity {
    private EditText clanName;
    private Button join;
    private Button create;
    private Button delete;
    private FirebaseAuth mAuth;
    private DatabaseReference userInfo;
    private TextView welcome;
    private String userName;
    private DatabaseReference clanInfo;
    private String clanNam;

    public String getUserName() {
        return userName;
    }

    public String getClanNam() {
        return clanNam;
    }

    public ClanActivity(){
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clan);
        userName = "";
        welcome = (TextView)findViewById(R.id.welcomeText);
        clanName = (EditText)findViewById(R.id.editText1);
        create = (Button)findViewById(R.id.createBtn);
        join = (Button)findViewById(R.id.joinBtn);
        delete = (Button)findViewById(R.id.button1);
        mAuth = FirebaseAuth.getInstance();
        userInfo = FirebaseDatabase.getInstance().getReference("users");
        clanInfo = FirebaseDatabase.getInstance().getReference("clans");



        userInfo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    if(user.email.equals(mAuth.getCurrentUser().getEmail())){
                        userName = user.userName.trim();
                        welcome.setText("Welcome "+userName+"!");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String owner = userName;
                final String clanId = clanName.getText().toString().trim();

                if(TextUtils.isEmpty(clanId)){
                    Toast.makeText(ClanActivity.this,"Please type in a clan name.",Toast.LENGTH_SHORT).show();
                }else{
                    clanInfo.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            boolean clanCheck = true;
                            for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                                Clan clan = snapshot.getValue(Clan.class);
                                if(clan.clanName.equals(clanId)||clan.owner.equals(owner)){
                                    Toast.makeText(ClanActivity.this,"Clan name already exists or you already have a clan.",Toast.LENGTH_SHORT).show();
                                    clanCheck=false;
                                }
                            }
                            if(clanCheck){
                                Clan clan = new Clan(clanId, owner);
                                clanInfo.child(clanId).setValue(clan);
                                clanNam = clanId;
                                Toast.makeText(ClanActivity.this, "Clan Created!",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),MainMenuActivity.class));
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String clanId = clanName.getText().toString().trim();
                if(TextUtils.isEmpty(clanName.getText().toString().trim())){
                    Toast.makeText(ClanActivity.this,"Please type in a clan name.",Toast.LENGTH_SHORT).show();
                }else{
                    clanInfo.addListenerForSingleValueEvent(new ValueEventListener(){
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            boolean checker = false;
                            for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                                Clan clan = snapshot.getValue(Clan.class);
                                if(clan.clanName.equals(clanId)){
                                    clanNam = clan.clanName;
                                    Toast.makeText(ClanActivity.this,"Clan joined!",Toast.LENGTH_SHORT).show();
                                    checker = true;
                                    startActivity(new Intent(getApplicationContext(),MainMenuActivity.class));
                                }
                            }
                            if(!checker){
                                Toast.makeText(ClanActivity.this,"Clan Does not Exist",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String clanId = clanName.getText().toString().trim();
                if(TextUtils.isEmpty(clanName.getText().toString().trim())){
                    Toast.makeText(ClanActivity.this,"Please type in a clan name.",Toast.LENGTH_SHORT).show();
                }else{
                    clanInfo.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            boolean checker = false;
                            for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                                Clan clan = snapshot.getValue(Clan.class);
                                if(clan.clanName.equals(clanId)){
                                    snapshot.getRef().removeValue();
                                    checker=true;
                                    Toast.makeText(ClanActivity.this,"Clan Deleted.",Toast.LENGTH_SHORT).show();
                                }
                            }
                            if(!checker){
                                Toast.makeText(ClanActivity.this,"Clan does not exist.",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });


    }
}
