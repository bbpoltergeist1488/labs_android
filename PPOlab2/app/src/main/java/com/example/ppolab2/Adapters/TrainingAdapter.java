package com.example.ppolab2.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.ppolab2.Activities.ActivityWithTimers;
import com.example.ppolab2.Activities.TimerActivity;
import com.example.ppolab2.Activities.TimerSpecificsActivity;
import com.example.ppolab2.R;
import com.example.ppolab2.SqlDatabase.DeleteHelper;
import com.example.ppolab2.Timer;

import java.util.List;

public class TrainingAdapter extends ArrayAdapter<String> {
    private LayoutInflater inflater;
    private int layout;
    private List<String> trainingNames;
    private Context context;

    public TrainingAdapter(Context context, int resource, List<String> trainingNames) {
        super(context, resource, trainingNames);
        this.trainingNames = trainingNames;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }
    @SuppressLint("SetTextI18n")
    public View getView(final int position, View convertView, ViewGroup parent) {

        final TrainingAdapter.ViewHolder viewHolder;
        if(convertView==null){
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new TrainingAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (TrainingAdapter.ViewHolder) convertView.getTag();
        }
        final String training = trainingNames.get(position);
        viewHolder.trainingTv.setText((position+1) +". "+training);
        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(context instanceof TimerActivity){
                    ((TimerActivity)context).setNewTraining(position);
                }
            }
        });
        return convertView;

    }
    private class ViewHolder {
        final LinearLayout layout;
        final TextView trainingTv;
        ViewHolder(View view){
            layout = view.findViewById(R.id.trainingLayout);
            trainingTv = (TextView) view.findViewById(R.id.trainingTv);
        }
    }
}
