package com.example.prjtceg;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

                // Create an AlertDialog.Builder instance
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                // Set the title for the dialog
                builder.setTitle("Enter a new event name");

                // Create an EditText instance
                final EditText input = new EditText(view.getContext());

                // Set the EditText as the view for the AlertDialog
                builder.setView(input);

                // Set the positive (OK) button and its click listener
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Get the input from the EditText
                        String userInput = input.getText().toString();
                        Admin admin = new Admin(userInput);
                        admin.Admin_add_event_title(userInput);

                        // After getting the user input, start the new activity
                        Intent intent_add = new Intent(getApplicationContext(), MainAdminAddActivity.class);
                        startActivity(intent_add);
                        finish();
                    }
                });

                // Set the negative (Cancel) button and its click listener
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                // Create and show the AlertDialog
                builder.show();
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