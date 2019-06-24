package com.example.explorun;


import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

import static com.example.explorun.MapFragment.googleMap;


public class MapsActivity extends AppCompatActivity {

    private Fragment selectedFragment;
    private Toolbar toolbar;

//    public MapsActivity(){
//        selectedFragment = new ProfilFragment();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Fragment mFragment = new MapFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fragment_container, mFragment).commit();

//        toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState != null && selectedFragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    selectedFragment).commit();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_navigation, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        // Define the listener

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                try {
                    String location = searchView.getQuery().toString();
                    List<Address> addressList = null;

                    if (location != null || !location.equals("")) {
                        Geocoder geocoder = new Geocoder(MapsActivity.this);
                        try {
                            addressList = geocoder.getFromLocationName(location, 1);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Address address = addressList.get(0);
                        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                        //googleMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
                        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                    }
                } catch (Exception e) {
                    //e.printStackTrace();
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return true;
    }

    public void changeToRoutesList(View view) {
        Intent i2 = new Intent(getApplicationContext(), RoutesActivity.class);
        startActivity(i2);
    }

    public void changeToFilter(View view) {
        Intent i = new Intent(getApplicationContext(), FilterActivity.class);
        startActivity(i);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    //  selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_map:
                            getSupportActionBar().show();
                            selectedFragment = new MapFragment();

                            break;
                        case R.id.nav_profil:
                            selectedFragment = new ProfilFragment();
                            getSupportActionBar().hide();

                            break;
                        case R.id.nav_best:
                            selectedFragment = new BestFragment();
                            getSupportActionBar().hide();

                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };

}