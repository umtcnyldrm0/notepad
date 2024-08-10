package com.pulsetech.notdefteri;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.pulsetech.notdefteri.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class addNoteActivity extends AppCompatActivity {

    private EditText inputTitle, inputDescription;
    private Button saveButton;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        inputTitle = findViewById(R.id.inputTextTitle);
        inputDescription = findViewById(R.id.inputTextDescription);
        saveButton = findViewById(R.id.buttonSaveNote);


        db = FirebaseFirestore.getInstance();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleString = inputTitle.getText().toString();
                String descriptionString = inputDescription.getText().toString();


                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
                String currentDateandTime = sdf.format(new Date());


                Map<String, Object> note = new HashMap<>();
                note.put("title", titleString);
                note.put("description", descriptionString);
                note.put("date", currentDateandTime);


                db.collection("notes")
                        .add(note)
                        .addOnSuccessListener(documentReference -> {

                            finish();
                        })
                        .addOnFailureListener(e -> {

                        });
            }
        });
    }
}
