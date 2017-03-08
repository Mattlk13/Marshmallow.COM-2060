package com.teammarshmallow.eventapp.eventplanner.Event;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.teammarshmallow.eventapp.eventplanner.EventDetailActivity;
import com.teammarshmallow.eventapp.eventplanner.R;

import java.util.ArrayList;

/**
 * Created by Adam Young on 07/03/2017.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    //Need to add ArrayList of the calendar object
    ArrayList<CalendarDemo> arrayList = new ArrayList<>();
    Context context;

    public EventAdapter(Context context){
        this.context = context;

        for(int i = 1; i <= 20; i++){
            arrayList.add(new CalendarDemo("Item " + i, "This is a description",
                    "0." + i + "m", "Running"));
        }
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View eventView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_item_layout, parent, false);

        //Preliminary test to establish how the flow of activities works
        eventView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.getApplicationContext()
                        .startActivity(new Intent(context, EventDetailActivity.class));
            }
        });
        return new EventViewHolder(eventView);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        /* Get the calendar entry from the ArrayList
        holder.eTitle = null; //Map each value from the entry to each value
        holder.eDescription = null;
        holder.eDistance = null;
        holder.eCategory = null;
        */

        CalendarDemo demo = arrayList.get(position);
        holder.eTitle.setText(demo.getTitle());
        holder.eDescription.setText(demo.getDescription());
        holder.eDistance.setText(demo.getDistance());
        holder.eCategory.setText(demo.getCategory());
    }

    @Override
    public int getItemCount() {
        /* Need to return the count of however many elements are in the calendar ArrayList
        return 0;
        */

        return arrayList.size();
    }


    public class EventViewHolder extends RecyclerView.ViewHolder {
        private TextView eTitle;
        private TextView eDescription;
        private TextView eDistance;
        private TextView eCategory;

        public EventViewHolder(View itemView) {
            super(itemView);
            eTitle = (TextView) itemView.findViewById(R.id.event_item_title);
            eDescription = (TextView) itemView.findViewById(R.id.event_item_description);
            eDistance = (TextView) itemView.findViewById(R.id.event_item_distance);
            eCategory = (TextView) itemView.findViewById(R.id.event_item_category);
        }
    }

    private class CalendarDemo{
        private String title;
        private String description;
        private String distance;
        private String category;

        public CalendarDemo(String title, String description, String distance, String category){
            this.title = title;
            this.description = description;
            this.distance = distance;
            this.category = category;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getTitle() {

            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
