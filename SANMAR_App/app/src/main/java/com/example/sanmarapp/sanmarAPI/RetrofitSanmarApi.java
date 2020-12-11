package com.example.sanmarapp.sanmarAPI;

import com.example.sanmarapp.model.UserCredentials;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitSanmarApi {
    // user email, password
    @POST("authenticate_user_api/{user_email}/{user_password}")
    Call<UserCredentials> postUserCredentials(
            @Path("user_email") String user_email,
            @Path("user_password") String user_password
    );
//    Call<UserCredentials> postUserCredentials(@Body UserCredentials userCredentials);

    @GET("authenticate_user_api/{user_email}/{user_password}")
    Call<UserCredentials> getUserCredentials(
            @Path("user_email") String user_email,
            @Path("user_password") String user_password
    );
}
