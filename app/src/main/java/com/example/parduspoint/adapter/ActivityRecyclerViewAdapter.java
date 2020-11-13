package com.example.parduspoint.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parduspoint.Program;
import com.example.parduspoint.R;
import com.example.parduspoint.upload;

import java.util.List;

public class ActivityRecyclerViewAdapter extends RecyclerView.Adapter<ActivityRecyclerViewAdapter.ActivityViewHolder> {

    public List<Program> programList;
    private Context context;

    public ActivityRecyclerViewAdapter (Context context, List<Program> programList){
        this.context = context;
        this.programList = programList;
    }

    @NonNull
    @Override
    public ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View activity_row = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_row,null);
        ActivityViewHolder activityVH = new ActivityViewHolder(activity_row);
        return activityVH;
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityViewHolder holder, int position) {


        holder.tvActivityName.setText(programList.get(position).getName());
        holder.imgActivity.setImageResource(programList.get(position).getImage());
        //holder.tvActivityMarks.setText(programList.get(position).getActivityMarks());

    }

    public int getItemCount(){ return programList.size();}

    public class ActivityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView tvActivityName;
        public TextView tvActivityMarks;
        public ImageView imgActivity;

        public ActivityViewHolder(View itemView){
            super(itemView);
            tvActivityName = itemView.findViewById(R.id.tv_program);
            imgActivity = itemView.findViewById(R.id.img_program);
            //tvActivityMarks = itemView.findViewById(R.id.tv_marks);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(),"Activity Name: " + programList.get(getAdapterPosition()).getName(),Toast.LENGTH_SHORT).show();

            Intent intent;
            intent = new Intent(view.getContext(), upload.class );
            intent.putExtra("ProgramName",programList.get(getAdapterPosition()).getName());
            view.getContext().startActivity(intent);
        }
    }
}