package com.example.roomdbtest.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.roomdbtest.model.entity.Note;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    void insert(Note note);

    @Query("SELECT * FROM notes")
    LiveData<List<Note>> getAllNote();

}
