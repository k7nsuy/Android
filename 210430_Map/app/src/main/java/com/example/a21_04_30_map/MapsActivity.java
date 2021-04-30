package com.example.a21_04_30_map;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        AutoPermissionsListener {

    private GoogleMap mMap;
    MarkerOptions myLocationMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        AutoPermissions.Companion.loadAllPermissions(this,103);

    }

    private void startLocationService() {
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        try {
            Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                showCurrentLocation(latitude,longitude);
            }
            gpsListener gpsListener = new gpsListener();
            long minTime = 10000;
            float minDistance = 0;
            manager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    minTime,
                    minDistance,
                    gpsListener
            );
        }
        catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    private void showCurrentLocation(double latitude, double longitude) {
        LatLng curPoint = new LatLng(latitude,longitude);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint,12));
        showMyLocationMark(curPoint);
    }

    private void showMyLocationMark(LatLng curPoint) {
        if (myLocationMarker == null) {
            myLocationMarker = new MarkerOptions();
            myLocationMarker.position(curPoint);
            myLocationMarker.title("내 위치\n");
            myLocationMarker.snippet("GPS 로 확인한 위치");
            myLocationMarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.mylocation));
            mMap.addMarker(myLocationMarker);
        } else {
            myLocationMarker.position(curPoint);
        }
    }

    @Override
    public void onDenied(int i, String[] strings) {
        Log.i("permission denied : ",strings.length+"");
    }

    @Override
    public void onGranted(int i, String[] strings) {
        Log.i("permission granted : ",strings.length+"");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoPermissions.Companion.parsePermissions(this,requestCode,permissions,this);
    }

    class gpsListener implements LocationListener {

        @Override
        public void onLocationChanged(@NonNull Location location) {
           double latitude = location.getLatitude();
           double longitude = location.getLongitude();
           showCurrentLocation(latitude,longitude);
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        startLocationService();

        showCurrentLocation(35.156,129.0595);

//        LatLng myPoint = new LatLng(35.156, 129.0595);
//        LatLng myPoint2 = new LatLng(35.157, 129.0605);
//
//
//        mMap.addMarker(new MarkerOptions().position(myPoint).title("Marker in myPoint"));
//        mMap.addMarker(new MarkerOptions().position(myPoint2).title("Marker in myPoint2"));
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myPoint,15));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

}