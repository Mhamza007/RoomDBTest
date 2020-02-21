package com.example.roomdbtest.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.roomdbtest.R;

public class NewNoteActivity extends AppCompatActivity {

    public static final String NOTE_ADDED = "new_note";

    private EditText etNewNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        etNewNote = findViewById(R.id.et_new_note);

        Button button = findViewById(R.id.bAdd);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent resultIntent = new Intent();

                if (TextUtils.isEmpty(etNewNote.getText().toString().trim())) {
                    setResult(RESULT_CANCELED, resultIntent);
                } else {
                    String note = etNewNote.getText().toString().trim();
                    resultIntent.putExtra(NOTE_ADDED, note);
                    setResult(RESULT_OK, resultIntent);
                }
                finish();
            }
        });
    }
}
