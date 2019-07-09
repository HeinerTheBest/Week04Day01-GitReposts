package com.mobileapps.week04day01getgitwithinternet.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobileapps.week04day01getgitwithinternet.R;
import com.mobileapps.week04day01getgitwithinternet.model.gitresponse.Item;

import java.util.ArrayList;
import java.util.List;

public class RepostListAdapter extends RecyclerView.Adapter<RepostListAdapter.ViewHolder>
{
    List<Item> items;

    public RepostListAdapter(List<Item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.repository_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        Item item;
        item = items.get(position);
        holder.tvTitle.setText(item.getName());
        String lenguage = "Lenguage: "+item.getLanguage();
        holder.tvLenguage.setText(lenguage);
        String score = "Score: "+item.getScore();
        holder.tvScore.setText(score);
        String watcher = item.getWatchers()+"\n Watcher(s)";
        holder.tvWatchers.setText(watcher);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvTitle, tvLenguage, tvScore, tvWatchers;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvLenguage = itemView.findViewById(R.id.tvLenguage);
            tvScore = itemView.findViewById(R.id.tvScore);
            tvWatchers = itemView.findViewById(R.id.tvWatchers);
        }
    }
}
