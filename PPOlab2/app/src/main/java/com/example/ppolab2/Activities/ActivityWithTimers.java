package com.example.ppolab2.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.example.ppolab2.Adapters.SpecAdapter;
import com.example.ppolab2.R;
import com.example.ppolab2.Settings.MySettingsActivity;
import com.example.ppolab2.SqlDatabase.SQLiteHelper;
import com.example.ppolab2.Timer;

import java.util.List;
import java.util.Locale;

public class ActivityWithTimers extends AppCompatActivity {
    ListView TimerList;
    Button mainActivityAddbtn,mainActivitySettingsbtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivityAddbtn = findViewById(R.id.mainActivityAddbtn);
        mainActivitySettingsbtn = findViewById(R.id.mainActivitySettingsbtn);
        TimerList = (ListView) findViewById(R.id.TimersList);
        mainActivityAddbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityWithTimers.this, TimerSpecificsActivity.class);
                startActivity(intent);
                finish();
            }
        });
        mainActivitySettingsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityWithTimers.this, MySettingsActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SQLiteHelper sqLite = new SQLiteHelper(this);
        sqLite.open();
        List<Timer> timersList = sqLite.getAllItems();
        SpecAdapter adapter = new SpecAdapter(this,R.layout.timer_example,timersList);
        TimerList.setAdapter(adapter);

    }

    public void recteateActivity(){
        recreate();
    }
}
