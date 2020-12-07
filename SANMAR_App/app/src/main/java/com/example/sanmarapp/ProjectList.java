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

    ArrayList<ProjectArray> projList = new ArrayList<>();

    RecyclerView recyclerView;
    RecyclerView.Adapter projectAdapter;
    RecyclerView.LayoutManager layoutManager;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_EMAIL = "name";
    private static final String KEY_PASSWORD = "password";

    private AlertDialog.Builder alertDialogBuilder;


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

        projList.add(new ProjectArray(R.drawable.img1, "Project 1", "322/B, Gulshan, Dhaka.", "Category A1"));
        projList.add(new ProjectArray(R.drawable.img2, "Project 2", "443/A, Dhanmondi, Dhaka.", "Category A2"));
        projList.add(new ProjectArray(R.drawable.img3, "Project 3", "232/B, Banani, Dhaka.", "Category A6"));
        projList.add(new ProjectArray(R.drawable.img4, "Project 4", "878/B, Kakrail, Dhaka.", "Category B1"));
        projList.add(new ProjectArray(R.drawable.img5, "Project 5", "453/D, Mirpur, Dhaka.", "Category A11"));
        projList.add(new ProjectArray(R.drawable.img6, "Project 6", "544/F, Old Dhaka, Dhaka.", "Category C1"));
        projList.add(new ProjectArray(R.drawable.img7, "Project 7", "875/K, Jigatola, Dhaka.", "Category A1"));
        projList.add(new ProjectArray(R.drawable.img8, "Project 8", "36/A, Uttora, Dhaka.", "Category A1"));
        projList.add(new ProjectArray(R.drawable.img9, "Project 9", "34/C, Khilgaon, Dhaka.", "Category B2"));
        projList.add(new ProjectArray(R.drawable.img10, "Project 10", "394/E, Rampura, Dhaka.", "Category A1"));
        projList.add(new ProjectArray(R.drawable.img11, "Project 11", "345/G, Badda, Dhaka.", "Category B1"));
        projList.add(new ProjectArray(R.drawable.img12, "Project 12", "80/D, Niketon, Dhaka.", "Category A1"));


        projectAdapter = new ProjectAdapter(this, projList);
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