package com.teammarshmallow.eventapp.eventplanner;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.teammarshmallow.eventapp.eventplanner.Data.LocationHelper;

public class NewEventActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap googleMap;
    LocationHelper locationHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);
        setAppBarDragBehaivour();
        locationHelper = new LocationHelper(this);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.new_event_map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Method to setup the map fragment, by moving to the user's location.
     * @param map The map fragment
     */
    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;

        locationHelper.enableMapLocationIcon(googleMap);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(locationHelper.getCurrentLocation()));
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
