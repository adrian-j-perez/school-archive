package edu.lewisu.cs.example.locationtest;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    //static variable used permission verification
    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 123;

    private Boolean mRequestingLocationUpdates;
    private Boolean mDeniedPermissions;

    // UI components
    private Button mStartUpdatesButton;
    private Button mStopUpdatesButton;
    private TextView mLastUpdateTimeTextView;
    private TextView mLatitudeTextView;
    private TextView mLongitudeTextView;

    // Labels
    private String mLatitudeLabel;
    private String mLongitudeLabel;
    private String mLastUpdateTimeLabel;
    private String mLastUpdateTime;

    private Location mLocation;
    private FusedLocationProviderClient mFusedLocationProvdierClient;
    private LocationRequest mLocationReqeust;
    private LocationCallback mLocationCallback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Locate the UI widgets.
        mStartUpdatesButton = findViewById(R.id.start_updates_button);
        mStopUpdatesButton = findViewById(R.id.stop_updates_button);
        mLatitudeTextView = findViewById(R.id.latitude_text);
        mLongitudeTextView = findViewById(R.id.longitude_text);
        mLastUpdateTimeTextView = findViewById(R.id.last_update_time_text);

        // Set labels.
        mLatitudeLabel = getResources().getString(R.string.latitude_label);
        mLongitudeLabel = getResources().getString(R.string.longitude_label);
        mLastUpdateTimeLabel = getResources().getString(R.string.last_update_time_label);
        mLastUpdateTime = "";

        mRequestingLocationUpdates = false;
        mDeniedPermissions = false;


        createLocaltionCallback();
        createLocationRequest();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mRequestingLocationUpdates && checkPermissions()) {
            //start location updates
            startLocationUpdate();

        } else if (!checkPermissions() && !mDeniedPermissions) {
            requestPermissions();
        }

        updateUI();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates(); //this will help us save  battery life

    }

    @SuppressLint("MissingPermission")
    private void startLocationUpdate(){
        mFusedLocationProvdierClient.requestLocationUpdates(mLocationReqeust, mLocationCallback, Looper.myLooper());
    }

    private void stopLocationUpdates(){
        if(!mRequestingLocationUpdates){
            Log.d(TAG, "stopLocationUpdates:  updates ewere nerver reuested");
        }
    }
    //todo fixmme check i is missed something here 33 min in

    public void createLocationRequest(){
        mLocationReqeust = LocationRequest.create();
        mLocationReqeust.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationReqeust.setInterval(10000);
        mLocationReqeust.setFastestInterval(5000);

    }

    private void createLocaltionCallback(){
        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);

                mLocation = locationResult.getLastLocation();
                mLastUpdateTime = DateFormat.getDateTimeInstance().format(new Date()); //doubel checkjif i am useing the right java lib for date
                updateUI();
            }
        };
    }

    private void updateUI() {
        setButtonsEnabledState();
        if (mLocation != null) {
            String latString  = getResources().getString(R.string.location_string, mLatitudeLabel, mLocation.getLatitude());
            String lonString = getResources().getString(R.string.location_string, mLongitudeLabel, mLocation.getLongitude());
            String timeString = getResources().getString(R.string.time_string, mLastUpdateTimeLabel, mLastUpdateTime);

            mLatitudeTextView.setText(latString);
            mLongitudeTextView.setText(lonString);
            mLastUpdateTimeTextView.setText(timeString);
        }
    }

    public void startUpdatesButtonHandler(View v){
        if(!mRequestingLocationUpdates){
            if(checkPermissions()){
                mRequestingLocationUpdates = true;
                setButtonsEnabledState();
                startLocationUpdate();
            }else{requestPermissions();}
        }
    }

    //TODo add the missing thing here 35 min int class
    private void setButtonsEnabledState() {
        if (mRequestingLocationUpdates) {
            mStartUpdatesButton.setEnabled(false);
            mStopUpdatesButton.setEnabled(true);
        } else {
            mStartUpdatesButton.setEnabled(true);
            mStopUpdatesButton.setEnabled(false);
        }
    }

    /**********************PERMISSION HANDLING******************************************************/

    private boolean checkPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        boolean shouldProvideRationale =
                ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_FINE_LOCATION);

        if (shouldProvideRationale) {
            Snackbar.make(findViewById(android.R.id.content),R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(
                            android.R.string.ok, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    // Request permission
                                    ActivityCompat.requestPermissions(MainActivity.this,
                                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                            REQUEST_PERMISSIONS_REQUEST_CODE);
                                }
                            }).show();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (mRequestingLocationUpdates) {
                    //start location updates
                    startLocationUpdate();

                }
            } else {
                mDeniedPermissions = true;
                Snackbar.make(findViewById(android.R.id.content),R.string.permission_denied_explanation, Snackbar.LENGTH_INDEFINITE)
                        .setAction(R.string.settings, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent();
                                intent.setAction(
                                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package",
                                        BuildConfig.APPLICATION_ID, null);
                                intent.setData(uri);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        }).show();
            }
        }
    }
}