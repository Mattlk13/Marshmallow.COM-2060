package com.teammarshmallow.eventapp.eventplanner.Event;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.teammarshmallow.eventapp.eventplanner.R;

/**
 * Created by Adam Young on 07/03/2017.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    //Need to add ArrayList of the calendar object

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View eventView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_item_layout, parent, false);
        return new EventViewHolder(eventView);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        //Get the calendar entry from the ArrayList
        holder.eTitle = null; //Map each value from the entry to each value
        holder.eDescription = null;
        holder.eDistance = null;
        holder.eCategory = null;
    }

    @Override
    public int getItemCount() {
        //Need to return the count of however many elements are in the calendar ArrayList
        return 0;
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
}
