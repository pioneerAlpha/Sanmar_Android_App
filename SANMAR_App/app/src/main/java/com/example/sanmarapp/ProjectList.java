package com.example.sanmarapp;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class ProjectList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter projectAdapter;
    RecyclerView.LayoutManager layoutManager;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_EMAIL = "name";
    private static final String KEY_PASSWORD = "password";

    private AlertDialog.Builder alertDialogBuilder;

    int [] projectImage = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6, R.drawable.img7, R.drawable.img8, R.drawable.img9, R.drawable.img10, R.drawable.img11, R.drawable.img12};
    String [] projectName = {"Project 1", "Project 2", "Project 3", "Project 4", "Project 5", "Project 6", "Project 7", "Project 8", "Project 9", "Project 10", "Project 11", "Project 12"};
    String [] projectAddress = {"322/B, Gulshan, Dhaka.", "322/B, Dhanmondi, Dhaka.", "322/B, Mohakhali, Dhaka.", "322/B, Khilgaon, Dhaka.", "322/B, Khulna.", "322/B, Comilla.", "322/B, Jamalpur.", "322/B, Bikrampur.", "322/B, Rajshahi.", "322/B, Chittagong.", "322/B, Barishal.", "322/B, Khulna."};
    String [] projectCategory = {"Category A1", "Category A2", "Category A3", "Category A4", "Category A5", "Category A6", "Category A7", "Category A8", "Category A9", "Category A10", "Category A11", "Category A12"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Project List");

        recyclerView = findViewById(R.id.recview_id);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        projectAdapter = new ProjectAdapter(this, projectImage, projectName, projectAddress, projectCategory);
        recyclerView.setAdapter(projectAdapter);


    }

    @Override
    public void onBackPressed() {
        alertDialogBuilder = new AlertDialog.Builder(ProjectList.this);
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
        if(id == R.id.menu_logout){

            alertDialogBuilder = new AlertDialog.Builder(ProjectList.this);
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
                    editor.commit();
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