package com.teammarshmallow.eventapp.eventplanner.Data;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Adam Young on 08/03/2017.
 */

public class LocationHelper {
    private Context context;
    private GoogleApiClient apiClient;
    private static LatLng location;

    public LocationHelper(Context context){
        this.context = context;
        initApi();
    }

    /**
     * Creates an auto-managed API client to interact with the location services API.
     */
    private void initApi(){
        //Instantiates a new Google API, which is auto-managed.
        apiClient = new GoogleApiClient.Builder(context)
                .enableAutoManage((FragmentActivity) context /* FragmentActivity */,
                        null /* OnConnectionFailedListener */)
                .addApi(LocationServices.API)
                .build();
    }

    /**
     * Method that queries the API client for a GPS location, and if nothing is found, returns the last
     * known location from the network.
     * @return A LatLng of the current position of the device.
     */
    public LatLng getCurrentLocation(){

        //Asks the user for permission to access location data.
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions((FragmentActivity) context,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
            ActivityCompat.requestPermissions((FragmentActivity) context,
                    new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    1);
        }
        //Gets last known location, using the API client.
        Location lastLoc = LocationServices.FusedLocationApi.getLastLocation(apiClient);

        if(lastLoc != null){
            location = new LatLng(lastLoc.getLatitude(), lastLoc.getLongitude());
        }
        else
        {
            LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            Location loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            location = new LatLng(loc.getLatitude(), loc.getLongitude());
        }
        return location;
    }
}
