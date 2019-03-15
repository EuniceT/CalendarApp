package com.example.matthew.calendarapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchEvent extends AppCompatActivity {

    Button searchButton;
    FirebaseDatabase database;
    DatabaseReference myRef;
    ArrayList<Event> EventList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchButton = findViewById((R.id.searchButton));
        listView = findViewById((R.id.listView));
        EventList = new ArrayList<>();


        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");

    }

    protected void onStart() {
        super.onStart();

//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {
//                    Event event = eventSnapshot.getValue(Event.class);
//                    EventList.add(event);
//                }
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
////                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });
    }

    public void searchingEvent() {
        EditText titleText = findViewById(R.id.title);
        String title = titleText.getText().toString();

        EditText timeText = findViewById(R.id.time);
        String time = timeText.getText().toString();

        EditText descripText = findViewById(R.id.description);
        String descrip = descripText.getText().toString();

        if (title.length() > 0 || time.length() > 0 || descrip.length() > 0) {

        } else {
            Toast.makeText(this, "Please fill out one blank", Toast.LENGTH_LONG).show();
        }

//        EditText remove_text = (EditText) findViewById(R.id.editRemoveTitle);
//        String string_text = remove_text.getText().toString();  // Extracts name from EditText
//        System.out.println(EventList);
//
        boolean found = false;
        for (Event event_obj : EventList)
        {
            if (titleText.equals(event_obj.getTitle()))
            {
//                EventList.remove(event_obj.getTitle());
//                myRef.child(event_obj.getTitle()).removeValue();
//                Toast.makeText(this, titleText + " successfully removed", Toast.LENGTH_LONG).show();

//                EventAdapter eventAdapter = new EventAdapter(SearchEvent.this, EventList);
//                listView.setAdapter(eventAdapter);

                found = true;
            } else if (timeText.equals(event_obj.getTime())) {

            } else if (descripText.equals(event_obj.getDescription())) {

            }
        }

        if (!found)
        {
            Toast.makeText(this, "Sorry, " + titleText + " not found. Please Try Again", Toast.LENGTH_LONG).show();
        }

    }


}
