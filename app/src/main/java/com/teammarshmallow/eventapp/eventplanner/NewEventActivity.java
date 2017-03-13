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
    private final int LOCATION_PERMISSION = 1;

    GoogleMap googleMap;
    LocationHelper locationHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);
        locationHelper = new LocationHelper(this);
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
}
