package com.example.clanmanager;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainMenuActivity extends AppCompatActivity {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    final FirebaseUser user = mAuth.getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        if(!user.isEmailVerified()){
            user.sendEmailVerification().addOnCompleteListener(this, new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(MainMenuActivity.this,"Please check "+user.getEmail()+" to verify user.",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainMenuActivity.this,"Failed to send verification email.",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{

        }
    }
}
