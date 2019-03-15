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

import java.util.List;

public class EventAdapter extends ArrayAdapter<Event> {

    private Activity mContext;
    private List<Event> EventList;

    public EventAdapter(Activity context, List<Event> list)
    {
        super( context, R.layout.activity_view_event, list);
        mContext = context;
        EventList = list;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItem = LayoutInflater.from(mContext).inflate(R.layout.activity_event_adapter,null,true);
        System.out.println("THIS IS ADAPTER HERE " + EventList);

        Event currentEvent = EventList.get(position);

        TextView name = (TextView) listItem.findViewById(R.id.eventName);
        name.setText(currentEvent.getTitle());

        TextView date = (TextView) listItem.findViewById(R.id.eventDate);
        date.setText(currentEvent.getDate());

        TextView time = (TextView) listItem.findViewById(R.id.eventTime);
        time.setText(currentEvent.getTime());

        TextView descript = (TextView) listItem.findViewById(R.id.eventDescript);
        descript.setText(currentEvent.getDescription());


        return listItem;
    }

}
