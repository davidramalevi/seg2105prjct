package com.example.prjtceg;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainAdminAddActivity extends AppCompatActivity {
    Button btn_submit, btn_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin_add);
        btn_submit = findViewById(R.id.submit_button);
        btn_cancel = findViewById(R.id.cancel_button);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Your code here
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(v.getContext())
                        .setTitle("Warning")
                        .setMessage("Cancelling will delete the event, would you like to proceed?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // TO:DO Delete eventName from database

                                //move to previous intent
                                Intent intent_admin = new Intent(getApplicationContext(), MainActivity_Admin.class);
                                startActivity(intent_admin);
                                finish();
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });



    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent_admin = new Intent(getApplicationContext(), MainActivity_Admin.class);
        startActivity(intent_admin);
        finish();
    }
}