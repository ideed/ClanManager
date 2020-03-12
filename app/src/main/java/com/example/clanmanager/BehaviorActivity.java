package com.example.clanmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentProviderClient;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class BehaviorActivity extends AppCompatActivity {
   // EditText search_box;
    RecyclerView memberNameList;
    Button submitBtn;
    EditText editComment;
    ToggleButton thumbsUp;
    ToggleButton thumbs_middle;
    ToggleButton thumbsDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior);

        //search_box = (EditText) findViewById(R.id.search);
        memberNameList = (RecyclerView) findViewById(R.id.recyclerView);
        submitBtn = (Button) findViewById(R.id.submitBtn);
        editComment = (EditText) findViewById(R.id.editComment);
        thumbsUp = (ToggleButton) findViewById(R.id.thumbsUp);
        thumbs_middle = (ToggleButton) findViewById(R.id.thumbsMiddle);
        thumbsDown = (ToggleButton) findViewById(R.id.thumbsDown);

        submitBtn.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View v) {
                String comment = editComment.getText().toString().trim();
                if(TextUtils.isEmpty(comment)){
                    Toast.makeText(BehaviorActivity.this,"Please fill in the comment box.",Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    Toast.makeText(BehaviorActivity.this,"submitted successfully.", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }

    public void onCustomToggleThumbsUpClick(View view) {
        Toast.makeText(this, "CustomToggleThumbsUp", Toast.LENGTH_SHORT).show();
    }
}
