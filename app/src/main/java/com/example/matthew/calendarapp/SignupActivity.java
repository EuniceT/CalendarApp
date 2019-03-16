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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Hashtable;

public class SignupActivity extends AppCompatActivity {

    EditText usernameText;
    EditText passwordText;
    Button signupButton;
    Hashtable<String, User> users;

    DatabaseReference myRef;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        usernameText = findViewById((R.id.username));
        passwordText = findViewById((R.id.password));
        signupButton = findViewById((R.id.signupButton));

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");

        users = new Hashtable<>();

        hideAllKeyBoard(usernameText);
        hideAllKeyBoard(passwordText);

        handlingClick();

    }

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

        signupButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();

                if (username.length() <= 0 || password.length() <= 0) {
                    Toast.makeText(getBaseContext(), "Fill out username or password",
                            Toast.LENGTH_LONG).show();

                } else {
                    // Check if username are found in the database?
                    System.out.println("inside!!");
                    if (users.containsKey(username)) {
                        Toast.makeText(getBaseContext(), "Account username already exists",
                                Toast.LENGTH_LONG).show();
                    } else {

                        User user = new User(username, password);
                        myRef.child(username).setValue(user);
                        Toast.makeText(getBaseContext(), "You have successfully created an account. Please log in.",
                                Toast.LENGTH_LONG).show();
                        signin(view);
                    }

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

    public void signin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
