package com.example.sanmarapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.GenericLifecycleObserver;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProjectDetails extends AppCompatActivity {

    ImageView imgDetails;

    TextView nameDetails;
    TextView addressDetails;
    TextView catDetails;
    Button playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Project Details");

        imgDetails = findViewById(R.id.image_details);
        nameDetails = findViewById(R.id.name_details);
        addressDetails = findViewById(R.id.address_details);
        catDetails = findViewById(R.id.category_details);
        playButton = findViewById(R.id.play_button);

        imgDetails.setImageResource(getIntent().getIntExtra("image", 0));
        nameDetails.setText(getIntent().getStringExtra("name"));
        addressDetails.setText(getIntent().getStringExtra("address"));
        catDetails.setText(getIntent().getStringExtra("category"));

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PlayVideoActivity.class);
                startActivity(intent);
            }
        });

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