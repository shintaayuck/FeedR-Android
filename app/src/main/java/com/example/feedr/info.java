package com.example.feedr;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class info extends Fragment {
    private float petDistance = 0;
    private TextView distanceTextView;
    private TextView locationTextView;

    private TextView mPetName;
    private TextView mPetType;
    private TextView mPetLocation;

    public MainActivity main_activity;
    public info() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_info, container, false);

        main_activity = (MainActivity) getActivity();

//        Log.d("Tes",main_activity.coba.toS);

        mPetName = (TextView) view.findViewById(R.id.petNameView);
        mPetType = (TextView) view.findViewById(R.id.petTypeView);
        mPetLocation = (TextView) view.findViewById(R.id.petLocationView);

        if (main_activity.pet != null) {
            Log.d("Ganti","Creaaate");

            mPetName.setText(main_activity.pet.getPetName());
            mPetType.setText(main_activity.pet.getType());
//            mPetLocation.setText(main_activity.pet.getLocation());
        }

        int MY_PERMISSION_ACCESS_COARSE_LOCATION = 42;

        distanceTextView = (TextView) view.findViewById(R.id.petDistanceView);
        locationTextView = (TextView) view.findViewById(R.id.petLocationView);

        final Location petLoc = new Location("");
        petLoc.setLatitude(-6.8915d);
        petLoc.setLongitude(107.6107d);

        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSION_ACCESS_COARSE_LOCATION);
        }

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {

                    @SuppressLint("DefaultLocale")
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            petDistance = location.distanceTo(petLoc);
                            distanceTextView.setText(String.format("%.2f Km", petDistance/1000));
                            locationTextView.setText(getAddress(location.getLatitude(), location.getLongitude()));
                        }
                    }
                });

        return view;
    }

    public String getAddress(double lat, double lng) {
        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            Address obj = addresses.get(0);

            return obj.getAddressLine(0);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
    }


    public void setInfo(){
        mPetName.setText(main_activity.pet.getPetName());
        mPetType.setText(main_activity.pet.getType());
//        mPetLocation.setText(main_activity.pet.getLocation());
    }
//    private void searchPet(){
//        String id = "1";

    @Override
    public void onStart() {
        super.onStart();
        Log.d("Masuk","Start");
        if (main_activity.pet != null) {
            Log.d("Ganti","Start");
            mPetName.setText(main_activity.pet.getPetName());
            mPetType.setText(main_activity.pet.getType());
//            mPetLocation.setText(main_activity.pet.getLocation());
        }
    }
//        new FetchPet(mPetName, mPetType, mPetLocation).execute(id);
//    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Masuk","Resume2");
        if (main_activity.pet != null) {
            Log.d("Ganti","Resume");
            mPetName.setText(main_activity.pet.getPetName());
            mPetType.setText(main_activity.pet.getType());
//            mPetLocation.setText(main_activity.pet.getLocation());
        }
    }
}
