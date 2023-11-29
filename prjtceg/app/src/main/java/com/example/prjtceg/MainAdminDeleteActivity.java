package com.example.prjtceg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainAdminDeleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin_delete);
    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent_admin = new Intent(getApplicationContext(), MainActivity_Admin.class);
        startActivity(intent_admin);
        finish();
    }
}