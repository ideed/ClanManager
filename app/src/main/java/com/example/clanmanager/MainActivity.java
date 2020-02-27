package com.example.clanmanager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private EditText userEmail;
    private EditText userPassword;
    private Button Login;
    private Button Register;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private TextView passwordText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userEmail = (EditText)findViewById(R.id.userEmailText);
        userPassword = (EditText)findViewById(R.id.passwordText);
        Login = (Button)findViewById(R.id.btnLogin);
        Register = (Button)findViewById(R.id.btnRegister);
        mAuth = FirebaseAuth.getInstance();
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        passwordText = (TextView) findViewById(R.id.passwordResetText);
        Login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String email = userEmail.getText().toString().trim();
                String password = userPassword.getText().toString().trim();
                if(TextUtils.isEmpty(email)||TextUtils.isEmpty(password)){
                    Toast.makeText(MainActivity.this,"Please fill in all fields.",Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        final FirebaseUser user = mAuth.getCurrentUser();
                        if(task.isSuccessful()){
                            if(user.isEmailVerified()) {
                                Toast.makeText(MainActivity.this, "Login Successful.", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainMenuActivity.class));
                            }else{
                                Toast.makeText(MainActivity.this,"Please verify your email to log in.", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(MainActivity.this,"Error ! "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });
            }
        });

        Register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });
        passwordText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(getApplicationContext(),passwordResetActivity.class));
            }
        });
    }


}