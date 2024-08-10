package com.pulsetech.notdefteri;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

        private RecyclerView mRecyclerView;
        private NotesAdapter notesAdapter;
        private ArrayList<Notes> notesArray = new ArrayList<>();
        private FirebaseFirestore db;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            mRecyclerView = findViewById(R.id.recyclerView);
            db = FirebaseFirestore.getInstance();
            notesAdapter = new NotesAdapter(notesArray, this);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView.setAdapter(notesAdapter);


            db.collection("notes").get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Notes note = document.toObject(Notes.class);
                        notesArray.add(note);
                    }
                    notesAdapter.notifyDataSetChanged();
                } else {

                }
            });



            findViewById(R.id.addNotes).setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, addNoteActivity.class);
                startActivity(intent);
            });
        }
    }
