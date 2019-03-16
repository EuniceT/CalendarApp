package com.example.matthew.calendarapp;


import android.content.Intent;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Hashtable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginActivity extends AppCompatActivity {

    EditText usernameText;
    EditText passwordText;
    Button loginButton;
    Hashtable<String, User> users;

    DatabaseReference myRef;
    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameText = findViewById((R.id.username));
        passwordText = findViewById((R.id.password));
        loginButton = findViewById((R.id.loginButton2));

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");

        users = new Hashtable<>();


//        users.put("Apple", "pass123");

        hideAllKeyBoard(usernameText);
        hideAllKeyBoard(passwordText);
        handlingClick();

    }

//    Bundle extras = getIntent().getExtras();
//            if( extras != null ) {
//        date = extras.getString("USER DATE");
//    }
//
//    Date user_date = new Date(date);
//    Event event = new Event(title, user_date.getDate(), text_description, text_time);
//            myRef.child(title).setValue(event);

    protected void onStart() {

        super.onStart();
        myRef.addValueEventListener(new ValueEventListener()
        {

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    User user = userSnapshot.getValue(User.class);
                    users.put(user.getUsername(), user);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
    }

    public void handlingClick() {

        loginButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();

                if (username.length() <= 0 || password.length() <= 0) {
                    Toast.makeText(getBaseContext(), "Fill out username or password",
                            Toast.LENGTH_LONG).show();

                } else {
                    // Check if username are found in the database?

                    try {
                        if (users.containsKey(username) && users.get(username).getPassword().equals(password)) {
                            Toast.makeText(getBaseContext(), "You have successfully signed in! :)",
                                    Toast.LENGTH_LONG).show();
                            menu(view);
                        } else {
                            Toast.makeText(getBaseContext(), "Incorrect username or password.",
                                    Toast.LENGTH_LONG).show();
                        }
                    } catch (NullPointerException e) {
                        Toast.makeText(getBaseContext(), "Error.",
                                Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
    }

    public void signup(View view) {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
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

    public void menu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
