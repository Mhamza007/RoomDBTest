package com.example.roomdbtest;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.roomdbtest.model.dao.NoteDao;
import com.example.roomdbtest.model.database.NoteRoomDB;
import com.example.roomdbtest.model.entity.Note;

import java.util.List;

public class NodeViewModel extends AndroidViewModel {

    private static final String TAG = "NodeViewModel";
    private NoteDao noteDao;
    private NoteRoomDB noteRoomDB;
    private LiveData<List<Note>> mAllNotes;

    public NodeViewModel(@NonNull Application application) {
        super(application);

        noteRoomDB = NoteRoomDB.getDatabase(application);
        noteDao = noteRoomDB.noteDao();
        mAllNotes = noteDao.getAllNote();
    }

    public void insert(Note note) {
        new InsertAsyncTask(noteDao).execute(note);
    }

    public LiveData<List<Note>> getAllNotes(){
        return mAllNotes;
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        Log.i(TAG, "onCleared: ViewModel Destroyed");
    }

    private class InsertAsyncTask extends AsyncTask<Note, Void, Void> {

        NoteDao mNoteDao;

        public InsertAsyncTask(NoteDao mNoteDao) {
            this.mNoteDao = mNoteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            mNoteDao.insert(notes[0]);
            return null;
        }

    }
}
