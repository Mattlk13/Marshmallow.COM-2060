package com.teammarshmallow.eventapp.eventplanner;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.teammarshmallow.eventapp.eventplanner.Data.LocationHelper;

public class EventDetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        setAppBarDragBehaivour();

        //Get the map fragment from the layout.
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.event_detail_map);
        //Instantiate the map fragment.
        mapFragment.getMapAsync(this);
    }

    /**
     * Method to setup the map fragment, by moving to the user's location.
     * @param googleMap The map fragment
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        //Move camera to event location.
    }

    /**
     * Method to register the selected event with the database.
     * @param view The current event view.
     */
    public void registerToEvent(View view) {
        //Register to event
        Toast.makeText(this, "Registered!", Toast.LENGTH_SHORT).show();
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
}
