package com.draco.noteapp.Adapters;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.draco.noteapp.Models.NotesModel;
import com.draco.noteapp.R;
import com.draco.noteapp.databinding.ItemNotesLayoutBinding;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.viewHolder> {

    List<NotesModel.NotesBean> notesBeans;
    public NotesAdapter(List<NotesModel.NotesBean> notesModel) {
        this.notesBeans = notesModel;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemNotesLayoutBinding binding = ItemNotesLayoutBinding.inflate(layoutInflater, parent, false);
        return new viewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.binding.tvTitle.setText(notesBeans.get(position).title);
        holder.binding.tvContent.setText(notesBeans.get(position).content);

        if (notesBeans.get(position).bgColor.toString().equals("#FF8989")){
            holder.binding.MaterialBackground.setBackgroundColor(holder.itemView.getResources().getColor(R.color.red));
        } else if (notesBeans.get(position).bgColor.toString().equals("#60CEFF")){
            holder.binding.MaterialBackground.setBackgroundColor(holder.itemView.getResources().getColor(R.color.blue));
        } else {
            holder.binding.MaterialBackground.setBackgroundColor(holder.itemView.getResources().getColor(R.color.green));
        }
    }

    @Override
    public int getItemCount() {
        return notesBeans == null ? 0  : notesBeans.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {
        private ItemNotesLayoutBinding binding;
        public viewHolder(ItemNotesLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
