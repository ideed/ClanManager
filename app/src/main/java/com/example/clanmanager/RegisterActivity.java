package com.example.clanmanager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private EditText userEmail;
    private EditText userPassword;
    private EditText userRepeatPassword;
    private EditText userName;
    private Button signUp;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private DatabaseReference userInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userEmail = (EditText)findViewById(R.id.emailText);
        userPassword = (EditText)findViewById(R.id.passwordText);
        userRepeatPassword = (EditText)findViewById(R.id.RPasswordText);
        signUp = (Button)findViewById(R.id.btnSignUp);
        userName = (EditText)findViewById(R.id.userNameText);
        mAuth = FirebaseAuth.getInstance();
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        userInfo = FirebaseDatabase.getInstance().getReference("users");
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String userId = userName.getText().toString().trim();
                final String email = userEmail.getText().toString().trim();
                final String password = userPassword.getText().toString().trim();
                String repeatPassword = userRepeatPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)||TextUtils.isEmpty(password)||TextUtils.isEmpty(repeatPassword)||TextUtils.isEmpty(userId)){
                    Toast.makeText(RegisterActivity.this,"Please fill in all fields.",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(passwordCheck(password,repeatPassword)){
                    progressBar.setVisibility(View.VISIBLE);
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            if(task.isSuccessful()){
                                User user = new User(userId, email, password);
                                userInfo.child(userId).setValue(user);
                                Toast.makeText(RegisterActivity.this,"User Created. Please check email to verify.",Toast.LENGTH_SHORT).show();
                                sendEmail();
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            }else{
                                Toast.makeText(RegisterActivity.this,"Error ! "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else{
                    userPassword.setError("Please ensure that the passwords match and it is greater than 8 characters and contains a special character.");
                }
            }
        });
    }

    public Boolean passwordCheck(String password,String RPassword){
        if((password.equals(RPassword))&&(password.length()>=8)&&(charCheck(password))){
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean charCheck(String password){
        String[] special ={"@","%","+","/","'","!","#","$","^","?",":",".","(",")","{","}","[","]","~","-","_",","};
        boolean charContained=false;
        for(int j=0;j<=special.length;j++){
            if(password.contains(special[j])){
                charContained=true;
                j=special.length;
            }
            else{
                charContained=false;
            }
        }
        return charContained;
    }

    public void sendEmail(){
       String email = userEmail.toString().trim();
       String password = userPassword.toString().trim();
       mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
            }
       });
       final FirebaseUser user = mAuth.getCurrentUser();
       user.sendEmailVerification().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
            }
       });
    }
}

