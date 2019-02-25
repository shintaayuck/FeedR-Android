package com.example.feedr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class EditPetActivity extends AppCompatActivity {

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
