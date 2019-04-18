package com.example.feedr;

import android.os.AsyncTask;


public class FeedPetAsync extends AsyncTask<PetModel,Void,String> {


    @Override
    protected String doInBackground(PetModel... pets) {
        return NetworkUtils.feedPet(pets[0]);
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
