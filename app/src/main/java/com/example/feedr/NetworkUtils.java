package com.example.feedr;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class NetworkUtils {
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
    private static final String GET_PET_URL =  "http://c3104bdd.ngrok.io/getpet?"; // Base URI for the Books API
    private static final String QUERY_PARAM = "id"; // Parameter for the search string
    private static final String PRINT_TYPE = "printType";   // Parameter to filter by print type

    static String getPetInfo(String id){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String petJSONString = null;
        try {
            Uri builtURI = Uri.parse(GET_PET_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, id)
                    .build();

            URL requestURL = new URL(builtURI.toString());

            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {

                buffer.append(line + "\n");
            }
            if (buffer.length() == 0) {
                return null;
            }
            petJSONString = buffer.toString();

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Log.d(LOG_TAG, petJSONString);
            return petJSONString;
        }
    }
}
