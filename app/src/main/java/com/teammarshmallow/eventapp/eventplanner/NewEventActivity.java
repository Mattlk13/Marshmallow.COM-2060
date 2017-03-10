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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);
        setAppBarDragBehaivour();

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.new_event_map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Method to setup the map fragment, by moving to the user's location.
     * @param googleMap The map fragment
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        //Check location permissions, and if not granted, as for them
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions((FragmentActivity) this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    1);
        }
        this.googleMap.setMyLocationEnabled(true);

        this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LocationHelper(this).getCurrentLocation()));
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
