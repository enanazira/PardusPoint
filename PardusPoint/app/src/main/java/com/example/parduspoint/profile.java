package com.example.parduspoint;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profile extends AppCompatActivity {
    TextView mName, mMatric, mEmail, mPhone, mFaculty, mRoom, mClub, mPosition;
    FirebaseAuth mFirebaseAuth;
    FirebaseUser user;
    DatabaseReference databaseReference;
    String id;

    public profile() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        user = FirebaseAuth.getInstance().getCurrentUser();
        id = user.getUid();


        mMatric = findViewById(R.id.tv_matric2);
        mName = findViewById(R.id.tv_name);
        mEmail = findViewById(R.id.tv_email);
        mPhone = findViewById(R.id.tv_phone);
        mFaculty = findViewById(R.id.tv_faculty);
        mRoom = findViewById(R.id.tv_room);
        mClub = findViewById(R.id.tv_club);
        mPosition = findViewById(R.id.tv_position);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String user_matric = dataSnapshot.child(id).child("Matric").getValue(String.class);
                String user_name = dataSnapshot.child(id).child("Name").getValue(String.class);
                String user_email = dataSnapshot.child(id).child("Email").getValue(String.class);
                String user_phone = dataSnapshot.child(id).child("Phone No").getValue(String.class);
                String user_faculty = dataSnapshot.child(id).child("Faculty").getValue(String.class);
                String user_room = dataSnapshot.child(id).child("Room No").getValue(String.class);
                String user_club = dataSnapshot.child(id).child("Club").getValue(String.class);
                String user_position = dataSnapshot.child(id).child("Position").getValue(String.class);

                mMatric.setText(user_matric);
                mName.setText(user_name);
                mEmail.setText(user_email);
                mPhone.setText(user_phone);
                mFaculty.setText(user_faculty);
                mRoom.setText(user_room);
                mClub.setText(user_club);
                mPosition.setText(user_position);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}