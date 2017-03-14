package com.teammarshmallow.eventapp.eventplanner;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.teammarshmallow.eventapp.eventplanner.Data.LocationHelper;

/**
 * Created by Adam Young on 13/03/2017.
 */

public abstract class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private LocationHelper locationHelper;
    private GoogleMap map;
    private SupportMapFragment mapFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        setAppBarDragBehaivour();

        locationHelper = new LocationHelper(this);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
}

    /**
     *  Method called when the activity is started for the first time.
     */
    @Override
    protected void onStart() {
        super.onStart();

        getLocationHelper().requestLocationPermission();
        mapFragment.getMapAsync(this);
    }

    /**
     * Method to assign the map value when the map is ready.
     * @param googleMap The GoogleMap fragment from the current activity.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            map.setMyLocationEnabled(true);
            map.moveCamera(CameraUpdateFactory.newLatLng(locationHelper.getCurrentLocation()));
        }
    }

    /**
     * Method that is called when the permission request dialog is responded to.
     * @param requestCode Int that corresponds to the permission type requested.
     * @param permissions String array of permissions that have been granted.
     * @param grantResults Int array of results that have been granted.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                            && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                        map.setMyLocationEnabled(true);
                        map.animateCamera(CameraUpdateFactory.newLatLng(locationHelper.getCurrentLocation()));
                    }
                }
        }
    }

    /**
     * Method to return an instance of LocationHelper.
     * @return An instance of LocationHelper.
     */
    protected LocationHelper getLocationHelper(){
        return locationHelper;
    }

    /**
     * Method to return the MapFragment from the current layout.
     * @return An instance of a GoogleMap.
     */
    protected GoogleMap getMap(){
        return map;
    }

    /**
     * Method to return the layout for implementing classes.
     * @return An Integer referencing the layout item.
     */
    protected abstract int getLayoutResourceId();

    /**
     * Method to disable scrolling of AppBarLayout. ID must be appbar as default.
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
