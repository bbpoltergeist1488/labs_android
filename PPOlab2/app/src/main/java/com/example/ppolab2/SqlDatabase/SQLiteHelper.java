package com.example.ppolab2.SqlDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.example.ppolab2.Timer;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper {
    private SQLiteDatabase sqLiteDatabase;
    private DBHelper dbHelper;

    public SQLiteHelper(Context context)
    {
        dbHelper = new DBHelper(context.getApplicationContext());
    }

    public SQLiteHelper open()
    {
        sqLiteDatabase = dbHelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        dbHelper.close();
    }

    private Cursor getAllEntries()
    {
        String[] columns = new String[] {DBHelper.KEY_ID, DBHelper.KEY_COLOR,
                DBHelper.KEY_TIMER_NAME, DBHelper.KEY_WARMUP, DBHelper.KEY_WORKOUT,
                DBHelper.KEY_REST, DBHelper.KEY_CYCLE, DBHelper.KEY_REPEAT,
                DBHelper.KEY_COOLDOWN};
        return  sqLiteDatabase.query(DBHelper.TABLE, columns, null, null,
                null, null, null);
    }

    public List<Timer> getAllItems()
    {
        ArrayList<Timer> sequences = new ArrayList<>();
        Cursor cursor = getAllEntries();
        if(cursor.moveToFirst())
        {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_ID));
                String timerName = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_TIMER_NAME));
                int warmup = cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_WARMUP));
                int workout = cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_WORKOUT));
                int rest = cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_REST));
                int cycle = cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_CYCLE));
                int repeat = cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_REPEAT));
                int cooldown = cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_COOLDOWN));
                int color = cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_COLOR));
                sequences.add(new Timer(timerName,color,warmup,workout,rest,cooldown,repeat,cycle,id));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return sequences;
    }

    public Timer getOneItem(int id)
    {
        Timer timer = null;
        String query = String.format("SELECT * FROM %s WHERE %s=?",DBHelper.TABLE, DBHelper.KEY_ID);
        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{ String.valueOf(id)});
        if(cursor.moveToFirst())
        {
            int color = cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_COLOR));
            String timerName = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_TIMER_NAME));
            int warmup = cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_WARMUP));
            int workout = cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_WORKOUT));
            int rest = cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_REST));
            int cycle = cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_CYCLE));
            int repeat = cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_REPEAT));
            int cooldown = cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_COOLDOWN));
            timer = new Timer(timerName,color,warmup,workout,rest,cooldown,repeat,cycle,id);
        }
        cursor.close();
        return timer;
    }

    public long insertOneItem(Timer sequence)
    {

        ContentValues cv = new ContentValues();
        cv.put(DBHelper.KEY_COLOR, sequence.color);
        cv.put(DBHelper.KEY_TIMER_NAME, sequence.timerName);
        cv.put(DBHelper.KEY_WARMUP, sequence.warmup);
        cv.put(DBHelper.KEY_WORKOUT, sequence.workout);
        cv.put(DBHelper.KEY_REST, sequence.rest);
        cv.put(DBHelper.KEY_CYCLE, sequence.cycle);
        cv.put(DBHelper.KEY_REPEAT, sequence.repeat);
        cv.put(DBHelper.KEY_COOLDOWN, sequence.cooldown);
        return  sqLiteDatabase.insert(DBHelper.TABLE, null, cv);
    }

    public long deleteOneItem(int id)
    {

        String whereClause = "_id = ?";
        String[] whereArgs = new String[]{String.valueOf(id)};
        return sqLiteDatabase.delete(DBHelper.TABLE, whereClause, whereArgs);
    }

    public long updateAllItems(Timer sequence)
    {
        String whereClause = DBHelper.KEY_ID + "=" + String.valueOf(sequence.id);
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.KEY_COLOR, sequence.color);
        cv.put(DBHelper.KEY_TIMER_NAME, sequence.timerName);
        cv.put(DBHelper.KEY_WARMUP, sequence.warmup);
        cv.put(DBHelper.KEY_WORKOUT, sequence.workout);
        cv.put(DBHelper.KEY_REST, sequence.rest);
        cv.put(DBHelper.KEY_CYCLE, sequence.cycle);
        cv.put(DBHelper.KEY_REPEAT, sequence.repeat);
        cv.put(DBHelper.KEY_COOLDOWN, sequence.cooldown);
        return sqLiteDatabase.update(DBHelper.TABLE, cv, whereClause, null);
    }
    public void clearDatabase()
    {
        dbHelper.onUpgrade(sqLiteDatabase, 0, 0);
    }

}
