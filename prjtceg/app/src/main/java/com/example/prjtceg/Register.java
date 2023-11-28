package com.example.prjtceg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class Register extends AppCompatActivity {
    TextInputEditText editTextEmail, editTextPassword;
    Button button_reg;
    RadioButton radio_clubowner, radio_participant;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    FirebaseAuth mAuth;
    ProgressBar progressBar;


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent_login = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent_login);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        button_reg = findViewById(R.id.btn_register);
        progressBar = findViewById(R.id.progressBar);
        radio_clubowner = findViewById(R.id.radioClubOwner);
        radio_participant = findViewById(R.id.radioParticipant);




        //listener button
        button_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String email, password;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());

                //check if empty
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Register.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Register.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    // registration was successful, display a message to the user.
                                    Toast.makeText(Register.this, "Registration successful.",
                                            Toast.LENGTH_SHORT).show();
                                    if(radio_clubowner.isChecked()){
                                        Map<String, String> user = new HashMap<>();
                                        user.put("email", email);
                                        user.put("role", radio_clubowner.getText().toString());

                                        DatabaseReference ref = database.getReference().child("Users").child("Role");
                                        ref.setValue(user);
                                    }
                                    if(radio_participant.isChecked()){
                                        Map<String, String> user = new HashMap<>();
                                        user.put("email", email);
                                        user.put("role", radio_participant.getText().toString());

                                        DatabaseReference ref = database.getReference().child("Users").child("Role");
                                        ref.setValue(user);
                                    }
                                    // Create an Intent to start the login activity
                                    Intent intent = new Intent(Register.this, login.class);
                                    startActivity(intent);
                                    finish();


                                } else {
                                    // If registration fails, display a message to the user.
                                    Toast.makeText(Register.this, "Registration failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });



            }
        });
    }
}