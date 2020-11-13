package com.example.parduspoint;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.parduspoint.adapter.ActivityRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class program_main extends AppCompatActivity {

    LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_main);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        linearLayoutManager = new LinearLayoutManager(program_main.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        List<Program> allprogramInfor = getAllProgramInfor();
        ActivityRecyclerViewAdapter activityRecyclerViewAdapter = new ActivityRecyclerViewAdapter(program_main.this,allprogramInfor);
        recyclerView.setAdapter(activityRecyclerViewAdapter);
    }

    private List<Program> getAllProgramInfor(){
        List<Program> allProgram = new ArrayList<Program>();

        allProgram.add(new Program("Hari interaksi",R.drawable.hari_interaksi));
        allProgram.add(new Program("Speaker Corner",R.drawable.speaker_corner));
        allProgram.add(new Program("Demam Final",R.drawable.demam_final));
        allProgram.add(new Program("Malam Cinta Rasul",R.drawable.malam_cinta_rasul));
        allProgram.add(new Program("Sukem",R.drawable.sukem_activity));
        allProgram.add(new Program("Fester",R.drawable.fester));
        allProgram.add(new Program("Perarakan Hari Malaysia",R.drawable.perarakan_hari_malaysia));
        allProgram.add(new Program("Program NADI",R.drawable.nadi));
        allProgram.add(new Program("Iqra' Care",R.drawable.iqra_care));
        allProgram.add(new Program("Malam Gala KUO",R.drawable.malam_gala_kuo));

        return allProgram;
    }
}