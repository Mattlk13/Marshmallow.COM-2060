package com.teammarshmallow.eventapp.eventplanner.Event;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.teammarshmallow.eventapp.eventplanner.NewEventActivity;
import com.teammarshmallow.eventapp.eventplanner.R;
import com.teammarshmallow.eventapp.eventplanner.SettingsActivity;

public class EventActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        initRecyclerView();

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.event_map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.event_toolbar, menu);
        return true;
    }

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
