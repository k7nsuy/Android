package com.example.a21_04_30_project_map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback,
        AutoPermissionsListener {

    private GoogleMap mMap;
    MarkerOptions myLocationMarker;

    int[] mapTypes = {GoogleMap.MAP_TYPE_NORMAL,
    GoogleMap.MAP_TYPE_TERRAIN,GoogleMap.MAP_TYPE_SATELLITE};
    int mapType = GoogleMap.MAP_TYPE_SATELLITE;

    double[] lats = {45,46};
    double[] lngs = {123,124};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        AutoPermissions.Companion.loadAllPermissions(this,104);
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

        // Add a marker in Sydney and move the camera
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
            long minTime = 100000;
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
        mMap.setMapType(mapType);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint,10));
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
            mMap.addMarker(myLocationMarker);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0,1,0,"일반 지도");
        menu.add(0,2,0,"지형 지도");
        menu.add(0,3,0,"위성 지도");
        menu.add(0,4,0,"부산 시청");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                mapType = mapTypes[0];
                break;
            case 2:
                mapType = mapTypes[1];
                break;
            case 3:
                mapType = mapTypes[2];
                break;
            case 4:
                showCurrentLocation(lats[0],lngs[0]);
        }
        return true;
    }

    @Override
    public void onDenied(int i, String[] strings) {

    }

    @Override
    public void onGranted(int i, String[] strings) {

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
}