package com.example.feedr;

import android.app.ProgressDialog;
import android.os.AsyncTask;

public class PetUpdater extends AsyncTask<PetModel,Void,String> {
    ProgressDialog dialog;

//    public  petUpdater(){
//        dialog = progressDialog;
//    }

    @Override
    protected String doInBackground(PetModel... pets) {

        return NetworkUtils.updatePetInfo(pets[0]);
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
