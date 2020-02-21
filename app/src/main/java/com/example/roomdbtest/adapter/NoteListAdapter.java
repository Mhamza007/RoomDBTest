package com.example.roomdbtest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdbtest.R;
import com.example.roomdbtest.model.entity.Note;

import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.ViewHolder> {

    private final LayoutInflater layoutInflater;
    private Context context;
    private List<Note> mNotes;

    public NoteListAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
        context = this.context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.list_items, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (mNotes != null) {
            Note note = mNotes.get(position);
            holder.setData(note.getNote(), position);
//            holder.setListeners();
        } else {
            // Covers the case of data not being ready yet.
            holder.noteItemView.setText(R.string.no_note);
        }
    }

    @Override
    public int getItemCount() {
        if (mNotes != null) {
            return mNotes.size();
        } else return 0;
    }

    public void setNotes(List<Note> notes){
        mNotes = notes;
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView noteItemView;
        private int mPosition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            noteItemView = itemView.findViewById(R.id.txvNote);
        }

        public void setData(String note, int position) {
            noteItemView.setText(note);
            mPosition = position;
        }

    }
}
