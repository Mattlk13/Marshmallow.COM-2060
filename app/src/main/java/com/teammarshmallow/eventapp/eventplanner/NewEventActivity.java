package com.teammarshmallow.eventapp.eventplanner;

import android.os.Bundle;

public class NewEventActivity extends MapActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_new_event;
    }
}
