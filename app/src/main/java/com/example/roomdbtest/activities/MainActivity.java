package com.example.roomdbtest.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.roomdbtest.NodeViewModel;
import com.example.roomdbtest.adapter.NoteListAdapter;
import com.example.roomdbtest.R;
import com.example.roomdbtest.model.entity.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;

import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int NEW_NOTE_ACTIVITY_REQUEST = 1;

    private NodeViewModel noteViewModel;

    private NoteListAdapter noteListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        noteListAdapter = new NoteListAdapter(this);
        recyclerView.setAdapter(noteListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewNoteActivity.class);
                startActivityForResult(intent, NEW_NOTE_ACTIVITY_REQUEST);
            }
        });

        noteViewModel = ViewModelProviders.of(this).get(NodeViewModel.class);

        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                noteListAdapter.setNotes(notes);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_NOTE_ACTIVITY_REQUEST && resultCode == RESULT_OK) {

            //insert note
            final String note_id = UUID.randomUUID().toString();
            Note note = new Note(note_id, data.getStringExtra(NewNoteActivity.NOTE_ADDED));
            noteViewModel.insert(note);

            Toast.makeText(this, R.string.saved, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.not_saved, Toast.LENGTH_SHORT).show();
        }
    }
}
