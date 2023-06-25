package com.example.studentkit.Note;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentkit.Model.ModelNote;
import com.example.studentkit.NotesActivity;
import com.example.studentkit.databinding.ItemNoteBinding;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    Context context;
    List<ModelNote> data = new ArrayList<>();

    public NoteAdapter(Context context, List<ModelNote> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNoteBinding binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelNote mh = data.get(position);
        holder.bind.judulEvent.setText(mh.getTitle());
        holder.bind.deskripsi.setText(mh.getDescription());
        holder.bind.cv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ((NotesActivity) context).deleteNote(String.valueOf(mh.getId()));
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemNoteBinding bind;
        public ViewHolder(@NonNull ItemNoteBinding i) {
            super(i.getRoot());
            bind = i;
        }
    }
}
