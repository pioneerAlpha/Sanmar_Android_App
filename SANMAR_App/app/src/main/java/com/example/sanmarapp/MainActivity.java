package com.example.sanmarapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sanmarapp.model.UserCredentials;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    EditText email, password;
    Button loginBtn;

    private ProgressDialog progressDialog;
    private AlertDialog.Builder alertDialogBuilder;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static String emailAddress;
    private static String passwordInput;

    private static CallApi callApi;

    UserCredentials userCredentials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        email = findViewById(R.id.email_id);
        password = findViewById(R.id.password_id);
        loginBtn = findViewById(R.id.login_btnID);

        String BASE_URL = "https://project-admin-dashboard.herokuapp.com/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        callApi = retrofit.create(CallApi.class);


        //for Shared Preferences
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        String checkVal = sharedPreferences.getString(KEY_EMAIL, null);

        if(checkVal != null){
            Intent intent = new Intent(getApplicationContext(), TabLayoutActivity.class);
            startActivity(intent);
            finish();
        }
        //

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailAddress = email.getText().toString().trim();
                passwordInput = password.getText().toString().trim();

                if(emailAddress.isEmpty()){
                    email.setError("Email Address Can't be Empty.");
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()) {
                    email.setError("Enter a Valid Email Address");
                    email.requestFocus();
                }
                else if(passwordInput.isEmpty()){
                    password.setError("Enter a Password");
                    password.requestFocus();
                }
                else if(passwordInput.length() < 5){
                    password.setError("Minimum Length of the Password Should be 5");
                }
                else {
                    if (isConnectedToInternet()) {
                        progressDialog = new ProgressDialog(MainActivity.this);
                        progressDialog.setCancelable(false);
                        progressDialog.setCanceledOnTouchOutside(false);
                        progressDialog.setTitle("Logging in");
                        progressDialog.setMessage("Checking Email and Password");
                        progressDialog.show();
                        verifyUser(emailAddress, passwordInput);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Please check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void verifyUser(String userEmail, String userPassword) {
        Call<UserCredentials> call = callApi.getUserCredentials(userEmail, userPassword);
        call.enqueue(new Callback<UserCredentials>() {
            @Override
            public void onResponse(Call<UserCredentials> call, Response<UserCredentials> response) {
                userCredentials = response.body();
                if (!response.isSuccessful()) {
                    // response not successful
                    progressDialog.dismiss();
                    Log.e(TAG, "onResponse: response not successful");
                    Toast.makeText(getApplicationContext(), "Wrong Email/Password or Network Error", Toast.LENGTH_SHORT).show();
                }
                else {
                    progressDialog.dismiss();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_EMAIL, userEmail);
                    editor.putString(KEY_PASSWORD, userPassword);
                    editor.apply();

                    Intent intent = new Intent(getApplicationContext(), TabLayoutActivity.class);
                    startActivity(intent);
                    finishAffinity();
                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserCredentials> call, Throwable t) {
                progressDialog.dismiss();
                Log.d(TAG, "onFailure: failed");
                t.printStackTrace();
            }
        });
    }

    public boolean isConnectedToInternet(){
        ConnectivityManager connectivity = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        //for setting title;
        alertDialogBuilder.setTitle("Exit?");

        //for setting message;
        alertDialogBuilder.setMessage("Do you want to exit?");

        //for setting positive button;
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //for exiting the app;
                finish();
                moveTaskToBack(true);
            }
        });

        //for setting negative button;
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //for exiting the app;
                dialogInterface.cancel();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}