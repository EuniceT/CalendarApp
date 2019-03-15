package com.example.matthew.calendarapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewEvent extends AppCompatActivity {

    private ListView ListView;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private ChildEventListener childEventListener;

    List<Event> EventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);
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

                    EventAdapter eventAdapter = new EventAdapter(ViewEvent.this, EventList);
                    ListView.setAdapter(eventAdapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }


        /*
        // Sets up the event listener that will specify what happens when access of a node
        // occurs in the database
        //childEventListener = new ChildEventListener(){
          //  @Override
            // Method is run when any new node is added to the database, and once
            // for every existing node when the activity is loaded
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                System.out.println("YEOOOOO BUDDDDDDDDDDDDDDDDDDDYYYYYYYYYY");

                System.out.println(Event.class);

                listAdapter.add( dataSnapshot.getValue(Event.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };

        // Need to add the event listener to the database
        myRef.addChildEventListener(childEventListener);

        // Sets up the list adapter to read from the results array
        listAdapter = new EventAdapter(this, EventList );
        ListView results = findViewById(R.id.listViewResults);
        results.setAdapter(listAdapter);
    }
    */


    public void home(View view)
    {
        Intent intent = new Intent( this, MainActivity.class);
        startActivity(intent);
    }

}
