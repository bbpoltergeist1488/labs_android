package com.example.timer.Holders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.timer.Models.RoundModel;
import com.example.timer.R;

public class RoundHolder extends RecyclerView.ViewHolder {
    TextView roundName;
    TextView roundTime;

    public RoundHolder(View itemView) {
        super(itemView);
        roundName = itemView.findViewById(R.id.round_name_item);
        roundTime = itemView.findViewById(R.id.round_time_item);

    }

    public void bind(RoundModel round, Context context) {
        if (round.roundName != null && !round.roundName.isEmpty())
            roundName.setText(round.roundName);
        roundTime.setText(String.format(context.getString(R.string.format_seconds), round.roundTime));
    }
}
