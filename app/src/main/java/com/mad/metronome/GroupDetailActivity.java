package com.mad.metronome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GroupDetailActivity extends AppCompatActivity {

    EditText groupId;
    CheckBox leaderCB;
    EditText strengthTV;
    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_detail);

        groupId = (EditText) findViewById(R.id.groupIdTV);
        leaderCB = (CheckBox) findViewById(R.id.leaderCB);
        strengthTV = (EditText) findViewById(R.id.strengthTV);
        nextButton = (Button) findViewById(R.id.nextButton);

        leaderCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    strengthTV.setVisibility(View.VISIBLE);
                } else {
                    strengthTV.setVisibility(View.INVISIBLE);
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(leaderCB.isChecked()) {

                    int groupStrength = 0;
                    try {
                        groupStrength = Integer.parseInt(strengthTV.getText().toString());
                    } catch (NumberFormatException e) {
                        Toast.makeText(GroupDetailActivity.this, "Enter valid strength", Toast.LENGTH_LONG).show();
                    }
                    if(groupStrength <= 0) {
                        Toast.makeText(GroupDetailActivity.this, "Enter valid strength", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
