package com.example.prjtceg;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity_Participant extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    Button btn_logout;
    DatabaseReference ref;
    TextView username, role;
    FirebaseUser user;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_participant);
        auth = FirebaseAuth.getInstance();
        btn_logout=findViewById(R.id.logout);
        username = findViewById(R.id.username);
        user = auth.getCurrentUser();
        role = findViewById(R.id.Role);




        if(user == null){
            Intent intent_null = new Intent(getApplicationContext(), login.class);
            startActivity(intent_null);
            finish();
        }
        else {
            username.setText(user.getEmail());
            try{
                ref = database.getReference().child("Users").child(user.getEmail().toString().replace(".", ","));
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String role_db = dataSnapshot.child("role").getValue(String.class);
                        role.setText(role_db);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle possible errors.
                    }
                });
            } catch (Exception e) {
                Toast.makeText(MainActivity_Participant.this, "ERROR.",
                        Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                Intent intent_log = new Intent(getApplicationContext(), login.class);
                startActivity(intent_log);
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