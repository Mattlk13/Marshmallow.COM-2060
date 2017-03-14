package com.teammarshmallow.eventapp.eventplanner.Event;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
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

    /**
     * Constructor to get the activity that implements the adapter
     * @param context The Recycler view attached to the adapter
     */
    public EventAdapter(Context context){
        this.context = context;

        for(int i = 1; i <= 20; i++){
            arrayList.add(new CalendarDemo("Item " + i, "This is a description",
                    "0." + i + "m", "Running"));
        }
    }

    /**
     * Method that creates a blank event layout
     * @param parent Recycler view container
     * @param viewType
     * @return A blank event item layout
     */
    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View eventView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_item_layout, parent, false);

        eventView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, EventDetailActivity.class));
            }
        });

        return new EventViewHolder(eventView);
    }

    /**
     * Method to bind data to the item layout.
     * @param holder The blank item layout.
     * @param position The position in the list of the recycler view.
     */
    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        CalendarDemo demo = arrayList.get(position);
        holder.eTitle.setText(demo.getTitle());
        holder.eDescription.setText(demo.getDescription());
        holder.eDistance.setText(demo.getDistance());
        holder.eCategory.setText(demo.getCategory());
    }

    /**
     * Method to return the size of the arraylist containing event objects.
     * @return An int of the size of the arraylist.
     */
    @Override
    public int getItemCount() {
        /* Need to return the count of however many elements are in the calendar ArrayList
        return 0;
        */

        return arrayList.size();
    }

    /**
     * A class that maps variable names onto each field of the item layout.
     */
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
