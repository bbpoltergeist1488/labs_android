package com.example.timer.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timer.Adapters.ListAdapter;
import com.example.timer.Database.Database;
import com.example.timer.Models.ItemModel;
import com.example.timer.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Database database;
    RecyclerView recycler;
    ListAdapter adapter;
    ImageButton addButton;
    ImageButton settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler = findViewById(R.id.item_list);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ListAdapter();
        recycler.setAdapter(adapter);

        database = Database.getInstance(this);
        List<ItemModel> list = database.Dao().GetAll();
        adapter.AddAll(list);
        addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), CreateActivity.class)));
        settings = findViewById(R.id.settings_button);
        settings.setOnClickListener(v->{
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        if (SettingsActivity.changed) {
            SettingsActivity.SetNightTheme(this);
            SettingsActivity.SetLanguage(this);
            SettingsActivity.SetFontSize(this);
            SettingsActivity.changed = false;
            recreate();
        }
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        List<ItemModel> list = database.Dao().GetAll();
        adapter.Clear();
        adapter.AddAll(list);
    }
}