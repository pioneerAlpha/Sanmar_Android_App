package com.example.sanmarapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.sanmarapp.model.UserCredentials;
import com.google.android.material.tabs.TabLayout;

public class TabLayoutActivity extends AppCompatActivity {

    TabLayout tab;
    ViewPager viewPager;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private String userEmail;

    private AlertDialog.Builder alertDialogBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        userEmail = sharedPreferences.getString(KEY_EMAIL, null);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //for removing shadow below action bar
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle("Sanmar");

        tab = findViewById(R.id.tab);
        viewPager = findViewById(R.id.viewpager);

        tab.setupWithViewPager(viewPager);
        initViewPager();

    }
    private void initViewPager(){
        ViewpagerAdapter viewpagerAdapter = new ViewpagerAdapter(getSupportFragmentManager());
        viewpagerAdapter.addFragment(new ProjectList(),"Project List");
        viewpagerAdapter.addFragment(new LuxuryActivity(), "Luxury");
        viewPager.setAdapter(viewpagerAdapter);
    }


    @Override
    public void onBackPressed() {
        alertDialogBuilder = new AlertDialog.Builder(TabLayoutActivity.this);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
        }
        if (id == R.id.menu_profile) {
            if(userEmail != null) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_EMAIL, userEmail);
                editor.apply();

                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
            }
        }
        if (id == R.id.menu_logout) {

            alertDialogBuilder = new AlertDialog.Builder(TabLayoutActivity.this);
            //for setting title;
            alertDialogBuilder.setTitle("Logout?");

            //for setting message;
            alertDialogBuilder.setMessage("Do you want to logout?");


            //for setting positive button;
            alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //for logout from the app;
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();
                    editor.apply();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(getApplicationContext(), "Logout Successful", Toast.LENGTH_SHORT).show();
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
        return super.onOptionsItemSelected(item);
    }
}