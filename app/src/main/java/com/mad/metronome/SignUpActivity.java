package com.mad.metronome;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseUser;

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
        

    }
}
