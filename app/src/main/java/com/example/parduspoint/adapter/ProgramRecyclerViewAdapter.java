package com.example.parduspoint.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parduspoint.Activity;
import com.example.parduspoint.R;

import java.util.List;

public class ProgramRecyclerViewAdapter extends RecyclerView.Adapter<ProgramRecyclerViewAdapter.ActivityViewHolder>{
    public List<Activity> programList;
    private Context context;

    public ProgramRecyclerViewAdapter(Context context, List<Activity> programList){
        this.context = context;
        this.programList = programList;
    }

    @NonNull
    @Override
    public ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View totalmerit = LayoutInflater.from(parent.getContext()).inflate(R.layout.totalmerit,null);
        ActivityViewHolder programVH = new ActivityViewHolder(totalmerit);
        return programVH;
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityViewHolder holder, int position) {


        holder.tvActivityName.setText(programList.get(position).getName());
        //holder.tvActivityMarks.setText(programList.get(position).getActivityMarks());

    }

    public int getItemCount(){ return programList.size();}

    public void setAdapter(ProgramRecyclerViewAdapter programRecyclerViewAdapter) {
    }

    public class ActivityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView tvActivityName;
        public TextView tvActivityMarks;

        public ActivityViewHolder(View itemView){
            super(itemView);
            tvActivityName = itemView.findViewById(R.id.tv_activity);
            tvActivityMarks = itemView.findViewById(R.id.tv_marks);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(),"Activity Name: " + programList.get(getAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();

//            Intent intent;
//            intent = new Intent(view.getContext(), upload.class );
//            intent.putExtra("ProgramName",programList.get(getAdapterPosition()).getName());
//            view.getContext().startActivity(intent);
        }
    }
}
