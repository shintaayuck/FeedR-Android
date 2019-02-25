package com.example.feedr;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class EditPetActivity extends AppCompatActivity {
    private TextView editPetLocationText;
    String newName;
    String newType;
    Double newLat;
    Double newLong;

    private EditText mPetName;
    private EditText mPetType;
    private EditText mPetLocation;
    private Button mSaveButton;

    PetModel pet;

    String petName = null;
    String petType = null;
    String petLocation = null;
    boolean petAvailable = false;
    String petId = null;
    String petLastFed = null;
    int petHighScore = -1;
    double petLatitude = 0.0;
    double petLongitude = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pet);

        editPetLocationText = (TextView) findViewById(R.id.editPetLocation);
    }

    public void getLocation(View view) {


        int MY_PERMISSION_ACCESS_COARSE_LOCATION = 42;
        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSION_ACCESS_COARSE_LOCATION);
        }

        newName = ((EditText)findViewById(R.id.editPetName)).getText().toString();
        newType = ((EditText)findViewById(R.id.editPetType)).getText().toString();

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {

                    @SuppressLint("DefaultLocale")
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            editPetLocationText.setText(getAddress(location.getLatitude(), location.getLongitude()));
                            newLat = location.getLatitude();
                            newLong = location.getLongitude();
                        }
                    }
                });
    }

    public String getAddress(double lat, double lng) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            Address obj = addresses.get(0);

            return obj.getAddressLine(0);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }

        mPetName = (EditText) findViewById(R.id.editPetName);
        mPetType = (EditText) findViewById(R.id.editPetType);
        mPetLocation = (EditText) findViewById(R.id.editPetLocation);
        mSaveButton = (Button) findViewById(R.id.edit_save_button);

        if (getIntent()!=null) {
            petName = getIntent().getExtras().getString("Pet Name");
            petType = getIntent().getExtras().getString("Pet Type");
            petLocation = getIntent().getExtras().getString("Pet Location");
            petAvailable = getIntent().getExtras().getBoolean("Pet Available");
            petId = getIntent().getExtras().getString("Pet Id");
            petLastFed = getIntent().getExtras().getString("Pet Last Fed");
            petHighScore = getIntent().getExtras().getInt("Pet Highscore");
            petLatitude = getIntent().getExtras().getInt("Pet Latitude");
            petLongitude = getIntent().getExtras().getInt("Pet Longitude");
        }

        mPetName.setText(petName);
        mPetType.setText(petType);
        mPetLocation.setText(petLocation);


    }

    public void updatePet(PetModel pet){
        Log.d("Masuk", "Updare");
        try {
            String result = new PetUpdater().execute(pet).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void savePet(View view) {
        petName = mPetName.getText().toString();
        petType = mPetType.getText().toString();
        petLocation = mPetLocation.getText().toString();

        pet = new PetModel(petId,petName,petType,petAvailable,petLastFed,petHighScore,petLatitude,petLongitude);
        updatePet(pet);
        Intent mainIntent =  new Intent(this,
                MainActivity.class);
        if (mainIntent != null) {
            startActivity(mainIntent);
            finish();
        }
    }
}
