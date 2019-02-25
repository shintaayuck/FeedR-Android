package com.example.feedr;

import android.net.Uri;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;


public class NetworkUtils {
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
    private static final String GET_PET_URL =  "http://fe3b42f8.ngrok.io/getpet?"; // Base URI for the Books API
    private static final String UPDATE_PET_URL = "http://fe3b42f8.ngrok.io/updatepet";
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
            if (buffer.length() == 0) {petJSONString = null;
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

    static String  updatePetInfo(PetModel pet){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String petJSONString = null;
        try {
            Uri builtURI = Uri.parse(UPDATE_PET_URL).buildUpon()
                    .build();

            URL requestURL = new URL(builtURI.toString());

            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);

            JSONObject postDataParams = new JSONObject();
            postDataParams.put("id", pet.getId());
            postDataParams.put("petName", pet.getPetName());
            postDataParams.put("type", pet.getType());
            if (pet.isAvailable()){
                postDataParams.put("isAvailable", 1);
            } else {
                postDataParams.put("isAvailable", 0);
            }
            //TODO:ILANGIN DEBUG
            postDataParams.put("lastFed", pet.getLastFed());
            postDataParams.put("highScore", pet.getHighScore());
            postDataParams.put("latitude",pet.getLatitude());
            postDataParams.put("longitude",pet.getLongitude());


            OutputStream os = urlConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();

            int responseCode=urlConnection.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {

                BufferedReader in=new BufferedReader(
                        new InputStreamReader(
                                urlConnection.getInputStream()));
                StringBuffer sb = new StringBuffer("");
                String line="";

                while((line = in.readLine()) != null) {

                    sb.append(line);
                    break;
                }

                in.close();
                petJSONString = sb.toString();

            }
            else {
                petJSONString = new String("false : "+responseCode);
            }

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

    public static String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }
}
