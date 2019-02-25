package com.example.feedr;

import android.os.AsyncTask;

public class PetUpdater extends AsyncTask<PetModel,Void,String> {


    @Override
    protected String doInBackground(PetModel... pets) {

        return NetworkUtils.updatePetInfo(pets[0]);
    }
}
