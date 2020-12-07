package com.example.sanmarapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {

    Context context;
    ArrayList<ProjectArray> projList;

    public ProjectAdapter(Context context, ArrayList<ProjectArray> projList) {
        this.context = context;
        this.projList = projList;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView projImage;
        TextView projName;
        TextView projAddress;
        TextView projCat;
        Button itemBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            projImage = itemView.findViewById(R.id.project_imageID);
            projName = itemView.findViewById(R.id.project_nameID);
            projAddress = itemView.findViewById(R.id.project_addressID);
            projCat = itemView.findViewById(R.id.project_catID);
            itemBtn = itemView.findViewById(R.id.item_button);
        }
    }

    @NonNull
    @Override
    public ProjectAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectAdapter.ViewHolder holder, final int position) {

        final ProjectArray projectArray = projList.get(position);

        holder.projImage.setImageResource(projectArray.getProjectImage());
        holder.projName.setText(projectArray.getProjectName());
        holder.projAddress.setText(projectArray.getProjectAddress());
        holder.projCat.setText(projectArray.getProjectCategory());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, projectArray.getProjectName(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProjectDetails.class);
                intent.putExtra("image", projectArray.getProjectImage());
                intent.putExtra("name", projectArray.getProjectName());
                intent.putExtra("address", projectArray.getProjectAddress());
                intent.putExtra("category", projectArray.getProjectCategory());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return projList.size();
    }
}
