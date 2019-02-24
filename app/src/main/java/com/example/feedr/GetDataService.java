package com.example.feedr;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;

public interface GetDataService {

    @GET("/pet?")
    Call<PetModel> getPet(@Field("id") String id);
}
