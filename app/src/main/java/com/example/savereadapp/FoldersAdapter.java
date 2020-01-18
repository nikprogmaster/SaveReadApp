package com.example.savereadapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FoldersAdapter extends RecyclerView.Adapter<FoldersAdapter.FolderHolder> {


    List<String> foldersList;

    FoldersAdapter(List<String> foldersList) {
        this.foldersList = foldersList;
    }

    @NonNull
    @Override
    public FolderHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.storage_element, viewGroup, false);
        return new FolderHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FolderHolder folderHolder, int i) {
        folderHolder.bindView(foldersList.get(i));
    }

    @Override
    public int getItemCount() {
        return foldersList.size();
    }

    class FolderHolder extends RecyclerView.ViewHolder {

        TextView name;

        public FolderHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.folder);
        }

        private void bindView(String folderName) {
            name.setText(folderName);
        }
    }
}
