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

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {

    Context context;
    int [] projectImage;
    String [] projectName;
    String [] projectAddress;
    String [] projectCategory;

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

    public ProjectAdapter(Context context, int [] projectImage, String [] projectName, String [] projectAddress, String [] projectCategory){
        this.context = context;
        this.projectImage = projectImage;
        this.projectName = projectName;
        this.projectAddress = projectAddress;
        this.projectCategory = projectCategory;
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
        holder.projImage.setImageResource(projectImage[position]);
        holder.projName.setText(projectName[position]);
        holder.projAddress.setText(projectAddress[position]);
        holder.projCat.setText(projectCategory[position]);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, projectName[position], Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProjectDetails.class);
                intent.putExtra("image", projectImage[position]);
                intent.putExtra("name", projectName[position]);
                intent.putExtra("address", projectAddress[position]);
                intent.putExtra("category", projectCategory[position]);

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return projectName.length;
    }

}
