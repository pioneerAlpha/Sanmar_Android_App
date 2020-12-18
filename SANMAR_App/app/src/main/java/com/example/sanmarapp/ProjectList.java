package com.example.sanmarapp;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class ProjectList extends Fragment {

    ArrayList<ProjectArray> projList = new ArrayList<>();

    RecyclerView recyclerView;
    RecyclerView.Adapter projectAdapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle){
        View view = inflater.inflate(R.layout.activity_project_list, viewGroup, false);
        init(view);
        return view;

    }

    private void init(View view) {

        recyclerView = view.findViewById(R.id.recview_id);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);


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

        projectAdapter = new ProjectAdapter(getContext(), projList);
        recyclerView.setAdapter(projectAdapter);

    }
}