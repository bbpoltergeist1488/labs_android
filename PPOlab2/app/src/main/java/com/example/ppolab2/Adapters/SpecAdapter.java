package com.example.ppolab2.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ppolab2.Activities.ActivityWithTimers;
import com.example.ppolab2.Activities.MainActivity;
import com.example.ppolab2.Activities.TimerActivity;
import com.example.ppolab2.R;
import com.example.ppolab2.SqlDatabase.DeleteHelper;
import com.example.ppolab2.Timer;
import com.example.ppolab2.Activities.TimerSpecificsActivity;

import java.util.List;

public class SpecAdapter extends ArrayAdapter<Timer> {
    private LayoutInflater inflater;
    private int layout;
    private List<Timer> timerList;

    private  Context context;
  public SpecAdapter(Context context, int resource, List<Timer> timers) {
        super(context, resource, timers);
        this.timerList = timers;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;
        if(convertView==null){
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Timer timer = timerList.get(position);
        viewHolder.layout.setBackgroundColor(timer.color);
        viewHolder.timerName.setText(timer.timerName);
        viewHolder.deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteHelper helper = new DeleteHelper(context);
                helper.delete(timer.id);
                if(context instanceof ActivityWithTimers){
                    ((ActivityWithTimers)context).recteateActivity();
                }
            }
        });
        viewHolder.layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(context, TimerSpecificsActivity.class);
                intent.putExtra("timer_id",timer.id);
                context.startActivity(intent);
                return true;
            }
        });
        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TimerActivity.class);
                intent.putExtra("timer_id",timer.id);
                context.startActivity(intent);
            }
        });
        return convertView;

    }
    private class ViewHolder {
        final LinearLayout layout;
        final Button deletebtn;
        final TextView timerName;
        ViewHolder(View view){
            layout = view.findViewById(R.id.timer_example_layout);
            deletebtn = (Button) view.findViewById(R.id.deleteItemButton);
            timerName = (TextView) view.findViewById(R.id.itemTextView);
        }
    }
}