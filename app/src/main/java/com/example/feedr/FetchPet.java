package com.example.feedr;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;

public class FetchPet extends AsyncTask<String,Void,String> {
    private  PetModel pet;

    public FetchPet(PetModel pet){
        this.pet = pet;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        pet = new PetModel("","","",false, null,0,0.0,0.0);
        try {
            JSONObject jsonObject = new JSONObject(s);
            String name=null;
            String type=null;
            String id = null;
            boolean isAvailable = false;
            String lastfed = null;
            int highScore = 0;
            double latitude =0.0;
            double longitude = 0.0;

            Log.d("Tes", "Gampar1");
            try {
                id = jsonObject.getString("id");
                name = jsonObject.getString("petName");
                type = jsonObject.getString("type");
                isAvailable = jsonObject.getInt("isAvailable") == 1;
                lastfed = jsonObject.getString("lastFed");
                highScore = jsonObject.getInt("highScore");
                latitude = jsonObject.getDouble("latitude");
                longitude = jsonObject.getDouble("longitude");

            } catch (Exception e){
                e.printStackTrace();
            }


            if (name != null && type != null){
                SimpleDateFormat formatter = new SimpleDateFormat("yy-mm-dd HH:mm:ss");
                Log.d("Tes", "Gampar2");
                pet = new PetModel(id, name,  type, isAvailable, lastfed, highScore, latitude, longitude);
                Log.d("Tes", "Gampar2");
                MainActivity.setPet(pet);

                return;
            }



        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected String doInBackground(String... strings) {
        return NetworkUtils.getPetInfo(strings[0]);
    }
}
