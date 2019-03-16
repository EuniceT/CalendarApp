package com.example.matthew.calendarapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class SearchEventAdapter extends ArrayAdapter<Event> {

    private Activity mContext;
    private ArrayList<Event> EventList;

    public SearchEventAdapter(Activity context, ArrayList<Event> list)
    {
        super( context, R.layout.activity_search, list);
        mContext = context;
        EventList = list;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItem = mContext.getLayoutInflater().inflate(R.layout.activity_event_adapter, null, true);
        System.out.println("THIS IS ADAPTER SEARCH HERE " + EventList);

        Event currentEvent = EventList.get(position);

        TextView name = listItem.findViewById(R.id.eventName);
        name.setText(currentEvent.getTitle());

        TextView date = listItem.findViewById(R.id.eventDate);
        date.setText(currentEvent.getDate());

        TextView time = listItem.findViewById(R.id.eventTime);
        time.setText(currentEvent.getTime());

        TextView descript = listItem.findViewById(R.id.eventDescript);
        descript.setText(currentEvent.getDescription());


        return listItem;
    }

}
