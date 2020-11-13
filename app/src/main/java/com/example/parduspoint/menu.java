package com.example.parduspoint;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class menu extends AppCompatActivity {

    TextView mMatricTitle;
    FirebaseUser user;
    DatabaseReference databaseReference;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        user = FirebaseAuth.getInstance().getCurrentUser();
        mMatricTitle = findViewById(R.id.tv_matric);
        id = user.getUid();

        CardView cvProfil = (CardView) findViewById(R.id.cv_profile);
        CardView cvAktiviti = (CardView) findViewById(R.id.cv_aktiviti);
        CardView cvTotal = (CardView) findViewById(R.id.cv_total);
        CardView cvOrganisasi = (CardView) findViewById(R.id.cv_organisasi);
        CardView cvPeta = (CardView) findViewById(R.id.cv_peta);
        CardView cvKecemasan = (CardView) findViewById(R.id.cv_kecemasan);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String user_matric = dataSnapshot.child(id).child("Matric").getValue(String.class);
                mMatricTitle.setText(user_matric);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        cvProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profileIntent = new Intent(menu.this, profile.class);
                startActivity(profileIntent);
            }
        });
        cvOrganisasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent organisasiIntent = new Intent(menu.this, pentadbiran.class);
                startActivity(organisasiIntent);
            }
        });


        cvTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent total = new Intent(menu.this, totalMerit.class);
                startActivity(total);
            }
        });

        cvKecemasan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent call = new Intent(menu.this, phone.class);
                startActivity(call);
            }
        });

        cvPeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent map = new Intent(menu.this, map.class);
                startActivity(map);
            }
        });

        cvAktiviti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aktiviti = new Intent(menu.this, program_main.class);
                startActivity(aktiviti);
            }
        });

    }
}
