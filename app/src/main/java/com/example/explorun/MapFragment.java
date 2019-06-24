package com.example.explorun;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.explorun.routes.Alsterrunde;
import com.example.explorun.routes.Kanalrunde;
import com.example.explorun.routes.Stadtpark;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.data.geojson.GeoJsonLayer;

import org.json.JSONException;

import java.io.IOException;


public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 5445;

    public static GoogleMap googleMap;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Marker currentLocationMarker;
    private Location currentLocation;
    private boolean firstTimeFlag = true;
    private View mView;
    private com.google.android.gms.maps.SupportMapFragment mMapFragment;
    private static SearchView searchView;
    private GeoJsonLayer glayer;

    private final View.OnClickListener clickListener = view -> {
        if (view.getId() == R.id.currentLocationImageButton && googleMap != null && currentLocation != null)
            animateCamera(currentLocation);
    };

    private final LocationCallback mLocationCallback = new LocationCallback() {

        @Override
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);
            if (locationResult.getLastLocation() == null)
                return;
            currentLocation = locationResult.getLastLocation();
            if (firstTimeFlag && googleMap != null) {
                animateCamera(currentLocation);
                firstTimeFlag = false;
            }
            showMarker(currentLocation);
        }
    };

    public MapFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_map, container, false);
        mMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapFragment);
        mMapFragment.getMapAsync(this);
        mView.findViewById(R.id.currentLocationImageButton).setOnClickListener(clickListener);
        return mView;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        this.googleMap = googleMap;

        UiSettings config = googleMap.getUiSettings();
        config.setMapToolbarEnabled(false);
        config.setZoomControlsEnabled(false);

        googleMap.setOnMarkerClickListener(this::onMarkerClick);

        Marker AlsterRunde = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(53.55886000, 10.00768000))
                .title("Alster Runde"));

        Marker KanalRunde = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(53.580196, 10.04))
                .title("Kanal Runde"));

        Marker StadtparkRunde = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(53.58000167, 10.04028233))
                .title("Stadtpark Runde"));

        Marker Bergedorf = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(53.50309383, 10.21822217))
                .title("Bergedorf"));

        Marker HellaHalb = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(53.54980967, 9.96556317))
                .title("Hella Halbmarathon"));

        Marker KoelnRunde = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(50.932295, 6.92948317))
                .title("Köln Runde"));

        googleMap.setOnMapClickListener(GoogleMap -> {
            if(glayer != null){
                glayer.removeLayerFromMap();
            }
        });

        //Aktion wenn auf einen Marker geklickt wird
        googleMap.setOnMarkerClickListener(marker -> {
            marker.showInfoWindow();
            if (marker.getTitle() == null && glayer != null) {
                glayer.removeLayerFromMap();
            }

            if (marker.getTitle() != null && marker.getTitle().equals("Alster Runde")) {
                if (glayer != null) {
                    glayer.removeLayerFromMap();
                }
                try {
                    glayer = new GeoJsonLayer(googleMap, R.raw.alsterrunde, getActivity().getApplicationContext());
                    glayer.addLayerToMap();

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


            if (marker.getTitle() != null && marker.getTitle().equals("Kanal Runde")) {
                if (glayer != null) {
                    glayer.removeLayerFromMap();
                }
                try {
                    glayer = new GeoJsonLayer(googleMap, R.raw.afternoonrun, getActivity().getApplicationContext());
                    glayer.addLayerToMap();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if (marker.getTitle() != null && marker.getTitle().equals("Stadtpark Runde")) {
                if (glayer != null) {
                    glayer.removeLayerFromMap();
                }
                try {
                    glayer = new GeoJsonLayer(googleMap, R.raw.moonrun, getActivity().getApplicationContext());
                    glayer.addLayerToMap();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if (marker.getTitle() != null && marker.getTitle().equals("Bergedorf")) {
                if (glayer != null) {
                    glayer.removeLayerFromMap();
                }
                try {
                    glayer = new GeoJsonLayer(googleMap, R.raw.bergedorf, getActivity().getApplicationContext());
                    glayer.addLayerToMap();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if (marker.getTitle() != null && marker.getTitle().equals("Hella Halbmarathon")) {
                if (glayer != null) {
                    glayer.removeLayerFromMap();
                }
                try {
                    glayer = new GeoJsonLayer(googleMap, R.raw.hellahalbmara, getActivity().getApplicationContext());
                    glayer.addLayerToMap();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if (marker.getTitle() != null && marker.getTitle().equals("Köln Runde")) {
                if (glayer != null) {
                    glayer.removeLayerFromMap();
                }
                try {
                    glayer = new GeoJsonLayer(googleMap, R.raw.koelnrunde, getActivity().getApplicationContext());
                    glayer.addLayerToMap();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return true;
        });

        //hier können neue Infos/activitys beim Klicken auf das infoWindow von Markern eigefügt werden

        googleMap.setOnInfoWindowClickListener(marker1 -> {
            if((marker1.getTitle() != null && marker1.getTitle().equals("Alster Runde"))) {
                Intent intent = new Intent(getActivity().getApplicationContext(), Alsterrunde.class);
                startActivity(intent);
            }
            if (marker1.getTitle() != null && marker1.getTitle().equals("Stadtpark Runde")) {
                Intent intent = new Intent(getActivity().getApplicationContext(), Stadtpark.class);
                startActivity(intent);
            }
            if (marker1.getTitle() != null && marker1.getTitle().equals("Kanal Runde")) {
                Intent intent = new Intent(getActivity().getApplicationContext(), Kanalrunde.class);
                startActivity(intent);
            }

        });

    }


    private void animateCamera(@NonNull Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));
        // googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(getCameraPositionWithBearing(latLng)));
    }

    @NonNull
    private CameraPosition getCameraPositionWithBearing(LatLng latLng) {
        return new CameraPosition.Builder().target(latLng).zoom(13).build();
    }

    private void showMarker(@NonNull Location currentLocation) {
        LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        if (currentLocationMarker == null)
            currentLocationMarker = googleMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)).position(latLng));
        else
            MarkerAnimation.animateMarkerToGB(currentLocationMarker, latLng, new LatLngInterpolator.Spherical());
    }

    private void startCurrentLocationUpdates() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(3000);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
                return;
            }
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, mLocationCallback, Looper.myLooper());
    }

    private boolean isGooglePlayServicesAvailable() {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int status = googleApiAvailability.isGooglePlayServicesAvailable(getActivity().getApplicationContext());
        if (ConnectionResult.SUCCESS == status)
            return true;
        else {
            if (googleApiAvailability.isUserResolvableError(status))
                Toast.makeText(getActivity().getApplicationContext(), "Please Install google play services to use this application", Toast.LENGTH_LONG).show();
        }
        return false;
    }

//    private BitmapDescriptor bitmapDescriptorFromVector(MapFragment context, @DrawableRes int vectorDrawableResourceId) {
//        Drawable background = ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.loca);
//        background.setBounds(0, 0, background.getIntrinsicWidth(), background.getIntrinsicHeight());
//        Drawable vectorDrawable = ContextCompat.getDrawable(getActivity().getApplicationContext(), vectorDrawableResourceId);
//        vectorDrawable.setBounds(40, 20, vectorDrawable.getIntrinsicWidth() + 40, vectorDrawable.getIntrinsicHeight() + 20);
//        Bitmap bitmap = Bitmap.createBitmap(background.getIntrinsicWidth(), background.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(bitmap);
//        background.draw(canvas);
//        vectorDrawable.draw(canvas);
//        return BitmapDescriptorFactory.fromBitmap(bitmap);
//    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_DENIED)
                Toast.makeText(getActivity().getApplicationContext(), "Permission denied by uses", Toast.LENGTH_SHORT).show();
            else if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                startCurrentLocationUpdates();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (fusedLocationProviderClient != null)
            fusedLocationProviderClient.removeLocationUpdates(mLocationCallback);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isGooglePlayServicesAvailable()) {
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity().getApplicationContext());
           startCurrentLocationUpdates();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fusedLocationProviderClient = null;
        googleMap = null;
    }


    @Override
    public boolean onMarkerClick(Marker marker) {

        return false;
    }
}