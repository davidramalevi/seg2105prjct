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

public class MainActivity_Admin extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    Button btn_logout, btn_add, btn_edit, btn_delete, btn_delete_acc;
    DatabaseReference ref;
    TextView username, role;
    FirebaseUser user;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        auth = FirebaseAuth.getInstance();
        btn_logout=findViewById(R.id.logout);
        btn_add=findViewById(R.id.button_add);
        btn_edit=findViewById(R.id.button_edit);
        btn_delete=findViewById(R.id.button_delete);
        btn_delete_acc=findViewById(R.id.button_delete_acc);
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

        }
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_add = new Intent(getApplicationContext(), MainAdminAddActivity.class);
                startActivity(intent_add);
                finish();
            }
        });
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_add = new Intent(getApplicationContext(), MainAdminEditActivity.class);
                startActivity(intent_add);
                finish();
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_add = new Intent(getApplicationContext(), MainAdminDeleteActivity.class);
                startActivity(intent_add);
                finish();
            }
        });
        btn_delete_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_add = new Intent(getApplicationContext(), MainAdminDeleteAccActivity.class);
                startActivity(intent_add);
                finish();
            }
        });

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