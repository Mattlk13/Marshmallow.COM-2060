package com.teammarshmallow.eventapp.eventplanner.Event;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.teammarshmallow.eventapp.eventplanner.Data.LocationHelper;
import com.teammarshmallow.eventapp.eventplanner.EventDetailActivity;
import com.teammarshmallow.eventapp.eventplanner.NewEventActivity;
import com.teammarshmallow.eventapp.eventplanner.R;
import com.teammarshmallow.eventapp.eventplanner.SettingsActivity;

public class EventActivity extends AppCompatActivity implements OnMapReadyCallback {
    private final int LOCATION_PERMISSION = 1;

    private GoogleMap googleMap;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    private LocationHelper locationHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        locationHelper = new LocationHelper(this);
        initRecyclerView();
        setAppBarDragBehaivour();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.event_map);
        mapFragment.getMapAsync(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setMapPosition();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        setMapPosition();
    }

    /**
     * Helper method to set the location of the current google map to
     */
    private void setMapPosition(){
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions( this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION);
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    LOCATION_PERMISSION);
        }
    }

    /**
     * Method to add icons to the toolbar at the top of the activity
     * @param menu The menu passed from the activity
     * @return True if method has finished.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.event_toolbar, menu);
        return true;
    }

    /**
     * Method that handles permission request results, using the constants defined above.
     * @param requestCode Value passed defining the event that requested the permission
     * @param permissions The permission the event requested
     * @param grantResults The permissions granted to the app
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case LOCATION_PERMISSION: {
                if(grantResults.length < 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                        this.googleMap.setMyLocationEnabled(true);
                        this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(locationHelper.getCurrentLocation()));
                    }
                }
            }
        }
    }

    /**
     * Method to alter the drag behaviour for the app bar, to allow free movement on the map
     * fragment's Y axis.
     */
    private void setAppBarDragBehaivour(){
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams();
        AppBarLayout.Behavior behavior = new AppBarLayout.Behavior();
        behavior.setDragCallback(new AppBarLayout.Behavior.DragCallback(){

            @Override
            public boolean canDrag(@NonNull AppBarLayout appBarLayout) {
                return false;
            }
        });
        params.setBehavior(behavior);
    }

    /**
     * Method to initialise the recycler view and its adapter, by setting layout structure and binding
     * the adapter.
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

    /**
     * Method called from the XML onClick tag.
     * @param item The item that fired the method.
     */
    public void createNewEventActivity(MenuItem item) {
        startActivity(new Intent(this, NewEventActivity.class));
    }

    /**
     * Method called from the XML onClick tag.
     * @param item The item that fired the method.
     */
    public void openSettingsActivity(MenuItem item) {
        startActivity(new Intent(this, SettingsActivity.class));
    }

    /**
     * Method called from the XML onClick tag.
     * @param view View that the onClick tag is called from.
     */
    public void openDetailActivity(View view) {
        Intent intent = new Intent(this, EventDetailActivity.class);
        startActivity(intent);
    }
}
