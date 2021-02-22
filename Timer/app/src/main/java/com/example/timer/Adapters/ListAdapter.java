package com.example.timer.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timer.Database.Database;
import com.example.timer.Holders.ListHolder;
import com.example.timer.Models.ItemModel;
import com.example.timer.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListHolder> {

    Context context;
    private List<ItemModel> Items = new ArrayList<>();

    @Override
    public int getItemCount() {
        return Items.size();
    }

    public void Clear() {
        Items.clear();
        notifyDataSetChanged();
    }

    public void AddAll(Collection<ItemModel> items) {
        Items.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_holder, parent, false);
        context = view.getContext();
        return new ListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListHolder holder, int position) {
        holder.bind(Items.get(position), context);
        holder.DeleteButton.setOnClickListener(v -> {
            Database.getInstance(context).Dao().Delete(Items.get(position));
            Items.remove(position);
            notifyDataSetChanged();

        });
    }


}