package com.example.prjtceg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainAdminAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin_add);
    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent_admin = new Intent(getApplicationContext(), MainActivity_Admin.class);
        startActivity(intent_admin);
        finish();
    }
}