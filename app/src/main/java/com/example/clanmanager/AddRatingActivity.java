package com.example.clanmanager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddRatingActivity extends AppCompatActivity {
    RatingBar ratingBar;
    Button updateBtn;
    EditText typedSkillName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rating);

        ratingBar = findViewById(R.id.ratingBar);
        updateBtn = findViewById(R.id.updateBtn);
        typedSkillName = (EditText)findViewById(R.id.addSkillNameBox);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = String.valueOf(ratingBar.getRating());
                String skillName = typedSkillName.getText().toString().trim();


                if(TextUtils.isEmpty(skillName)||TextUtils.isEmpty(s)){
                    Toast.makeText(AddRatingActivity.this,"Please type skill name and rate.",Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    Toast.makeText(getApplicationContext(), skillName+"  "+s+"  Star",Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext()," Updated successfully",Toast.LENGTH_SHORT).show();
                }
               // Intent backRatingIntent = new Intent(AddRatingActivity.this,RatingActivity.class);
                //startActivity(backRatingIntent);


            }
        });
    }
}
