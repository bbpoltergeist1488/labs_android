package com.example.timer.Database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.timer.Models.ItemModel;


@androidx.room.Database(entities = {ItemModel.class}, version = 1)
public abstract class Database extends RoomDatabase {
    private static volatile Database instance;

    public static synchronized Database getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, Database.class, "db")
                    .allowMainThreadQueries().build();
        }
        return instance;
    }
    public abstract MyDao Dao();
}