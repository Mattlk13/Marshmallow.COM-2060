package com.teammarshmallow.eventapp.eventplanner.Event;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.teammarshmallow.eventapp.eventplanner.EventDetailActivity;
import com.teammarshmallow.eventapp.eventplanner.MapActivity;
import com.teammarshmallow.eventapp.eventplanner.NewEventActivity;
import com.teammarshmallow.eventapp.eventplanner.R;
import com.teammarshmallow.eventapp.eventplanner.SettingsActivity;

public class EventActivity extends MapActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initRecyclerView();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_event;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.event_toolbar, menu);
        return true;
    }

    /**
     * Method to instantiate the EventAdapter and attach it to the current RecyclerView.
     */
    private void initRecyclerView(){
        mRecyclerView = (RecyclerView) findViewById(R.id.event_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);

        mAdapter = new EventAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void createNewEventActivity(MenuItem item) {
        startActivity(new Intent(this, NewEventActivity.class));
    }

    public void openSettingsActivity(MenuItem item) {
        startActivity(new Intent(this, SettingsActivity.class));
    }
}
