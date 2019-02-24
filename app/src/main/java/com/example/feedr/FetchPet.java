package com.example.feedr;

import android.os.AsyncTask;
import android.widget.TextView;

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
    }

    @Override
    protected String doInBackground(String... strings) {
        return NetworkUtils.getPetInfo(strings[0]);
    }
}
