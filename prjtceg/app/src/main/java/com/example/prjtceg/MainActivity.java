package com.example.prjtceg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    Button btn_logout;
    TextView username;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();
        btn_logout=findViewById(R.id.logout);
        username = findViewById(R.id.username);
        user = auth.getCurrentUser();
        if(user == null){
            Intent intent_null = new Intent(getApplicationContext(), login.class);
            startActivity(intent_null);
            finish();
        }
        else{
            username.setText(user.getEmail());
            if (user.getEmail()=="admin@admin.ca"){
                Intent intent_admin = new Intent(getApplicationContext(), MainActivity_Admin.class);
                startActivity(intent_admin);
                finish();
            }
        }
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent_logout = new Intent(getApplicationContext(), login.class);
                startActivity(intent_logout);
                finish();
            }
        });
    }
}