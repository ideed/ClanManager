package com.example.clanmanager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    private EditText userName;
    private EditText userEmail;
    private EditText password;
    private EditText repeatPassword;
    private Button signUp;
    private TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userName = (EditText)findViewById(R.id.userNameText);
        userEmail = (EditText)findViewById(R.id.emailText);
        password = (EditText)findViewById(R.id.passwordText);
        repeatPassword = (EditText)findViewById(R.id.RPasswordText);
        signUp = (Button)findViewById(R.id.btnSignUp);
        result = (TextView)findViewById(R.id.textView);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passwordCheck(password.getText().toString(),repeatPassword.getText().toString());
            }
        });
    }

    public void passwordCheck(String password,String RPassword){
        if((password.equals(RPassword))&&(password.length()>=8)){
            result.setText("Success");
        }
        else{
            result.setText("Fail");
        }
    }
}
