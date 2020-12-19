package com.example.sanmarapp;

import com.example.sanmarapp.model.UserCredentials;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CallApi {
    @GET("user_details_api/{user_email}/{user_password}")
    Call<UserCredentials> getUserCredentials(
            @Path("user_email") String user_email,
            @Path("user_password") String user_password
    );
}
