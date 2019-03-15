package com.example.matthew.calendarapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    CalendarView calendarView;
    TextView Date;
    Button AddButton;
    Button SButton;
    Button RemoveButton;
    Button LoginButton;
    String the_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        Date = (TextView) findViewById(R.id.Date);
        AddButton = (Button) findViewById(R.id.Addbutton);
        SButton = (Button) findViewById(R.id.Sbutton);
        RemoveButton = (Button) findViewById(R.id.Removebutton);
        LoginButton = (Button) findViewById(R.id.LoginButton);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2)
            {
                the_date = (i1+1) + "/" + i2 + "/" + i;
                Date.setText(the_date);

            }
        });

    }

    public void EventAdding( View view)
    {
        Intent intent = new Intent( this, AddingActivity.class);
        intent.putExtra("USER DATE", the_date);
        startActivity(intent);
    }

    public void EventRemoval( View view)
    {
        Intent intent = new Intent( this, RemoveEvent.class);
        startActivity(intent);
    }


    public void EventView( View view)
    {
        Intent intent = new Intent( this, ViewEvent.class);
        startActivity(intent);
    }

    public void EventLogin(View view) {

        Intent intent = new Intent (MainActivity.this, LoginActivity.class );
        startActivity(intent);
    }
}
