package com.example.ppolab2.SqlDatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Timers.db";
    private static final int SCHEMA = 1;
    static final String TABLE = "Timer";
    public static final String KEY_ID = "_id";

    public static final String KEY_TIMER_NAME = "timerName";
    public static final String KEY_WARMUP = "warmup";
    public static final String KEY_WORKOUT = "workout";
    public static final String KEY_REST = "rest";
    public static final String KEY_CYCLE = "cycle";
    public static final String KEY_REPEAT = "repeat";
    public static final String KEY_COOLDOWN = "cooldown";
    public static final String KEY_COLOR = "color";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

        db.execSQL("CREATE TABLE " + TABLE + " (" + KEY_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_COLOR
                + " INTEGER, " + KEY_TIMER_NAME
                + " TEXT, " + KEY_WARMUP
                + " INTEGER, " + KEY_WORKOUT
                + " INTEGER, " + KEY_REST
                + " INTEGER, " + KEY_CYCLE
                + " INTEGER, " + KEY_REPEAT
                + " INTEGER, " + KEY_COOLDOWN + " INTEGER);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }
}
