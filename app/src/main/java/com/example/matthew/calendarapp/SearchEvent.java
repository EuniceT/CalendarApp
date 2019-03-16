package com.example.matthew.calendarapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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

    EditText titleText;
    Button searchButton;
    FirebaseDatabase database;
    DatabaseReference myRef;
    ArrayList<Event> EventList;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        titleText = findViewById(R.id.eventTitle);

        searchButton = findViewById((R.id.searchButton2));
        listView = findViewById(R.id.listView);
        EventList = new ArrayList<>();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Events");

        if (titleText != null)
            hideAllKeyBoard(titleText);

        handlingClick();
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
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
    }

    public void handlingClick() {

        searchButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                listView.setAdapter(null);
                System.out.println(EventList);
                String title = titleText.getText().toString();

                if (title.length() <= 0) {
                    Toast.makeText(getBaseContext(), "Please fill out one blank", Toast.LENGTH_LONG).show();
                }

                boolean found = false;
                ArrayList<Event> searchedList = new ArrayList<>();
                for (Event event_obj : EventList)
                {
                    if (title.equals(event_obj.getTitle()))
                    {
                        searchedList.add(event_obj);
                        found = true;
                    }
                }

                if (!found)
                {
                    Toast.makeText(getBaseContext(), "Sorry, " + title + " not found. Please Try Again", Toast.LENGTH_LONG).show();
                } else {
                    SearchEventAdapter eventAdapter = new SearchEventAdapter(SearchEvent.this, searchedList);
                    listView.setAdapter(eventAdapter);
                }
            }
        });
    }


    public void hideKeyBoard(View view) {
        InputMethodManager imm = (InputMethodManager)
                getSystemService(AddingActivity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void hideAllKeyBoard(EditText editText) {
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean isFocus) {
                if (!isFocus)
                    hideKeyBoard(view);
            }
        });
    }


}
