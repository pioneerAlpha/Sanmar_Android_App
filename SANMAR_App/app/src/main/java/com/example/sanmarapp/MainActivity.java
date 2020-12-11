package com.example.sanmarapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sanmarapp.model.UserCredentials;
import com.example.sanmarapp.sanmarAPI.RetrofitSanmarApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    EditText email, password;
    Button loginBtn;

    private AlertDialog.Builder alertDialogBuilder;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_EMAIL = "name";
    private static final String KEY_PASSWORD = "password";
    private static final String BASE_URL = "http://3.21.122.37/";

    private static RetrofitSanmarApi sanmarApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getSupportActionBar().hide();

        email = findViewById(R.id.email_id);
        password = findViewById(R.id.password_id);
        loginBtn = findViewById(R.id.login_btnID);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        sanmarApiService = retrofit.create(RetrofitSanmarApi.class);

        //for Shared Preferences
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        String checkVal = sharedPreferences.getString(KEY_EMAIL, null);

        if(checkVal != null){
            Intent intent = new Intent(getApplicationContext(), ProjectList.class);
            startActivity(intent);
            finish();
        }
        //

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailAddress = email.getText().toString().trim();
                String passwordInput = password.getText().toString().trim();

                if(emailAddress.isEmpty()){
                    email.setError("Email Address Can't be Empty.");
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()) {
                    email.setError("Enter a Valid Email Address");
                    email.requestFocus();
                    return;
                }
                else if(passwordInput.isEmpty()){
                    password.setError("Enter a Password");
                    password.requestFocus();
                    return;
                }
                else if(passwordInput.length() < 5){
                    password.setError("Minimum Length of the Password Should be 5");
                }
                else {
                    authenticateUser(emailAddress, passwordInput);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_EMAIL, emailAddress);
                    editor.putString(KEY_PASSWORD, passwordInput);
                    editor.apply();

                    Intent intent = new Intent(getApplicationContext(), ProjectList.class);
                    startActivity(intent);
                    finishAffinity();
                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void authenticateUser(String userEmail, String userPassword) {
//        UserCredentials userCredentials = new UserCredentials(userEmail, userPassword);
//        Call<UserCredentials> call = sanmarApiService.postUserCredentials(userCredentials);

        Call<UserCredentials> call = sanmarApiService.getUserCredentials(userEmail, userPassword);
        call.enqueue(new Callback<UserCredentials>() {
            @Override
            public void onResponse(Call<UserCredentials> call, Response<UserCredentials> response) {
                if (!response.isSuccessful()) {
                    // response not successful
                    Log.e(TAG, "onResponse: response not successful");
                    return;
                }
                Log.d(TAG, "onResponse: response code: " + response.code());
            }

            @Override
            public void onFailure(Call<UserCredentials> call, Throwable t) {
                Log.d(TAG, "onFailure: failed");
                t.printStackTrace();
            }
        });
/*
        Call<UserCredentials> call = sanmarApiService.postUserCredentials(userEmail, userPassword);
        call.enqueue(new Callback<UserCredentials>() {
            @Override
            public void onResponse(Call<UserCredentials> call, Response<UserCredentials> response) {
                if (!response.isSuccessful()) {
                    // response not successful
                    Log.e(TAG, "onResponse: response not successful");
                    return;
                }
                Log.d(TAG, "onResponse: response code: " + response.code());
            }

            @Override
            public void onFailure(Call<UserCredentials> call, Throwable t) {
                Log.d(TAG, "onFailure: failed");
            }
        });
*/
    }

    @Override
    public void onBackPressed() {
        alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        //for setting title;
        alertDialogBuilder.setTitle("Exit?");

        //for setting message;
        alertDialogBuilder.setMessage("Do you want to exit?");

        //for setting icon;
        alertDialogBuilder.setIcon(R.drawable.tips);


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