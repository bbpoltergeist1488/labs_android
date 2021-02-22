package com.example.timer.Holders;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timer.Activities.CreateActivity;
import com.example.timer.Activities.PlayActivity;
import com.example.timer.Models.ItemModel;
import com.example.timer.R;

public class ListHolder extends RecyclerView.ViewHolder {
    public TextView ItemName;
    public ImageButton DeleteButton;
    public ImageButton RunButton;
    public ImageButton EditButton;
    public ConstraintLayout layout;

    public ListHolder(View itemView) {
        super(itemView);
        ItemName = itemView.findViewById(R.id.item_name);
        DeleteButton = itemView.findViewById(R.id.delete_button);
        RunButton = itemView.findViewById(R.id.run_button);
        EditButton = itemView.findViewById(R.id.edit_button);
        layout = itemView.findViewById(R.id.holder_layout);

    }

    public void bind(ItemModel item, Context context) {
        if (item.ItemName != null && !item.ItemName.isEmpty())
            ItemName.setText(item.ItemName);


        layout.setBackgroundColor(item.Color);
        RunButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, PlayActivity.class);
            intent.putExtra("item", item);
            context.startActivity(intent);
        });

        EditButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, CreateActivity.class);
            intent.putExtra("item", item);
            context.startActivity(intent);
        });
    }
}