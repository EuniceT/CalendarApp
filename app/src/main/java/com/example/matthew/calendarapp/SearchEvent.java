package com.example.matthew.calendarapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchEvent extends AppCompatActivity {

    Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchButton = findViewById((R.id.searchButton));
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
    }
}
