package com.example.clanmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RatingActivity extends AppCompatActivity {
    //RatingBar ratingBar;
    //Button updateBtn;
    Button  addSkillBtn;
    SearchView searchView;
    TextView skillTv;
    private RecyclerView recycleView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Member> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
       //ratingBar = findViewById(R.id.ratingBar);
       // updateBtn = findViewById(R.id.updateBtn);
        addSkillBtn= findViewById(R.id.addSkillBtn);
        searchView =findViewById(R.id.searchView);
        skillTv = findViewById(R.id.skillTv);



        addSkillBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addRatingIntent = new Intent(RatingActivity.this,AddRatingActivity.class);
                startActivity(addRatingIntent);
            }
        });

        /*updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = String.valueOf(ratingBar.getRating());
                Toast.makeText(getApplicationContext(),s+"  Star",Toast.LENGTH_SHORT).show();

            }
        }); */

        recycleView = findViewById(R.id.recyclerView);// connect id
        recycleView.setHasFixedSize(true);//reinforce for recyclerview functionality
        layoutManager = new LinearLayoutManager(this);
        recycleView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();// arrayList to contain object (toward adapter)
        database = FirebaseDatabase.getInstance();// connect firebase database
        databaseReference = database.getReference("Member"); //connect DB table, member console and print out data)
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //place that the data from firebase database is put
                arrayList.clear(); // 기존 배열리스타가 존재하지 않게 초기화 (방지차원) -add전에 초기화 해주는 거다
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {//extract data list using iterator

                    Member member = snapshot.getValue(Member.class);// put data to Member object
                    arrayList.add(member); // being ready to send it to recyclerview
                }
                adapter.notifyDataSetChanged(); //save and refresh the lists
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            //in case of error occurs when bring DB
                Log.e("RatingActivity", String.valueOf(databaseError.toException())); //print error statement
            }
        });

        adapter =  new CustomAdapter(arrayList,this);
        recycleView.setAdapter(adapter);// connect Adapter to recyclerview

    }
}
