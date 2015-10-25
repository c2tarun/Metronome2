package com.mad.metronome;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {
    EditText etEmail, etPassword;
    Button loginBtn, createBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = (EditText) findViewById(R.id.etEmailAdr);
        etPassword = (EditText) findViewById(R.id.etPassword);
        loginBtn = (Button) findViewById(R.id.btnlogin);
        createBtn = (Button) findViewById(R.id.btnSignUp);
        if (ParseUser.getCurrentUser() != null) {
            Intent i = new Intent(LoginActivity.this, GroupDetailActivity.class);
            startActivity(i);
            finish();
        }
        loginBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (etEmail.getText().toString().trim().isEmpty()
                        || etPassword.getText().toString().trim().isEmpty()) {
                    Toast.makeText(LoginActivity.this,"Empty Field/s",
                            Toast.LENGTH_SHORT).show();
                } else {
                    ParseUser.logInInBackground(etEmail.getText().toString(),
                            etPassword.getText().toString(),
                            new LogInCallback() {
                                public void done(ParseUser user,
                                                 ParseException e) {
                                    if (user != null) {
                                        Toast.makeText(LoginActivity.this,
                                                "Login Success",
                                                Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(
                                                LoginActivity.this,
                                                GroupDetailActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(LoginActivity.this,
                                                "login error",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
        createBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        LoginActivity.this,
                        SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
