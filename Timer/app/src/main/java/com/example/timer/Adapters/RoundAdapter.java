package com.example.timer.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timer.Holders.RoundHolder;
import com.example.timer.Models.RoundModel;
import com.example.timer.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class RoundAdapter extends RecyclerView.Adapter<RoundHolder> {

    private List<RoundModel> rounds = new ArrayList<>();
    Context context;

    public void AddAll(Collection<RoundModel> rounds) {
        this.rounds.addAll(rounds);
        notifyDataSetChanged();
    }

    public void DeleteByIndex(int index) {
        if (!rounds.isEmpty()) {
            rounds.remove(index);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RoundHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.round_item, parent, false);
        context = view.getContext();
        return new RoundHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoundHolder holder, int position) {
        holder.bind(rounds.get(position),context);
    }

    @Override
    public int getItemCount() {
        return rounds.size();
    }
}