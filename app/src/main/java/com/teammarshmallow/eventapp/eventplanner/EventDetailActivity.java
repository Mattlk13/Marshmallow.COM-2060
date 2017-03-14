package com.teammarshmallow.eventapp.eventplanner;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class EventDetailActivity extends MapActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_event_detail;
    }

    public void registerToEvent(View view) {
        //Register to event
        Toast.makeText(this, "Registered!", Toast.LENGTH_SHORT).show();
    }
}
