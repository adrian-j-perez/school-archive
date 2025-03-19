package edu.lewis.cs.adrian.classexamplemaps;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

//note when making an app we have to give credit and tell about what we are doing with location

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private Boolean mMapReady;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }//end of onCreate

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
        mMapReady = true;

        LatLng chicago = new LatLng(41.881832, -87.623177);
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(chicago));

        CameraPosition.Builder builder = new CameraPosition.Builder();
        builder.target(chicago);
        //builder.bearing(112.5f); // the direction from north
        //builder.tilt(65);
        builder.zoom(13); // the smaller the number the farther it gets
        CameraPosition target = builder.build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(target));

        MarkerOptions options = new MarkerOptions();
        options.position(new LatLng(41.8789, -87.6359));
        options.title("Willis Tower"); // we need to use a sting resource for this
        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        // we can change the color of the marker
        mMap.addMarker(options);
    }


    //method to change the map type when clicking on a button//

    public void mapButtonClick(View v){
        if(mMapReady){
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }
    }

    public void satelliteButtonClick(View v){
        if(mMapReady){
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        }
    }

    public void hybridButtonClick(View v){
        if(mMapReady){
            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        }
    }

}//end of class