package com.example.sanmarapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class LuxuryActivity extends Fragment {

    TextView luxTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle){
        View view = inflater.inflate(R.layout.activity_luxury, viewGroup, false);
        init(view);
        return view;

    }

    private void init(View view) {
        luxTextView = view.findViewById(R.id.luxury_id);
    }
}