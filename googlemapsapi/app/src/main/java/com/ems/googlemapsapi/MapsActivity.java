package com.ems.googlemapsapi;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // forçar a liberação da permissão de acesso a Geolocalização
        String permission = Manifest.permission.ACCESS_FINE_LOCATION;
        int grant = ContextCompat.checkSelfPermission(this, permission);
        if (grant != PackageManager.PERMISSION_GRANTED) {
            String[] permissions = new String[1];
            permissions[0] = permission;
            ActivityCompat.requestPermissions(this, permissions, 1);
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        mMap.setOnMapClickListener(this);

        try {
            // configurações personalizadas do mapa
            mMap.setMapType(GoogleMap.MAP_TYPE_NONE);
            UiSettings settings = mMap.getUiSettings();
            settings.setScrollGesturesEnabled(true);
            settings.setCompassEnabled(true);
            settings.setZoomControlsEnabled(true);
            settings.setZoomGesturesEnabled(true);
            settings.setRotateGesturesEnabled(true);

            // Add a marker in Sydney and move the camera
            LatLng localAtual = new LatLng(-23.557055, -46.560603);
            mMap.addMarker(new MarkerOptions().position(localAtual).title("Aqui mora o Tio Edson"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(localAtual, 18));
        }catch(Exception ex){

        }
    }


    @Override
    public void onMapClick(LatLng latLng) {
        // pega a latitude e a longitude atual (onde foi clicado)
        LatLng localAtual = new LatLng(latLng.latitude, latLng.longitude);

        // coloca uma marca no mapa
        mMap.addMarker(new MarkerOptions().
                position(localAtual).icon(BitmapDescriptorFactory
        .defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
        Toast.makeText(this, String.valueOf(latLng.latitude),
                Toast.LENGTH_LONG).show();
    }
}