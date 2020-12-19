package com.example.sanmarapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sanmarapp.model.UserCredentials;

public class ProfileActivity extends AppCompatActivity {

    ImageView profileImage;
    TextView profileName;
    TextView profileEmail;

    UserCredentials userCredentials;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_EMAIL = "email";

    private String userEmail;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("User Details");

        profileImage = findViewById(R.id.profile_imgID);
        profileName = findViewById(R.id.profile_nameID);
        profileEmail = findViewById(R.id.profile_emailID);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        userEmail = sharedPreferences.getString(KEY_EMAIL, null);

        if(userEmail != null){
            profileEmail.setText("Email: "+userEmail);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}