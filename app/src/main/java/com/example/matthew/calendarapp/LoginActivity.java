package com.example.matthew.calendarapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;


public class LoginActivity extends AppCompatActivity {

    EditText usernameText;
    EditText passwordText;
    Button loginButton;
    Hashtable<String, String> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameText = findViewById((R.id.username));
        passwordText = findViewById((R.id.password));
        loginButton = findViewById((R.id.loginButton2));

        users = new Hashtable<>();

        users.put("Apple", "pass123");

        hideAllKeyBoard(usernameText);
        hideAllKeyBoard(passwordText);
        handlingClick();

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
                    if (users.containsKey(username) && users.get(username).equals(password)) {
                        Toast.makeText(getBaseContext(), "You have successfully signed in! :)",
                                Toast.LENGTH_LONG).show();
                        menu(view);
                    } else {
                        Toast.makeText(getBaseContext(), "Incorrect username or password.",
                                Toast.LENGTH_LONG).show();
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

    public void menu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
