package com.mad.metronome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
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
    View strengthLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_detail);

        groupId = (EditText) findViewById(R.id.groupIdTV);
        leaderCB = (CheckBox) findViewById(R.id.leaderCB);
        strengthTV = (EditText) findViewById(R.id.strengthTV);
        nextButton = (Button) findViewById(R.id.nextButton);
        strengthLayout = findViewById(R.id.strengthLayout);

        leaderCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    strengthLayout.setVisibility(View.VISIBLE);
                } else {
                    strengthLayout.setVisibility(View.GONE);
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean allGood = true;

                String groupId = strengthTV.getText().toString();
                if (groupId.isEmpty()) {
                    allGood = false;
                }

                int groupStrength = 0;
                if (leaderCB.isChecked()) {

                    try {
                        groupStrength = Integer.parseInt(strengthTV.getText().toString());
                    } catch (NumberFormatException e) {
                        Toast.makeText(GroupDetailActivity.this, "Enter valid strength", Toast.LENGTH_LONG).show();
                        allGood = false;
                    }
                    if (groupStrength <= 0) {
                        Toast.makeText(GroupDetailActivity.this, "Enter valid strength", Toast.LENGTH_LONG).show();
                        allGood = false;
                    }
                }

                if (allGood) {
                    ParseUtil.updateStrengthTable(groupId, groupStrength);

                } else {
                    Toast.makeText(GroupDetailActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
