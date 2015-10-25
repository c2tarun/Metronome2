package com.mad.metronome;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {
    EditText etFirstName, etLastName, etUserEmail, etPass, etPassConfirm;
    Button signUpBtn, cancelBtn;
    ParseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etUserEmail = (EditText) findViewById(R.id.etEmailAdrSignUp);
        etPass = (EditText) findViewById(R.id.etPasswordSignUp);
        etPassConfirm = (EditText) findViewById(R.id.etconfrmPswrdSignUp);
        signUpBtn = (Button) findViewById(R.id.btnsignUpPage);
        cancelBtn = (Button) findViewById(R.id.btnCancelSignUp);
        cancelBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this,
                        LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        signUpBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (etFirstName.getText().toString().trim().isEmpty()
                        || etLastName.getText().toString().trim().isEmpty()
                        || etUserEmail.getText().toString().trim().isEmpty()
                        || etPass.getText().toString().trim().isEmpty()
                        || etPassConfirm.getText().toString().trim().isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Empty Field/s",
                            Toast.LENGTH_SHORT).show();
                    return;
                } else if (!etPass.getText().toString().equals(etPassConfirm.getText().toString())){
                    Toast.makeText(SignUpActivity.this, "Passwords Mismatch",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                user = new ParseUser();
                user.setUsername(etUserEmail.getText()
                        .toString());
                user.setPassword(etPass.getText().toString());
                user.put("UserFullName", etFirstName.getText()
                        .toString() + " " + etLastName.getText().toString());
                user.put("email", etUserEmail.getText().toString());
                user.put("lastName", etLastName.getText().toString());

                user.signUpInBackground(new SignUpCallback() {

                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            //					if (!etUserEmail.getText().toString().equals("")){
                            Toast.makeText(SignUpActivity.this,
                                    "Successful SignUp", Toast.LENGTH_SHORT)
                                    .show();
                            Intent intent = new Intent(SignUpActivity.this, GroupDetailActivity.class);
                            startActivity(intent);
                            finish();
                            //					}
                        } else {
                            Toast.makeText(SignUpActivity.this, "Sign Up Error",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });

    }
}
