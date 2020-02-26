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

public class RegisterActivity extends AppCompatActivity {
    private EditText userEmail;
    private EditText userPassword;
    private EditText userRepeatPassword;
    private Button signUp;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userEmail = (EditText)findViewById(R.id.emailText);
        userPassword = (EditText)findViewById(R.id.passwordText);
        userRepeatPassword = (EditText)findViewById(R.id.RPasswordText);
        signUp = (Button)findViewById(R.id.btnSignUp);
        mAuth = FirebaseAuth.getInstance();
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = userEmail.getText().toString().trim();
                String password = userPassword.getText().toString().trim();
                String repeatPassword = userRepeatPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)||TextUtils.isEmpty(password)||TextUtils.isEmpty(repeatPassword)){
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
                                Toast.makeText(RegisterActivity.this,"User Created.",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),MainMenuActivity.class));
                            }else{
                                Toast.makeText(RegisterActivity.this,"Error ! "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                    });
                } else{
                    Toast.makeText(RegisterActivity.this,"Please make sure passwords match and includes 1 special character.",Toast.LENGTH_SHORT).show();
                    return;
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
        String special[]={"@","%","+","/","'","!","#","$","^","?",":",".","(",")","{","}","[","]","~","-","_",","};
        Boolean charContained=false;
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
}
