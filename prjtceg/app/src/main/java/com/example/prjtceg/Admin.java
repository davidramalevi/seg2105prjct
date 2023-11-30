package com.example.prjtceg;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Admin {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref;
    String eventName;
    String eventDescription;
    String eventType;
    String eventDate;

    // Constructor with 1 parameter
    public Admin(String eventName) {
        this.eventName = eventName;
    }
    // Constructor with 4 parameters
    public Admin(String eventName, String eventDescription, String eventType, String eventDate) {
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventType = eventType;
        this.eventDate = eventDate;
    }
    public void Admin_add_event_title(String eventName){
        ref = database.getReference().child("Events").child(eventName.replace(".", ","));
        ref.setValue(eventName);

    }
    public void Admin_add_event_description(String eventName, String eventDescription, String eventType, String eventDate){

    }
    public void Admin_delete(String eventName){

    }
}
