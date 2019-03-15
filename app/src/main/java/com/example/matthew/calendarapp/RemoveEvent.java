package com.example.matthew.calendarapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RemoveEvent extends AppCompatActivity {

    // Google Firebase Database Reference
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private ListView ListView;

    private ChildEventListener childEventListener;

    private ArrayList<Event> EventList;

    private ArrayList<Event> searchResults;

    private EventAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_event);
        ListView = findViewById(R.id.listView);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Events");

        // Set up an array that will have the contents that you want to display
        EventList = new ArrayList<>();
    }

    protected void onStart() {

        super.onStart();
        myRef.addValueEventListener(new ValueEventListener()
        {

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {
                    Event event = eventSnapshot.getValue(Event.class);
                    EventList.add(event);
                }

               // EventAdapter eventAdapter = new EventAdapter(RemoveEvent.this, EventList);
               // ListView.setAdapter(eventAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_event);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        // Initializes the local data structures
        EventList = new ArrayList<Event>();
        searchResults = new ArrayList<Event>();

        // Sets up the event listener that will specify what happens when access of a node
        // occurs in the database
        childEventListener = new ChildEventListener() {
            // Method is run when any new node is added to the database, and once
            // for every existing node when the activity is loaded
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                EventList.add(dataSnapshot.getValue(Event.class));

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };



        myRef.addChildEventListener(childEventListener);

        // Sets up the list view/list adapter to read from the search results array
        listAdapter = new EventAdapter(this, searchResults);

        //Associates the ListView to the adapter
        ListView results = findViewById(R.id.listView);
    }
*/
    // Upon button click of "Remove"
    public void remove(View view) {
        int count = 0;
        System.out.println("REMOVE FUNCTION AT CLASSS ********************");

        System.out.println(EventList);

        //listAdapter.clear();    // clears any content
        EditText remove_text = (EditText) findViewById(R.id.editRemoveTitle);
        String string_text = remove_text.getText().toString();  // Extracts name from EditText
        System.out.println(EventList);

        for (Event event_obj : EventList)
        {
            if (string_text.equals(event_obj.getTitle()))
            {
                EventList.remove(event_obj.getTitle());
                myRef.child(event_obj.getTitle()).removeValue();
                Toast.makeText(this, string_text + " successfully removed", Toast.LENGTH_LONG).show();
            } else
            {
                count++;
            }
        }
        if (count == EventList.size())
        {
            Toast.makeText(this, "Sorry, " + string_text + " not found. Please Try Again", Toast.LENGTH_LONG).show();
        }

        remove_text.setText("");
    }


    public void menu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
