package com.example.feedr;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class FetchPet extends AsyncTask<String,Void,String> {
    private TextView mPetName;
    private TextView mPetType;
    private TextView mPetLocation;

    public FetchPet(TextView mPetName, TextView mPetType, TextView mPetLocation){
        this.mPetName = mPetName;
        this.mPetType = mPetType;
        this.mPetLocation = mPetLocation;


    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            JSONObject jsonObject = new JSONObject(s);
            String name=null;
            String type=null;
            String location=null;

            try {
                name = jsonObject.getString("petName");
                type = jsonObject.getString("type");
                location = jsonObject.getString("location");
            } catch (Exception e){
                e.printStackTrace();
            }


            if (name != null && type != null && location !=null && mPetName!= null && mPetType!=null){
                mPetName.setText(name);
                mPetType.setText(type);
                mPetLocation.setText(location);
                return;
            }

        mPetName.setText("");
        mPetType.setText("");
        mPetLocation.setText("");


        } catch (Exception e){
            mPetName.setText("");
            mPetType.setText("");
            mPetLocation.setText("");
            e.printStackTrace();
        }
    }

    @Override
    protected String doInBackground(String... strings) {
        return NetworkUtils.getPetInfo(strings[0]);
    }
}
