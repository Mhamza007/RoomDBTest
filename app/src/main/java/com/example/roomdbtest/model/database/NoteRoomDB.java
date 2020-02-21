package com.example.roomdbtest.model.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.roomdbtest.model.dao.NoteDao;
import com.example.roomdbtest.model.entity.Note;

@Database(entities = Note.class, version = 1)
public abstract class NoteRoomDB extends RoomDatabase {

    public abstract NoteDao noteDao();

    public static volatile NoteRoomDB noteRoomInstance;

    public static NoteRoomDB getDatabase(final Context context) {
        if (noteRoomInstance == null) {
            synchronized (NoteRoomDB.class) {
                if (noteRoomInstance == null) {
                    noteRoomInstance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            NoteRoomDB.class,
                            "note_database"
                    ).build();
                }
            }
        }
        return noteRoomInstance;
    }

}
